package com.keithmann.polyhedra;

import java.util.Collection;

/**
 * Created by kmann on 2014-05-14.
 */
public class FaceFactory {

    private static FaceFactory instance;

    private FaceFactory() {

    }

    public static FaceFactory getInstance() {
        if (instance == null) {
            instance = new FaceFactory();
        }
        return instance;
    }

    public Face makeFace(FaceType faceType) {

        Face face;
        face = new Face(faceType);

        int numberOfEdges;

        switch (faceType) {
            case TRIANGLE:
                numberOfEdges = 3;
                break;
            case SQUARE:
                numberOfEdges = 4;
                break;
            case PENTAGON:
                numberOfEdges = 5;
                break;
            case HEXAGON:
                numberOfEdges = 6;
                break;
            default:
                numberOfEdges = 0;
                break;
        }

        assembleFace(face, numberOfEdges);

        return face;
    }

    private void assembleFace(Face face, int numberOfEdges) {
        makeEdges(face, numberOfEdges);
        joinEdges(face, numberOfEdges);
    }

    private void makeEdges(Face face, int numberOfEdges) {
        for (int i = 0; i < numberOfEdges; i++) {
            Edge edge;
            edge = EdgeFactory.getInstance().makeEdge(face);
            face.addEdge(edge);
        }
    }

    private void joinEdges(Face face, int numberOfEdges) {
        Edge[] edges;
        edges = face.getEdges().toArray(new Edge[numberOfEdges]);

        for (int i = 0; i < numberOfEdges; i++) {
            Vertex[] vertexes = edges[i].getVertexes();

            if (i < (numberOfEdges - 1)) {
                vertexes[1] = edges[i + 1].getVertexes()[0];
            } else {
                vertexes[1] = edges[0].getVertexes()[0];
            }

        }
    }

}