package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/30/17.
 */
public interface Status {

    void putFamilyMember();
    void pickCard();
    void activateProdHarv();/** ci porta nello stato di decisione in cui si decide se attivare immediatamente la produzione o meno*/
    void chooseProdHarvCard();
    void userInput();
    void end();
    void privilegeSelection(); /** ci porta allo stato in cui l'utente scegli che privilegio vuole*/
    void startProduction();/** ci porta nello stato di attivazione della produzione*/

}
