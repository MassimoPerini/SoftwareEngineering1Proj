package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageMultipleSteps;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

import java.util.Observable;

/**
 * Created by massimo on 06/07/17.
 */
public class MessageCreator extends Observable {

    private MessageClient messageClient;
    private int familyMember;
    private int powerUp;
    ClientNetworkOrchestrator clientNetworkOrchestrator;

    public MessageCreator(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    private synchronized void reset()
    {
        this.familyMember = -1;
        this.powerUp = 0;
        this.messageClient = null;
    }

    private synchronized void validate()
    {
        if (messageClient==null) {
            setChanged();
            notifyObservers(false);
            return;
        }

        if (messageClient instanceof MessageMultipleSteps)
        {
            MessageMultipleSteps messageMultipleSteps = (MessageMultipleSteps) messageClient;
            messageMultipleSteps.setFamilyMember(familyMember);
            messageMultipleSteps.setPowerUp(powerUp);
            setChanged();
            notifyObservers(messageMultipleSteps.isValid());
            return;
        }
        else
        {
            setChanged();
            notifyObservers(true);
        }
    }

    public synchronized void setMessageClient(MessageClient messageClient)
    {
        this.messageClient = messageClient;
        validate();
    }


    public synchronized void setFamilyMember(int familyMember)
    {
        this.familyMember = familyMember;
        validate();
    }

    public synchronized void setPowerUp(int powerUp)
    {
        this.powerUp = powerUp;
        validate();
    }

    public synchronized void send()
    {
        clientNetworkOrchestrator.send(messageClient);
        this.reset();
    }

}
