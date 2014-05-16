package com.keithmann.polyhedra;

import com.sun.org.apache.bcel.internal.generic.FDIV;

/**
 * Created by kmann on 14-04-09.
 */
public class PolyhedronFactory {

    private static PolyhedronFactory instance;

    private PolyhedronFactory() {

    }

    public static PolyhedronFactory getInstance() {
        if (instance == null) {
            instance = new PolyhedronFactory();
        }
        return instance;
    }

    public Polyhedron makePolyhedron(PolyhedronType polyhedronType) {

        Polyhedron polyhedron;
        polyhedron = new Polyhedron(polyhedronType);

        int numberOfFaces;

        switch (polyhedronType) {
            case DODECAHEDRON:
                numberOfFaces = 12;
                break;
            case TRUNCATED_ICOSAHEDRON:
                numberOfFaces = 32;
                break;
            default:
                numberOfFaces = 0;
                break;
        }

        assemblePolyhedron(polyhedron, numberOfFaces);

        return polyhedron;

    }

    private void assemblePolyhedron(Polyhedron polyhedron, int numberOfFaces) {

        makeFaces(polyhedron, numberOfFaces);
        joinFaces(polyhedron, numberOfFaces);

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

    private void joinFaces(Polyhedron polyhedron, int numberOfFaces) {
        Face[] faces;
        faces = polyhedron.getFaces().toArray(new Face[numberOfFaces]);

        for (int i = 0; i < numberOfFaces; i++) {
            int numberOfEdges = faces[i].getEdges().size();
            Edge[] edges = faces[i].getEdges().toArray(new Edge[numberOfEdges]);

            // I don't like adding explanatory comments to my code because they cause the code to smell (the smell
            // being that of code that isn't good enough the explain itself). But in this case I'm not sure I can
            // even figure this out myself without writing it in english first. If I eventually manage to refactor
            // the code sufficiently, this comment will disappear. In the meantime, here is how we go about joining
            // up all the individual faces we just created so as to make a polyhedron.
            //
            // So a polyhedron is kind of just the 3-space version of a polygon. This is only really obvious when you
            // think about a cube being the 3-space version of a square, but hear me out. You see, you make a polygon
            // by making a bunch of edges and joining them up so that each edge shares a vertex with each adjacent
            // edge. I mean, you probably don't think of it that way, but that's what you do. To make a polyhedron,
            // you make a bunch of polygons and join them up so that each polygon shares an edge with each adjacent
            // polygon. I'm betting that you can make a hyperpolygon by joining up faces, and so on ad infinitum,
            // but my brain starts to hurt when I think in dimensions greater than three. Although I think I could
            // try to construct a hypercube that way without blowing a cerebellum.
            //
            // So this algorithm is just an expansion of what we did to join up edges by setting the second vertex of
            // edge i equal to the first vertex of edge i+1. We made this circular by joining the second vertex of the
            // last edge to the first vertex of the first edge. (See FaceFactory.joinEdges()).
            //
            // Let's start by re-expressing what we did there. We set the second through last vertex of edge i
            // equal to the first unconnected vertex of edge i+1. We just took a shortcut, knowing that "second through
            // last vertex" would always just be the last vertex, and that the "first unconnected vertex of edge i+1"
            // would always be its first vertex. And then we did the circular bit.
            //
            // OK, so here we want to join up faces by setting the second through last edges of face i equal to the
            // first unconnected edge of the next (edges - 1) faces. And we'll do the same circular bit.
            //
            // Note to self: the real trick here is getting the faces in the right order to begin with. It doesn't
            // matter when they're all the same type of face (i.e., when we're making a dodecahedron and all the
            // faces are pentagons), but it DOES matter when we're making a truncated icosahedron or anything bigger,
            // in which case we'll have twelve pentagons and a bunch of hexagons.


            // TODO all that stuff I just said I was going to do in those comments up there

            /*
            if (i < (numberOfEdges - 1)) {
                edges[1] = faces[i + 1].getEdges().toArray(new Edge[faces[i + 1].getEdges().size()])[0];
            } else {
                edges[1] = faces[0].getEdges().toArray(new Edge[faces[i + 1].getEdges().size()])[0];
            }
            */
        }
    }
}

