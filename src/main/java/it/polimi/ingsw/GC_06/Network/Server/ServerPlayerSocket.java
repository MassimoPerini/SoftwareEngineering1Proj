package it.polimi.ingsw.GC_06.Network.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.Network.LoginHub;
import it.polimi.ingsw.GC_06.Network.Message.Client.Login;
import it.polimi.ingsw.GC_06.Network.Message.MessageClient;
import it.polimi.ingsw.GC_06.Network.Message.MessageServer;
import it.polimi.ingsw.GC_06.Network.Message.Server.ChangeStatus;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
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

    @NotNull
    private final Socket socket;
    @NotNull private final BufferedReader socketIn;
    @NotNull private final ObjectOutputStream socketOut;
    @NotNull private ExecutorService executor;
    @NotNull private final Gson readGson;

    private String player;
    private Game game;


    public ServerPlayerSocket(@NotNull Socket socket) throws IOException {
        this.socket = socket;
        this.socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socketOut = new ObjectOutputStream(socket.getOutputStream());
        this.executor = Executors.newFixedThreadPool(1);

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(MessageClient.class, "type").registerSubtype(Login.class);
        readGson=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();

        this.send(new ChangeStatus());
    }

    @Override
    public void run() {

        try{
            String input;
            //Expected login here
            while (((input = socketIn.readLine()) != null) && player == null)
            {
                try {
                    LoginHub.getInstance().addUser(input);
                    player = input;
                }catch (Exception e)
                {
                    send(new ChangeStatus());
                }
            }


            while (true)
            {
                while ((input = socketIn.readLine()) != null)
                {
                    MessageClient messageClient = readGson.fromJson(input, MessageClient.class);
                }
            }
        }
        catch(IOException e)
        {

        }
    }

    public void send(MessageServer messageServer) throws IOException {
        executor.submit (() -> {
            try {
                RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(MessageServer.class, "type"); //.registerSubtype(.class);
                Gson gson=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();
                String res = gson.toJson(messageServer);
                socketOut.writeChars(res);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
