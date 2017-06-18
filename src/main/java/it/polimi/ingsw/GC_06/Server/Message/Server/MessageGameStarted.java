package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientBoardGame;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Board.MarketAndCouncil;
import it.polimi.ingsw.GC_06.model.Board.ProdHarvZone;
import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.*;

/**
 * Created by massimo on 17/06/17.
 */
public class MessageGameStarted implements MessageServer {

    private Map<String, Integer> towers;
    private List<Integer> councils;
    private List<Integer> prodHarv;
    private List<Integer> market;
    private List<String> players;

    //Remind: gestire solo valori primitivi e non modificabili! Altrimenti è pericoloso mandare reference con RMI

    public MessageGameStarted(Game game)
    {
        towers = new HashMap<>();
        councils = new LinkedList<>();
        prodHarv = new LinkedList<>();
        market = new LinkedList<>();
        players = new LinkedList<>();

        for (String s : game.getGameStatus().getPlayers().keySet()) {
            players.add(s);
        }

        for (String s : game.getBoard().getTowers().keySet()) {
            towers.put(s, game.getBoard().getTowers().get(s).getTowerFloor().size());       //Color - tower floors
        }

        for (MarketAndCouncil marketAndCouncil : game.getBoard().getCouncils()) {
            councils.add(marketAndCouncil.getActionPlaces().size());            //Il primo council ha x actionplaces...
        }

        for (ProdHarvZone prodHarvZone : game.getBoard().getProdHarvZones()) {
            prodHarv.add(prodHarvZone.getActionPlaces().size());
        }

        for (MarketAndCouncil marketAndCouncil : game.getBoard().getMarket()) {
            market.add(marketAndCouncil.getActionPlaces().size());
        }
    }

    @Override
    public void execute(ClientController clientController) {

        ClientBoardGame clientBoardGame = clientController.getMainClientModel().getClientBoardGame();

        for (int i=0;i<councils.size();i++) {
            clientBoardGame.createCouncil(i, councils.get(i));
        }
        for (int i=0;i<prodHarv.size();i++) {
            clientBoardGame.createProdHarv(i, prodHarv.get(i));
        }
        for (int i=0;i<market.size();i++) {
            clientBoardGame.createMarket(i, market.get(i));
        }
        for (String s : towers.keySet()) {
            clientBoardGame.createTower(s, towers.get(s));
        }

        for (String player : players) {
            clientController.getMainClientModel().generateNewPlayerBoard(player);
        }

        clientController.getViewOrchestrator().change(ClientStateName.GAME_START, "");

    }
}
