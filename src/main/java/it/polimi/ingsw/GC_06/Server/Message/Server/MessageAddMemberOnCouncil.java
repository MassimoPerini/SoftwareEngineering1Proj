package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

/**
 * Created by giuseppe on 7/7/17.
 */
public class MessageAddMemberOnCouncil implements MessageServer{

    private int indexCounsilSpace;
    private int familyMemberValue;
    private String usernameID;
    private String familyMemberColour;

    public MessageAddMemberOnCouncil(int indexCounsilSpace, FamilyMember familyMember) {
        this.indexCounsilSpace = indexCounsilSpace;
        this.familyMemberColour = familyMember.getDiceColor();
        this.usernameID = familyMember.getPlayerUserName();
        this.familyMemberValue = familyMember.getValue();
    }

    @Override
    public void execute(ClientController clientController) {
        ClientFamilyMember clientFamilyMember = new ClientFamilyMember(usernameID,familyMemberValue,familyMemberColour);
        clientController.getMainClientModel().getClientBoardGame().getCouncil().get(0).get(indexCounsilSpace).addClientFamilyMember(clientFamilyMember);
    }
}
