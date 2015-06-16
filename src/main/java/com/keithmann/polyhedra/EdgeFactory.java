package com.keithmann.polyhedra;

/**
 * Created by kmann on 2014-05-14.
 */
class EdgeFactory {

    private static EdgeFactory instance;

    private EdgeFactory() {
        setupFactory();
    }

    private void setupFactory() {}

    private void teardownFactory() {}

    public static EdgeFactory getInstance() {
        if (instance == null) {
            instance = new EdgeFactory();
        }
        return instance;
    }

    public Edge makeEdge() {
        Edge edge;
        edge = new Edge();

        assembleEdge(edge);

        return edge;
    }

    private void assembleEdge(Edge edge) {
        edge.setVertexes(new Vertex[]{new Vertex(), new Vertex()});
    }

}
