package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageChooseParchment;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.Arrays;
import java.util.List;



/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnParchment implements Effect, Blocking {

    private List<ResourceSet> parchments;
    private boolean isDifferent;
    private List choosen;
    private final Object lock = new Object();

    public EffectOnParchment() {
        super();
    }

    @Override
    public void execute (Player player,Game game) {
        GameList.getInstance().setCurrentBlocking(game, this);
        FileLoader fileLoader = FileLoader.getFileLoader();
        parchments = Arrays.asList(fileLoader.loadParchments());
        MessageChooseParchment messageChooseParchment = new MessageChooseParchment(parchments);
        game.getGameStatus().sendMessage(messageChooseParchment);

        while(choosen==null)
        {
            try {
                synchronized (lock) {
                    lock.wait();
                    System.out.println("Thread svegliato");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("THREAD ATTIVO!");
        player.variateResource(parchments.get((Integer) choosen.get(0)));
        choosen=null;
    }

    public void setDifferent(boolean different) {
        isDifferent = different;
    }

    @Override
    public void setOptionalParams(List list) {
        synchronized (lock) {
            this.choosen = list;
            lock.notifyAll();
        }
    }
}