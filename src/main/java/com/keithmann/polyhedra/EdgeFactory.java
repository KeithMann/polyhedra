package com.keithmann.polyhedra;

/**
 * Created by kmann on 2014-05-14.
 */
class EdgeFactory {

    private static EdgeFactory instance;

    private EdgeFactory() {

    }

    public static EdgeFactory getInstance() {
        if (instance == null) {
            instance = new EdgeFactory();
        }
        return instance;
    }

    public Edge makeEdge() {
        Edge edge;
        edge = new Edge();

        edge.setVertexes(new Vertex[]{new Vertex(), new Vertex()});

        return edge;
    }

}
