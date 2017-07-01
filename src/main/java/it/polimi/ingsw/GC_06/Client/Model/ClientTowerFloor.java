package it.polimi.ingsw.GC_06.Client.Model;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientTowerFloor {
    private String card;
    private ClientSpaceAction spaceAction;

    public ClientTowerFloor()
    {
        card="";
        spaceAction = new ClientSpaceAction();
    }

    synchronized void removeCard()
    {
        card = null;
    }
    synchronized void addFamilyMember(ClientFamilyMember clientFamilyMember)
    {
        spaceAction.addClientFamilyMember(clientFamilyMember);
    }

    synchronized void removeFamilyMember()
    {
        spaceAction.reset();
    }
    synchronized void setNewCard(String card)
    {
        this.card = card;
    }


    //*******


    public synchronized String getCard() {
        return card;
    }

    public synchronized ClientSpaceAction getSpaceAction() {
        return spaceAction;
    }
}
