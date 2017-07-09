package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Loader.Setting;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by giuseppe on 6/14/17.
 */

public class ClientBoardGame extends Observable {
    private final Map<String, List<ClientTowerFloor>> towersClient;
    private final List<List<ClientSpaceAction>> harvestZone;
    private final List<List<ClientSpaceAction>> productionZone;
    private final List<List<ClientSpaceAction>> market;
    private final List<List<ClientSpaceAction>> council;
    private final List<String> orderTowers;

    public ClientBoardGame()
    {
        towersClient = new ConcurrentHashMap<>();
        harvestZone = Collections.synchronizedList(new ArrayList<>());
        productionZone = Collections.synchronizedList(new ArrayList<>());
        market = Collections.synchronizedList( new ArrayList<>());
        council = Collections.synchronizedList( new ArrayList<>());
        orderTowers = Collections.synchronizedList(Arrays.asList(Setting.getInstance().getListProperty("order_towers")));
    }

    public List<String> getOrderTowers() {
        return orderTowers;
    }

    public synchronized void createTower(String color, int floors)
    {
        List<ClientTowerFloor> list = Collections.synchronizedList(new ArrayList<>());
        for (int i=0;i<floors;i++) {
            ClientTowerFloor clientTowerFloor = new ClientTowerFloor();
            clientTowerFloor.setContainer(color);
            clientTowerFloor.setContent(i);
            list.add(clientTowerFloor);

        }
        towersClient.put(color, list);
    }

  /**  public synchronized void createProdHarv(int position, int spaces)
    {
        List<ClientSpaceAction> item = generateBoardItems(spaces);
        productionHarvest.add(position, item);
    }*/

    public synchronized void createProductionZone(int position, int spaces){
        List<ClientSpaceAction> item = generateBoardItems(spaces);
        productionZone.add(item);
    }

    public synchronized void createHarvestZone(int position, int spaces){
        List<ClientSpaceAction> item = generateBoardItems(spaces);
        harvestZone.add(position,item);
    }

    public synchronized void createMarket(int position, int spaces)
    {
        List<ClientSpaceAction> item = generateBoardItems(spaces);
        market.add(position, item);
    }

    public synchronized void createCouncil(int position, int spaces)
    {
        List<ClientSpaceAction> item = generateBoardItems(spaces);
        council.add(position, item);
    }


    private synchronized List<ClientSpaceAction> generateBoardItems(int items)
    {
        List list = Collections.synchronizedList(new ArrayList());
        for (int i=0;i<items;i++) {
            list.add(new ClientSpaceAction());
        }
        return list;
    }

    public synchronized void removeCard(String tower, int plane)
    {
        ClientTowerFloor clientTowerFloor = towersClient.get(tower).get(plane);
        clientTowerFloor.removeCard();
/*
        setChanged();
        notifyObservers();*/
    }

    public synchronized void addFamilyMemberToTower(ClientFamilyMember clientFamilyMember, String tower, int index)
    {
        towersClient.get(tower).get(index).addFamilyMember(clientFamilyMember);
        setChanged();
        notifyObservers();
    }

    public synchronized void setNewTowerCards(String tower, List<String> cards)
    {
        int i=0;
        for (ClientTowerFloor clientTowerFloor : towersClient.get(tower)) {
            clientTowerFloor.setNewCard(cards.get(i));
            i++;
        }
        setChanged();
        notifyObservers();

    }

    public synchronized void clearAllFamilyMembers()
    {
        for (List<ClientTowerFloor> clientTowerFloors : towersClient.values()) {
            for (ClientTowerFloor clientTowerFloor : clientTowerFloors) {
                clientTowerFloor.removeFamilyMember();
            }
        }
/**
        for (List<ClientSpaceAction> clientSpaceActions : productionHarvest) {
            for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                clientSpaceAction.reset();
            }
        }
*/

        for (List<ClientSpaceAction> clientSpaceActions : productionZone) {
            for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                clientSpaceAction.reset();
            }
        }


        for (List<ClientSpaceAction> clientSpaceActions : harvestZone) {
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

    //*******



    public synchronized Map<String, List<ClientTowerFloor>> getTowersClient() {
        return towersClient;
    }

    /**public synchronized List<List<ClientSpaceAction>> getProductionHarvest() {
        return productionHarvest;
    }*/

    public synchronized List<List<ClientSpaceAction>> getMarket() {
        return market;
    }

    public synchronized List<List<ClientSpaceAction>> getCouncil() {
        return council;
    }

    public List<List<ClientSpaceAction>> getHarvestZone() {
        return harvestZone;
    }

    public List<List<ClientSpaceAction>> getProductionZone() {
        return productionZone;
    }
}
