package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

/**
 * Created by giuseppe on 7/9/17.
 */
public class MessageAddMemberOnHarvest implements MessageServer {

    private int harvestIndex;
    private int slotIndex;
    private int valueFamilyMember;
    private String userFamilyMember;
    private String diceColor;

    public MessageAddMemberOnHarvest(int harvestIndex, int slotIndex, FamilyMember familyMember){
        this.harvestIndex = harvestIndex;
        this.slotIndex = slotIndex;
        this.valueFamilyMember = familyMember.getValue();
        this.userFamilyMember = familyMember.getPlayerUserName();
        this.diceColor = familyMember.getDiceColor();
    }

    @Override
    public void execute(ClientController clientController) {
        ClientFamilyMember clientFamilyMember = new ClientFamilyMember(userFamilyMember,valueFamilyMember,diceColor);
        clientController.getMainClientModel().getClientBoardGame().getHarvestZone().get(harvestIndex).get(slotIndex).addClientFamilyMember(clientFamilyMember);
    }
}
