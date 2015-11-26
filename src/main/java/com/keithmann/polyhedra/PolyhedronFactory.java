package com.keithmann.polyhedra;

import java.util.EnumMap;
import java.util.List;

/**
 * Created by kmann on 14-04-09.
 */


class PolyhedronFactory {

    private static EnumMap<PolyhedronType, Integer> numberOfFacesPerPolyhedron;
    private static EnumMap<PolyhedronType, Integer> numberOfTiersPerPolyhedron;

    private static PolyhedronFactory instance;

    private PolyhedronFactory() {
        setupFactory();
    }

    private void setupFactory() {
        setupNumberOfFacesPerPolyhedron();
        setupNumberOfTiersPerPolyhedron();
    }

    private void teardownFactory() {}

    private void setupNumberOfTiersPerPolyhedron() {
        numberOfTiersPerPolyhedron.put(PolyhedronType.DODECAHEDRON, 2);
        numberOfTiersPerPolyhedron.put(PolyhedronType.TRUNCATED_ICOSAHEDRON, 4);
    }

    private void setupNumberOfFacesPerPolyhedron() {
        numberOfFacesPerPolyhedron.put(PolyhedronType.DODECAHEDRON, 12);
        numberOfFacesPerPolyhedron.put(PolyhedronType.TRUNCATED_ICOSAHEDRON, 32);
    }

    public static PolyhedronFactory getInstance() {
        if (instance == null) {
            instance = new PolyhedronFactory();
        }
        return instance;
    }

    // TODO Overload makePolyhedron with one that takes Coordinates initialCenterOfPolyhedron
    /* NOTE centerOfPolyhedron might not be a great name. Well, I mean, it IS the center initially, but we don't
    // want to hang on to it because it could change, and we're relying on the vertices to tell us about where
    // the poly is in space. It's more like "OK, what do you want to use as the point from which I calculate the
    // initial coordinates of all the vertices in this poly? Maybe I should call it "referencePoint" or something.
    // Or "initialCenter"? OR maybe all polys get created at 0 and then moved? Could be a problem for collisions,
    // and besides, the poly was never REALLY at 0. So no. I like "initialCenterOfPolyhedron". It's true. It stays
    // true.
    */
    // TODO Default initialCenterOfPolyhedron to origin of space (0,0,...0)
    // TODO Consider "movePolyhedron" method to move the Polyhedron "properly" (ie, move vertices
    // NOTE that center of polyhedron can be derived from vertex coordinates and vice-versa...might be useful for
    // testing? What is point of truth in our model? Answer: the vertices are -- this allows for distortional
    // transforms. Center of poly is always calculated.

    public Polyhedron makePolyhedron(PolyhedronType polyhedronType) {

        Polyhedron polyhedron;
        polyhedron = new Polyhedron(polyhedronType);

        assemblePolyhedron(polyhedron);

        return polyhedron;
    }

    private void assemblePolyhedron(Polyhedron polyhedron) {

        makeTiers(polyhedron);
        connectTiers(polyhedron);
    }

    private void makeTiers(Polyhedron polyhedron) {

        int numberOfTiers = calculateNumberOfTiers(polyhedron.getPolyhedronType());

        for (int tierNumber = 0; tierNumber < numberOfTiers; tierNumber++) {
            makeTier(polyhedron, tierNumber);
        }
    }

    private void makeTier(Polyhedron polyhedron, int tier) {

        makeFaces(polyhedron);
        connectFacesWithinTier(polyhedron);
    }


    private void makeFaces(Polyhedron polyhedron, int tier) {

        FaceFactory faceFactory = FaceFactory.getInstance();



        makePentagons(polyhedron, faceFactory);
        makeHexagons(polyhedron, faceFactory, numberOfFaces);
    }

    private void makePentagons(Polyhedron polyhedron, FaceFactory faceFactory) {
        for (int i = 0; i < 12; i++) {
            polyhedron.addFace(faceFactory.makeFace(FaceType.PENTAGON));
        }
    }

    private void makeHexagons(Polyhedron polyhedron, FaceFactory faceFactory, int numberOfFaces) {
        int numberOfHexagons = numberOfFaces - 12;
        for (int i = 0; i < numberOfHexagons; i++) {
            polyhedron.addFace(faceFactory.makeFace(FaceType.HEXAGON));
        }
    }

    private void connectFaces(Polyhedron polyhedron, int numberOfFaces, int numberOfTiers) {

        Face[] faces = polyhedron.getFaces().toArray(new Face[numberOfFaces]);

        connectTopFaceToFirstTierFaces(faces);
        connectFacesWithinTiers(faces, numberOfTiers);
        connectFacesAcrossTiers(faces, numberOfTiers);
        connectBottomFaceToLastTierFaces(faces);
    }

    // TODO connectTopFaceToFirstTierFaces and connectBottomFaceToLastTierFaces are very similar -- combine them

    private void connectTopFaceToFirstTierFaces(Face[] faces) {

        Face topFace = faces[0];
        Edge[] topFaceEdges = getEdgesAsArray(topFace);

        int numberOfTopFaceEdges = topFaceEdges.length;

        for (int i = 0; i < numberOfTopFaceEdges; i ++) {

            Edge topFaceEdge = topFaceEdges[i];
            Face firstTierFace = faces[i + 1];

            connectEdgeToFace(topFaceEdge, firstTierFace);
        }
    }


    // TODO extend to handle general case (this is just for dodecahedrons right now)

    private void connectFacesWithinTiers(Face[] faces, int numberOfTiers) {

        for (int tierNumber = 1; tierNumber <= numberOfTiers; tierNumber++) {
            
            if (tierNumber == 1) {

                for (int fromFaceIndex = 1; fromFaceIndex < 6; fromFaceIndex++) {

                    Face fromFace = faces[fromFaceIndex];
                    Face toFace = faces[calculateToFaceIndex(fromFaceIndex)];

                    Edge[] fromFaceEdges = getEdgesAsArray(fromFace);
                    Edge[] toFaceEdges = getEdgesAsArray(toFace);

                    int numberOfFromFaceEdges = fromFaceEdges.length;
                    
                    // TODO Refactor this to better express intent of the algorithm

                    for (int fromEdgeIndex = 0; fromEdgeIndex < numberOfFromFaceEdges; fromEdgeIndex++) {
                        if (fromEdgeIndex < (numberOfFromFaceEdges - 1)) {
                            fromFaceEdges[fromEdgeIndex] = faces[fromFace + 1].getEdges().toArray(new Edge[faces[fromFace + 1].getEdges().size()])[0];
                        } else {
                            fromFaceEdges[0] = faces[0].getEdges().toArray(new Edge[faces[fromFace + 1].getEdges().size()])[0];
                        }
                    }
                }
            } else if (tierNumber == numberOfTiers) {

                // TODO Figure out what to do

            }
        }
    }

    // TODO put something between those braces

    private void connectFacesAcrossTiers(Face[] faces, int numberOfTiers) {

    }

    private void connectBottomFaceToLastTierFaces(Face[] faces) {

        int numberOfFacesOnPolyhedron = faces.length;

        Face bottomFace = faces[numberOfFacesOnPolyhedron - 1];
        Edge[] bottomFaceEdges = getEdgesAsArray(bottomFace);

        int numberOfBottomFaceEdges = bottomFaceEdges.length;

        for (int i = 0; i < numberOfBottomFaceEdges; i++) {

            Edge bottomFaceEdge = bottomFaceEdges[i];
            Face lastTierFace = faces[numberOfFacesOnPolyhedron - 6 + i];

            connectEdgeToFace(bottomFaceEdge, lastTierFace);
        }
    }

    private Edge[] getEdgesAsArray(Face face) {
        int numberOfEdges = face.getEdges().size();
        Edge[] edges = getEdgesAsArray(face.getEdges().toArray(new Edge[numberOfEdges]);
        return edges;
    }

    private int calculateToFaceIndex(int fromFaceNumber) {
        int toFaceIndex;

        if (fromFaceNumber < 5) {
            toFaceIndex = i + 1;
        } else {
            toFaceIndex = 1;
        }

        return toFaceIndex;

    }

    private void connectEdgeToFace(Edge fromFaceEdge, Face toFace) {
        fromFaceEdge.addFace(toFace);
        mergeEdges(fromFaceEdge, toFace);
    }

    private void mergeEdges(Edge fromFaceEdge, Face toFace) {
        List<Edge> toFaceEdges = toFace.getEdges();
        toFaceEdges.set(0,fromFaceEdge);
        toFace.setEdges(toFaceEdges);
    }



}


