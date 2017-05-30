package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/29/17.
 */
public class SelectionProdCardStatus implements Status {

    private Gioco gameStatus;
    public SelectionProdCardStatus(Gioco gameStatus){
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
        System.out.println("Let's go to the production");
        gameStatus.setStateGame(gameStatus.getHarvProdStatus());

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
