package com.keithmann.polyhedra;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by kmann on 14-04-04.
 */
public class PolyhedronTest {

    PolyhedronType polyhedronType;
    Vertex vertex;
    Space space;
    Polyhedron polyhedron;

    @BeforeMethod
    public void setUp() throws Exception {
        polyhedronType = PolyhedronType.DODECAHEDRON;
        polyhedron = PolyhedronFactory.getInstance().makePolyhedron(polyhedronType);

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testPolyhedronExists() throws Exception {
        Assert.assertNotNull(polyhedron);
    }

}
