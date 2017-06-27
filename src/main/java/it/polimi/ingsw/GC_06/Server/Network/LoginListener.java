package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Network.ClientRMI;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageLoggedIn;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageUpdateState;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 26/06/17.
 */
public class LoginListener extends UnicastRemoteObject implements RMIListener {

    private RMIServer rmiServer;
    private ExecutorService executorService;

    public LoginListener(RMIServer server) throws RemoteException {
        super();
        this.rmiServer = server;
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public ServerPlayerRMIClient login(String username, ClientRMI clientRMI) throws RemoteException{

        //LoginHandler call
        boolean ok = LoginHub.getInstance().access(username);
        if (!ok)
        {
            executorService.submit(
                    () -> {
                        try {
                            clientRMI.receive(new MessageUpdateState(ClientStateName.LOGIN));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
            );

            return null;
        }

        ServerPlayerRMIHandler serverPlayerRMIClient = new ServerPlayerRMIHandler(username, clientRMI);
        UnicastRemoteObject.exportObject(serverPlayerRMIClient, 0);
        rmiServer.addPlayerRMI(serverPlayerRMIClient, username);
        serverPlayerRMIClient.send(new MessageLoggedIn(username));

        LoginHub.getInstance().loginHandler(username);
        return serverPlayerRMIClient;
    }
}