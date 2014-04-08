package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-04.
 */

import java.util.Collection;

public class Polyhedron {

    private PolyhedronType polyhedronType;
    private Collection<PolyVertex> vertices;

    public Polyhedron(PolyhedronType polyhedronType) {
        this.polyhedronType = polyhedronType;
    }

    public Collection<PolyVertex> getVertices() {
        return vertices;
    }

    public void setVertices(Collection<PolyVertex> vertices) {
        this.vertices = vertices;
    }

    public PolyhedronType getPolyhedronType() {
        return polyhedronType;
    }

}
