
package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnResources;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/6/17.
 */
public class BonusMalusOnResourcesTest {

    /** vanno testate tre tipologie qua
     * 1) se prendi questa risorsa ti levo un valore x
     * 2) Dato un resource set ( come malus entity) levo al mio targetSet la somma delle quantità
     * 3) Ti levo 1 per ogni tot di risorse che mi passi
     */

    private BonusMalusOnResources bonusMalusOnResources;
    private BonusMalusOnResources bonusMalusOnResources1;
    private Resource bonusMalusTarget;

    private ResourceSet bonusMalusSet = new ResourceSet();
    private ResourceSet bonusMalusSet1 = new ResourceSet();
    private ResourceSet targetResourceSet = new ResourceSet();
    private ResourceSet targetSet2 = new ResourceSet();
    private ResourceSet targetSet = new ResourceSet();
    private BonusMalusOnResources bonusMalusOnResources3;
    private int coefficient;



    @Before
    public void setUp(){

        /** settaggi riferiti al 1)*/
        bonusMalusTarget = Resource.MONEY;
        bonusMalusSet.variateResource(Resource.MONEY,1);
        coefficient = -1;
        targetResourceSet.variateResource(Resource.MONEY,10);
        targetResourceSet.variateResource(Resource.MILITARYPOINT,15);
        bonusMalusOnResources = new BonusMalusOnResources(bonusMalusSet,coefficient,ActionType.RESOURCEACTION,Resource.MONEY);

        /** settaggi riferiti al secondo test*/

        Resource bonusMalusTarget1 = Resource.FAITHPOINT;
        bonusMalusSet1.variateResource(Resource.MILITARYPOINT,10);
        bonusMalusSet1.variateResource(Resource.WOOD,10);
        bonusMalusSet1.variateResource(Resource.SERVANT,10);
        targetSet.variateResource(Resource.FAITHPOINT,50);
        targetSet2.variateResource(Resource.FAITHPOINT,50);
        bonusMalusOnResources1 = new BonusMalusOnResources(bonusMalusSet1,coefficient,ActionType.RESOURCEACTION,Resource.FAITHPOINT);
        bonusMalusOnResources3 = new BonusMalusOnResources(targetSet2,-0.2,ActionType.RESOURCEACTION,Resource.FAITHPOINT);



    }




    @Test
    public void firstTest(){


        bonusMalusOnResources.modify(targetResourceSet);
        assertTrue(targetResourceSet.getResourceAmount(Resource.MONEY) == 9);
       // assertTrue(targetResourceSet.getResourceAmount(Resource.MILITARYPOINT) == 9);


        bonusMalusOnResources1.modify(targetSet);
        assertTrue(20 == targetSet.getResourceAmount(Resource.FAITHPOINT));


        bonusMalusOnResources3.modify(targetSet2);
        assertTrue(40 == targetSet2.getResourceAmount(Resource.FAITHPOINT));
    }


}
