package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.BoardActionOnProdHarv;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.DefaultAskUserSelector;
import it.polimi.ingsw.GC_06.model.Board.ProdHarvZone;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 6/20/17.
 */
public class MessageProdHarv implements MessageMultipleSteps{

    private String player;
    private int prodHarvAreaSelector;
    private int clientFamilyMember;
    private int game;
    private int index;
    private int powerUpValue;  /** di quanto il player vuole alzare il valore del familiare */

    public MessageProdHarv(int prodHarvAreaSelector, int familyMember,int index, int powerUpValue) {
        this.prodHarvAreaSelector = prodHarvAreaSelector;
        this.clientFamilyMember = familyMember;
        this.index = index;
        this.powerUpValue = powerUpValue;
    }

    public MessageProdHarv(Object prodHarvAreaSelector, int index) {
        this.prodHarvAreaSelector = (int) prodHarvAreaSelector;
        this.index = index;
        this.clientFamilyMember = -1;
    }

    @Override
    public void execute() {

        Game currentGame = GameList.getInstance().getGameId(game);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);
        FamilyMember familyMember = currentPlayer.getFamilyMembers()[clientFamilyMember];
        ProdHarvZone prodHarvZone = currentGame.getBoard().getProdHarvZones().get(prodHarvAreaSelector);
        DefaultAskUserSelector defaultAskUserSelector = new DefaultAskUserSelector();



        BoardActionOnProdHarv boardActionOnProdHarv = new BoardActionOnProdHarv(currentPlayer,index,prodHarvZone,prodHarvZone.getActionType(),defaultAskUserSelector,familyMember, currentGame);
        PowerUpFamilyMember powerUpFamilyMember = new PowerUpFamilyMember(currentPlayer,familyMember,powerUpValue);
/*
        System.out.println("ACTION STARTED");
        int originalValue = familyMember.getValue();
        BonusMalusHandler.filter(player,ACTION_TYPE,tower.getColor(),familyMember);
        if (this.isAllowed()) {
            this.execute();
            // devo rimuovere il bonus o il malus che ho utilizzato
        }
        else{
            familyMember.setValue(originalValue);
            System.out.println("ERRORE, NON POSSO ESEGUIRE L'AZIONE");
        }
*/
        try {
            if (powerUpFamilyMember.isAllowed())
                powerUpFamilyMember.execute();
            if (boardActionOnProdHarv.isAllowed()) {
                boardActionOnProdHarv.execute();
            }
        }
        catch (InterruptedException e)
        {
            return;
        }
        /*
        if(powerUpFamilyMember.isAllowed()){
            powerUpFamilyMember.execute();
        }
        */
        //TODO fix it

        /** rollBack */

        /*
        if(!boardActionOnProdHarv.isAllowed()){
           int newPowerUpValue = -powerUpValue;
           powerUpFamilyMember = new PowerUpFamilyMember(currentPlayer,familyMember,newPowerUpValue);
           powerUpFamilyMember.setCoefficient(0);
           return;
        }
        else{
            boardActionOnProdHarv.execute();
        }
        */
    }

    @Override
    public void setGame(int game) {

        this.game = game;
    }

    @Override
    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean isValid() {
        return clientFamilyMember!=-1;
    }

    @Override
    public void run() {
        execute();
    }

    @Override
    public void setFamilyMember(int index) {
        this.clientFamilyMember = index;
    }

    @Override
    public void setPowerUp(int powerUp) {
        this.powerUpValue = powerUp;
    }
}
