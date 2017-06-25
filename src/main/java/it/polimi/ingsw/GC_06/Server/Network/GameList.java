package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by giuseppe on 6/14/17.
 */
public class GameList {

    private static GameList instance = new GameList();
    private Map<Game,List<String>> gameMap = new HashMap<>(); /** game and associated players */
    private Map<Game, Blocking> gameBlocking=new HashMap<>();
    private ServerOrchestrator serverOrchestrator;

    private GameList(){}

    public synchronized static GameList getInstance(){
        return instance;
    }

    //Non serve a nulla, in game ci sono già i giocatori associati, rivedi la struttura!
    public int getGame(String username){
        int gameId = -1;
        for(Game game : gameMap.keySet()){
            if(gameMap.get(game).contains(username)){
                gameId = game.getId();
            }
        }
        return gameId;
    }

    public synchronized void setServerOrchestrator(ServerOrchestrator serverOrchestrator) {
        this.serverOrchestrator = serverOrchestrator;
    }

    public synchronized void setCurrentBlocking(Game game, Blocking action, MessageServer messageServer)
    {
        setCurrentBlocking(game, action);
        serverOrchestrator.send(game.getCurrentPlayer().getPLAYER_ID(), messageServer);

    }

    public synchronized void setCurrentBlocking(Game game, Blocking action)
    {
        if (gameBlocking.get(game)==null)
            gameBlocking.put(game, action);
        else
            gameBlocking.replace(game, action);
    }

    public synchronized void unlock(Game game, Object object)
    {
        gameBlocking.get(game).setOptionalParams(object);
    }

    /**
     * @param gameId
     * @return Game
     * It finds the game where a player was inscribed
     */
    public Game getGameId(int gameId){
        Set<Game> games = gameMap.keySet();

        for(Game game : games){
            if(game.getId() == gameId){
                return game;
            }
        }
        return null;
    }

    public void add(Game game, List<String> usernames){
        gameMap.put(game,usernames);
    }

    /**
     * it should eliminate the player from this list
     * @param gameID
     */
    public void remove(int gameID,String username){
        gameMap.get(gameID).remove(username);
    }

}
