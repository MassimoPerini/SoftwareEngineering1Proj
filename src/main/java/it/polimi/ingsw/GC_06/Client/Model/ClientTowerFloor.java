package it.polimi.ingsw.GC_06.Client.Model;

import java.util.Observable;

/**
 * Created by giuseppe on 6/14/17.
 * this class represents a towerFloor client side, is an observable class
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

    /**
     * removes the card from the TowerFloor
     */
    synchronized void removeCard()
    {
        card = null;
        setChanged();
        notifyObservers(card);
    }

    /**
     * adds a familyMember to the TowerFloor
     * @param clientFamilyMember familyMember to be added
     */
    synchronized void addFamilyMember(ClientFamilyMember clientFamilyMember)
    {
        spaceAction.addClientFamilyMember(clientFamilyMember);
        setChanged();
        notifyObservers();
    }

    /**
     * removes a familyMember
     */
    synchronized void removeFamilyMember()
    {
        spaceAction.reset();
        setChanged();
        notifyObservers();
    }

    /**
     *
     * @param card card to be associated to the TowerFloor
     */
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
