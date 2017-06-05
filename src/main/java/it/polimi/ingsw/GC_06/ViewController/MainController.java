package it.polimi.ingsw.GC_06.ViewController;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.ActionBoh;
import it.polimi.ingsw.GC_06.model.Action.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.GameStatus;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 21/05/17.
 */
public class MainController {

    public ActionBoh powerUpFamilyMember (int points, int familyMember)
    {
        ResourceSet resourceSet = new ResourceSet();
        BonusMalusHandler bonusMalusHandler = new BonusMalusHandler();
        resourceSet.variateResource(Resource.SERVANT, -points);
        //TODO FIX CONTROLLI RESOURCESET
        GameStatus status = Game.getInstance().getGameStatus();
        Player player = status.getCurrentPlayer();
        FamilyMember familyMember1 = player.getFamilyMembers()[familyMember];

        ActionBoh action = new PowerUpFamilyMember(status.getCurrentPlayer(), familyMember1, resourceSet, points,bonusMalusHandler);
        return action;

    }

}
