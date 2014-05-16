package com.keithmann.polyhedra;

import java.util.Collection;

/**
 * Created by kmann on 14-04-07.
 */
public class PolyEdge {

    private Collection<PolyVertex> polyVertexes;

    public PolyEdge() {

    }

    public Collection<PolyVertex> getPolyVertexes() {
        return polyVertexes;
    }

    public void addPolyVertex(PolyVertex polyVertex) {
        polyVertexes.add(polyVertex);
    }

}
