package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.*;

/**
 * Created by gabri on 01/07/2017.
 */
public class Ranker {

    private Map<String, Player> players;
    private GameStatus gameStatus;

    public Ranker( GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.players = gameStatus.getPlayers();
    }

    public List<Player> rankByResource(Resource resource) {
        Map<Integer, Player> backMap = new HashMap<>();
        List<Player> rankedList = new ArrayList<>();
        for (Player player : players.values()) {
            backMap.put(player.getResourceSet().getResourceAmount(resource), player);
        }
        List<Integer> values = new ArrayList<>();
        for (Integer i : backMap.keySet()) {
            values.add(i);
        }
        Collections.sort(values, Collections.reverseOrder());
        for (Integer value : values) {
            rankedList.add(backMap.get(value));
        }
        return rankedList;
    }
}
