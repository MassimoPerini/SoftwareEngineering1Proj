package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

/**
 * Created by giuseppe on 7/7/17.
 */
public class MessageAddMemberOnMarket implements MessageServer {

    private int marketIndex;
    private int familyMemberValue;
    private String familyMemberColour;
    private String usernameId;

    public MessageAddMemberOnMarket(int marketIndex, FamilyMember familyMember){
        this.marketIndex = marketIndex;
        this.familyMemberValue = familyMember.getValue();
        this.familyMemberColour = familyMember.getDiceColor();
        this.usernameId = familyMember.getPlayerUserName();

    }

    @Override
    public void execute(ClientController clientController) {
        ClientFamilyMember clientFamilyMember = new ClientFamilyMember(usernameId,familyMemberValue,usernameId);
        clientController.getMainClientModel().getClientBoardGame().getMarket().get(0).get(marketIndex).addClientFamilyMember(clientFamilyMember);

    }
}
