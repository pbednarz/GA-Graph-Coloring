package pl.edu.prz.isk.t4;

/**
 * Klasa reprezentująca krawędź, parę wierzchołków grafu
 *
 * @author Piotr Bednarz
 * @author Piotr Bajorek
 */
class IntEdgePair {

    private final int vertexSrc;
    private final int vertexDst;

    public IntEdgePair(int vertexSrc, int vertexDst) {
        this.vertexSrc = vertexSrc;
        this.vertexDst = vertexDst;
    }

    public int getVertexSrc() {
        return vertexSrc;
    }

    public int getVertexDst() {
        return vertexDst;
    }
}