package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 19/06/17.
 */
public class MessageLoggedIn implements MessageServer {

    private final String username;

    public MessageLoggedIn(String username) {
        this.username = username;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().setMyUsername(username);
        clientController.getMainClientModel().changeMyState(ClientStateName.LOGGED);
    }

    @Override
    public String toString() {
        return "MessageLoggedIn{" +
                "username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageLoggedIn that = (MessageLoggedIn) o;

        return username != null ? username.equals(that.username) : that.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
