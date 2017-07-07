package it.polimi.ingsw.GC_06.Client.Model;

import java.util.Observable;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientTowerFloor extends Observable {
    private String card;
    private ClientSpaceAction spaceAction;
    private String container = "";
    private int content;

    public ClientTowerFloor()
    {
        card="";
        spaceAction = new ClientSpaceAction();

    }

    public void setContainer(String container) {
        this.container = container;
    }

    public void setContent(int content) {
        this.content = content;
    }

    synchronized void removeCard()
    {
        card = null;
        setChanged();
        notifyObservers(card);
    }
    synchronized void addFamilyMember(ClientFamilyMember clientFamilyMember)
    {
        spaceAction.addClientFamilyMember(clientFamilyMember);
        setChanged();
        notifyObservers();
    }

    synchronized void removeFamilyMember()
    {
        spaceAction.reset();
        setChanged();
        notifyObservers();
    }
    synchronized void setNewCard(String card)
    {
        this.card = card;
        setChanged();
        notifyObservers();
    }


    //*******


    public String getContainer() {
        return container;
    }

    public int getContent() {
        return content;
    }

    public synchronized String getCard() {
        return card;
    }

    public synchronized ClientSpaceAction getSpaceAction() {
        return spaceAction;
    }
}
