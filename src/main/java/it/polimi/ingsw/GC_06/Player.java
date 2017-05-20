package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Card.Card;
import it.polimi.ingsw.GC_06.Card.CardSet;
import it.polimi.ingsw.GC_06.Card.CardType;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.ArrayList;

/**
 * Created by massimo on 12/05/17.
 */
public class Player
{
    //TODO FIX IT!!! (GENERIC, NOT 4)
    private FamilyMember [] familyMembers=new FamilyMember[4];
    private PlayerId id;
    private ResourceSet resources;
    private ArrayList<CardSet> cardSets = new ArrayList<>();
    
    


    public Player(PlayerId id, ResourceSet resources, FamilyMember [] familyMembers)
    {
        super ();

        this.id=id;
        this.resources=resources;

        this.familyMembers = familyMembers;

        for (int i=0; i<CardType.values().length;i++)
            cardSets[i] = new CardSet(CardType.values()[i]);


        for (FamilyMember familyMember: this.familyMembers)
            familyMember.setPlayerColor(this.id);

    }

    public CardSet[] getCardSets() {
        return cardSets;
    }

    public ResourceSet getResources()
    {
        return resources;
    }

    public void addCard(Card card)
    {
        cardSets[card.getCardType().ordinal()].addCard(card);
        // TODO EXECUTE IMMEDIATE EFFECTS
    }

    public FamilyMember[] getFamilyMembers() {
        return familyMembers;
    }
}
