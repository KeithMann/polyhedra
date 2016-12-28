package com.keithmann.polyhedra;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by kmann on 14-04-07.
 */
class Face {


    private FaceType faceType;

    private List<Edge> edges = new ArrayList<Edge>();

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
        System.out.println("Added edge to face. Size of List<edge> edges is now: " + edges.size());
    }

    public void setEdges(List<Edge> edges) {this.edges = edges;}

}
