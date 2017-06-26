package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 19/06/17.
 */
public class MessageBoardActionTower implements MessageClient{

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

        try {
            if (boardActionOnTower.isAllowed()) {
                boardActionOnTower.execute();
            }
        }
        catch (InterruptedException e)
        {
            return;
        }

        // if (f.get() == null)  per vedere se è ok

        //TODO FIX IT!

      /*  /** rollBack
        if(!boardActionOnTower.isAllowed()){
            System.out.println("Azione non consentita");
            int newPowerUpValue = -powerUpValue;
            powerUpFamilyMember = new PowerUpFamilyMember(currentPlayer,familyMember,newPowerUpValue);
            powerUpFamilyMember.setCoefficient(0);
            return;
        }
        else{

         //   boardActionOnTower.execute();
        }*/

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
    public void run() {
        execute();
    }
}
