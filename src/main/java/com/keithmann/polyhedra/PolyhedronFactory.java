package com.keithmann.polyhedra;

import java.util.EnumMap;
import java.util.List;

/**
 * Created by kmann on 14-04-09.
 */


class PolyhedronFactory {

    private static EnumMap<PolyhedronType, Integer> numberOfFacesPerPolyhedron = new EnumMap<PolyhedronType, Integer>(PolyhedronType.class);
    private static EnumMap<PolyhedronType, Integer> numberOfTiersPerPolyhedron = new EnumMap<PolyhedronType, Integer>(PolyhedronType.class);

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
        numberOfTiersPerPolyhedron.put(PolyhedronType.DODECAHEDRON, 4);
        numberOfTiersPerPolyhedron.put(PolyhedronType.TRUNCATED_ICOSAHEDRON, 6);
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
    // TODO Default initialCenterOfPolyhedron to origin of space (0,0,...0)
    // TODO Consider "movePolyhedron" method to move the Polyhedron "properly" (ie, move vertices)
    // NOTE that center of polyhedron can be derived from vertex coordinates and vice-versa...might be useful for
    // testing? What is point of truth in our model? Answer: the vertices are -- this allows for distortional
    // transforms. Center of poly is always calculated.

    public Polyhedron makePolyhedron(PolyhedronType polyhedronType) {

        Polyhedron polyhedron;
        polyhedron = new Polyhedron(polyhedronType);

        assemblePolyhedron(polyhedron);

        return polyhedron;
    }

    // BEGIN ALGORITHMIC METHODS (INDENTED TO SHOW FLOW)

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

                int firstFaceOfTier = polyhedron.getFaces().size();
                makeFaces(polyhedron, tier);
                int lastFaceOfTier = polyhedron.getFaces().size() - 1;
                if (firstFaceOfTier < lastFaceOfTier) {
                    connectFacesWithinTier(polyhedron, firstFaceOfTier, lastFaceOfTier);
                }
            }

                private void makeFaces(Polyhedron polyhedron, int tier) {

                    FaceFactory faceFactory = FaceFactory.getInstance();

                    // TODO We're doing this tier-by-tier now so this will have to change

                    /* I'm working on figuring out some sort of tier-by-tier pattern
                       of Pentagons and Hexagons that's easily derivable from polyhedron
                       type.
                     */

                    if (polyhedron.getPolyhedronType() == PolyhedronType.DODECAHEDRON) {
                        switch (tier) {
                            case 0:
                                polyhedron.addFace(faceFactory.makeFace(FaceType.PENTAGON));
                                break;
                            case 1:
                                for (int i = 1; i < 6; i++) {
                                    polyhedron.addFace(faceFactory.makeFace(FaceType.PENTAGON));
                                }
                                break;
                            case 2:
                                for (int i = 1; i < 6; i++) {
                                    polyhedron.addFace(faceFactory.makeFace(FaceType.PENTAGON));
                                }
                                break;
                            case 3:
                                polyhedron.addFace(faceFactory.makeFace(FaceType.PENTAGON));
                                break;
                        }
                    } else if (polyhedron.getPolyhedronType() == PolyhedronType.TRUNCATED_ICOSAHEDRON) {
                        switch (tier) {
                            case 0:
                                polyhedron.addFace(faceFactory.makeFace(FaceType.PENTAGON));
                                break;
                            case 1:
                                for (int i = 1; i < 6; i++) {
                                    polyhedron.addFace(faceFactory.makeFace(FaceType.PENTAGON));
                                }
                                break;
                            case 2:
                                for (int i = 1; i < 6; i++) {
                                    polyhedron.addFace(faceFactory.makeFace(FaceType.PENTAGON));
                                }
                                break;
                            case 3:
                                polyhedron.addFace(faceFactory.makeFace(FaceType.PENTAGON));
                                break;
                        }
                    }

                }

                private void connectFacesWithinTier(Polyhedron polyhedron, int firstFaceOfTier, int lastFaceOfTier) {

                    Face[] faces = polyhedron.getFaces().toArray(new Face[polyhedron.getFaces().size()]);

                    for (int fromFaceIndex = firstFaceOfTier; fromFaceIndex < lastFaceOfTier; fromFaceIndex++) {

                        Face fromFace = faces[fromFaceIndex];
                        int toFaceIndex = calculateToFaceIndex(fromFaceIndex);
                        Face toFace = faces[toFaceIndex];

                        Edge[] fromFaceEdges = getEdgesAsArray(fromFace);
                        Edge[] toFaceEdges = getEdgesAsArray(toFace);

                        int numberOfFromFaceEdges = fromFaceEdges.length;

                        // TODO Refactor this to better express intent of the algorithm

                        for (int fromEdgeIndex = 0; fromEdgeIndex < numberOfFromFaceEdges; fromEdgeIndex++) {
                            if (fromEdgeIndex < (numberOfFromFaceEdges - 1)) {
                                fromFaceEdges[fromEdgeIndex] = toFaceEdges[0];
                            } else {
                                fromFaceEdges[0] = faces[0].getEdges().toArray(new Edge[faces[fromFaceIndex + 1].getEdges().size()])[0];
                            }
                        }
                    }

                    // TODO Figure out what to do

                }

        private void connectTiers (Polyhedron polyhedron) {

            int numberOfFaces = numberOfFacesPerPolyhedron.get(polyhedron.getPolyhedronType());
            int numberOfTiers = calculateNumberOfTiers(polyhedron.getPolyhedronType());

            Face[] faces = polyhedron.getFaces().toArray(new Face[numberOfFaces]);

            connectTopFaceToFirstTierFaces(faces);
            connectFacesAcrossTiers(faces, numberOfTiers);
            connectBottomFaceToLastTierFaces(faces);
        }

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

    // END ALGORITHMIC METHODS




    // BEGIN EXTRACTED REUSABLE METHODS

    private int calculateNumberOfTiers(PolyhedronType polyhedronType) {

        int numberOfTiers;

        switch (polyhedronType) {

            case DODECAHEDRON:
                numberOfTiers = 4;
                break;

            case TRUNCATED_ICOSAHEDRON:
                numberOfTiers = 6;
                break;

            default:

                // TODO figure it out from number of faces or something

                numberOfTiers = 0;
                break;
        }

        return numberOfTiers;

    }

    private Edge[] getEdgesAsArray(Face face) {
        int numberOfEdges = face.getEdges().size();
        Edge[] edges = face.getEdges().toArray(new Edge[numberOfEdges]);
        return edges;
    }

    private int calculateToFaceIndex(int fromFaceNumber) {
        int toFaceIndex;

        if (fromFaceNumber < 5) {
            toFaceIndex = fromFaceNumber + 1;
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

