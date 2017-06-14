package it.polimi.ingsw.GC_06.ServerTry;

/**
 * Created by giuseppe on 6/13/17.
 */
public class LoginHub {
    /**
    List<String> hub = new ArrayList<>();
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
        System.out.println(hub.size());
        if (hub.size() == 2 /**Integer.parseInt(Setting.getInstance().getProperty("min_players"))) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    for (String player : hub) {
                        Game.getInstance().addPlayer(player);
                    }
                    Game.getInstance().start();
                }
            }, 1000*60*60);

        }
        if (hub.size() == 4 /**Integer.parseInt(Setting.getInstance().getProperty("max_players"))) {
            int i = 0;

            for (String player : hub) {
                Game.getInstance().addPlayer(player);
            }
            hub = new LinkedList<String>();
            System.out.println("sto creando il gioco numero: " + i);
            Game.getInstance().start();
            i++;
        }
    }*/

}
