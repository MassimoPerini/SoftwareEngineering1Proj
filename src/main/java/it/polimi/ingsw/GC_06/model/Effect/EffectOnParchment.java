package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

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

    private List<ResourceSet> parchments;

    public EffectOnParchment(List<ResourceSet> parchments) {

        this.parchments = parchments;


    }

    @Override
    public void execute (Player player){
        Game.getInstance().getGameStatus().changeState(TransitionType.CHOOSING_PARCHMENT, parchments);
        }
    }