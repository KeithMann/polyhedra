package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-09.
 */
public class GraphFactory {

    private static GraphFactory instance;

    private GraphFactory() {

    }

    public static GraphFactory getInstance() {
        if (instance == null) {
            instance = new GraphFactory();
        }
        return instance;
    }

    public Graph makeGraph(PolyhedronType polyhedronType) {

        Graph graph = new Graph(polyhedronType);

        switch (polyhedronType) {
            case DODECAHEDRON:

                break;
            case TRUNCATED_ICOSAHEDRON:

                break;
        }


        return graph;

    }


}
