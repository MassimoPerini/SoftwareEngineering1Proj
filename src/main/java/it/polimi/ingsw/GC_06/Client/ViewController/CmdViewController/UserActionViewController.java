package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.*;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageBoardActionTower;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageThrowDice;
import it.polimi.ingsw.GC_06.model.Resource.Resource;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by massimo on 19/06/17.
 */
public class UserActionViewController implements ViewPresenterCLI {

    private ClientBoardGame clientBoardGame;
    private Map<String, ClientPlayerBoard> clientPlayerBoard;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private Future future;
    private final CommandView commandView;


    public UserActionViewController(ClientBoardGame clientBoardGame, Map<String, ClientPlayerBoard> clientPlayerBoard, ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.clientBoardGame = clientBoardGame;
        this.clientPlayerBoard = clientPlayerBoard;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.commandView = new CmdView();
    }

    @Override
    public void viewWillAppear() {
        ExecutorService executor = Executors.newCachedThreadPool();
        this.future = executor.submit(this);
    }

    @Override
    public void addText(String txt) {

    }

    @Override
    public void viewWillDisappear() {
        this.future.cancel(true);
    }

    @Override
    public void run() {
        System.out.println("CLIENTBOARDGAME INVOKED");
        while(true) {
            commandView.addLocalizedText("E' il tuo turno. Inserire s per mostrare la board o le board dell'utente, d per tirare i dadi, p per prendere una carta");
            String input = commandView.getString();

            if (input.equals("s")) {
                BoardStatusViewController boardStatusViewController = new BoardStatusViewController(clientBoardGame, clientPlayerBoard, clientNetworkOrchestrator);
                boardStatusViewController.showStatus();        //NON AVERE UN ALTRO THREAD!!!
            }

            if (input.equals("d")) {
                MessageThrowDice messageThrowDice = new MessageThrowDice();
                clientNetworkOrchestrator.send(messageThrowDice);
            }

            if (input.equals("p")) {
                String inp = JOptionPane.showInputDialog("Torre Piano Familiare");
                String[] inpList;
                inpList = inp.split(" ");
                MessageBoardActionTower messageBoardActionTower = new MessageBoardActionTower(inpList[0], Integer.parseInt(inpList[1]), Integer.parseInt(inpList[2]));
                clientNetworkOrchestrator.send(messageBoardActionTower);
            }
        }
    }
}
