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
        traversablePolyhedron = new TraversablePolyhedron();
        traversablePolyhedron.setPolyhedronType(polyhedronType);
        traversablePolyhedron.setGraph(new Graph(polyhedronType));
        traversablePolyhedron.setPolyhedron(new Polyhedron(polyhedronType));

        return traversablePolyhedron;

    }
}
