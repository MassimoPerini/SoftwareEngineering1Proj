package it.polimi.ingsw.GC_06;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {

        Gson gson = new Gson ();
        Card[] cards = gson.fromJson(new FileReader("datas/test.json"), Card[].class);
        int i=1;
        for (Card card : cards)
        {
            System.out.println("Carta "+i+":\n"+card.toString());
            i++;
        }

    }
}
