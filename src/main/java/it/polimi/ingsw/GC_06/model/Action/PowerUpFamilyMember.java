package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 26/05/17.
 */
public class PowerUpFamilyMember implements  Action{

    private Player player;
    private FamilyMember familyMember;
    private ResourceSet variateResources;
    private int variateFamilyMember;

    public PowerUpFamilyMember(Player player, FamilyMember familyMember, ResourceSet variateResources, int variateFamilyMember) {
        this.player = player;
        this.familyMember = familyMember;
        this.variateResources = variateResources;
        this.variateFamilyMember = variateFamilyMember;
    }

    @Override
    public void execute() {

        player.getResourceSet().variateResource(variateResources);
        familyMember.variateValue(variateFamilyMember);

    }

    @Override
    public boolean isAllowed() {

        return ! player.getResourceSet().isIncluded(variateResources);

    }
}