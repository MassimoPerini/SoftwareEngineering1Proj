package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.Server.MessageGameStarted;
import it.polimi.ingsw.GC_06.model.Board.MarketAndCouncil;
import it.polimi.ingsw.GC_06.model.Board.ProdHarvZone;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 14/06/17.
 */
public class ControllerGame implements Observer {


    private final Game game;
    private final ServerOrchestrator serverOrchestrator;
    private final int id;
    private final ControllerUser controllerUser;

    public ControllerGame (@NotNull Game game, @NotNull ServerOrchestrator serverOrchestrator, int id)
    {
        this.game = game;
        this.serverOrchestrator = serverOrchestrator;
        this.id = id;
        controllerUser = new ControllerUser(serverOrchestrator, game);
    }

    public void start()
    {
        for (Tower tower : game.getBoard().getTowers().values()) {
            tower.addObserver(this);
        }
        for (ProdHarvZone prodHarvZone : game.getBoard().getProdHarvZones()) {
            prodHarvZone.addObserver(this);
        }
        for (MarketAndCouncil marketAndCouncil : game.getBoard().getMarket()) {
            marketAndCouncil.addObserver(this);
        }
        Map<String, Player> players = game.getGameStatus().getPlayers();
        for (Player player : players.values()) {
            player.addObserver(this);
        }
        for (Player player : game.getGameStatus().getPlayers().values()) {
            for (FamilyMember familyMember : player.getFamilyMembers()) {
                familyMember.addObserver(this);
            }
        }
        game.getRoundManager().addObserver(this);

        serverOrchestrator.startGame(game.getGameStatus().getPlayers(), id);
        controllerUser.start();


        //---- Notificare l'init
        MessageGameStarted messageGameStarted = new MessageGameStarted(game);
        //In futuro da togliere
        serverOrchestrator.send(id, messageGameStarted);

        //------- END MESSAGE

        /** si salva per ogni gioco l'id dei partecipanti -> Mappa <username/Socket>*/
        this.game.start(new DefaultEventManager(serverOrchestrator, game));
    }

    public void stop()
    {

    }

    @Override
    public void update(Observable o, Object arg) {
        serverOrchestrator.send(id, arg);
    }
}
