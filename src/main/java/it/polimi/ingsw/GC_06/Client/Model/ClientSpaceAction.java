package it.polimi.ingsw.GC_06.Client.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientSpaceAction {
    private List<ClientFamilyMember> familyMembers;

    void addClientFamilyMember(ClientFamilyMember clientFamilyMember) {
    }

    void reset() {
        familyMembers = new LinkedList<>();
        //notify
    }
}
