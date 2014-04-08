package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-08.
 */
public class TraversablePolyhedron {

    private Polyhedron polyhedron;
    private Graph graph;
    private PolyhedronType polyhedronType;

    public Polyhedron getPolyhedron() {
        return polyhedron;
    }

    public Graph getGraph() {
        return graph;
    }

    public PolyhedronType getPolyhedronType() {
        return polyhedronType;
    }

    public void setPolyhedron(Polyhedron polyhedron) {
        this.polyhedron = polyhedron;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void setPolyhedronType(PolyhedronType polyhedronType) {
        this.polyhedronType = polyhedronType;
    }


}
