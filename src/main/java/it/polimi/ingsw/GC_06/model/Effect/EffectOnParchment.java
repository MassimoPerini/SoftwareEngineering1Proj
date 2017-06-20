package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;



/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnParchment implements Effect {

    private ResourceSet[] parchments;
    private boolean isDifferent;

    public EffectOnParchment() {
        super();
    }

    @Override
    public void execute (Player player,Game game) {
        FileLoader fileLoader = FileLoader.getFileLoader();
        parchments = fileLoader.loadParchments();
        game.getGameStatus().changeState(TransitionType.CHOOSE_PARCHMENT, parchments);
        }

    public void setDifferent(boolean different) {
        isDifferent = different;
    }
}