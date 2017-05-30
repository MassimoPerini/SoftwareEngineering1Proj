package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/29/17.
 */
/** Stato di attesa dell'utente: aspettiamo che ci dia un'altra carta*/
public class DecisionCardStatus implements Status {


    Gioco gameStatus;


    public DecisionCardStatus(Gioco gameStatus){
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

        /** vedremo come funziona */
        System.out.println("You must choose another card");
        gameStatus.setStateGame(gameStatus.getCardSelectedStatus());

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
