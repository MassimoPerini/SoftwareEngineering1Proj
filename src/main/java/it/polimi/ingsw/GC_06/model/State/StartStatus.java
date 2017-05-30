package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/29/17.
 */
public class StartStatus implements Status {

    Gioco gameStatus;



    public StartStatus(Gioco gameStatus){
        this.gameStatus = gameStatus;
    }


    @Override
    public void putFamilyMember() {
        /** qualche controllo qui non andrebbbe fatto??*/

        /** if ( posso mettere il familiare dove ho chiesto) */
                System.out.println("It's your turn: Choose a pawn and move");
                gameStatus.setStateGame(gameStatus.getPawnPositionedStatus());
        /**Else*/
        //System.out.println("you can't put your family member there");



    }

    @Override
    public void pickCard() {
        System.out.println("not allowed");
    }

    @Override
    public void activateProdHarv() {
        System.out.println("not allowed");

    }

    @Override
    public void chooseProdHarvCard() {
        System.out.println("not allowed");

    }

    @Override
    public void userInput() {
        System.out.println("not allowed");

    }

    @Override
    public void end() {
        System.out.println("not allowed");

    }

    @Override
    public void privilegeSelection() {

    }

    @Override
    public void startProduction() {
        System.out.println("not allowed");

    }
}
