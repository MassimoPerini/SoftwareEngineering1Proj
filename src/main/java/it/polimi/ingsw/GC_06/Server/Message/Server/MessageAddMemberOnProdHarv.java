package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

/**
 * Created by giuseppe on 7/7/17.
 */
public class MessageAddMemberOnProdHarv implements MessageServer {

    private int harvestProdIndex; // specifica quale fra harv o prod area utilizzare
    private int slotIndex;// which slot
    private int valueFamilyMember;
    private String userFamilyMember;
    private String diceColor;

    public MessageAddMemberOnProdHarv(int harvestProdIndex, int slotIndex, FamilyMember familyMember) {
        this.harvestProdIndex = harvestProdIndex;
        this.slotIndex = slotIndex;
        this.valueFamilyMember = familyMember.getValue();
        this.userFamilyMember = familyMember.getPlayerUserName();
        this.diceColor = familyMember.getDiceColor();
    }

    @Override
    public void execute(ClientController clientController) {
        ClientFamilyMember clientFamilyMember = new ClientFamilyMember(userFamilyMember,valueFamilyMember,diceColor);
        clientController.getMainClientModel().getClientBoardGame().getProductionHarvest().get(harvestProdIndex).get(slotIndex).addClientFamilyMember(clientFamilyMember);
    }
}
