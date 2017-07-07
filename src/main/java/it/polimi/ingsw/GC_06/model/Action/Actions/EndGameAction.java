package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.*;

/**
 * Created by giuseppe on 6/16/17.
 * la classe si occuppa delle azioni a fine gioco, eseguondo anche i calcoli dei punti legati all'endGame
 */
public class EndGameAction extends Observable implements Action {

    private Map<String,List<Integer>> endGameMap;
    private List<Player> players;
    private Resource resource; /** saranno i punti fede*/
    private it.polimi.ingsw.GC_06.model.Resource.Resource extraResources;
    private Game game;
    private double coefficient;
    private final ActionType ACTION_TYPE = ActionType.END_GAME;

    public EndGameAction(List<Player> players, Resource resource) {
        this.endGameMap = FileLoader.getFileLoader().loadEndGameMap();
        this.players = players;
        this.resource = resource;
        this.coefficient = Integer.parseInt(Setting.getInstance().getProperty("end_game_coefficient"));
    }

    /**
     * il metodo esegue tutti i calcoli sui punti legati all'endGame, compresi filtraggi
     */
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

    }


    @Override
    public boolean isAllowed() {
        return false;
    }

    /**
     * il metodo si occupa della conversione di alcuni tipi di carte in punti
     * @param player il giocatore di cui calcolare i punti
     *               per l'endGame
     */
    public void turnCardsIntoPoints(Player player){

        Set<String> colours = this.endGameMap.keySet();

        for(String colour : colours){
            int numbOfCards = player.getPlayerBoard().getDevelopmentCards(colour).size()-1;

            if(numbOfCards > this.endGameMap.get(colour).size()){
                numbOfCards = this.endGameMap.get(colour).size()-1;
            }

            int endPoint = this.endGameMap.get(colour).get(numbOfCards);
            endPoint = BonusMalusHandler.filter(player,ACTION_TYPE,endPoint,colour);

            ResourceSet resourceSet = new ResourceSet();
            resourceSet.variateResource(resource,endPoint);
            player.variateResource(resourceSet);
        }

    }

    /** dovrebbe fare anche l'aggiunzione dei punti finali */

    /**
     * il metodo si occupa della conversione di risorse in punti
     * @param player il giocatore di cui calcolare i punti
     *               per l'endGame
     */
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
       //BonusMalusHandler.filter(player,player.getResourceSet(),ACTION_TYPE);
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
