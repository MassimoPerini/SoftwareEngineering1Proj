package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;



/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnParchment implements Effect {

    private ArrayList<ResourceSet> parchments;
    // uso uno scanner per imitare la view, andrà sostituito poi con la view vera
    private Scanner view = new Scanner(System.in);
    private String choice;

    public EffectOnParchment(ArrayList<ResourceSet> parchments) {

        this.parchments = parchments;


    }

    @Override
    public void execute (Player player){
        Game.getInstance().getGameStatus().changeState(TransitionType.CHOOSING_PARCHMENT, parchments );
        }
    }