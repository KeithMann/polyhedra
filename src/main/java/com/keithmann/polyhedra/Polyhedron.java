package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-04.
 */

import java.util.Collection;

public class Polyhedron {

    private Collection<PolyVertex> vertices;

    public Polyhedron(Collection<PolyVertex> vertices) {
        this.vertices = vertices;
    }

    public Collection<PolyVertex> getVertices() {
        return vertices;
    }

    public void setVertices(Collection<PolyVertex> vertices) {
        this.vertices = vertices;
    }

}
