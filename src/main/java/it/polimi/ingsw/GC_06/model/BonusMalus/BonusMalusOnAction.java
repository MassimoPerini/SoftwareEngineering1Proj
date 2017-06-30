package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by giuseppe on 6/2/17.
 */
public class BonusMalusOnAction {

    /** serve se viene fatta una azione torre*/
    private String colourTarget;
    private List<String> familyMemberColours; /** i colori dei familiari su cui questo bonus impatta */
    /** actionType serve ad identificare l'azione sulla quale il bonus o il malus agirà*/
    private ActionType actionType;
    private boolean permanent;
    private int value;

    public BonusMalusOnAction(String colourTarget, List<String> familyMemberColours, ActionType actionType, boolean permanent,int value) {
        this.colourTarget = colourTarget;
        this.familyMemberColours = familyMemberColours;
        this.actionType = actionType;
        this.permanent = permanent;
        this.value = value;
    }

    /** con questo metodo riduciamo il valore dell'azione, cambiando il punteggio del familiare*/
    public void modify(FamilyMember familyMember) {
        int newValue = familyMember.getValue() + value;
        if(newValue < 0 ){
            newValue = 0;
        }
        familyMember.setValue(newValue);
    }

    public void setBonusMalusEntity(int bonusMalusEntity) {
        this.value = bonusMalusEntity;
    }

    public boolean isAllowed(FamilyMember familyMember,ActionType actionType,String towerColour){

        if(checkTowerAction(actionType,familyMember,towerColour) ||checkGeneralAction(actionType)){
            return true;
        }
        else
            return false;

    }

    private boolean checkTowerAction(ActionType actionType,FamilyMember familyMember ,String towerColour){

        if(this.actionType.equals(actionType) && familyMemberColours.contains(familyMember.getDiceColor())
                && towerColour.equals(colourTarget)){
            return true;
        }
        else
            return false;

    }

    private boolean checkGeneralAction(ActionType actionType){

        if((this.actionType.equals(ActionType.GENERAL) || this.actionType.equals(actionType)) && this.colourTarget == null){
            return true;
        }
        else
            return false;
    }

    public boolean iAllowed(FamilyMember familyMember, ActionType actionType){
        if(checkFamilyMember(familyMember) && checkCompatibility(actionType)){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean checkFamilyMember(FamilyMember familyMember){
        if(familyMember.getDiceColor() == null){
            return true;
        }
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

    public ActionType getActionType() {
        return actionType;
    }
}
