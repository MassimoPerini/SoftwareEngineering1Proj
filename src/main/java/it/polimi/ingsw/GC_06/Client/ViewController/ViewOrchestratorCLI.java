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
 * This class is responsible for managing the general behaviour of the view (CLI version)
 */
public class ViewOrchestratorCLI implements ViewOrchestrator{

    private Map<ClientStateName, ViewPresenterCLI> clientStates;
    private ClientStateName currentState = ClientStateName.START;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private MainClientModel mainClientModel;
    private ViewPresenterCLI currentView;
    private Map<String, ClientPlayerBoard> clientPlayerBoard;
    private ExecutorService executorService;
    private boolean suspended = false;

    public ViewOrchestratorCLI(ClientNetworkOrchestrator clientNetworkOrchestrator, MainClientModel mainClientModel)
    {
        this.clientStates = new HashMap<>();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.mainClientModel = mainClientModel;

        executorService = Executors.newSingleThreadExecutor();

        mainClientModel.addObserver(this);

        this.generateViewCli();
    }

    /**
     * generates a map of the possible states of the client and respective views
     */
    private void generateViewCli()
    {
        clientStates.put(ClientStateName.LOGIN, new LoginViewPresenterCLI(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.START, new ConnectionTypeViewPresenterCLI(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.LOGGED, new SimpleViewController("LOGGED"));
        clientStates.put(ClientStateName.WAIT_TURN, new BoardStatusViewController(mainClientModel.getClientBoardGame(), mainClientModel.getClientPlayerBoard()));     //TEST, Observer?
        clientStates.put(ClientStateName.MY_TURN, new UserActionViewController(mainClientModel, clientNetworkOrchestrator));
        clientStates.put(ClientStateName.ACTION_FINISHED, new MainActionFinished(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.END_GAME,new EndGameViewController(mainClientModel.getPersonalStatistics()));
    }

    public synchronized void suspend()
    {
        try {
            suspended = true;
            executorService.shutdownNow();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
            System.out.println("SOSPESO!!!!!");
        }
        catch (InterruptedException e)
        {}
    }

    public synchronized void resume()
    {
        System.out.println("RESUMING...");
        suspended = false;
        executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> {
            try {
                clientStates.get(currentState).viewWillAppear();
            } catch (InterruptedException e) {
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
    public synchronized void update(Observable o, Object arg) {

        ClientStateName state = (ClientStateName) arg;
        if (currentState==state)
        {
            return;
        }
        try {
            executorService.shutdownNow();
            //wait here....
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
            this.currentState = state;
            if (clientStates.get(state) != null && !suspended) {
                System.out.println("EXECUTING UPDATE...");
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
