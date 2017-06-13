package it.polimi.ingsw.GC_06;


import it.polimi.ingsw.GC_06.Network.Client.ClientOrchestrator;
import it.polimi.ingsw.GC_06.Network.Client.ClientSocket;
import it.polimi.ingsw.GC_06.ViewController.CmdViewController.LoginViewController;

import java.io.IOException;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class AppClient {

    //Main controller
    //http://stackoverflow.com/questions/34712885/how-to-load-an-external-properties-file-from-a-maven-java-project
    //http://www.avajava.com/tutorials/lessons/how-do-i-read-a-properties-file-with-a-resource-bundle.html

    public static void main(String[] args) throws IOException {

        System.out.println("Che connessione vuoi?");
        ClientOrchestrator clientOrchestrator = new ClientOrchestrator(new ClientSocket(new Socket("127.0.0.1", 1337)));
        LoginViewController loginViewController = new LoginViewController(clientOrchestrator);

        //TODO Implement FIX: https://futurestud.io/tutorials/how-to-deserialize-a-list-of-polymorphic-objects-with-gson
        //TODO http://stackoverflow.com/questions/19588020/gson-serialize-a-list-of-polymorphic-objects

    }
}
