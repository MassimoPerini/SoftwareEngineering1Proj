package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;

/**
 * Created by gabri on 19/06/2017.
 */
public class RmiServer {
    //TODO
    private final static int RMI_PORT = 52365;
    private ControllerGame controllerGame;

    public RmiServer(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }

    public void startRMI() {

    }
}
