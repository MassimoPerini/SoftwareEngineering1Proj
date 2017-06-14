package it.polimi.ingsw.GC_06.Client.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientBoardGame {
    private Map<String, List<ClientTowerFloor>> towersClient;
    private List<ClientSpaceAction> production;
    private List<ClientSpaceAction> harvestZone;
    private List<ClientSpaceAction> market;
    private List<ClientSpaceAction> CounsilPalace;


    public void removeCard(String tower, int plane)
    {
        ClientTowerFloor clientTowerFloor = towersClient.get(tower).get(plane);
        clientTowerFloor.removeCard();
    }

    public void addFamilyMemberToTower(ClientFamilyMember clientFamilyMember, String tower, int index)
    {
        towersClient.get(tower).get(index).addFamilyMember(clientFamilyMember);
    }

}
