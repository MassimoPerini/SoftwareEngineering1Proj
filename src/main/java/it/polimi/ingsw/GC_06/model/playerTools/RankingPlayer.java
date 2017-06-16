package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.model.Resource.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by giuseppe on 6/16/17.
 */
public class RankingPlayer {


    /** get an ordered list of players by a resource*/
    /** da migliorare perchè fa schifo*/

    public static List<Player> getRanking(List<Player> players,Resource resource){

        List<Player> ranking = new ArrayList<>();
        for(int i = 0;  i < players.size(); i++){
            int pos = 0;
            for(int j = 1; j <players.size();j++){
                if(players.get(i).getResourceSet().getResourceAmount(resource)> players.get(j).getResourceSet().getResourceAmount(resource)){
                    pos++;
                }
            }
            ranking.add(pos,players.get(i));
        }
        Collections.reverse(ranking);
        return ranking;
    }
}
