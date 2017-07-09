package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

/**
 * Created by giuseppe on 7/9/17.
 */
public class MessageAddMemberOnProduction implements MessageServer{

    private int productionIndex;
    private int valueFamilyMember;
    private int slotIndex;
    private String userFamilyMember;
    private String diceColor;

    public MessageAddMemberOnProduction(int productionIndex, int slotIndex, FamilyMember familyMember){
        this.productionIndex = productionIndex;
        this.slotIndex = slotIndex;
        this.valueFamilyMember = familyMember.getValue();
        this.diceColor = familyMember.getDiceColor();
        this.userFamilyMember = familyMember.getPlayerUserName();
    }

    @Override
    public void execute(ClientController clientController) {
        ClientFamilyMember clientFamilyMember = new ClientFamilyMember(userFamilyMember,valueFamilyMember,diceColor);
        clientController.getMainClientModel().getClientBoardGame().getProductionZone().get(productionIndex).get(slotIndex).addClientFamilyMember(clientFamilyMember);
    }
}
