package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Board.Component;
import it.polimi.ingsw.GC_06.Board.Council;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class ActionOnCounsilPalace  implements Action {

    private Player player;
    private Council component;
    private FamilyMember familyMember;
    private int index;

    public ActionOnCounsilPalace(Player player,Council component,FamilyMember familyMember, int index) {

        this.familyMember = familyMember;
        this.component = component;
        this.player = player;
        this.index = index;
    }

    @Override
    public void execute() {

        component.addFamilyMember(familyMember, index);
        ArrayList<Effect> effects = component.getEffect(index);
        for(Effect effect : effects){
            effect.execute(player);
        }

    }

    @Override
    public boolean isAllowed() {
        return component.isAllowed(familyMember, index);
    }
}

