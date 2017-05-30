package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/29/17.
 */

/** questa è la classe che abbiamo chiamato "mossa effettuata"*/
public class PawnPositionedStatus implements Status {

    /** non inserisco la transizione ai privilegi del consiglio in attesa di Martin*/

    Gioco gameStatus;
    public PawnPositionedStatus(Gioco gameStatus){
        this.gameStatus = gameStatus;
    }


    @Override
    public void putFamilyMember() {

    }

    @Override
    public void pickCard() {

        System.out.println("It's time to pick a Card");

        gameStatus.setStateGame(gameStatus.getSelectionProdCardStatus());

    }

    @Override
    public void activateProdHarv() {
        System.out.println("Let's go to activate Production on Harvest Zone");
        gameStatus.setStateGame(gameStatus.getCardSelectedStatus());
    }

    @Override
    public void chooseProdHarvCard() {

    }

    @Override
    public void userInput() {

    }

    @Override
    public void end() {
        System.out.println("Let's begin again");
        gameStatus.setStateGame(gameStatus.getStartStatus());
    }

    @Override
    public void privilegeSelection() {

    }

    @Override
    public void startProduction() {

    }
}
