package it.polimi.ingsw.GC_06.Client.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientSpaceAction {
    private List<ClientFamilyMember> familyMembers;

    public ClientSpaceAction()
    {
        familyMembers = new LinkedList<>();
    }

    void addClientFamilyMember(ClientFamilyMember clientFamilyMember) {
        familyMembers.add(clientFamilyMember);
    }

    void reset() {
        familyMembers = new LinkedList<>();
        //notify
    }

    //*******

    public List<ClientFamilyMember> getFamilyMembers() {
        return familyMembers;
    }
}
