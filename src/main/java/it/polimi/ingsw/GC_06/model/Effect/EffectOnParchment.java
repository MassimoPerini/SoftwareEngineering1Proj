package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageChooseParchment;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnParchment implements Effect, Blocking {

    private List<ResourceSet> parchments;
    private boolean different;
    private Integer choosen;
    private int quantity;

    public EffectOnParchment(int numbers, boolean different) {
        super();
        this.quantity = numbers;
        this.different = different;
    }

    @Override
    public void execute (Player player,Game game) {
        FileLoader fileLoader = FileLoader.getFileLoader();
        parchments = Arrays.asList(fileLoader.loadParchments());
        List<Integer> alreadyChoosed = new LinkedList<>();

        for (int i=0;i<quantity;i++) {
            MessageChooseParchment messageChooseParchment = new MessageChooseParchment(parchments, "");
            game.getGameStatus().sendMessage(messageChooseParchment);
            GameList.getInstance().setCurrentBlocking(game, this, messageChooseParchment);
            System.out.println("THREAD ATTIVO!");
            int choice = choosen;
            int isAlreadySelected = Collections.binarySearch(alreadyChoosed, choice);
            if (isAlreadySelected!=-1 && different)
            {
                messageChooseParchment = new MessageChooseParchment(parchments, "Presente, devono essere diversi");
                GameList.getInstance().setCurrentBlocking(game, this, messageChooseParchment);
                i--;
                waitAnswer();
            }
            else {
                player.variateResource(parchments.get(choice));
                choosen = null;
            }
        }
    }

    private void waitAnswer()
    {
        while (choosen == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread svegliato");
        }
    }

    @Override
    public void setOptionalParams(Object list) {
            this.choosen = (Integer) list;
            notifyAll();
    }

}