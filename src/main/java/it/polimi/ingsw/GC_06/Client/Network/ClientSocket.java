package it.polimi.ingsw.GC_06.Client.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 11/06/17.
 */
public class ClientSocket extends Client {

    @NotNull private final BufferedReader socketIn;
    @NotNull private final OutputStreamWriter socketOut;
    @NotNull private final ExecutorService pool;
    @NotNull private final Gson readGson;
    @NotNull private final Gson writeGson;
    @NotNull private final Socket socket;


    public ClientSocket(@NotNull Socket socket) throws IOException {
        this.socket = socket;
        this.socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socketOut = new OutputStreamWriter(socket.getOutputStream());
        this.pool = Executors.newFixedThreadPool(1);
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(MessageServer.class, "type"); //.registerSubtype(.class);
        readGson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();

        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(MessageClient.class, "type"); //.registerSubtype(.class);
        writeGson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory).create();
    }

    @Override
    public void run() {

        try {
            while (true)
            {
                String input;

                while ((input = socketIn.readLine()) != null)
                {
                    MessageServer messageServer = readGson.fromJson(input, MessageServer.class);
                    setChanged();
                    notifyObservers(messageServer);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void submit(MessageClient action) {
        pool.submit(() -> {
            //Serializzazione e invio
            try {
                socketOut.write(writeGson.toJson(action));
                socketOut.flush();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void submit(String string) {
        pool.submit(() -> {
            //Serializzazione e invio
            try {
                socketOut.write(string);
                socketOut.flush();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public void finish() throws IOException {
        socketOut.close();
        socketIn.close();
        socket.close();
    }

}
