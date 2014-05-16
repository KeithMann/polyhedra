package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-08.
 */
public class TraversablePolyhedron {

    private Polyhedron polyhedron;
    private Graph graph;
    private PolyhedronType polyhedronType;

    public TraversablePolyhedron(PolyhedronType polyhedronType) {
        this.polyhedronType = polyhedronType;
    }

    public void setPolyhedron(Polyhedron polyhedron) {
        this.polyhedron = polyhedron;

        //TODO check that polyhedron.polyhedronType = this.polyhedronType else throw exception
    }
    
    public Polyhedron getPolyhedron() {
        return polyhedron;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;

        //TODO check that graph is dual of polyhedron else throw exception

    }

    public PolyhedronType getPolyhedronType() {
        return polyhedronType;
    }

}
