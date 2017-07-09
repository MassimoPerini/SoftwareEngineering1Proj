package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageAddMemberOnTower;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageNewCardOnTower;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageRemoveCardOnTower;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by massimo on 13/05/17.
 */

/**
 * @author massimo
 * This class represents a "tower" of the game
 */
public class Tower extends Observable{

    private final List<TowerFloor> towerFloors;
    private int maxSamePlayerFamilyMember; /** massimo family member che il giocatore può piazzare */
    private int minFamilyMembersMalus; /**numero al di sopra del quale applichiamo il malus sulle risorse */
    private List<DevelopmentCard> cards;
    private ResourceSet malusOnMultipleFamilyMembers;
    private String color;

    public Tower(List<TowerFloor> floors, int maxSamePlayerFamilyMember, int minFamilyMembersMalus, String color, ResourceSet malusOnMultipleFamilyMembers){
    	this.towerFloors = floors;
    	this.maxSamePlayerFamilyMember = maxSamePlayerFamilyMember;
    	this.minFamilyMembersMalus = minFamilyMembersMalus;
    	this.color = color;
    	this.malusOnMultipleFamilyMembers = malusOnMultipleFamilyMembers;
    }

    /**
     * Chack if you deserve a malus when you add the card (the -3 gold).
     * It will be used together with getMalusOnMultipleFamilyMembers
     * @return se va applicato il malus di -3 legato alla torre
     */





    public boolean shouldThrowPenality(String playerUsername)
    {
        int familyMemberCount = 1;      //I'm not adding it (but I'm checking)
        for (TowerFloor towerFloor : towerFloors)
        {
            for (FamilyMember familyMember1 : towerFloor.getActionPlace().getMembers()) {     //Se ci sono + familiari per effetto di carte eroe...
                if(familyMember1.getPlayerUserName().equals(playerUsername)){
                    familyMemberCount++;
                }
            }
        }
        return familyMemberCount > minFamilyMembersMalus;

    }

    /**
     * Check if the player can add the family member to a floor. It doesn't manage the malus when a player is added
     * Neutral family member doesn't count
     * @param familyMember
     * @param towerFloorUser
     * @return se può essere o meno aggiunto il familiare
     */
    public boolean isAllowed(FamilyMember familyMember, int towerFloorUser) {

        if (familyMember.isNeutral()) {
            return towerFloors.get(towerFloorUser).isAllowed(familyMember);
        }

        int samePlayerFamilyMember = 1;     //I'm checking, but you have not added it

        for (TowerFloor towerFloor : towerFloors)
        {
            for (FamilyMember familyMember1 : towerFloor.getActionPlace().getMembers())     //Se ci sono + familiari per effetto di carte eroe...
            {
                if (familyMember1.getPlayerUserName().equals(familyMember.getPlayerUserName()) && !familyMember1.isNeutral())
                {
                    samePlayerFamilyMember++;
                }
            }
        }

        if (samePlayerFamilyMember > maxSamePlayerFamilyMember)
            return false;
        return towerFloors.get(towerFloorUser).isAllowed(familyMember);
    }

    /**
     * Set an amount of cards on the tower
     * @param cards
     */
    public void setCards(List<DevelopmentCard> cards)
    {
        this.cards = cards;
    }

    /**
     * \requires this.cards.size() at least towerFloors.size()
     * Gives a new card for each towerFloor
     */
    public void shuffle()
    {
        LinkedList<DevelopmentCard> addedCards = new LinkedList<>();

        for (int i=0;i<this.towerFloors.size();i++)
        {
            this.towerFloors.get(i).setCard(cards.get(0));
            cards.remove(0);
        }

        MessageServer messageServer = new MessageNewCardOnTower(this);
        setChanged();
        notifyObservers(messageServer);
    }

    //TODO create addFamilyMemeber here
    public DevelopmentCard pickCard(int indexFloor)
    {
        DevelopmentCard res = towerFloors.get(indexFloor).pickCard();
        MessageServer message = new MessageRemoveCardOnTower(this.color, indexFloor);
        setChanged();
        notifyObservers(message);
        return res;
    }

    void removeFamilyMembers()
    {
        for (TowerFloor towerFloor : towerFloors) {
            towerFloor.removeFamilyMembers();
        }
    }

    public void addFamilyMember(FamilyMember familyMember, int index)
    {
        towerFloors.get(index).addFamilyMember(familyMember);

        MessageServer messageServer = new MessageAddMemberOnTower(this.color, index, familyMember);
        setChanged();
        notifyObservers(messageServer);
    }

    public List<TowerFloor> getTowerFloor()
    {
        return Collections.unmodifiableList(this.towerFloors);
    }

    public ResourceSet getMalusOnMultipleFamilyMembers() {
        return malusOnMultipleFamilyMembers;
    }

    public String getColor() {
        return color;
    }
}
