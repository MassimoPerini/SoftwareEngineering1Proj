package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/29/17.
 */
public class PrivilegeDecisionStatus implements Status {

    Gioco gameStatus;
    public PrivilegeDecisionStatus(Gioco gameStatus){
        this.gameStatus = gameStatus;
    }




    @Override
    public void putFamilyMember() {

    }

    @Override
    public void pickCard() {

    }

    @Override
    public void activateProdHarv() {

    }

    @Override
    public void chooseProdHarvCard() {

    }

    @Override
    public void userInput() {

        gameStatus.setStateGame(gameStatus.getCounsilPrivilegeStatus());

    }

    @Override
    public void end() {

    }

    @Override
    public void privilegeSelection() {

    }

    @Override
    public void startProduction() {

    }
}
