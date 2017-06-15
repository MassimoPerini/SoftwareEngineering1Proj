package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by giuseppe on 6/14/17.
 */
public class Trash {

    Map<String,Integer> playerTrash = new HashMap<>();
    /** private static Trash instance = new Trash();*/

    public Trash() {
    }

    /**public static Trash getInstance(){
     return instance
     }
     */
    public boolean search(String username){
        return playerTrash.containsKey(username);
    }

    public int getGameID(String username){
        return playerTrash.get(username);
    }

    public void restore(){

    }

    public void add(String username, int gameID){
        playerTrash.put(username,gameID);
    }


}
