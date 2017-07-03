package it.polimi.ingsw.GC_06.Client.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by giuseppe on 7/2/17.
 */
public class MainClientModelTest {

    private MainClientModel mainClientModel;

    @Before
    public void setUp(){
        mainClientModel = new MainClientModel();
    }
    @Test
    public void updateStatus() throws Exception {
        mainClientModel.updateStatus(1,2,"peppe");
        assertTrue(mainClientModel.getEra() == 2 && mainClientModel.getTurn() ==1);
    }

    @Test
    public void changeMyState() throws Exception {
        mainClientModel.changeMyState(ClientStateName.CHOOSE_NEW_CARD);
        assertTrue(mainClientModel.getMyStatus().equals(ClientStateName.CHOOSE_NEW_CARD));
    }



}