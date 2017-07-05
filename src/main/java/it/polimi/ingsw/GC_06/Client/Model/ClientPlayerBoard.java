package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Resource.Resource;

import java.util.*;

/**
 * Created by giuseppe on 6/14/17.
 */

public class ClientPlayerBoard extends Observable{
    private Map<String, List<String>> cards;
    private List<String> excommunication;
    private Map<Resource, Integer> resourceSet;
    private String playerUsername;
    private List<ClientFamilyMember> familyMembers;
    private List<String> playerProdHarvBonus;
    private List<String> heroCards;

    public ClientPlayerBoard(String username, List<String> playerProdHarvBonus)
    {
        cards = new HashMap<>();
        excommunication = new LinkedList<>();
        resourceSet = new HashMap<>();
        playerUsername = username;
        familyMembers = new LinkedList<>();
        this.playerProdHarvBonus = playerProdHarvBonus;
        this.heroCards = new LinkedList<>();
    }

    public synchronized void addFamilyMember(ClientFamilyMember clientFamilyMember)
    {
        this.familyMembers.add(clientFamilyMember);
    }

    public synchronized void addCard(String colour, String card) {
        List<String> cardsColour = this.cards.get(colour);
        if (cardsColour==null) {
            cardsColour = new ArrayList<>();
            cards.put(colour, cardsColour);
        }
        cardsColour.add(card);
    }

    public synchronized void addExcommunication(String excommunication) {
        this.excommunication.add(excommunication);
    }

    public synchronized void updateResourceSet(Map<Resource, Integer> resourceSet) {
        this.resourceSet = resourceSet;
    }

    public synchronized void changeValueFamilyMember(String color, int newVal)
    {
        for (ClientFamilyMember familyMember : familyMembers) {
            if (familyMember.getColor().equals(color)){
                familyMember.setValue(newVal);
            }
        }
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
