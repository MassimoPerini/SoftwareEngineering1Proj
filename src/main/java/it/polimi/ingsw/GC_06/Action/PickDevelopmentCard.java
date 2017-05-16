package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Board.TowerFloor;
import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Player;

/**
 * Created by massimo on 12/05/17.
 */
public class PickDevelopmentCard implements Action {

    private DevelopmentCard developmentCard;
    private Player player;
    private TowerFloor floor;
    private FamilyMember member;

    public PickDevelopmentCard(Player player, TowerFloor floor, FamilyMember member)
    {
    //    this.developmentCard = floor.getCard();
        this.player = player;
        this.floor = floor;
        this.member = member;
    }

    @Override
    public boolean isApplicable() {
        if (! developmentCard.areAllowedImmediateActions())
            return false;
        //TODO
        return true;
    }

    @Override
    public void apply() {
        if (! this.isApplicable())
            throw new IllegalStateException();

        player.addCard(floor.addFamilyMember(member));
        developmentCard.applyImmediateActions(); //TODO????
    }
}
