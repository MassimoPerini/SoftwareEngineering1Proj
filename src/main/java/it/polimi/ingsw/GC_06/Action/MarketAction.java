package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Board.Component;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.playerTools.Player;


import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class MarketAction implements Action {

    private Player player;
    private FamilyMember familyMember;
    private Component component;
    int index;


    public MarketAction(Player player, FamilyMember familyMember, int index, Component component) {
        this.player = player;
        this.familyMember = familyMember;
        this.index = index;
        this.component = component;
    }

    @Override
    public void execute() {

        ArrayList<Effect> effects = component.addFamilyMember(familyMember, index);
        //facciamo un ciclo
        for(Effect effect : effects){
            //TODO definire meglio l'interfaccia
            effect.execute(player);
        }
    }
/**
    public void setPlayer(Player player) {
        this.player = player;
    }*/

    @Override
    public boolean isAllowed() {
        return component.isAllowed(familyMember, index) ;
    }

}
