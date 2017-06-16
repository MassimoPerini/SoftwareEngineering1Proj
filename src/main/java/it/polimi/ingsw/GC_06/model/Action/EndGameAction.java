package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by giuseppe on 6/16/17.
 */
public class EndGameAction  implements Action{

    private Game game;
    private Player player;
    private it.polimi.ingsw.GC_06.model.Resource.Resource resource;

    public EndGameAction(Game game, Player player, it.polimi.ingsw.GC_06.model.Resource.Resource resource) {
        this.game = game;
        this.player = player;
        this.resource = resource;
    }

    @Override
    public void execute() {

        /** ho la mappa delle conversioni delle carte che deve essere caricata da file*/
        Map<String, List<Integer>> conversionTable = new HashMap<>();/**Setting.getInstance().getProperty("conversion_table")*/;

        Set<String> colours = conversionTable.keySet();

        for(String colour : colours){
           int numbOfcard =  player.getPlayerBoard().getDevelopmentCards(colour).size();
           int bonus = conversionTable.get(colour).get(numbOfcard -1);
           player.getResourceSet().variateResource(resource,bonus);
        }


    }



    @Override
    public boolean isAllowed() {
        return false;
    }
}
