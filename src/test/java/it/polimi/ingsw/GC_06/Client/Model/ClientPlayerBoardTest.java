package it.polimi.ingsw.GC_06.Client.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by giuseppe on 7/2/17.
 */
public class ClientPlayerBoardTest {

    private String username;
    private ClientPlayerBoard clientPlayerBoard;

    @Before
    public void setUp(){

        username  ="peppe";
        clientPlayerBoard = new ClientPlayerBoard(username, new LinkedList<String>());


    }
    @Test
    public void addFamilyMember() throws Exception {

        ClientFamilyMember clientFamilyMember = new ClientFamilyMember("peppe",3,"WHITE");
        clientPlayerBoard.addFamilyMember(clientFamilyMember);
        assertTrue(clientPlayerBoard.getFamilyMembers().size() == 1);

    }

    @Test
    public void addCard() throws Exception {

        clientPlayerBoard.addCard("YELLOW","");
        assertTrue(clientPlayerBoard.getCards().get("YELLOW").size() == 1);
    }

    @Test
    public void addExcommunication() throws Exception {
        clientPlayerBoard.addExcommunication("marthin Luther");
        assertTrue(clientPlayerBoard.getExcommunication().size() == 1);
    }



    @Test
    public void changeValueFamilyMember() throws Exception {
        ClientFamilyMember clientFamilyMember = new ClientFamilyMember("peppe",3,"WHITE");
        clientPlayerBoard.addFamilyMember(clientFamilyMember);

        clientPlayerBoard.changeValueFamilyMember("WHITE",5);

        assertTrue(clientPlayerBoard.getFamilyMembers().get(0).getValue() == 5);
    }

}