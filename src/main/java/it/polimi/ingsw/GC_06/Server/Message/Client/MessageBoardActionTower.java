package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 19/06/17.
 */

public class MessageBoardActionTower implements MessageMultipleSteps{

    private String tower;
    private int floor;
    private int clientFamilyMember;
    private int game;
    private String player;
    private int powerUpValue; /** the value that we want from the user to increase the servant value*/

    public MessageBoardActionTower(String tower, int floor, int familyMember, int powerUpValue)
    {
        this.tower = tower;
        this.floor = floor;
        this.clientFamilyMember = familyMember;
        this.powerUpValue = powerUpValue;
    }

    public MessageBoardActionTower(Object tower, int floor, ActionType actionType)
    {
        this.tower = (String) tower;
        this.floor = floor;
        clientFamilyMember = -1;
    }

    @Override
    public void execute() {

        Game currentGame = GameList.getInstance().getGameId(game);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);
        FamilyMember familyMember = currentPlayer.getFamilyMembers()[clientFamilyMember];
        Tower currentTower = currentGame.getBoard().getTowers().get(tower);

        BoardActionOnTower boardActionOnTower = new BoardActionOnTower(currentPlayer, floor, currentTower, familyMember,currentGame);

        PowerUpFamilyMember powerUpFamilyMember = new PowerUpFamilyMember(currentPlayer,familyMember,powerUpValue);

        if(powerUpFamilyMember.isAllowed()){
            powerUpFamilyMember.execute();
        }
        else{
            currentGame.getGameStatus().changeState(TransitionType.ERROR);
        }

        try {
            if (boardActionOnTower.isAllowed()) {
                boardActionOnTower.execute();
            }
            else{
                currentGame.getGameStatus().changeState(TransitionType.ERROR);
            }
        }
        catch (InterruptedException e)
        {
            return;
        }

        // if (f.get() == null)  per vedere se è ok

        //TODO FIX IT!


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
    public void setFamilyMember(int index) {
        this.clientFamilyMember = index;
    }

    @Override
    public void setPowerUp(int powerUp) {
        this.powerUpValue = powerUp;
    }

    @Override
    public boolean isValid() {
        return clientFamilyMember!=-1;
    }
}
