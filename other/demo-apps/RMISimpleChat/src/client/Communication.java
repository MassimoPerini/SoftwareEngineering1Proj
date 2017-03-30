package client;

import common.ClientInt;
import common.ServerInt;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

/**
 * Created by massimo on 30/03/17.
 */
public class Communication extends UnicastRemoteObject implements ClientInt {
    private final ServerInt server;
    private final LinkedList <ViewControllerInt> views= new LinkedList<>();
    private String username;
    private int secret;

    public Communication(String username) throws RemoteException, NotBoundException {
        super();
        Registry registry= LocateRegistry.getRegistry();
        System.out.print("RMI registry bindings: ");
        String remoteObjectName = "chat_server";
        server = (ServerInt) registry.lookup(remoteObjectName);
        this.username=username;
        this.register();
    }

    public void addView (ViewControllerInt v)
    {
        views.add(v);
    }

    public void sendMessage (String message) throws RemoteException{


        try {

            server.send(username, message, secret);

        }catch (RemoteException e){e.printStackTrace();}
      /*  Thread t = new Thread() {
            public void run() {
                System.out.println("Sending message");

            }
        };
        t.start();*/
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean register (String user) throws RemoteException
    {
        this.setUsername(user);
        return register();
    }

    public boolean register () throws RemoteException
    {
        secret = server.register(this, username);
        if (secret>0) {
            System.out.println("OK");
            return true;
        }
        else {
            System.out.println("Errore");
            return false;       //TODO username presente, dare errore
        }
    }

    public boolean remove () throws RemoteException
    {
        int res = server.remove(username, secret);
        if (res==0)
        {
            return true;
        }
        return false;
    }

    public void publish(String input) throws RemoteException
    {
        for (ViewControllerInt v: views)
        {
            v.showText(input);
        }
    }


}
