package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

/**
 * Created by massimo on 13/05/17.
 */
public class Board {
    private ArrayList<Tower> towers = new ArrayList<>();
 //   private ArrayList<MixedActionPlace> operations = new ArrayList<>();
    private MultipleActionPlace council;


    private ArrayList<Tower> towers = new ArrayList<>();
    private ArrayList<Tower> towers = new ArrayList<>();


    //TODO DA RIFARE

    public Board()
    {
        for (int i=0;i<4;i++)
        {
            towers.add(new Tower());
        }
    //    operations.add(new MixedActionPlace(1,1));
    //    operations.add(new MixedActionPlace(1,1));
        council = new MultipleActionPlace(1);
        market.add(new SingleActionPlace(1));
        market.add(new SingleActionPlace(1));
        market.add(new SingleActionPlace(1));
        market.add(new SingleActionPlace(1));
        market.add(new SingleActionPlace(1));
    }

    public void addToTower (int tower, int floor, FamilyMember familyMember)
    {

    }
    public void addToCouncil (){

    }

    public void addToOperations(){

    }
    public void addToMarket()
    {

    }

}
