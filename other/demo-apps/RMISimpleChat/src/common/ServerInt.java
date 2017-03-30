package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by massimo on 29/03/17.
 */

////TODO RICORDA THROWS REMOTEEXCEPTION!!!!

public interface ServerInt extends Remote{
    public int register(ClientInt client, String username) throws RemoteException;
    public int remove(String username, int idCode) throws RemoteException;
    public int send(String id, String message, int code) throws RemoteException;
}
