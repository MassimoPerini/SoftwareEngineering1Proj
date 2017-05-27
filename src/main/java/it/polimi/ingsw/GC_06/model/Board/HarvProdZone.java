package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Effect.Effect;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by giuseppe on 5/27/17.
 */
public class HarvProdZone implements Component{

    private LinkedList<SmallActionPlace> littleActionSpace = new LinkedList<SmallActionPlace>();
    private BigActionSpace bigActionSpace = new BigActionSpace();


    @Override
    public void addFamilyMember(FamilyMember familyMember, int index) {


        if(!isAllowed(familyMember,index)){
            throw new IllegalStateException();
        }

        for(SmallActionPlace smallLittleActionSpace : littleActionSpace){
            /**se ce ne è uno che non è full significa che possiamo mettere il family member*/
            if(smallLittleActionSpace.isFull()){
                /** se abbiamo trovato il family member*/
                smallLittleActionSpace.addFamilyMember(familyMember);
                return ;
            }
        }


        /** se siamo arrivati qua significa che nessuno spazio piccolo era libero
         * possiamo mettere il familiare ma PRENDIAMO IL MALUS
         */

        /** la storia del malus me la sbrigo dentro il mio BigActionSpace*/
        bigActionSpace.addFamilyMember(familyMember);


    }

    @Override
    public boolean isAllowed(FamilyMember familyMember, int index) {
        return false;
    }

    @Override
    public ArrayList<Effect> getEffect(int index) {
        return null;
    }
}
