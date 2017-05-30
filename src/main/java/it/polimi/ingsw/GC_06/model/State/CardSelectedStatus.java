package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/29/17.
 */
/** Carta + Effetti*/
public class CardSelectedStatus implements Status{


    Gioco gameStatus;

    public CardSelectedStatus(Gioco gameStatus){
        this.gameStatus = gameStatus;
    }



    @Override
    public void putFamilyMember() {

    }

    @Override
    public void pickCard() {

        System.out.println("You are going to choose another card because of an effect");
        gameStatus.setStateGame(gameStatus.getDecisionCardStatus());

    }

    @Override

    public void activateProdHarv() {
        System.out.println("we can start the production or harv control");
        gameStatus.setStateGame(gameStatus.getCardProdHarvControlStatus());

    }

    @Override
    public void chooseProdHarvCard() {

    }

    @Override
    public void userInput() {

    }

    @Override
    public void end() {
        System.out.println("Let's start again");
        gameStatus.setStateGame(gameStatus.getStartStatus());
    }

    @Override
    public void privilegeSelection() {

    }

    @Override
    public void startProduction() {

    }
}
