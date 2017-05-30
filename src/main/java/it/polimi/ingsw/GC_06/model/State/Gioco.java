package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/30/17.
 */
public class Gioco {

     private Status startStatus;
     private Status selectionProdCardStatus;
     private Status privilegeDecisionStatus;
     private Status pawnPositionedStatus;
     private Status harvProdStatus;
     private Status decisionCardStatus;
     private Status counsilPrivilegeStatus;
     private Status cardSelectedStatus;
     private Status CardProdHarvControlStatus;


    Status stateGame;

    public Gioco(){
        startStatus = new StartStatus(this);
        selectionProdCardStatus = new SelectionProdCardStatus(this);
        privilegeDecisionStatus = new PrivilegeDecisionStatus(this);
        pawnPositionedStatus = new PawnPositionedStatus(this);
        decisionCardStatus = new DecisionCardStatus(this);
        cardSelectedStatus = new CardSelectedStatus(this);
        counsilPrivilegeStatus = new CounsilPrivilegeStatus(this);
        CardProdHarvControlStatus = new CardProdHarvControlStatus(this);

        stateGame = startStatus;
    }

    public void setStateGame(Status newGameStatus){
        stateGame = newGameStatus;
    }

    public void putFamilyMember(){
        stateGame.putFamilyMember();
    }

    public void pickCard(){
        stateGame.pickCard();
    }

    public void activateProdHarv(){
        stateGame.activateProdHarv();
    }

    public void chooseProdHarvCard(){
        stateGame.chooseProdHarvCard();
    }
    public void userInput(){
        stateGame.userInput();
    }

    public void startProduction(){
        stateGame.startProduction();
    }

    public void privilegeSelection(){
        stateGame.privilegeSelection();
    }

    public void end(){
        stateGame.end();
    }


    /** getter*/

    public Status getStartStatus() {
        return startStatus;
    }

    public Status getSelectionProdCardStatus() {
        return selectionProdCardStatus;
    }

    public Status getPrivilegeDecisionStatus() {
        return privilegeDecisionStatus;
    }

    public Status getPawnPositionedStatus() {
        return pawnPositionedStatus;
    }

    public Status getHarvProdStatus() {
        return harvProdStatus;
    }

    public Status getDecisionCardStatus() {
        return decisionCardStatus;
    }

    public Status getCounsilPrivilegeStatus() {
        return counsilPrivilegeStatus;
    }

    public Status getCardSelectedStatus() {
        return cardSelectedStatus;
    }

    public Status getCardProdHarvControlStatus() {
        return CardProdHarvControlStatus;
    }
}
