package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 6/11/17.
 */
public class HeroCardAction implements Action{

    private Player player;
    private HeroCard heroCard;


    @Override
    public void execute() {

        if(isAllowed()){
            ArrayList<Effect> effectList = heroCard.getEffectList();

            /** qui lanciamo gli effetti della carta eroe sulle risorse
             * gli effetti sulle risorse di tipo eroe sono attivate una volta per turno
             */
            for(Effect effect: effectList){
                effect.execute(player);
            }

            /** la carta potrebbe avere dei bonus associati che possono essere di tipo permanente */

            if(heroCard.isPermanent()){
                /** stiamo aggiungendo i bonus ti tipo permanente al set del giocatore*/
                player.getBonusMalusSet().joinSet(heroCard.getBonusMalusSet());
            }
        }

    }

    @Override
    public boolean isAllowed() {
       return heroCard.isActivable(player);
    }
}
