package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Board.Component;
import it.polimi.ingsw.GC_06.Board.TowerFloor;
import it.polimi.ingsw.GC_06.Card.Requirement;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class ChooseCard implements Action{

    private Player player;
    private FamilyMember familyMember;
    private Component component;
    private TowerFloor towerFloor;
    int index;


    public ChooseCard(Player player, TowerFloor towerFloor, Component component) {
        this.player = player;
        this.towerFloor = towerFloor;
        this.component = component
    }

    @Override
    public void execute() {
        player.addCard(towerFloor.pickCard());

    }

    @Override
    public boolean isAllowed() {
        return component.isAllowed(FamilyMember familyMember);
    }

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
}
