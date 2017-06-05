package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.PlayType;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Board.TowerFloor;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 26/05/17.
 */
public class PickCard extends Action {

    
    private Player player;
    private TowerFloor towerFloor;
    private PayCard payCard;
    private Tower tower;

    public PickCard(Player player, TowerFloor towerFloor, Tower tower, int valueFamilyMember, BonusMalusHandler bonusMalusHandler)
    {
        super(PlayType.piCkCard, valueFamilyMember,bonusMalusHandler);
        if (player==null || towerFloor==null)
            throw new NullPointerException();
        this.player = player;
        this.towerFloor = towerFloor;
        this.tower = tower;
        this.payCard = new PayCard(towerFloor.getCard(), player,bonusMalusHandler);
    }

    @Override
    public void execute() {

        if (!isAllowed())
            throw new IllegalStateException();

        /**if we are in the real action we add the family member in the correct position*/
        //Tower penality
        this.executePenality(player);

        //Apply ActionSpace effects
        List<Effect> effects = towerFloor.getActionPlace().getEffects();
        this.executeEffects(player, effects);

        /**we are adding the card to the player board*/
        DevelopmentCard c = towerFloor.pickCard();
        player.getPlayerBoard().addCard(c);

        payCard.execute();

        //TODO qui deve partire l'effetto immediato della carta in automatico
    }

    @Override
    public boolean isAllowed() {
        //Can add in PlayerBoard
        FamilyMember familyMemberTest = new FamilyMember(null, player.getPLAYER_ID());
        familyMemberTest.setValue(super.getValueAction());

        if (!tower.isAllowed(familyMemberTest, towerFloor))
            return false;


        if (!player.getPlayerBoard().canAdd(towerFloor.getCard()))
            return false;

        //clone player
        Player pClone = new Player(player);     //CLONE (I hope...) TODO

        //Test tower penality
        try {
            executePenality(pClone);
        }
        catch(IllegalStateException e) {
                return false;
        }
        //Check requirements (add plane and...)
        //Start effect plane!

        //Apply ActionSpace effects to clone
        List<Effect> effects = towerFloor.getActionPlace().getEffects();
        executeEffects(pClone, effects);

        //Can I pay?
        PayCard payClone = new PayCard(towerFloor.getCard(), pClone,super.getBonusMalusHandler());
        return payClone.isAllowed();
    }

    private void executeEffects(Player p, List<Effect> effects)
    {
        for (Effect effect:effects)
            effect.execute(p);
    }

    private void executePenality(Player player)
    {
        if (!tower.isNoPenalityAllowed())
        {
            List<Effect> effects = tower.getMalusOnMultipleFamilyMembers();
            for (Effect effect: effects)
            {
                effect.execute(player);
            }
        }
    }

}
