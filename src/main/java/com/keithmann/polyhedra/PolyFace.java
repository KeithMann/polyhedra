package com.keithmann.polyhedra;

import java.util.Collection;

/**
 * Created by kmann on 14-04-07.
 */
public class PolyFace {


    private PolyFaceType polyFaceType;

    private Collection<PolyEdge> polyEdges;

    public PolyFace(PolyFaceType polyFaceType) {
        this.polyFaceType = polyFaceType;
    }

    public PolyFaceType getPolyFaceType() {
        return polyFaceType;
    }

    public Collection<PolyEdge> getPolyEdges() {
        return polyEdges;
    }

    public void addPolyEdge(PolyEdge polyEdge) {
        polyEdges.add(polyEdge);
    }

}
