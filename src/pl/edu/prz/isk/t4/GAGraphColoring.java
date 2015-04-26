package pl.edu.prz.isk.t4;

import com.softtechdesign.ga.ChromStrings;
import com.softtechdesign.ga.Crossover;
import com.softtechdesign.ga.GAException;
import com.softtechdesign.ga.GAStringsSeq;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Główna klasa programu odpowiedzialna za wczytanie danych oraz obliczenie
 * wyniku
 *
 * @author Piotr Bednarz
 * @author Piotr Bajorek
 */
public class GAGraphColoring extends GAStringsSeq {

    private static final DataSet currentData = DataSet.GRAF_CYKLICZNY_9;
    final static String fileName = currentData.getFilename();

    static String[] possibleColors;
    static int[] graphVertices;
    static ArrayList<IntEdgePair> graphEdges;

    public GAGraphColoring() throws GAException {
        super(graphVertices.length, //size of chromosome
                200, //population has N chromosomes
                0.7, //crossover probability
                10, //random selection chance % (regardless of fitness)
                2000, //max generations
                0, //num prelim runs (to build good breeding stock for final/full run)
                20, //max generations per prelim run
                0.06, //chromosome mutation prob.
                0, //number of decimal places in chrom
                possibleColors, //gene space (possible gene values)
                Crossover.ctTwoPoint, //crossover type
                true); //compute statisitics
    }

    public static void main(String[] args) throws GAException {
        try {
            readFile(fileName);
            System.out.println("Running GA on: " + fileName);
            GAGraphColoring ga = new GAGraphColoring();
            Thread threadGraph = new Thread(ga);
            threadGraph.setPriority(Thread.MAX_PRIORITY);
            threadGraph.start();
            threadGraph.join();
            if (!threadGraph.isAlive()) {
                if (ga.getFittestChromosomesFitness() != 0) {
                    int numOfUsedColors = (graphVertices.length + 1 - (int) ga.getFittestChromosomesFitness());
                    System.out.println("Number of used colors in solution: " + numOfUsedColors);
                }
            }
        } catch (FileNotFoundException | GAException | InterruptedException ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "an exception was thrown:", ex);
        }
    }

    @Override
    protected double getFitness(int chromeIndex) {
        Map<String, AtomicInteger> usedColors = new HashMap<>();
        ChromStrings chromosome = getChromosome(chromeIndex);
        String genes[] = chromosome.getGenes();
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

    public static void readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));
        Set<Integer> vertices = new HashSet<>();
        int numOfVertices;
        int numOfEdges = scanner.nextInt();
        graphEdges = new ArrayList<>(numOfEdges);
        while (scanner.hasNextLine()) {
            int edgeNumber = scanner.nextInt();
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
            possibleColors[i] = intToStringSeq(i + 1);
        }
    }

    public static String intToStringSeq(int index) {
        if (index < 27) {
            return Character.toString((char) (index + 96));
        } else {
            if (index % 26 == 0) {
                return intToStringSeq((index / 26) - 1) + intToStringSeq(((index - 1) % 26 + 1));
            } else {
                return intToStringSeq(index / 26) + intToStringSeq(index % 26);
            }
        }
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