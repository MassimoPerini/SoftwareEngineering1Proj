package it.polimi.ingsw.GC_06.Client.Model;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientTowerFloor {
    private String card;
    private ClientSpaceAction spaceAction;

    void removeCard()
    {
        card = null;
    }
    void addFamilyMember(ClientFamilyMember clientFamilyMember)
    {
        spaceAction.addClientFamilyMember(clientFamilyMember);
    }

    void removeFamilyMember()
    {
        spaceAction.reset();
    }
    void setNewCard(String card)
    {
        this.card = card;
    }
}
