package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Client.Model.ClientBoardGame;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 7/2/17.
 */
public class ClientBoardGameTest {

    private ClientBoardGame clientBoardGame;

    @Before
    public void setUp(){
        Setting.getInstance().addPath("settings/bundle");
        clientBoardGame = new ClientBoardGame();
    }

    @Test
    public void createTower() throws Exception {
        String colour = "YELLOW";
        clientBoardGame.createTower(colour,3);
        assertTrue(clientBoardGame.getTowersClient().get("YELLOW").size() == 3 );
    }

  /**  @Test
    public void createProdHarv() throws Exception {

        clientBoardGame.createProdHarv(0,2);
        assertTrue(clientBoardGame.getProductionHarvest().get(0).size() == 2);
    }*/

    @Test
    public void createMarket() throws Exception {
        clientBoardGame.createMarket(0,4);
        assertTrue(clientBoardGame.getMarket().get(0).size() == 4);
    }


    @Test
    public void createCouncil() throws Exception {
        clientBoardGame.createCouncil(0,1);
        assertTrue(clientBoardGame.getCouncil().get(0).size() == 1);
    }

    @Test
    public void removeCard() throws Exception {
        //clientBoardGame.removeCard("YELL");
    }


    @Test
    public void clearAllFamilyMembers() throws Exception {
        clientBoardGame.createTower("YELLOW",3);
        clientBoardGame.createTower("BLUE",3);
        clientBoardGame.addFamilyMemberToTower(new ClientFamilyMember("peppe",5,"RED"),"YELLOW",0);



        clientBoardGame.clearAllFamilyMembers();
        assertTrue(clientBoardGame.getTowersClient().get("YELLOW").get(0).getSpaceAction().getFamilyMembers().size() == 0);
    }

}
