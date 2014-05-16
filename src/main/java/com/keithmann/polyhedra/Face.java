package com.keithmann.polyhedra;

import java.util.Collection;

/**
 * Created by kmann on 14-04-07.
 */
public class Face {


    private FaceType faceType;

    private Collection<Edge> edges;

    public Face(FaceType faceType) {
        this.faceType = faceType;
    }

    public FaceType getFaceType() {
        return faceType;
    }

    public Collection<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

}
