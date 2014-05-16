package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-08.
 */
public class TraversablePolyhedronFactory {

    private static TraversablePolyhedronFactory instance;

    private TraversablePolyhedronFactory() {

    }

    public static TraversablePolyhedronFactory getInstance() {
        if (instance == null) {
            instance = new TraversablePolyhedronFactory();
        }
        return instance;
    }

    public TraversablePolyhedron makeTraversablePolyhedron(PolyhedronType polyhedronType) {

        TraversablePolyhedron traversablePolyhedron;
        traversablePolyhedron = new TraversablePolyhedron(polyhedronType);
        traversablePolyhedron.setGraph(GraphFactory.getInstance().makeGraph(polyhedronType));
        traversablePolyhedron.setPolyhedron(PolyhedronFactory.getInstance().makePolyhedron(polyhedronType));

        switch (polyhedronType) {
            case DODECAHEDRON:
                assembleTraversableDodecahedron(traversablePolyhedron);
                break;
            case TRUNCATED_ICOSAHEDRON:
                assembleTraversableTruncatedIcosahedron(traversablePolyhedron);
                break;
        }

        return traversablePolyhedron;

    }

    private void assembleTraversableDodecahedron(TraversablePolyhedron traversablePolyhedron) {
        Polyhedron polyhedron;
        Graph graph;

        polyhedron = traversablePolyhedron.getPolyhedron();
        graph = traversablePolyhedron.getGraph();


    }

    private void assembleTraversableTruncatedIcosahedron(TraversablePolyhedron traversablePolyhedron) {

    }
}
