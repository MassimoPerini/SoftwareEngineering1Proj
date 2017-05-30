package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/29/17.
 */

/** Stato di controllo in cui si capisce se far partire direttamente la produzione o meno*/
public class CardProdHarvControlStatus implements Status {

    private Gioco gameStatus;



    public CardProdHarvControlStatus(Gioco gameStatus){
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
        System.out.println("We can now  activate the productiony");
        gameStatus.setStateGame(gameStatus.getHarvProdStatus());

    }

    @Override
    public void chooseProdHarvCard() {
        System.out.println("You have to choose which card you want to activate");
        gameStatus.setStateGame(gameStatus.getSelectionProdCardStatus());

    }

    @Override
    public void userInput() {

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
