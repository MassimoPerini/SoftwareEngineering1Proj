package it.polimi.ingsw.GC_06.Client.Model;

import javafx.beans.property.SimpleListProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientBoardGame {
    private Map<String, List<ClientTowerFloor>> towersClient;
    private List<List<ClientSpaceAction>> productionHarvest;
    private List<List<ClientSpaceAction>> market;
    private List<List<ClientSpaceAction>> council;


    public ClientBoardGame()
    {
        towersClient = new HashMap<>();
        productionHarvest = new ArrayList<>();
        market = new ArrayList<>();
        council = new ArrayList<>();
    }


    public void createTower(String color, int floors)
    {
        List<ClientTowerFloor> list = new ArrayList<>();
        for (int i=0;i<floors;i++) {
            list.add(new ClientTowerFloor());
        }
        towersClient.put(color, list);
    }

    public void createProdHarv(int position, int spaces)
    {
        List<ClientSpaceAction> item = generateBoardItems(spaces);
        productionHarvest.add(position, item);
    }

    public void createMarket(int position, int spaces)
    {
        List<ClientSpaceAction> item = generateBoardItems(spaces);
        market.add(position, item);
    }

    public void createCouncil(int position, int spaces)
    {
        List<ClientSpaceAction> item = generateBoardItems(spaces);
        council.add(position, item);
    }


    private List<ClientSpaceAction> generateBoardItems(int items)
    {
        List list = new ArrayList();
        for (int i=0;i<items;i++) {
            list.add(new ClientTowerFloor());
        }
        return list;
    }

    public void removeCard(String tower, int plane)
    {
        ClientTowerFloor clientTowerFloor = towersClient.get(tower).get(plane);
        clientTowerFloor.removeCard();
    }

    public void addFamilyMemberToTower(ClientFamilyMember clientFamilyMember, String tower, int index)
    {
        towersClient.get(tower).get(index).addFamilyMember(clientFamilyMember);
    }

    public void setNewTowerCards(String tower, List<String> cards)
    {
        int i=0;
        for (ClientTowerFloor clientTowerFloor : towersClient.get(tower)) {
            clientTowerFloor.setNewCard(cards.get(i));
            i++;
        }
    }

    public void clearAllFamilyMembers()
    {
        for (List<ClientTowerFloor> clientTowerFloors : towersClient.values()) {
            for (ClientTowerFloor clientTowerFloor : clientTowerFloors) {
                clientTowerFloor.removeFamilyMember();
            }
        }

        for (List<ClientSpaceAction> clientSpaceActions : productionHarvest) {
            for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                clientSpaceAction.reset();
            }
        }

        for (List<ClientSpaceAction> clientSpaceActions : market) {
            for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                clientSpaceAction.reset();
            }
        }

        for (List<ClientSpaceAction> clientSpaceActions : council) {
            for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                clientSpaceAction.reset();
            }
        }
    }

}
