package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageChooseParchment;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.Arrays;
import java.util.List;



/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnParchment implements Effect {

    private List<ResourceSet> parchments;
    private boolean isDifferent;

    public EffectOnParchment() {
        super();
    }

    @Override
    public void execute (Player player,Game game) {
        FileLoader fileLoader = FileLoader.getFileLoader();
        parchments = Arrays.asList(fileLoader.loadParchments());
        MessageChooseParchment messageChooseParchment = new MessageChooseParchment(parchments);
        game.getGameStatus().changeState(TransitionType.CHOOSE_PARCHMENT, messageChooseParchment);
        }

    public void setDifferent(boolean different) {
        isDifferent = different;
    }
}