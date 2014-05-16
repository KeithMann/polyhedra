package com.keithmann.polyhedra;

/**
 * Created by kmann on 2014-05-14.
 */
public class PolyEdgeFactory {

    private static PolyEdgeFactory instance;

    private PolyEdgeFactory() {

    }

    public static PolyEdgeFactory getInstance() {
        if (instance == null) {
            instance = new PolyEdgeFactory();
        }
        return instance;
    }

    public PolyEdge makePolyEdge() {
        PolyEdge polyEdge;
        polyEdge = new PolyEdge();

        polyEdge.addPolyVertex(new PolyVertex());
        polyEdge.addPolyVertex(new PolyVertex());

        return polyEdge;
    }

}
