package com.keithmann.polyhedra;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpaceTest {

    Space space;

    @BeforeMethod
    public void setUp() throws Exception {
        space = Space.getInstance();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void spaceExists() throws Exception {
        Assert.assertNotNull(space);
    }

    @Test
    public void spaceHasDimensions() throws Exception {
        Assert.assertNotNull(space.getNumberOfDimensions());
    }


}
