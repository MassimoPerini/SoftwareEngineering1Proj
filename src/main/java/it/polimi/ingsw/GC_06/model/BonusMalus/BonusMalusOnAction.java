package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

/**
 * Created by giuseppe on 6/2/17.
 */
public class BonusMalusOnAction {


    private String colourTarget;
    private int malusEntity;
    /** actionType serve ad identificare l'azione sulla quale il bonus o il malus agirà*/
    private ActionType actionType;

    public BonusMalusOnAction(String colourTarget, int malusEntity,ActionType actionType) {
        this.colourTarget = colourTarget;
        this.malusEntity = malusEntity;
        this.actionType = actionType;
    }

        /** con questo metodo riduciamo il valore dell'azione, cambiando il punteggio del familiare*/
    public void modify(FamilyMember familyMember) {
        int newValue = familyMember.getValue() - malusEntity;
        familyMember.setValue(newValue);
    }

    public ActionType getActionType() {
        return actionType;
    }
}
