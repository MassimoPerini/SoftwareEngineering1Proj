package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

/**
 * Created by massimo on 14/06/17.
 */
public class MessageAddMemberOnTower implements MessageServer{

    private final String tower;
    private final int index;
    private final int valueFamilyMember;
    private final String userFamilyMember;
    private final String diceColor;

    public MessageAddMemberOnTower(String tower, int index, FamilyMember familyMember)
    {
        this.tower = tower;
        this.index = index;
        this.valueFamilyMember = familyMember.getValue();
        this.userFamilyMember = familyMember.getPlayerUserName();
        this.diceColor = familyMember.getDiceColor();
    }


    @Override
    public void execute(ClientController clientController) {
        ClientFamilyMember clientFamilyMember = new ClientFamilyMember(userFamilyMember, valueFamilyMember, diceColor);
        clientController.getMainClientModel().getClientBoardGame().addFamilyMemberToTower(clientFamilyMember, tower, index);
    }
}
