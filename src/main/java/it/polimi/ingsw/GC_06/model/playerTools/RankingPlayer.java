package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.*;

/**
 * Created by giuseppe on 6/16/17.
 */

/**
public class RankingPlayer {



    public static List<Player> getRanking(Game game, Resource resource) {

        Set<String> gamers = game.getGameStatus().getPlayers().keySet();
        List<Player> players = new LinkedList<>();

        for (String gamer : gamers) {
            players.add(game.getGameStatus().getPlayers().get(gamer));
        }

        // adesso qui dobbiamo iniziare a fare la lista che sarà un ranking rispetto alla risorsa

        /** facciamo un ordinamento a cazzo al limite poi lo facciamo con il merge sort



        boolean exchange = true;

        while (exchange) {
            exchange = false;
            for (int i = 0; i < players.size(); i++) {
                for (int j = 1; j < players.size(); i++) {

                    if (players.get(i).getResourceSet().getResourceAmount(resource) < players.get(j).getResourceSet().getResourceAmount(resource)) {
                        Player tmpPlayer = players.get(j);
                        Player tmp1Player = players.get(i);
                        players.get(j) = tmp1Player;
                        players.get(i) = tmpPlayer;

                    }
                }
            }

        }
    }








    private void sortPlayerList(List<Player> players, Resource resource){


        boolean swapped = true;

        for(int i = 0; i < players.size(); i++){
            for(int j = i; j < players.size()-1;j++){
                if(players.get(i).getResourceSet().getResourceAmount(resource) < players.get(i).getResourceSet().getResourceAmount(resource)){
                    this.swap(i,j,players);
                }
            }
        }



    private void swap(int index1, int index2, List<Player> players){

            Player playerTmp = players.get(index1);
            Player playerTmp1 = players.get(index2);

            players.get(index1) = players.get(index2);
    }



    }




}*/