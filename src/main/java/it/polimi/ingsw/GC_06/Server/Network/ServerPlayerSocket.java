package it.polimi.ingsw.GC_06.Server.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.Server.Message.Client.*;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.ProdHarvAnswer;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.*;
import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.*;
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
        this.loginHub = loginHub;

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(MessageClient.class, "type")
                .registerSubtype(MessageBoardActionTower.class)
                .registerSubtype(MessageThrowDice.class)
                .registerSubtype(MessageProdHarv.class)
                .registerSubtype(DefaultAnswer.class)
                .registerSubtype(MessageEndTurn.class)
                .registerSubtype(MessageMarketCouncil.class)
                .registerSubtype(ProdHarvAnswer.class)
                .registerSubtype(PlayerChoiceExcommunication.class)
                ;
        readGson=new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory2).create();
        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(MessageServer.class, "type")
                .registerSubtype(MessageAddMemberOnTower.class)
                .registerSubtype(MessageClearBoard.class)
                .registerSubtype(MessageNewCardOnTower.class)
                .registerSubtype(MessageUpdateState.class)
                .registerSubtype(MessageChoosePersonalBonus.class)
                .registerSubtype(MessageChoosePowerUp.class)
                .registerSubtype(MessageAddCard.class)
                .registerSubtype(MessageRemoveCardOnTower.class)
                .registerSubtype(MessageChangePlayer.class)
                .registerSubtype(MessageGameStarted.class)
                .registerSubtype(MessageError.class)
                .registerSubtype(MessagePickAnotherCard.class)
                .registerSubtype(MessageChooseProdHarv.class)
                .registerSubtype(MessageChoosePayment.class)
                .registerSubtype(UpdateValueFamilyMember.class)
                .registerSubtype(MessageLoggedIn.class)
                .registerSubtype(MessageChooseParchment.class)
                .registerSubtype(MessageUpdateResource.class)
                .registerSubtype(MessageRankingPopUp.class)
                ;
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
                        if(!loginHub.access(input)){
                            this.send(new MessageError("Error, please login again"));
                        }
                        else {
                            player = input;
                            final String tmpString = input;
                            this.send(new MessageLoggedIn(input));
                            ExecutorService executorService = Executors.newSingleThreadExecutor();
                            executorService.submit(() -> loginHub.loginHandler(tmpString));
                        }
                }
            }
            System.out.println("ServerPlayerSocket: LOGGED: "+player+"\nNow listening commands...");
            while (true)
            {
                while ((input = socketIn.readLine()) != null) {
                    System.out.println("SERVER: from " + player + " :" + input);
                    MessageClient messageClient = readGson.fromJson(input, MessageClient.class);
                    messageClient.setGame(game);
                    messageClient.setPlayer(player);
                    setChanged();
                    notifyObservers(messageClient);
                }
            }
        }
        catch(Exception e)
        {
            loginHub.manageLogOut(player);
        }
    }

    public void send(MessageServer messageServer){
     //   executor.submit (() -> {
            String res = writeGson.toJson(messageServer, MessageServer.class);
            System.out.println("SERVER: SENDING " + res+" TO "+player+" SOCKET");
            socketOut.println(res);
            socketOut.flush();
            if (socketOut.checkError())
            {
                loginHub.manageLogOut(player);
            }

    //    });
    }

    public void finish() throws IOException {
        socketOut.close();
        socketIn.close();
        socket.close();
    }


}
