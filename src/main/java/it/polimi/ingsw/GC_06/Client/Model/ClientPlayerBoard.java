package it.polimi.ingsw.GC_06.Client.Model;

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

    public ClientPlayerBoard(String username)
    {
        cards = new HashMap<>();
        excommunication = new LinkedList<>();
        resourceSet = new HashMap<>();
        playerUsername = username;
        familyMembers = new LinkedList<>();
    }

    public void addFamilyMember(ClientFamilyMember clientFamilyMember)
    {
        this.familyMembers.add(clientFamilyMember);
    }

    public void addCard(String colour, String card) {
        this.cards.get(colour).add(card);
    }

    public void addExcommunication(String excommunication) {
        this.excommunication.add(excommunication);
    }

    public void updateResourceSet(Map<Resource, Integer> resourceSet) {
        this.resourceSet = resourceSet;
    }


    //*******


    public Map<String, List<String>> getCards() {
        return cards;
    }

    public List<String> getExcommunication() {
        return excommunication;
    }

    public Map<Resource, Integer> getResourceSet() {
        return resourceSet;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public List<ClientFamilyMember> getFamilyMembers() {
        return familyMembers;
    }
}
