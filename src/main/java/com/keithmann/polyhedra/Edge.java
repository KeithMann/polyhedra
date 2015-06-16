package com.keithmann.polyhedra;

import java.util.List;

/**
 * Created by kmann on 14-04-07.
 */
class Edge {

    private Vertex[] vertexes;

    private List<Face> faces;

    public Edge() {

    }

    public Vertex[] getVertexes() {
        return vertexes;
    }

    public void setVertexes(Vertex[] vertexes) {
        this.vertexes = vertexes;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void addFace(Face face) {
        this.faces.add(face);
    }



}
