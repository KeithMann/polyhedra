package com.keithmann.polyhedra;

/**
 * Created by kmann on 14-04-04.
 */

public class PolyVertex {

    private double[] coordinates;

    public PolyVertex(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public double getCoordinate(int dimension) {
        return coordinates[dimension]; //coordinates[dimension];
    }

}
