package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-04.
 */
public class Space {

    static final int NUMBER_OF_DIMENSIONS = 3;
    private static Space instance;

    private Space() {

    }

    public static Space getInstance() {
        if (instance == null) {
            instance = new Space();
        }

        return instance;
    }

    public static int getNumberOfDimensions() {
        return NUMBER_OF_DIMENSIONS;
    }

}
