package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import it.polimi.ingsw.GC_06.model.playerTools.RankingPlayer;

import java.util.List;
import java.util.Set;

/**
 * Created by giuseppe on 6/16/17.
 */
public class EndGameAction  implements Action {

    private EndGameMap endGameMap;
    private List<Player> players;
    private Resource resource; /** saranno i punti fede*/
    private it.polimi.ingsw.GC_06.model.Resource.Resource extraResources;
    private Game game;
    private final ActionType ACTION_TYPE = ActionType.END_GAME;

    public EndGameAction(EndGameMap endGameMap, List<Player> players, it.polimi.ingsw.GC_06.model.Resource.Resource resource) {
        this.endGameMap = endGameMap;
        this.players = players;
        this.resource = resource;
    }

    @Override
    public void execute() {

        /** aggiungiamo i punti provenienti da endGameMap per tutti i giocatori */

        for(Player player : players){
            this.turnCardsIntoPoints(player);
            this.turnResourceIntoPoint(player);
            //this.addFinalPoint(player);
        }

        this.getPointsFromRanking();

        /** vorrei anche che questa classe mi dicesse chi ha vinto */

    }



    @Override
    public boolean isAllowed() {
        return false;
    }

    private void turnCardsIntoPoints(Player player){

        Set<String> colours = endGameMap.getEndGameMap().keySet();

        for(String colour : colours){
            int numbOfCards = player.getPlayerBoard().getDevelopmentCards(colour).size();
            int endPoint = endGameMap.getEndGameMap().get(colour).get(numbOfCards);
            BonusMalusHandler.filter(player,ACTION_TYPE,endPoint,colour);
            player.getResourceSet().variateResource(resource, endPoint);
        }

    }

    /** dovrebbe fare anche l'aggiunzione dei punti finali */

    private void turnResourceIntoPoint(Player player){
       int endPoint =  player.getResourceSet().totalResourceQuantity() - player.getResourceSet().getResourceAmount(resource);
       player.getResourceSet().variateResource(resource, endPoint);
       BonusMalusHandler.filter(player,player.getResourceSet(),ACTION_TYPE);
    }

   /** private void addFinalPoint(Player player){

        int endPoint = player.getResourceSet().getResourceAmount(resource);
        player.getResourceSet().variateResource(resource,endPoint);
    }*/

    private void getPointsFromRanking(){
        List<Player> ranking = RankingPlayer.getRanking(this.players,this.extraResources);

        for(int i = 0; i < ranking.size(); i++){
            int endPoints = this.endGameMap.getEndGameResourceMap().get(this.extraResources).get(i);
            ranking.get(i).getResourceSet().variateResource(this.resource,endPoints);

        }
    }

}
