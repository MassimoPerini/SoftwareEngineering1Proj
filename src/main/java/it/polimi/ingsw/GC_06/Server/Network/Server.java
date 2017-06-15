package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;

/**
 * Created by massimo on 13/06/17.
 */
public abstract class Server extends Observable {
    public abstract void start();
    public abstract boolean startGame(@NotNull Map<String, Player> players, int id);
    public abstract boolean isPlayerManaged(@NotNull String player);
    public abstract void sendMessageToPlayer(@NotNull String player, @NotNull Object o) throws IOException;
    public abstract void sendMessageToGame(int game, @NotNull Object o) throws IOException;
    public abstract void setLoginHub(LoginHub loginHub);
}
