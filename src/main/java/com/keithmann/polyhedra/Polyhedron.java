package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-04.
 */

import java.util.List;

class Polyhedron {

    private PolyhedronType polyhedronType;

    private List<Face> faces;

    public Polyhedron(PolyhedronType polyhedronType) {
        this.polyhedronType = polyhedronType;
    }

    public PolyhedronType getPolyhedronType() {
        return polyhedronType;
    }

    public void addFace(Face face) {
        faces.add(face);
    }

    public List<Face> getFaces() {
        return faces;
    }


}
