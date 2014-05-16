package com.keithmann.polyhedra;

/**
 * Created by kmann on 2014-05-14.
 */
public class PolyFaceFactory {

    private static PolyFaceFactory instance;

    private PolyFaceFactory() {

    }

    public static PolyFaceFactory getInstance() {
        if (instance == null) {
            instance = new PolyFaceFactory();
        }
        return instance;
    }

    public PolyFace makePolyFace(PolyFaceType polyFaceType) {

        PolyFace polyFace;
        polyFace = new PolyFace(polyFaceType);

        int numberOfEdges;

        switch (polyFaceType) {
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

        assemblePolyFace(polyFace, numberOfEdges);

        return polyFace;
    }

    private void assemblePolyFace(PolyFace polyFace, int numberOfEdges) {
        for (int i = 1; i <= numberOfEdges; i++) {
            polyFace.addPolyEdge(PolyEdgeFactory.getInstance().makePolyEdge());
        }

        //TODO join up edges by setting vertexes equal

    }

}