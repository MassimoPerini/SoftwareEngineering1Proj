package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 09/06/17.
 * this class represents a single slot for the playerBoard
 */
public class PlayerBoardSlot {

    private ResourceSet requirements;
    private DevelopmentCard developmentCard;
    private ResourceSet bonusEnd;


    public PlayerBoardSlot(ResourceSet requirements, ResourceSet bonusEnd)
    {
        this.requirements = requirements;
        this.bonusEnd = bonusEnd;
    }

    void addCard(DevelopmentCard developmentCard, ResourceSet resourceSet)
    {
        if (!isAllowed(developmentCard, resourceSet))
            throw new IllegalStateException();
        this.developmentCard = developmentCard;
    }

    boolean isAllowed(DevelopmentCard developmentCard, ResourceSet resourceSet)
    {
        return developmentCard!=null && resourceSet.isIncluded(requirements);
    }

    DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public boolean isEmpty()
    {
        return developmentCard==null;
    }
}
