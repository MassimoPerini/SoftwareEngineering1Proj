package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageMultipleSteps;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

/**
 * Created by massimo on 06/07/17.
 */
public class MessageCreator {

    private MessageClient messageClient;
    private int familyMember;
    private int powerUp;
    ClientNetworkOrchestrator clientNetworkOrchestrator;

    public MessageCreator(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    private void reset()
    {
        this.familyMember = -1;
        this.powerUp = 0;
    }

    private boolean validate()
    {
        if (messageClient==null) {
            return false;
        }

        if (messageClient instanceof MessageMultipleSteps)
        {
            MessageMultipleSteps messageMultipleSteps = (MessageMultipleSteps) messageClient;
            messageMultipleSteps.setFamilyMember(familyMember);
            messageMultipleSteps.setPowerUp(powerUp);
            return messageMultipleSteps.isValid();
        }
        else
        {
            return true;
        }
    }

    public void setMessageClient(MessageClient messageClient)
    {
        this.messageClient = messageClient;
    }


    public void setFamilyMember(int familyMember)
    {
        this.familyMember = familyMember;
    }

    public void setPowerUp(int powerUp)
    {
        this.powerUp = powerUp;
    }

    public void send()
    {
        if (this.validate()) {
            clientNetworkOrchestrator.send(messageClient);
        }
        this.reset();
    }

}
