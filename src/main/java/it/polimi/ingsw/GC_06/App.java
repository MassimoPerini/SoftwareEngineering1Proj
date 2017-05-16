package it.polimi.ingsw.GC_06;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.GC_06.Control.Control;
import it.polimi.ingsw.GC_06.View.CmdView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class App 
{

    //Main controller

    public static void main( String[] args ) {
        Game gioco = Game.getInstance();
        CmdView view = new CmdView();
        Control c = new Control(gioco, view);

        view.startMessage();
/*
        Gson gson = new Gson ();
        Card[] cards = gson.fromJson(new FileReader("datas/test.json"), Card[].class);
        int i=1;
        for (Card card : cards)
        {
            System.out.println("Carta "+i+":\n"+card.toString());
            i++;
        }
        */

    }
}
