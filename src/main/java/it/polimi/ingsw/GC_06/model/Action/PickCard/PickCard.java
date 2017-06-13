package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Board.TowerFloor;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 26/05/17.
 */
public class PickCard implements Action {

    
    private final Player player;
    private final TowerFloor towerFloor;
    private final Tower tower;
    private final int valueFamilyMember;
    private Game game;

    public PickCard(Player player, Tower tower, TowerFloor towerFloor, int valueFamilyMember)
    {
        super();
        if (player==null || towerFloor==null)
            throw new NullPointerException();
        this.player = player;
        this.towerFloor = towerFloor;
        this.tower = tower;
        this.valueFamilyMember = valueFamilyMember;
    }

    @Override
    public void execute() {

        if (!isAllowed())
            throw new IllegalStateException();
        game.getGameStatus().changeState(TransitionType.PICK_CARD);


        /**if we are in the real action we add the family member in the correct position*/
        //Tower penality
        if (!tower.isNoPenalityAllowed()) {
            ResourceSet malusResources = tower.getMalusOnMultipleFamilyMembers();
            player.variateResource(malusResources);
        }

        //Apply ActionSpace effects
        List<Effect> effects = towerFloor.getActionPlace().getEffects();
        ExecuteEffects executeEffects = new ExecuteEffects(effects, player,game);
        executeEffects.execute();

        /**we are adding the card to the player board*/
        DevelopmentCard c = towerFloor.pickCard();
        player.getPlayerBoard().addCard(c, player.getResourceSet());

        //pay the card
        PayCard payCard = new PayCard(c, player);
        payCard.execute();

        executeEffects = new ExecuteEffects(c.getImmediateEffects(), player,game);
        executeEffects.execute();

    }

    public void setGame(Game game) {
        this.game = game;
    }
    public void setGame (int game){}
    public void setPlayer(String player){}

    @Override
    public boolean isAllowed() {
        //Can add in PlayerBoard
        FamilyMember familyMemberTest = new FamilyMember(null, player.getPLAYER_ID());
        familyMemberTest.setValue(this.valueFamilyMember);

        if (!tower.isAllowed(familyMemberTest, towerFloor))
            return false;

        /** controllo se il player può aggiungere la carta */
        if (!player.getPlayerBoard().canAdd(towerFloor.getCard(), player.getResourceSet()))
            return false;

        //clone player
        Player pClone = new Player(player);     //CLONE (I hope...) TODO

        //Test tower penality

        if (!tower.isNoPenalityAllowed()) {
            ResourceSet malusResources = tower.getMalusOnMultipleFamilyMembers();

            try {
                pClone.variateResource(malusResources);
            }
            catch (IllegalArgumentException e)
            {
                //Non posso sottrarre risorse
                return false;
            }
        }

        //Check requirements (add plane and...)
        //Start effect plane!

        //Apply ActionSpace effects to clone
        List<Effect> effects = towerFloor.getActionPlace().getEffects();

        for(Effect effect: effects)
        {
            effect.execute(pClone,game);
        }

        //Can I pay?
        PayCard payClone = new PayCard(towerFloor.getCard(), pClone);

        if(payClone.isAllowed())
        {
            return false;
        }

        ExecuteEffects executeEffects = new ExecuteEffects(towerFloor.getCard().getImmediateEffects(), pClone,game);

        return executeEffects.isAllowed();
    }


}
