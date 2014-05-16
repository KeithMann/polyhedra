package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-04.
 */

import java.util.Collection;

public class Polyhedron {

    private PolyhedronType polyhedronType;

    private Collection<Face> faces;

    public Polyhedron(PolyhedronType polyhedronType) {
        this.polyhedronType = polyhedronType;
    }

    public PolyhedronType getPolyhedronType() {
        return polyhedronType;
    }

    public void addFace(Face face) {
        faces.add(face);
    }

    public Collection<Face> getFaces() {
        return faces;
    }


}
