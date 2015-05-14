package pl.edu.prz.isk.t4;

/**
 * Klasa reprezentująca zestaw danych wejściowych
 *
 * @author Piotr Bednarz
 * @author Piotr Bajorek
 */
public enum DataSet {

    /**
     * Graf o 7 wierzchołkach
     */
    DATA1("data/data1.txt", 7, 10, 3),

    /**
     * Drzewo binarne o rozmiarze 3 i wysokości 1
     */
    BINARY_TREE_3("data/drzewo_binarne_3.txt", 3, 2, 3),

    /**
     * Graf regularny, 2 stopnia, cykliczny o parzystej liczbie wierzchołków
     */
    GRAF_CYKLICZNY_8("data/graf_cykliczny_8.txt", 8, 8, 2),

    /**
     * Graf regularny, 2 stopnia, cykliczny o nieparzystej liczbie wierzchołków
     */
    GRAF_CYKLICZNY_9("data/graf_cykliczny_9.txt", 9, 9, 3),

    /**
     * http://mat.gsia.cmu.edu/COLOR/instances/myciel3.col
     */
    MYCIEL3("data/myciel3.txt", 11, 20, 4),

    /**
     * http://mat.gsia.cmu.edu/COLOR/instances/myciel4.col
     */
    MYCIEL4("data/myciel4.txt", 23, 71, 5),

    /**
     * http://mat.gsia.cmu.edu/COLOR/instances/myciel5.col
     */
    MYCIEL5("data/myciel5.txt", 47, 236, 6),

    /**
     * http://mat.gsia.cmu.edu/COLOR/instances/myciel6.col
     */
    MYCIEL6("data/myciel6.txt", 95, 755, 7),

    /**
     * http://mat.gsia.cmu.edu/COLOR/instances/games120.col
     * Graph representing the games played in a college football season can be represented by a graph where the nodes represent each college team. Two teams are connected by an edge if they played each other during the season.
     */
    GAMES120("data/games120.txt", 120, 638, 9),

    /**
     * Queen Graphs. Given an n by n chessboard, a queen graph is a graph on n^2 nodes, each corresponding to a square of the board.
     * Two nodes are connected by an edge if the corresponding squares are in the same row, column, or diagonal.
     * Unlike some of the other graphs, the coloring problem on this graph has a natural interpretation:
     * Given such a chessboard, is it possible to place n sets of n queens on the board so that no two queens of the same
     * set are in the same row, column, or diagonal? The answer is yes if and only if the graph has coloring number n.
     * Martin Gardner states without proof that this is the case if and only if $n$ is not divisible by either 2 or 3. In all cases,
     * the maximum clique in the graph is no more than n, and the coloring value is no less than n.
     */
    /**
     * http://mat.gsia.cmu.edu/COLOR/instances/queen6_6.col
     */
    QUEEN6_6("data/queen6_6.txt", 36, 290, 7),

    /**
     * http://mat.gsia.cmu.edu/COLOR/instances/queen8_8.col
     */
    QUEEN8_8("data/queen8_8.txt", 64, 728, 9),

    /**
     * Graf dwudzielny
     */
    GRAF_DWUDZIELNY_6("data/graf_dwudzielny_6.txt", 6, 6, 2),

    /**
     * Graf pełny
     */
    GRAF_PELNY_5("data/graf_pelny_5.txt", 5, 10, 5);

    private final String filename;
    private final int numOfEdges;
    private final int numOfVertices;
    private final int chromaticNumber;

    DataSet(String filename, int numOfVertices, int numOfEdges, int chromaticNumber) {
        this.filename = filename;
        this.numOfEdges = numOfEdges;
        this.numOfVertices = numOfVertices;
        this.chromaticNumber = chromaticNumber;
    }

    public String getFilename() {
        return filename;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int getNumOfVertices() {
        return numOfVertices;
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }

}