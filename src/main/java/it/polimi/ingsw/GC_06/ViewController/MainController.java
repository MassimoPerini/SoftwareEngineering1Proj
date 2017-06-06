package it.polimi.ingsw.GC_06.ViewController;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 21/05/17.
 */
public class MainController {

    public Action powerUpFamilyMember (int points, int familyMember)
    {
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.SERVANT, -points);
        //TODO FIX CONTROLLI RESOURCESET
        Game status = Game.getInstance();
        Player player = status.getCurrentPlayer();
        FamilyMember familyMember1 = player.getFamilyMembers()[familyMember];

        Action action = new PowerUpFamilyMember(status.getCurrentPlayer(), familyMember1, resourceSet, points);
        return action;

    }

}
