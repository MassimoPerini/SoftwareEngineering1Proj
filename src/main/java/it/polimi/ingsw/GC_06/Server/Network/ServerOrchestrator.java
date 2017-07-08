package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 13/06/17.
 */
public class ServerOrchestrator extends Observable implements Observer {

    private final List<Server> servers;
    private final Map<String, Server> serverByString;
    private final Map<Integer, List<Server>> serverByGame;
    private final Map<String, Timer> userTimer;
    private final int timeout;
    private final static String TIMEOUT_KEY = "timeout";
    private final ExecutorService executorService;


    public ServerOrchestrator() {
        this.servers = new ArrayList<>();
        this.serverByString = new HashMap<>();
        this.serverByGame = new HashMap<>();
        this.userTimer = new HashMap<>();
        this.timeout = Integer.parseInt(Setting.getInstance().getProperty(TIMEOUT_KEY));
        executorService = Executors.newCachedThreadPool();
    }

    synchronized public void addServer(Server server)
    {
        servers.add(server);
        server.addObserver(this);
    }

    synchronized public void start()
    {
        for (Server server : servers) {
            server.start();
        }
    }

    synchronized public void stop()
    {
        try {
            for (Server server : servers) {
                server.stop();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    synchronized public void startGame(Map<String, Player> players, int id)
    {
        List<Server> serversContainer = new LinkedList<>();
        for (Server server : servers) {
            if (server.startGame(players, id))
            {
                serversContainer.add(server);
                for (String s : players.keySet()) {
                    if (server.isPlayerManaged(s))
                    {
                        serverByString.put(s, server);
                    }
                }
            }
        }
        this.serverByGame.put(id, serversContainer);
    }

    //Manda e attendi risposta
    synchronized public void send(final String playerId, final Object o)  {
            final Server server = serverByString.get(playerId);
     //       executorService.submit(() -> {
                try {
                    server.sendMessageToPlayer(playerId, o);
                } catch (IOException e) {
                    e.printStackTrace();
                }
      //      });


            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        System.out.println("sono qua");
                        LoginHub.getInstance().manageLogOut(playerId);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            },timeout);

            userTimer.put(playerId, timer);
    }

    synchronized public void send (int game, Object o)  {
        try {
            List<Server> servers = serverByGame.get(game);
            for (Server server : servers) {
                server.sendMessageToGame(game, o);
            }
        }
        catch (IOException e) {
        e.printStackTrace();
        }
    }

    public void remove(String player)
    {
        Server playerServer = serverByString.get(player);
        playerServer.remove(player);
        serverByString.remove(player);
    }

    //Stop timer
    @Override
    synchronized public void update(Observable o, Object arg) {
        //A server called me;
        System.out.println("ServerOrcherstrator---");
            MessageClient messageClient = (MessageClient) arg;

            String player = messageClient.getPlayer();
            Timer timer = userTimer.get(player);
            if (timer != null) {
                timer.cancel();
                userTimer.remove(player);
            }

            setChanged();
            // trigger notification
            notifyObservers(arg);
    }
}
