package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-04.
 */

import java.util.Collection;

public class Polyhedron {

    private Collection<Vertex> vertices;

    public Polyhedron(Collection<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Collection<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(Collection<Vertex> vertices) {
        this.vertices = vertices;
    }

}
