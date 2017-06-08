package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by giuseppe on 6/8/17.
 */
public class SuiteTest {

    public static Test suite(){
        TestSuite suite = new TestSuite();
        suite.addTest(new BonusMalusOnResourcesTest("firstTest"));
        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
