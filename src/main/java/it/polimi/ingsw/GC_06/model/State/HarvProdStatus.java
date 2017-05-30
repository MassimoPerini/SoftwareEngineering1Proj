package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/29/17.
 */
public class HarvProdStatus implements Status {

    private Gioco gameStatus;

    public HarvProdStatus(){
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
        System.out.println("From production to the start");
        gameStatus.setStateGame(gameStatus.getStartStatus());

    }

    @Override
    public void privilegeSelection() {

    }

    @Override
    public void startProduction() {

    }
}
