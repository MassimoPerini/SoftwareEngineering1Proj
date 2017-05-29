package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/19/17.
 */
public interface Status {

    void putFamilyMember();
    void pickCard();
    void activateProdHarv();
    void chooseProdHarvCard();
    void userInput();
    void end();
    void startProduction();

}

