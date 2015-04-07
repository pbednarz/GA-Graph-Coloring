package pl.edu.prz.isk.t4;

import com.softtechdesign.ga.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pbednarz
 */
public class GAGraphColoring extends GAStringsSeq {

    List<Integer> graphVerticesList = new ArrayList<>();
    Map<Integer, Integer> graphEdgesMap = new HashMap<>();
    static String[] possibleColors;
    static int[] graphVertices;
    final static ArrayList<IntEdgePair> graphEdges = new ArrayList<>();
    final static String fileName = "data.txt"; // "data2.txt"

    public static void main(String[] args) throws GAException {
        try {
            readFile(fileName);
            GAGraphColoring ga = new GAGraphColoring();
            Thread threadGraph = new Thread(ga);
            threadGraph.start();
            threadGraph.join();
            if (!threadGraph.isAlive()) {
                if (ga.getFittestChromosomesFitness() != 0) {
                    System.out.println("Chromatic Number for solution: " + (graphVertices.length + 1 - (int) ga.getFittestChromosomesFitness()));
                }
            }
        } catch (FileNotFoundException | GAException | InterruptedException ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "an exception was thrown:", ex);
        }
    }

    public static void readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));
        Set<Integer> vertices = new HashSet<>();
        int numOfVertices;
        int numOfEdges = scanner.nextInt();
        while (scanner.hasNextInt()) {
            int edgeNumberIgnored = scanner.nextInt();
            int verticeIndexStart = scanner.nextInt();
            int verticeIndexEnd = scanner.nextInt();
            vertices.add(verticeIndexStart);
            vertices.add(verticeIndexEnd);
            graphEdges.add(new IntEdgePair(verticeIndexStart, verticeIndexEnd));
        }
        numOfVertices = vertices.size();
        graphVertices = new int[numOfVertices];
        int index = 0;
        for (Integer i : vertices) {
            graphVertices[index++] = i;
        }
        possibleColors = new String[numOfVertices];
        for (int i = 0; i < numOfVertices; i++) {
            possibleColors[i] = String.valueOf((char) (97 + i));
        }
    }

    public GAGraphColoring() throws GAException {
        super(graphVertices.length, //size of chromosome
                200, //population has N chromosomes
                0.7, //crossover probability
                10, //random selection chance % (regardless of fitness)
                200, //max generations
                0, //num prelim runs (to build good breeding stock for final/full run)
                20, //max generations per prelim run
                0.06, //chromosome mutation prob.
                0, //number of decimal places in chrom
                possibleColors, //gene space (possible gene values)
                Crossover.ctTwoPoint, //crossover type
                true); //compute statisitics?
    }

    @Override
    protected double getFitness(int iChromIndex) {
        Map<String, AtomicInteger> usedColors = new HashMap<>();
        String genes[] = this.getChromosome(iChromIndex).getGenes();
        for (IntEdgePair graphEdge : graphEdges) {
            if (!genes[graphEdge.getVertexSrc() - 1].equals(genes[graphEdge.getVertexDst() - 1])) {
                addToMap(usedColors, genes[graphEdge.getVertexDst() - 1]);
                addToMap(usedColors, genes[graphEdge.getVertexSrc() - 1]);
            } else {
                return 0;
            }
        }
        return (graphVertices.length + 1 - usedColors.size());
    }

    void addToMap(Map<String, AtomicInteger> map, String name) {
        AtomicInteger value = map.get(name);
        if (value == null) {
            map.put(name, new AtomicInteger(1));
        } else {
            value.incrementAndGet();
        }
    }
}