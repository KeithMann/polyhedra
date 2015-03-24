package com.keithmann.polyhedra;

import java.util.EnumMap;

/**
 * Created by kmann on 2014-05-14.
 */
 class FaceFactory {

    private static EnumMap<FaceType, Integer> numberOfEdgesPerFace;

    private static FaceFactory instance;


    private FaceFactory() {
        setupNumberOfEdgesPerFace();
    }

    private void setupNumberOfEdgesPerFace() {
        numberOfEdgesPerFace = new EnumMap<FaceType, Integer>(FaceType.class);
        numberOfEdgesPerFace.put(FaceType.TRIANGLE, 3);
        numberOfEdgesPerFace.put(FaceType.SQUARE, 4);
        numberOfEdgesPerFace.put(FaceType.PENTAGON, 5);
        numberOfEdgesPerFace.put(FaceType.HEXAGON, 6);
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
        assembleFace(face, numberOfEdgesPerFace.get(faceType));

        return face;
    }


    private void assembleFace(Face face, int numberOfEdges) {

        makeEdges(face, numberOfEdges);
        connectEdges(face, numberOfEdges);
    }


    private void makeEdges(Face face, int numberOfEdges) {

        for (int i = 0; i < numberOfEdges; i++) {

            Edge edge;
            edge = EdgeFactory.getInstance().makeEdge();
            face.addEdge(edge);
            edge.addFace(face);
        }
    }


    private void connectEdges(Face face, int numberOfEdges) {

        Edge[] edges;
        edges = face.getEdges().toArray(new Edge[numberOfEdges]);

        for (int i = 0; i < numberOfEdges; i++) {
            Vertex[] vertexes = edges[i].getVertexes();

            if (i < (numberOfEdges - 1)) {
                vertexes[1] = edges[i + 1].getVertexes()[0];
            } else {
                vertexes[1] = edges[0].getVertexes()[0];
            }

            edges[i].setVertexes(vertexes);
        }

    }
}