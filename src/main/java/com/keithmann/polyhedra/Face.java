package com.keithmann.polyhedra;

import java.util.List;

/**
 * Created by kmann on 14-04-07.
 */
class Face {


    private FaceType faceType;

    private List<Edge> edges;

    public Face(FaceType faceType) {
        this.faceType = faceType;
    }

    public FaceType getFaceType() {
        return faceType;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void setEdges(List<Edge> edges) {this.edges = edges;}

}
