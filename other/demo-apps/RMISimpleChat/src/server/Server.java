package server;

import common.ClientInt;
import common.ServerInt;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by massimo on 29/03/17.
 */
public class Server extends UnicastRemoteObject implements ServerInt {

    private Map<Integer, ClientInt> clients=new HashMap<Integer, ClientInt>();
    private Map <String, Integer> secret = new HashMap<String, Integer>();
    private int IDClients;

    public Server(int port) throws RemoteException {
        super(port);
        IDClients=1;
    }
    public Server() throws RemoteException {
        super();
        IDClients=1;
    }

    public static void main (String [] args) throws RemoteException, AlreadyBoundException {
        System.out.println("Constructing server implementation...");
        Server server = new Server();
        System.out.println("Binding server implementation to registry...");
        Registry registry= LocateRegistry.getRegistry();
        registry.bind("chat_server", server);
        System.out.println("Waiting for invocations from clients...");

    }

    @Override
    public synchronized int register(ClientInt client, String username) throws RemoteException {
        System.out.println("Registering "+client.toString());
        if (secret.get(username)==null) {
            int code=IDClients;
            IDClients++;
            clients.put(code, client);
            secret.put(username, code);
            System.out.println("Registered with ID: " + code+" and user: "+username);
            return code;
        }
        else{
            return -1;
        }

    }
    @Override
    public synchronized int remove(String username, int idCode) throws RemoteException{
        System.out.println("Removing "+username);

        if (secret.get(username).equals(idCode)) {
            clients.remove(idCode);
            secret.remove(username);
            System.out.println("Removed");
            return 0;
        }
        else
            return -1;
    }
        @Override
    public synchronized int send(String id, String message, int code) throws RemoteException {

        if (secret.get(id) == null || !secret.get(id).equals(code))
            return -1;

        System.out.println("Sending "+message);
        for (Map.Entry<Integer, ClientInt>mapEntry:clients.entrySet())
        {
            ClientInt client = mapEntry.getValue();
            client.publish(id+": "+message);
        }
        return 0;
    }
}
