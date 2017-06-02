package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Board.Council;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giuseppe on 5/20/17.
 */
public class BoardActionOnCounsilMarket extends BoardAction {

    private Player player;
    private Council component;
    private int index;

    public BoardActionOnCounsilMarket(Player player, Council component, FamilyMember familyMember, int index, int value) {

        super(familyMember, value);
        this.component = component;
        this.player = player;
        this.index = index;
        
    }

    @Override
    public void execute() {

        component.addFamilyMember(getFamilyMember(), index);
        List<Effect> effects = component.getEffect(index);
        for(Effect effect : effects){
            effect.execute(player);
        }

    }

    @Override
    public boolean isAllowed() {
        return component.isAllowed(getFamilyMember(), index);
    }
}

