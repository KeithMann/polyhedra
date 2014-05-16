package com.keithmann.polyhedra;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by kmann on 14-04-04.
 */
public class PolyVertexTest {

    PolyVertex polyVertex;
    Space space;

    @BeforeMethod
    public void setUp() throws Exception {
        polyVertex = new PolyVertex();
        polyVertex.setCoordinates(new double[]{1.0,1.0,1.0});
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testVertexExists() throws Exception {
         Assert.assertNotNull(polyVertex);
    }

    @Test
    public void testVertexHasCoordinates() throws Exception {
        Assert.assertNotNull(polyVertex.getCoordinates());
    }

    @Test
    public void testCoordinatesArePointInSpace() throws Exception {

        double[] coordinates;
        coordinates = polyVertex.getCoordinates();
        for (int i = 0; i < space.getNumberOfDimensions(); i++) {
            Assert.assertEquals(coordinates[i],1.0);
        }
    }

}
