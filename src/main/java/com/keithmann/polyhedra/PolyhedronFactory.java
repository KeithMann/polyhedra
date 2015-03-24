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

        setupNumberOfFacesPerPolyhedron();
        setupNumberOfTiersPerPolyhedron();
    }

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

    // TODO Overload constructor with one that takes Coordinates initialCenterOfPolyhedron
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

        assemblePolyhedron(polyhedron, numberOfFacesPerPolyhedron.get(polyhedronType), numberOfTiersPerPolyhedron.get(polyhedronType));

        return polyhedron;

    }

    private void assemblePolyhedron(Polyhedron polyhedron, int numberOfFaces, int numberOfTiers) {

        makeFaces(polyhedron, numberOfFaces);
        connectFaces(polyhedron, numberOfFaces, numberOfTiers);

    }

    private void makeFaces(Polyhedron polyhedron, int numberOfFaces) {
        FaceFactory faceFactory = FaceFactory.getInstance();

        makePentagons(polyhedron, faceFactory);

        if (numberOfFaces > 12) {
            makeHexagons(polyhedron, faceFactory, numberOfFaces);
        }
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

        connectTopFaceToFirstTierFaces(polyhedron, numberOfFaces);
        connectFacesWithinTiers(polyhedron, numberOfFaces, numberOfTiers);
        connectFacesAcrossTiers(polyhedron, numberOfFaces, numberOfTiers);
        connectBottomFaceToLastTierFaces(polyhedron, numberOfFaces);
    }

    // TODO connectTopFaceToFirstTierFaces and connectBottomFaceToLastTierFaces are very similar -- combine them

    private void connectTopFaceToFirstTierFaces(Polyhedron polyhedron, int numberOfFaces) {

        Face[] faces = polyhedron.getFaces().toArray(new Face[numberOfFaces]);

        Face topFace = faces[0];
        Edge[] topFaceEdges = topFace.getEdges().toArray(new Edge[topFace.getEdges().size()]);

        for (int i = 0; i < 5; i ++) {

            Edge topFaceEdge = topFaceEdges[i];

            Face firstTierFace = faces[i + 1];
            topFaceEdge.addFace(firstTierFace);

            List<Edge> firstTierFaceEdges = firstTierFace.getEdges();
            firstTierFaceEdges.set(0,topFaceEdge);
            firstTierFace.setEdges(firstTierFaceEdges);
        }
    }

    private void connectBottomFaceToLastTierFaces(Polyhedron polyhedron, int numberOfFaces) {

        Face[] faces = polyhedron.getFaces().toArray(new Face[numberOfFaces]);

        Face bottomFace = faces[numberOfFaces - 1];
        Edge[] bottomFaceEdges = bottomFace.getEdges().toArray(new Edge[bottomFace.getEdges().size()]);

        for (int i = 0; i < 5; i++) {

            Edge bottomFaceEdge = bottomFaceEdges[i];

            Face lastTierFace = faces[numberOfFaces - 6 + i];
            bottomFaceEdge.addFace(lastTierFace);

            List<Edge> lastTierFaceEdges = lastTierFace.getEdges();
            lastTierFaceEdges.set(0, bottomFaceEdge);
            lastTierFace.setEdges(lastTierFaceEdges);
        }
    }

    // TODO refactor to handle general case (this is just for dodecahedrons right now)

    private void connectFacesWithinTiers(Polyhedron polyhedron, int numberOfFaces, int numberOfTiers) {

        Face[] faces = polyhedron.getFaces().toArray(new Face[numberOfFaces]);

        for (int tier = 1; tier <= numberOfTiers; tier++) {

            switch (tier) {

                case 1:

                    for (int i = 1; i < 6; i++) {

                        Face fromFace = faces[i];
                        Face toFace;

                        if (i < 5) {
                            toFace = faces[i + 1];
                        } else {
                            toFace = faces[1];
                        }

                        Edge[] fromFaceEdges = fromFace.getEdges().toArray(new Edge[fromFace.getEdges().size()]);
                        Edge[] toFaceEdges = toFace.getEdges().toArray(new Edge[toFace.getEdges().size()]);

                        for (int fromEdge = 0; fromEdge < numberOfEdges; fromEdge++) {
                            if (fromEdge < (numberOfEdges - 1)) {
                                edges[fromEdge] = faces[fromFace + 1].getEdges().toArray(new Edge[faces[fromFace + 1].getEdges().size()])[0];
                            } else {
                                edges[0] = faces[0].getEdges().toArray(new Edge[faces[fromFace + 1].getEdges().size()])[0];
                            }
                        }
                    }

                    break;

                case numberOfTiers:

                    break;

                default:
                    break;
            }
        }
    }

    // TODO put something between those braces

    private void connectFacesAcrossTiersTiers(Polyhedron polyhedron, int numberOfFaces, int numberOfTiers) {

    }
}


