package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnCost;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by giuseppe on 6/17/17.
 */
public class BonusMalusOnCostTest {
    private ResourceSet resourceSet = new ResourceSet();
    private ResourceSet bonusMalusEntity = new ResourceSet();
    private Requirement requirement = new Requirement(resourceSet,resourceSet);
    private List<Requirement> requirementList = new LinkedList<>();
    private List<Effect> effectList = new LinkedList<>();
    private Map<Integer,List<ProdHarvEffect>> effectMap = new HashMap<>();
    private String colour = "YELLOW";
    private List<String> coloursTarget = new ArrayList<>();
    private BonusMalusOnCost bonusMalusOnCost;
    private DevelopmentCard card;

    @Before
    public void setUp(){
        resourceSet.variateResource(it.polimi.ingsw.GC_06.model.Resource.Resource.MILITARYPOINT,10);
        resourceSet.variateResource(it.polimi.ingsw.GC_06.model.Resource.Resource.MONEY,10);
        bonusMalusEntity.variateResource(it.polimi.ingsw.GC_06.model.Resource.Resource.MONEY,-3);
        requirementList.add(requirement);
        coloursTarget.add("YELLOW");
        coloursTarget.add("GREEN");
        coloursTarget.add("PURPLE");
        card = new DevelopmentCard("Peppe",1,requirementList,effectList,colour,effectMap);
        bonusMalusOnCost = new BonusMalusOnCost(bonusMalusEntity,coloursTarget, ActionType.PAYCARDACTION);
    }

    @Test
    public void testOnCost(){

        DevelopmentCard newCard = bonusMalusOnCost.modify(card);
        //System.out.println(card.getRequirements().get(0).getCost().getResourceAmount(it.polimi.ingsw.GC_06.model.Resource.Resource.MONEY));
        System.out.println(card.getIdColour());

        assertTrue(bonusMalusOnCost.isAllowed(card));
        assertTrue( 7 == card.getRequirements().get(0).getCost().
                getResourceAmount(it.polimi.ingsw.GC_06.model.Resource.Resource.MONEY));
        assertTrue(10 == card.getRequirements().get(0).getCost().
                getResourceAmount(it.polimi.ingsw.GC_06.model.Resource.Resource.MILITARYPOINT));

        assertTrue( 7 == newCard.getRequirements().get(0).getCost().
                getResourceAmount(it.polimi.ingsw.GC_06.model.Resource.Resource.MONEY));
        assertTrue(10 == newCard.getRequirements().get(0).getCost().
                getResourceAmount(it.polimi.ingsw.GC_06.model.Resource.Resource.MILITARYPOINT));
        System.out.println(card.getRequirements().get(0).getCost(). getResourceAmount(it.polimi.ingsw.GC_06.model.Resource.Resource.MONEY));
    }
}
