package it.polimi.ingsw.GC_06.Client.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.Server.Message.Client.*;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.MessageComeBack;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.PlayerHeroCardChoices;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.ProdHarvAnswer;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.*;
import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 11/06/17.
 * This class manages communications of the client via Socket
 */
public class ClientSocket extends Client {

    private final BufferedReader socketIn;
    private final PrintWriter socketOut;
    private final ExecutorService pool;
    private final Gson readGson;
    private final Gson writeGson;
    private final Socket socket;


    /**
     *
     * @param socket socket through which communications are passed
     * @throws IOException
     */
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
                .registerSubtype(MessageNewCardOnTower.class)
                .registerSubtype(MessageUpdateState.class)
                .registerSubtype(MessageChoosePowerUp.class)
                .registerSubtype(MessageRemoveCardOnTower.class)
                .registerSubtype(MessageChangePlayer.class)
                .registerSubtype(MessageChoosePersonalBonus.class)
                .registerSubtype(MessageGameStarted.class)
                .registerSubtype(MessagePickAnotherCard.class)
                .registerSubtype(MessageChooseProdHarv.class)
                .registerSubtype(MessageChoosePayment.class)
                .registerSubtype(UpdateValueFamilyMember.class)
                .registerSubtype(MessageLoggedIn.class)
                .registerSubtype(MessageChooseParchment.class)
                .registerSubtype(MessageUpdateResource.class)
                .registerSubtype(MessageRankingPopUp.class)
                .registerSubtype(MessageError.class)
             //   .registerSubtype(MessageAddMemberOnProdHarv.class)
                .registerSubtype(MessageAddMemberOnMarket.class)
                .registerSubtype(MessageAddMemberOnCouncil.class)
                .registerSubtype(HeroCardUploadMessageServer.class)
                .registerSubtype(MessageActivatePopup.class)
                .registerSubtype(MessagePlayerDisconnected.class)
                .registerSubtype(MessageAddMemberOnProduction.class)
                .registerSubtype(MessageAddMemberOnHarvest.class)
                .registerSubtype(MessageUserExcommunication.class)
                ;
        readGson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory1).create();

        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(MessageClient.class, "type")
                .registerSubtype(MessageBoardActionTower.class)
                .registerSubtype(MessageProdHarv.class)
                .registerSubtype(DefaultAnswer.class)
                .registerSubtype(MessageEndTurn.class)
                .registerSubtype(MessageMarketCouncil.class)
                .registerSubtype(ProdHarvAnswer.class)
                .registerSubtype(PlayerChoiceExcommunication.class)
                .registerSubtype(PlayerHeroCardChoices.class)
                .registerSubtype(DiscardHeroCardMessage.class)
                .registerSubtype(MessageComeBack.class)
                ;
        writeGson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
    }

    /**
     * this method also receives all the comunications from the server
     */
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

    /**
     * submits an action to the server
     * @param action action to be passed to the server
     */
    @Override
    public synchronized void submit(MessageClient action) {
    //    pool.submit(() -> {
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
    //    });
    }

    /**
     * submits a string to the server
     * @param string string to be passed to the server
     */
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

    /**
     * shuts down and close the socket
     * @throws IOException
     */
    public void finish() throws IOException {
        socketOut.close();
        socketIn.close();
        socket.close();
    }

}
