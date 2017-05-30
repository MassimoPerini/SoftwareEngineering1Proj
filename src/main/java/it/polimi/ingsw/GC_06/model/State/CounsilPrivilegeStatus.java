package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/29/17.
 */
public class CounsilPrivilegeStatus implements Status {

    private Gioco gameStatus;

    public CounsilPrivilegeStatus(Gioco gameStatus){
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

    }

    @Override
    public void end() {
        System.out.println("let's go to the start");
        gameStatus.setStateGame(gameStatus.getStartStatus());

    }

    @Override
    public void privilegeSelection() {
        System.out.println("let's go to the selection of counsil privilege");
        gameStatus.setStateGame(gameStatus.getPrivilegeDecisionStatus());
    }

    @Override
    public void startProduction() {

    }


}
