package it.polimi.ingsw.GC_06.View;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by massimo on 16/05/17.
 */
public class CmdView extends Observable implements Observer {

    private Scanner input;

    public CmdView()
    {
        super();
        input = new Scanner (System.in);
    }

    public void startMessage()
    {
        System.out.print("Benvenuto nel gioco di Lorenzo il magnifico\nSpero ti possa divertire più di quanto mi sono divertito io\n");
        System.out.println("What do you want to do? 1: posiziona familiare, 2: incrementa valore familiare, 3: Gioca carta eroe 4:scarta carta eroe");
        System.out.println("Poichè l'unica opzione implementata è la 1, non mi serve la tua risposta");
        System.out.println("Che familiare vuoi usare? Questi sono i valori[0-3]: NaN");
        int answ = input.nextInt();

        setChanged();
        notifyObservers(answ);

        System.out.println("Dove lo vuoi mettere? Torri[0-3]:");

        answ = input.nextInt();

        setChanged();
        notifyObservers(answ);

        System.out.println("Fine gioco");

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("CmdView: Model changed!\nThis is the object passed:");
        System.out.println(arg);
    }
}
