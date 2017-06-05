package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;

/**
 * Created by giuseppe on 6/5/17.
 */
public class BonusMalusOnConditions {

    /** annullano il valore di conversione delle carte in punti*/

    private DevelopmentCard developmentCard;
    private int value;

    public BonusMalusOnConditions(DevelopmentCard developmentCard, int value) {
        this.developmentCard = developmentCard;
        this.value = value;
    }
    public  void modify(int size, String developmentCard_ID){

        // non mi piace inserire la logica di controllo qui dentro
        if(developmentCard_ID.equals(developmentCard.getIdColour())){
            value = size;
        }
    }
}
