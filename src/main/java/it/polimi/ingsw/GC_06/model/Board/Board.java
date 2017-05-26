package it.polimi.ingsw.GC_06.model.Board;

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

    public Board(ArrayList<Tower> towers, ArrayList<Market> markets, ArrayList<ProdHarvZone> prodHarvZones, ArrayList<Council> councils)
    {
        super();
        this.towers = towers;
        this.markets = markets;
        this.prodHarvZones = prodHarvZones;
        this.councils = councils;

    }//TODO da integrare con il caricamento degli effetti da file, in modo da mettere gli effetti sui vari actionplace

    //TODO remove FIX here!
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void addToTower (int tower, int floor, FamilyMember familyMember)
    {
      this.towers.get(tower).addFamilyMember(familyMember, floor);
    }
    public void addToCouncil (FamilyMember familyMember, int index){
       this.councils.get(0).addFamilyMember(familyMember, index);
    }

    public void addToOperations(int prodHarv, FamilyMember familyMember, int index){
      this.prodHarvZones.get(prodHarv).addFamilyMember(familyMember, index);
    }
    public void addToMarket(FamilyMember familyMember, int index)
    {
      this.markets.get(0).addFamilyMember(familyMember, index);
    }

}
