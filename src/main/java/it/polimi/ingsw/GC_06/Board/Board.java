package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

/**
 * Created by massimo on 13/05/17.
 */
public class Board {
    private final ArrayList<Tower> towers;
    private final ArrayList<Market> markets;
    private final ArrayList<ProdHarvZone> prodHarvZones;
    private final ArrayList<Council> councils;

    //TODO DA RIFARE

    public Board(ArrayList<Tower> towers, ArrayList<Market> markets, ArrayList<ProdHarvZone> prodHarvZones, ArrayList<Council> councils)
    {
        super();
        this.towers = towers;
        this.markets = markets;
        this.prodHarvZones = prodHarvZones;
        this.councils = councils;

    }//TODO da integrare con il caricamento degli effetti da file, in modo da mettere gli effetti sui vari actionplace

    //TODO remove
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void addToTower (int tower, int floor, FamilyMember familyMember)
    {
      //TODO
    }
    public void addToCouncil (){
       //TODO
    }

    public void addToOperations(){
      //TODO
    }
    public void addToMarket()
    {
      //TODOS
    }

}
