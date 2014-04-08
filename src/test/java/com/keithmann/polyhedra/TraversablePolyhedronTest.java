package com.keithmann.polyhedra;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by kmann on 14-04-08.
 */
public class TraversablePolyhedronTest {

    PolyhedronType polyhedronType;
    TraversablePolyhedron tp;
    TraversablePolyhedronFactory tpFactory;

    @BeforeMethod
    public void setUp() throws Exception {
        tpFactory = TraversablePolyhedronFactory.getInstance();
        polyhedronType = PolyhedronType.DODECAHEDRON;
        tp = tpFactory.makeTraversablePolyhedron(polyhedronType);
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testHasType() throws Exception {
        Assert.assertEquals(tp.getPolyhedronType(), polyhedronType);
    }

    @Test
    public void testHasPolyhedronOfSameType() throws Exception {
        Assert.assertEquals(tp.getPolyhedronType(),tp.getPolyhedron().getPolyhedronType());
    }

    @Test
    public void testHasGraphOfSameType() throws Exception {
        Assert.assertEquals(tp.getPolyhedronType(),tp.getGraph().getPolyhedronType());
    }
}
