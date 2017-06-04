package it.polimi.ingsw.GC_06.model.BonusMalus;

/**
 * Created by giuseppe on 6/2/17.
 */
public class BonusMalusOnAction {


    private String colourTarget;
    private int malusEntity;

    public BonusMalusOnAction(String colourTarget, int malusEntity) {
        this.colourTarget = colourTarget;
        this.malusEntity = malusEntity;
    }

    public void setMalusEntity(int malusEntity) {
        this.malusEntity = malusEntity;
    }

    public String getColourTarget() {
        return colourTarget;
    }



    public void modify(int actionValue){

        actionValue = actionValue+malusEntity;
    }
}
