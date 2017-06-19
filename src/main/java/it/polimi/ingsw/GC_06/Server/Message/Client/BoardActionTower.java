package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 19/06/17.
 */
public class BoardActionTower implements MessageClient{

    private String tower;
    private int floor;
    private int clientFamilyMember;
    private int game;
    private String player;

    public BoardActionTower(String tower, int floor, int familyMember)
    {
        this.tower = tower;
        this.floor = floor;
        this.clientFamilyMember = familyMember;
    }

    @Override
    public void execute() {

        Game currentGame = GameList.getInstance().getGameId(game);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);
        FamilyMember familyMember = currentPlayer.getFamilyMembers()[clientFamilyMember];
        Tower currentTower = currentGame.getBoard().getTowers().get(tower);

        BoardActionOnTower boardActionOnTower = new BoardActionOnTower(currentPlayer, floor, currentTower, familyMember);
        if (!boardActionOnTower.isAllowed())
        {
            System.out.println("Errore eccezione");
            return;
        }
        boardActionOnTower.execute();

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
