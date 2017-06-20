package it.polimi.ingsw.GC_06.Client.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageBoardActionTower;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageEndTurn;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageThrowDice;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.*;
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
    @NotNull private final PrintWriter socketOut;
    @NotNull private final ExecutorService pool;
    @NotNull private final Gson readGson;
    @NotNull private final Gson writeGson;
    @NotNull private final Socket socket;


    public ClientSocket(@NotNull Socket socket) throws IOException {
        this.socket = socket;
        this.socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     //   this.socketIn = new ObjectInputStream(socket.getInputStream());
        this.socketOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.pool = Executors.newCachedThreadPool();
        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(MessageServer.class, "type")
                .registerSubtype(MessageAddCard.class)
                .registerSubtype(MessageAddMemberOnTower.class)
                .registerSubtype(MessageClearBoard.class)
                .registerSubtype(MessageNewCards.class)
                .registerSubtype(MessageUpdateView.class)
                .registerSubtype(MessageRemoveCard.class)
                .registerSubtype(MessageUpdateResource.class)
                .registerSubtype(MessageChangePlayer.class)
                .registerSubtype(MessageFamilyMember.class)
                .registerSubtype(MessageLoggedIn.class)
                .registerSubtype(MessageGameStarted.class);
        readGson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory1).create();

        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(MessageClient.class, "type")
                .registerSubtype(MessageBoardActionTower.class)
                .registerSubtype(MessageThrowDice.class)
                .registerSubtype(MessageEndTurn.class)
                ; //.registerSubtype(.class);
        writeGson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
    }

    @Override
    public void run() {

            while (true) {
                try {
       /*         String input = (String)socketIn.readUnshared();
                System.out.println("CLIENT RECEIVED: "+input);
                MessageServer messageServer = readGson.fromJson(input, MessageServer.class);
                setChanged();
                notifyObservers(messageServer);
*/
                    String input;
                    while ((input = socketIn.readLine()) != null) {
                        System.out.println("CLIENT RECEIVED: " + input);
                        MessageServer messageServer = readGson.fromJson(input, MessageServer.class);
                        setChanged();
                        notifyObservers(messageServer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    @Override
    public void submit(MessageClient action) {
        pool.submit(() -> {
            //Serializzazione e invio
            try {
                String serialized = writeGson.toJson(action, MessageClient.class);
                serialized+="\n";
                socketOut.write(serialized);
                socketOut.flush();
                System.out.println("CLIENT: SENT "+serialized);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void submit(String string) {
        pool.execute(() -> {
            //Serializzazione e invio
            try {
                socketOut.println(string);
                socketOut.flush();
                System.out.println("Client: SENT "+string);
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
