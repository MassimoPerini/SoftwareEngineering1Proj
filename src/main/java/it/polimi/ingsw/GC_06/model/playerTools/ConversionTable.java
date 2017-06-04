package it.polimi.ingsw.GC_06.model.playerTools;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by giuseppe on 5/31/17.
 */

public class ConversionTable {

    private HashMap<String, ArrayList<Integer>> conversionTable;
    /** hasmap contiene
     * 1) Una stringa che è il colore della carta
     * 2) un array di interi : che sono i punti bonus conferiti a ciascun giocatore alla fine del gioco
     */


    public ConversionTable(HashMap<String, ArrayList<Integer>> conversionTable) {
        this.conversionTable = conversionTable;
    }

    public HashMap<String, ArrayList<Integer>> getConversionTable() {
        return conversionTable;
    }
}
