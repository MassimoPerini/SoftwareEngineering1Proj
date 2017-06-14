package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Resource.Resource;

import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientPlayerBoard extends Observable{
    private Map<String, List<String>> cards;
    private List<String> excommunication;
    private Map<Resource, Integer> resourceSet;
    private String playerUsername;

    public void addCard(String colour, String card) {
    }

    public void addExcommunication(String excommunication) {
    }

    public void updateResourceSet(Map<Resource, Integer> resourceSet) {
        this.resourceSet = resourceSet;
    }
}
