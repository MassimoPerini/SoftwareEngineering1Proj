package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import javafx.collections.FXCollections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by giuseppe on 6/14/17.
 * this class represents the personal playerBoard client side, is an observable class
 */

public class ClientPlayerBoard extends Observable{
    private Map<String, List<String>> cards;
    private List<String> excommunication;
    private Map<Resource, Integer> resourceSet;
    private String playerUsername;
    private List<ClientFamilyMember> familyMembers;
    private List<String> playerProdHarvBonus;
    private List<String> heroCards;

    public ClientPlayerBoard()
    {
        super();
    }

    public ClientPlayerBoard(String username, List<String> playerProdHarvBonus)
    {
        cards = new ConcurrentHashMap<>();
        excommunication = Collections.synchronizedList(new LinkedList<>());
        resourceSet = new ConcurrentHashMap<>();
        playerUsername = username;
        familyMembers = Collections.synchronizedList(FXCollections.observableArrayList());
        this.playerProdHarvBonus = playerProdHarvBonus;
        this.heroCards = Collections.synchronizedList(new LinkedList<>());
    }

    /**
     *
     * @param clientFamilyMember familyMember to be added
     */
    public synchronized void addFamilyMember(ClientFamilyMember clientFamilyMember)
    {
        this.familyMembers.add(clientFamilyMember);
    }

    /**
     *
     * @param colour colour of the card to be added
     * @param card string that represents the name of the card
     */
    public synchronized void addCard(String colour, String card) {
        List<String> cardsColour = this.cards.get(colour);
        if (cardsColour==null) {
            cardsColour = Collections.synchronizedList(new ArrayList<>());
            cards.put(colour, cardsColour);
        }
        cardsColour.add(card);
        setChanged();
        notifyObservers();
    }

    /**
     *
     * @param excommunication name that represents the excomunication to be asssociated to the player
     */
    public synchronized void addExcommunication(String excommunication) {
        this.excommunication.add(excommunication);
        setChanged();
        notifyObservers();
    }

    /**
     *
     * @param resourceSet the new state of the resourceSet
     */
    public synchronized void updateResourceSet(Map<Resource, Integer> resourceSet) {
        this.resourceSet = resourceSet;
        setChanged();
        notifyObservers();
    }

    public synchronized void changeValueFamilyMember(String color, int newVal)
    {
        for (ClientFamilyMember familyMember : familyMembers) {
            if (familyMember.getColor().equals(color)){
                familyMember.setValue(newVal);
            }
        }
        setChanged();
        notifyObservers();
    }


    //*******

    public synchronized List<String> getPlayerProdHarvBonus() {
        return playerProdHarvBonus;
    }

    public synchronized Map<String, List<String>> getCards() {
        return cards;
    }

    public synchronized List<String> getExcommunication() {
        return excommunication;
    }

    public synchronized Map<Resource, Integer> getResourceSet() {
        return resourceSet;
    }

    public synchronized String getPlayerUsername() {
        return playerUsername;
    }

    public synchronized List<ClientFamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public synchronized List<String> getHeroCards() {
        return heroCards;
    }

    public synchronized void setHeroCards(List<String> heroCards) {
        this.heroCards = heroCards;
    }

}
