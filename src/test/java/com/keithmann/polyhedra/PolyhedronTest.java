package com.keithmann.polyhedra;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by kmann on 14-04-04.
 */
public class PolyhedronTest {


    PolyVertex polyVertex;
    Space space;
    Polyhedron polyhedron;

    @BeforeMethod
    public void setUp() throws Exception {

        polyhedron = new Polyhedron(new ArrayList<PolyVertex>() {
        });

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testPolyhedronExists() throws Exception {
        Assert.assertNotNull(polyhedron);
    }

    @Test
    public void testPolyhedronHasVertices() throws Exception {
        Assert.assertNotNull(polyhedron.getVertices());
    }
}
