package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.BoardActionOnProdHarv;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.DefaultAskUserSelector;
import it.polimi.ingsw.GC_06.model.Board.ProdHarvZone;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
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
    private ActionType actionType;
    private ActionType startProdHarv;

    public MessageProdHarv(int prodHarvAreaSelector, int familyMember,int index, int powerUpValue,ActionType actionType) {
        this.prodHarvAreaSelector = prodHarvAreaSelector;
        this.clientFamilyMember = familyMember;
        this.index = index;
        this.powerUpValue = powerUpValue;
        this.actionType = actionType;
    }

    public MessageProdHarv(Object prodHarvAreaSelector, int index, ActionType actionType) {
        this.prodHarvAreaSelector = (int) prodHarvAreaSelector;
        this.index = index;
        this.clientFamilyMember = -1;
    }

    @Override
    public void execute() {

        Game currentGame = GameList.getInstance().getGameId(game);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);
        FamilyMember familyMember = currentPlayer.getFamilyMembers()[clientFamilyMember];


        ProdHarvZone prodOrharvZone;
        if(actionType.equals(ActionType.BOARD_ACTION_ON_HARV)){
            prodOrharvZone = currentGame.getBoard().getHarvestZones().get(prodHarvAreaSelector);
        }
        else{
            prodOrharvZone = currentGame.getBoard().getProductionZones().get(prodHarvAreaSelector);
        }
        DefaultAskUserSelector defaultAskUserSelector = new DefaultAskUserSelector();




        BoardActionOnProdHarv boardActionOnProdHarv = new BoardActionOnProdHarv(currentPlayer,index,prodOrharvZone,actionType,startProdHarv,defaultAskUserSelector,familyMember, currentGame);
        PowerUpFamilyMember powerUpFamilyMember = new PowerUpFamilyMember(currentPlayer,familyMember,powerUpValue);

        try {
            if (powerUpFamilyMember.isAllowed())
                powerUpFamilyMember.execute();
            if (boardActionOnProdHarv.isAllowed()) {
                boardActionOnProdHarv.execute();
            }
            else{
                currentGame.getGameStatus().changeState(TransitionType.ERROR);
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
    public void setFamilyMember(int index) {
        this.clientFamilyMember = index;
    }

    @Override
    public void setPowerUp(int powerUp) {
        this.powerUpValue = powerUp;
    }

    public void setStartProdHarv(ActionType startProdHarv) {
        this.startProdHarv = startProdHarv;
    }
}
