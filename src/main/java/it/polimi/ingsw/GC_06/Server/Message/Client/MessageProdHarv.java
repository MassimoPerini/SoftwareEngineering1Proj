package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.BoardActionOnProdHarv;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.DefaultAskUserSelector;
import it.polimi.ingsw.GC_06.model.Board.ProdHarvZone;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 6/20/17.
 */
public class MessageProdHarv implements MessageClient{

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

    @Override
    public void execute() {

        Game currentGame = GameList.getInstance().getGameId(game);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);
        FamilyMember familyMember = currentPlayer.getFamilyMembers()[clientFamilyMember];
        ProdHarvZone prodHarvZone = currentGame.getBoard().getProdHarvZones().get(prodHarvAreaSelector);
        DefaultAskUserSelector defaultAskUserSelector = new DefaultAskUserSelector();



        BoardActionOnProdHarv boardActionOnProdHarv = new BoardActionOnProdHarv(currentPlayer,index,prodHarvZone,prodHarvZone.getActionType(),defaultAskUserSelector,familyMember);
        PowerUpFamilyMember powerUpFamilyMember = new PowerUpFamilyMember(currentPlayer,familyMember,powerUpValue);

        if(powerUpFamilyMember.isAllowed()){
            powerUpFamilyMember.execute();
        }

        /** rollBack */
        if(!boardActionOnProdHarv.isAllowed()){
           int newPowerUpValue = -powerUpValue;
           powerUpFamilyMember = new PowerUpFamilyMember(currentPlayer,familyMember,newPowerUpValue);
           powerUpFamilyMember.setCoefficient(0);
           return;
        }
        else{
            boardActionOnProdHarv.execute();
        }
    }

    @Override
    public void setGame(int game) {

        this.game = game;
    }

    @Override
    public void setPlayer(String player) {
        this.player = player;
    }
}
