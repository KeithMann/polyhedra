package com.keithmann.polyhedra;

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

        switch (polyhedronType) {
            case DODECAHEDRON:
                assembleDodecahedron(polyhedron);
                break;
        }

        return polyhedron;

    }

    private void assembleDodecahedron(Polyhedron polyhedron) {

        PolyFaceFactory polyFaceFactory = PolyFaceFactory.getInstance();

        for (int i = 1; i <= 12; i++) {
            polyhedron.addPolyFace(polyFaceFactory.makePolyFace(PolyFaceType.PENTAGON));
        }

        //TODO join up pentagons by setting edges equal

    }
}