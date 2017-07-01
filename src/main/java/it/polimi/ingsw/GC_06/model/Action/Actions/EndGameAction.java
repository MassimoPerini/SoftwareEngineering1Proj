package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

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
    private double coefficient;
    private final ActionType ACTION_TYPE = ActionType.END_GAME;

    public EndGameAction(EndGameMap endGameMap, List<Player> players, Resource resource,double coefficient) {
        this.endGameMap = endGameMap;
        this.players = players;
        this.resource = resource;
        this.coefficient = coefficient;
    }

    @Override
    public void execute() {

        /** aggiungiamo i punti provenienti da endGameMap per tutti i giocatori */

        //prima di fare il calcolo del punteggio vittoria dobbiamo sottrarre vari punteggi

        for(Player player : players){
            // qui eliminiamo per tutti i giocatori i punti dovuti al proprio resource set
            BonusMalusHandler.filter(player,player.getResourceSet(),ACTION_TYPE);
            this.turnCardsIntoPoints(player);
            this.turnResourceIntoPoint(player);
            //this.addFinalPoint(player);
        }

      //  this.getPointsFromRanking();

        /** vorrei anche che questa classe mi dicesse chi ha vinto */

    }



    @Override
    public boolean isAllowed() {
        return false;
    }

    public void turnCardsIntoPoints(Player player){

        Set<String> colours = endGameMap.getEndGameMap().keySet();

        for(String colour : colours){
            int numbOfCards = player.getPlayerBoard().getDevelopmentCards(colour).size()-1;

            if(numbOfCards > endGameMap.getEndGameMap().get(colour).size()){
                numbOfCards = endGameMap.getEndGameMap().get(colour).size()-1;
            }

            int endPoint = endGameMap.getEndGameMap().get(colour).get(numbOfCards);
            endPoint = BonusMalusHandler.filter(player,ACTION_TYPE,endPoint,colour);

            ResourceSet resourceSet = new ResourceSet();
            resourceSet.variateResource(resource,endPoint);
            player.variateResource(resourceSet);
        }

    }

    /** dovrebbe fare anche l'aggiunzione dei punti finali */

    public void turnResourceIntoPoint(Player player){

        // qui usiamo i bonus e i malus che mi levano i punti per determinate risorse

        BonusMalusHandler.filter(player,player.getResourceSet(),ACTION_TYPE);


       int endPoint =  player.getResourceSet().totalResourceQuantity() - player.getResourceSet().getResourceAmount(resource);

       //endPoint deve essere filtrato rispetto ad un coefficiente
        endPoint = (int) (endPoint*coefficient);
        ResourceSet resourceSet = new ResourceSet();
        // sono inclusi anche i punti accumulati durante la partita
        resourceSet.variateResource(resource,endPoint);
        player.variateResource(resourceSet);

       // qui dovremmo essere riusciti a modificare l'attribuzione dei punti al player
       BonusMalusHandler.filter(player,player.getResourceSet(),ACTION_TYPE);
    }



    public void addFinalPoint(Player player) {

        player.variateResource(player.getAddAtTheEnd());
    }
/*
    private void getPointsFromRanking(){
        List<Player> ranking = RankingPlayer.getRanking(this.players,this.extraResources);

        for(int i = 0; i < ranking.size(); i++){
            int endPoints = this.endGameMap.getEndGameResourceMap().get(this.extraResources).get(i);
            ranking.get(i).getResourceSet().variateResource(this.resource,endPoints);

        }
    }*/

}
