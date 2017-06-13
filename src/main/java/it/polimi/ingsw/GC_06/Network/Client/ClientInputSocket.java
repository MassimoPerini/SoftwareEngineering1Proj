package it.polimi.ingsw.GC_06.Network.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by massimo on 11/06/17.
 */
public class ClientInputSocket extends Observable implements Runnable {

    private ObjectInputStream objectInputStream;

    public ClientInputSocket(ObjectInputStream objectInputStream)
    {
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void run() {

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(objectInputStream));

            String outputFromServer="";
            while((outputFromServer=in.readLine())!= null){
                System.out.println("FROM SERVER: "+outputFromServer);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



}
