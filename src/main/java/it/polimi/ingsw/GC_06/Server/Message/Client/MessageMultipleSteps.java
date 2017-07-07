package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

/**
 * Created by massimo on 06/07/17.
 */
public interface MessageMultipleSteps extends MessageClient{

    void setFamilyMember(int index);
    void setPowerUp(int powerUp);
    boolean isValid();
}
