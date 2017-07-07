package it.polimi.ingsw.GC_06.model.Card;

/**
 * Created by massimo on 12/05/17.
 * rappresenta una generica carta del gioco
 */
public class Card {
    private String path;
 //   private ArrayList<Requirement> requirements;       //requisiti per l'utilizzo


    public Card (String path)
    {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
