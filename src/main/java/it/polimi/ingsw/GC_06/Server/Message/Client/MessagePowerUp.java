package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 6/20/17.
 */
public class MessagePowerUp implements MessageClient {

    private String player;
    private int game;
    private int clientFamilyMember;
    private int powerUpValue;/** quantità di quanto il player vuole potenziare */
    private Resource resource;
    private int quantity;/** quanto paghi nel resourceSet */
    private int prodHarvSelector; /** la zona di azione se prod o harv */

    public MessagePowerUp(int powerUpValue, int quantity, int clientFamilyMember, int prodHarvSelector) {

        this.powerUpValue = powerUpValue;
        this.resource = Resource.SERVANT;
        this.quantity = quantity;
        this.clientFamilyMember = clientFamilyMember;
    }

    @Override
    public void execute() {

        Game currentGame = GameList.getInstance().getGameId(game);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);
        FamilyMember familyMember = currentPlayer.getFamilyMembers()[clientFamilyMember];
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(resource,quantity);
        PowerUpFamilyMember powerUpFamilyMember = new PowerUpFamilyMember(currentPlayer,familyMember,powerUpValue);


        if(powerUpFamilyMember.isAllowed()){
            powerUpFamilyMember.execute();
        }

        //BoardActionOnProdHarv boardActionOnProdHarv = new BoardActionOnProdHarv(currentPlayer,)


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
