package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;
import java.util.Map;

/**
 * Created by giuseppe on 6/16/17.
 */
public class EndGameMap {

    /** conversion table for cards */
    private Map<String, List<Integer>> endGameMap;
    private Map<Resource,List<Integer>> endGameResourceMap;

    public EndGameMap(Map<String, List<Integer>> endGameMap) {
        this.endGameMap = endGameMap;
    }

    public void addConversionLine(String colour, List<Integer> conversionValues){
        this.endGameMap.put(colour,conversionValues);
    }

    /** questo metodo posso usarlo nei miei fantastici bonus malus on End */

    public void editConversionTable(String colour , int value){
        List<Integer> conversionValues = endGameMap.get(colour);

        for(int i = 0; i < conversionValues.size();i++){
           conversionValues.set(i,value);
        }
    }

    public Map<String, List<Integer>> getEndGameMap() {
        return endGameMap;
    }

    public Map<Resource, List<Integer>> getEndGameResourceMap() {
        return endGameResourceMap;
    }
}
