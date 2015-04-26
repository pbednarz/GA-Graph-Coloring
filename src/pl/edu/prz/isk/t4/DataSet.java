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