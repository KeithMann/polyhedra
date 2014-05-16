package com.keithmann.polyhedra;

import java.util.Collection;

/**
 * Created by kmann on 14-04-07.
 */
public class Edge {

    private Vertex[] vertexes;

    private Face[] faces;

    public Edge() {

    }

    public Vertex[] getVertexes() {
        return vertexes;
    }

    public void setVertexes(Vertex[] vertexes) {
        this.vertexes = vertexes;
    }

    public Face[] getFaces() {
        return faces;
    }

    public void setFaces(Face[] faces) {
        this.faces = faces;
    }

}
