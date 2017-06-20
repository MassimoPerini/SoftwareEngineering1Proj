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
    /** actionType serve ad identificare l'azione sulla quale il bonus o il malus agirà*/
    private ActionType actionType;
    private boolean permanent;
    private int value;

    public BonusMalusOnAction(String colourTarget, LinkedList<String> familyMemberColours, ActionType actionType, boolean permanent,int value) {
        this.colourTarget = colourTarget;
        this.familyMemberColours = familyMemberColours;
        this.actionType = actionType;
        this.permanent = permanent;
        this.value = value;
    }

    /** con questo metodo riduciamo il valore dell'azione, cambiando il punteggio del familiare*/
    public void modify(FamilyMember familyMember) {
        int newValue = familyMember.getValue() + value;
        familyMember.setValue(newValue);
    }

    public void setBonusMalusEntity(int bonusMalusEntity) {
        this.value = bonusMalusEntity;
    }

    public boolean isAllowed(FamilyMember familyMember, ActionType actionType){
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


    public String getColourTarget() {
        return colourTarget;
    }


    public boolean isPermanent() {
        return permanent;
    }

}
