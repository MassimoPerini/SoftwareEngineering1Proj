package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

/**
 * Created by massimo on 13/05/17.
 */
public class Board {
    private ArrayList<Tower> towers = new ArrayList<>();
    private Council council;
    private Market market;
    private ProdHarvZone production;
    private ProdHarvZone harvest;



    //TODO DA RIFARE

    public Board()
    {
        for (int i=0;i<4;i++)
        {
            towers.add(new Tower(null));
        }
        
        this.council = new Council(null);
        this.market = new Market(null);
        this.production = new ProdHarvZone();
        this.harvest = new ProdHarvZone();
    }//TODO da integrare con il caricamento degli effetti da file, in modo da mettere gli effetti sui vari actionplace

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
