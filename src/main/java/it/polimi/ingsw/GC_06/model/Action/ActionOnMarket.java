package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Board.Market;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;


import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class ActionOnMarket extends Action {

    private Player player;
    private Market component;
    int index;


    public ActionOnMarket(Player player, FamilyMember familyMember, int index, Market component, int value) {
        super(familyMember, value);
    	this.player = player;
        this.index = index;
        this.component = component;
    }

    @Override
    public void execute() {

        if(!isAllowed()){
            return ;
        }

        else {

            component.addFamilyMember(getFamilyMember(), index);
            ArrayList<Effect> effects = component.getEffect(index);
            //facciamo un ciclo
            for (Effect effect : effects) {
                effect.execute(player);
            }
        }
    }
/**
    public void setPlayer(Player player) {
        this.player = player;
    }*/

    @Override
    public boolean isAllowed() {

        return false;
    }

}
