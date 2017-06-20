package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.util.LinkedList;

/**
 * Created by giuseppe on 6/2/17.
 */
public class BonusMalusOnAction {

    /** serve se viene fatta una azione torre*/
    private String colourTarget;
    private LinkedList<String> familyMemberColours; /** i colori dei familiari su cui questo bonus impatta */
    private int bonusMalusEntity;
    /** actionType serve ad identificare l'azione sulla quale il bonus o il malus agirà*/
    private ActionType actionType;
    private boolean permanent;
    private boolean ON;

    public BonusMalusOnAction(String colourTarget, int malusEntity,ActionType actionType,LinkedList<String> familyMemberColours) {
        this.colourTarget = colourTarget;
        this.bonusMalusEntity = malusEntity;
        this.actionType = actionType;
        this.familyMemberColours = familyMemberColours;
    }

        /** con questo metodo riduciamo il valore dell'azione, cambiando il punteggio del familiare*/
    public void modify(FamilyMember familyMember) {
        int newValue = familyMember.getValue() + bonusMalusEntity;
        familyMember.setValue(newValue);
    }

    public boolean isAllowed(FamilyMember familyMember,ActionType actionType){
        if(checkFamilyMember(familyMember) && checkCompatibility(actionType)){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean checkFamilyMember(FamilyMember familyMember){

        return this.familyMemberColours.contains(familyMember.getDiceColor());

    }

    private boolean  checkCompatibility(ActionType actionType){
        if(this.actionType.equals(ActionType.GENERAL)){
            return true;
        }
        else{
            if(!this.actionType.equals(actionType)){
                return false;
            }
            return  true;
        }
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setColourTarget(String colourTarget) {
        this.colourTarget = colourTarget;
    }

    public String getColourTarget() {
        return colourTarget;
    }

    public boolean isON() {
        return ON;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setON(boolean ON) {
        this.ON = ON;
    }
}
