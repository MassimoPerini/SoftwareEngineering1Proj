package it.polimi.ingsw.GC_06.Network.Server1;

import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.*;

/**
 * Created by giuseppe on 6/13/17.
 */
public class LoginHub {

    ArrayList<String> hub = new ArrayList<>();
    private static LoginHub instance = null;

    private LoginHub() {

    }

    public static LoginHub getInstance() {
        if (instance == null) {
            instance = new LoginHub();
        }
        return instance;
    }

    public void fillHub(String username) {
        hub.add(username);
        if (hub.size() == Integer.parseInt(Setting.getInstance().getProperty("min_players"))) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    for (String player : hub) {
                        Game.getInstance().addPlayer(player);
                    }
                    Game.getInstance().start();
                }
            }, 1000);

        }
        if (hub.size() == Integer.parseInt(Setting.getInstance().getProperty("max_players"))) {

            /** game non è un sigleton*/
            for (String player : hub) {
                Game.getInstance().addPlayer(player);
            }
            hub.clear();
            Game.getInstance().start();
        }
    }

}
