package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Board.Board;
import it.polimi.ingsw.GC_06.Board.Component;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class PutFamilyMember implements Action {

    private Player player;
    private FamilyMember familyMember;
    private Component component;
    int index;


    public PutFamilyMember(Player player, FamilyMember familyMember, int index,Component component) {
        this.player = player;
        this.familyMember = familyMember;
        this.index = index;
        this.component = component;
    }

    @Override
    public void execute() {

        ArrayList<Effect> effects = component.addFamilyMember(familyMember);

        //facciamo un ciclo
        for(Effect effect : effects){
            //TODO definire meglio l'interfaccia
            effect.execute();
        }
    }


    @Override
    public boolean isAllowed() {
        return component.isAllowed(familyMember, index) ;
    }

    public

}
