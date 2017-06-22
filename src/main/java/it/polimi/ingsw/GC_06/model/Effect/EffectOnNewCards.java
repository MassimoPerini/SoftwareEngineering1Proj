package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessagePickAnotherCard;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Action.PickCard.PayCard;
import it.polimi.ingsw.GC_06.model.Board.TowerFloor;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by gabri on 06/06/2017.
 */
public class EffectOnNewCards implements Effect, Blocking {
    private Map<String, Integer> towerFloors;
    private List inputParams;
    private List<Map<String, Integer>> toAsk = new LinkedList();

    public EffectOnNewCards(Map<String, Integer> towerFloors) { //Integer è il punteggio max
        super();
        this.towerFloors = towerFloors;
    }

    @Override
    public synchronized void execute(Player player,Game game) {

        GameList.getInstance().setCurrentBlocking(game, this);
        for (String s : game.getBoard().getTowers().keySet()) {
            int i=0;
            for (TowerFloor towerFloor : game.getBoard().getTowers().get(s).getTowerFloor()) {
                if (!towerFloor.isEmpty() && towerFloor.getActionPlace().getPrice() <= towerFloors.get(s))
                {
                    Map<String, Integer> tmpMap = new HashMap<>();
                    tmpMap.put(s, i);
                    toAsk.add(tmpMap);
                }
                i++;
            }
        }

        MessagePickAnotherCard messagePickAnotherCard = new MessagePickAnotherCard(toAsk);
        GameList.getInstance().setCurrentBlocking(game, this);
        game.getGameStatus().sendMessage(messagePickAnotherCard);
        while (inputParams ==null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //inputParams =
        int towerChoose = (Integer) inputParams.get(0);
        Map<String, Integer> answer = toAsk.get(towerChoose);
        String tower = "";
        for (String s : answer.keySet()) {
            tower = s;
        }
        PayCard payCard = new PayCard(game.getBoard().getTowers().get(tower),answer.get(tower), player, game);
        if (payCard.isAllowed())
            payCard.execute();
    }

    //Intero della scelta

    @Override
    public synchronized void setOptionalParams(List list) {
        this.inputParams = list;
        notifyAll();
    }
}