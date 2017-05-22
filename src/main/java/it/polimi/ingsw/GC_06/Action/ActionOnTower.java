package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Board.Component;
import it.polimi.ingsw.GC_06.Board.PlayerBoard;
import it.polimi.ingsw.GC_06.Board.TowerFloor;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.playerTools.Player;


import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class ActionOnTower implements Action{

    private Player player;
    private FamilyMember familyMember;
    private Component component;
    private TowerFloor towerFloor;
    int index;


    public ActionOnTower(Player player, TowerFloor towerFloor, Component component) {
        this.player = player;
        this.towerFloor = towerFloor;
        this.component = component;
    }

    @Override
    public void execute() {
        ArrayList<Effect> effects;

        if(familyMember!=null){
            effects = component.addFamilyMember(familyMember, index);
        }

        else{
            effects = component.getEffect();
        }

        for(Effect effect : effects){
            //TODO definire meglio l'interfaccia
            effect.execute(player);
        }

        /** we must controll if the player can afford the card */



        /**we are adding the card to the player board*/
        player.getPlayerBoard().getCards().put(towerFloor.getCard().getIdColour(), towerFloor.getCard());


    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean isAllowed() {

    }
/**
    private void payCard(){

        int counter = 0;
        ArrayList<Requirement> satisfiedRequirements = new ArrayList<>();
        ArrayList<Requirement> requirements =  towerFloor.getCard().getRequirements();
        //TODO parte della richiesta all'utente sulla scelta di pagamento
        for(Requirement requirement : requirements){
            if(requirement.isSatisfied(player.getResources())){
                satisfiedRequirements.add(requirement){
            }
        }

        if(satisfiedRequirements.size() == 1){
            requirement.doIt(player.getResources());
        }
        else{
            //TODO sono cazzi amari
        }
        player.getResources().removeResource(card.);
    }
 */
}
