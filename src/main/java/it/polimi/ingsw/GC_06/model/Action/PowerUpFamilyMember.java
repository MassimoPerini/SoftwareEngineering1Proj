package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 26/05/17.
 */
public class PowerUpFamilyMember implements Action {

    private final Player player;
    private final FamilyMember familyMember;
    private final ResourceSet variateResources;
    private final int value;

    public PowerUpFamilyMember(Player player, FamilyMember familyMember, ResourceSet variateResources, int variateFamilyMember) {
        super();
        this.player = player;
        this.familyMember = familyMember;
        this.variateResources = variateResources;
        this.value = variateFamilyMember;
    }

    @Override
    public void execute() {

        player.variateResource(variateResources);
        familyMember.variateValue(value);

    }

    @Override
    public boolean isAllowed() {

        return ! player.getResourceSet().isIncluded(variateResources);

    }
}
