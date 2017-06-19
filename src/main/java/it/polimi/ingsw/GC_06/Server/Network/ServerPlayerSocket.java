package it.polimi.ingsw.GC_06.Server.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageBoardActionTower;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageThrowDice;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 12/06/17.
 */
public class ServerPlayerSocket extends Observable implements Runnable {

    @NotNull private final Socket socket;
    @NotNull private final BufferedReader socketIn;
    @NotNull private final PrintWriter socketOut;
    @NotNull private final ExecutorService executor;
    @NotNull private final Gson readGson;
    @NotNull private final Gson writeGson;
    @NotNull private final LoginHub loginHub;

    private String player;
    private int game;

    public String getPlayer() {
        return player;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public ServerPlayerSocket(@NotNull Socket socket, LoginHub loginHub) throws IOException {
        this.socket = socket;
        this.socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socketOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.executor = Executors.newFixedThreadPool(1);
        this.loginHub = loginHub;

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(MessageClient.class, "type")
                .registerSubtype(MessageBoardActionTower.class)
                .registerSubtype(MessageThrowDice.class)
                ;
        readGson=new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory2).create();
        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(MessageServer.class, "type")
                .registerSubtype(MessageAddCard.class)
                .registerSubtype(MessageAddMemberOnTower.class)
                .registerSubtype(MessageClearBoard.class)
                .registerSubtype(MessageNewCards.class)
                .registerSubtype(MessageUpdateView.class)
                .registerSubtype(MessageRemoveCard.class)
                .registerSubtype(MessageChangePlayer.class)
                .registerSubtype(MessageGameStarted.class)
                .registerSubtype(MessageFamilyMember.class)
                .registerSubtype(MessageUpdateResource.class);
        writeGson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();  //setPrettyPrinting
    }

    @Override
    public void run() {

        try{
            String input;
            //Expected login here
            System.out.println("ServerPlayerSocket: waiting login...");
            while ( player == null)
            {
                input = socketIn.readLine();
                if(input != null) {
                    System.out.println("SERVER: RECEIVED "+input);
                    try {
                        if(!loginHub.access(input)){
                            this.send(new MessageUpdateView(ClientStateName.LOGIN, "Error, please login again"));
                        }
                        else {
                            player = input;
                            this.send(new MessageUpdateView(null, "Login OK, please wait"));
                            loginHub.loginHandler(input);
                        }
                    } catch (Exception e) {
                        System.out.println("Error login!");
                    }
                }
            }

            System.out.println("ServerPlayerSocket: LOGGED: "+player+"\nNow listening commands...");
            while (true)
            {
                while ((input = socketIn.readLine()) != null)
                {
                    System.out.println("SERVER: from "+player+" :" +input);
                    MessageClient messageClient = readGson.fromJson(input, MessageClient.class);
                    messageClient.setGame(game);
                    messageClient.setPlayer(player);
                    setChanged();
                    notifyObservers(messageClient);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Ti sei sloggato!");
        }
    }

    public void send(MessageServer messageServer) throws IOException {
     //   executor.submit (() -> {
                String res = writeGson.toJson(messageServer, MessageServer.class);
                System.out.println("SERVER: SENDING "+res);
                socketOut.println(res);
                socketOut.flush();
    //    });
    }

    public void finish() throws IOException {
        socketOut.close();
        socketIn.close();
        socket.close();
    }


}
