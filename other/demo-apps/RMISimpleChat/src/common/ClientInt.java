package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by massimo on 29/03/17.
 */
public interface ClientInt extends Remote {

    public void publish(String input) throws RemoteException;

}
