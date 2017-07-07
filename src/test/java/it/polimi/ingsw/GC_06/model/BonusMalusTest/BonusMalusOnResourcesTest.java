
package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnResources;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/6/17.
 */
public class BonusMalusOnResourcesTest {




    private Resource resource;
    private int malusEntity;
    private ResourceSet effectResourceSet;
    private BonusMalusOnResources bonusMalusOnResources;


    @Before
    public void setUp(){
        Setting.getInstance().addPath("settings/bundle");
        effectResourceSet = new ResourceSet();
        effectResourceSet.variateResource(Resource.MILITARYPOINT,12);
        effectResourceSet.variateResource(Resource.MONEY,4);
        resource = Resource.MILITARYPOINT;
        malusEntity = -10;

        bonusMalusOnResources = new BonusMalusOnResources(resource,malusEntity,ActionType.BOARD_ACTION_ON_MARKET,false);

    }

    @Test
    public void firstTest(){

        bonusMalusOnResources.modify(effectResourceSet);
        assertTrue(2 == effectResourceSet.getResourceAmount(Resource.MILITARYPOINT));
        assertTrue(4 == effectResourceSet.getResourceAmount(Resource.MONEY));
    }


}
