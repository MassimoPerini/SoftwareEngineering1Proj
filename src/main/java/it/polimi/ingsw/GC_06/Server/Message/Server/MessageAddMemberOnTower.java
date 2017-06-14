package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import org.jetbrains.annotations.NotNull;

/**
 * Created by massimo on 14/06/17.
 */
public class MessageAddMemberOnTower implements MessageServer{

    private final String tower;
    private final int index;
    private final int valueFamilyMember;
    private final String userFamilyMember;
    private final String diceColor;

    public MessageAddMemberOnTower(@NotNull String tower, int index, @NotNull FamilyMember familyMember)
    {
        this.tower = tower;
        this.index = index;
        this.valueFamilyMember = familyMember.getValue();
        this.userFamilyMember = familyMember.getPlayerUserName();
        this.diceColor = familyMember.getDiceColor();
    }


    @Override
    public void execute(MainClientModel mainClientModel) {
        ClientFamilyMember clientFamilyMember = new ClientFamilyMember(userFamilyMember, valueFamilyMember, diceColor);
        mainClientModel.getClientBoardGame().addFamilyMemberToTower(clientFamilyMember, tower, index);
    }
}
