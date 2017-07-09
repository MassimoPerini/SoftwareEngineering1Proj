package it.polimi.ingsw.GC_06.Client.ViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by massimo on 16/06/17.
 */
public class ViewOrchestratorCLI implements ViewOrchestrator{

    private Map<ClientStateName, ViewPresenterCLI> clientStates;
    private ClientStateName currentState = ClientStateName.START;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private MainClientModel mainClientModel;
    private ViewPresenterCLI currentView;
    private Map<String, ClientPlayerBoard> clientPlayerBoard;
    private ExecutorService executorService;

    public ViewOrchestratorCLI(ClientNetworkOrchestrator clientNetworkOrchestrator, MainClientModel mainClientModel)
    {
        this.clientStates = new HashMap<>();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.mainClientModel = mainClientModel;

        executorService = Executors.newSingleThreadExecutor();

        mainClientModel.addObserver(this);

        this.generateViewCli();
    }

    private void generateViewCli()
    {
        clientStates.put(ClientStateName.LOGIN, new LoginViewPresenterCLI(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.START, new ConnectionTypeViewPresenterCLI(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.LOGGED, new SimpleViewController("LOGGED"));
        clientStates.put(ClientStateName.WAIT_TURN, new BoardStatusViewController(mainClientModel.getClientBoardGame(), mainClientModel.getClientPlayerBoard()));     //TEST, Observer?
        clientStates.put(ClientStateName.MY_TURN, new UserActionViewController(mainClientModel, clientNetworkOrchestrator));
        clientStates.put(ClientStateName.ACTION_FINISHED, new MainActionFinished(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.EXCOMMUNICATION, new ExcommunicationViewController(clientNetworkOrchestrator));//POPUP
        clientStates.put(ClientStateName.END_GAME,new EndGameViewController(mainClientModel.getPersonalStatistics()));
    }

    public void suspend()
    {
        try {
            executorService.shutdownNow();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {}
    }

    public void resume()
    {
        executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> {
            try {
                clientStates.get(currentState).viewWillAppear();
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        });
    }

    @Override
    public void change(ClientStateName state, String property) {

    }

    @Override
    public void execute(String [] args)
    {
        try {
            clientStates.get(currentState).viewWillAppear();
            currentState = ClientStateName.LOGIN;
            currentView = clientStates.get(currentState);
            currentView.viewWillAppear();
        }
        catch (InterruptedException e)
        {
            return;
        }
    }

    //L'era o qualcos'altro è finita
    @Override
    public void update(Observable o, Object arg) {

        ClientStateName state = (ClientStateName) arg;
        try {
            if (clientStates.get(state) != null) {

                executorService.shutdownNow();
                //wait here....
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
                this.currentState = state;
                executorService = Executors.newSingleThreadExecutor();
                Future future = executorService.submit(() -> {
                    try {
                        clientStates.get(currentState).viewWillAppear();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

                /*
                clientStates.get(currentState).viewWillDisappear();
                this.currentState = state;
                clientStates.get(currentState).viewWillAppear();
                */
            }
        }
        catch (InterruptedException e){}

    }
}
