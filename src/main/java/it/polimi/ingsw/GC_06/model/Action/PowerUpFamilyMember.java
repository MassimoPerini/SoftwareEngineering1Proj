package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;


/**
 * Created by massimo on 26/05/17.
 */
public class PowerUpFamilyMember implements Action {

    private final Player player;
    private final FamilyMember familyMember;
    private final Resource resource = Resource.SERVANT;
    private final int value;
    private int coefficient;
    private ResourceSet variateResources = new ResourceSet();

    public PowerUpFamilyMember(Player player, FamilyMember familyMember,int variateFamilyMember) {
        super();
        this.player = player;
        this.familyMember = familyMember;
        this.value = variateFamilyMember;
        this.coefficient = 1;
        variateResources.variateResource(resource,value*coefficient);
    }

    @Override
    public void execute() {

        ResourceSet variateResources = new ResourceSet();
        variateResources.variateResource(resource,value*coefficient);
        player.variateResource(variateResources);
        familyMember.variateValue(value);

    }

    @Override
    public boolean isAllowed() {

        return ! player.getResourceSet().isIncluded(variateResources);

    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
}
