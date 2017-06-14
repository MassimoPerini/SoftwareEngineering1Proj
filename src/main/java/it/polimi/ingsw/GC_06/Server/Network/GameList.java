package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.*;

/**
 * Created by giuseppe on 6/14/17.
 */
public class GameList {

    private static GameList instance = new GameList();
    private Map<Game,List<String>> gameMap = new HashMap<>();

    private GameList(){}

    public static GameList getInstance(){
        return instance;
    }

    public int getGame(String username){
        int gameId = -1;
        for(Game game : gameMap.keySet()){
            if(gameMap.get(game).contains(username)){
                gameId = game.getId();
            }
        }
        return gameId;
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

}
