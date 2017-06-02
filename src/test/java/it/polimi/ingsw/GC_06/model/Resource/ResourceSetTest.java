package it.polimi.ingsw.GC_06.model.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by massimo on 28/05/17.
 */
public class ResourceSetTest {

    private ResourceSet r1, r2;

    @Before
    public void setUp() throws Exception {

        r1 = new ResourceSet();
        r2 = new ResourceSet();
    }

    @Test
    public void isIncludedWithEmpty() {
        assertTrue(r1.isIncluded(r2));
    }

    @Test
    public void isIncludedWithNegative() {
        r1.variateResource(Resource.SERVANT, 5);
        assertTrue(r1.isIncluded(Resource.SERVANT, -3));
    }


}