package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.Scanner;

import it.polimi.ingsw.GC_06.model.playerTools.Player;



/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnParchment implements Effect {


    private EffectOnResources applicationeEffect;
    private ResourceSet parchment1;
    private ResourceSet parchment2;
    private ResourceSet parchment3;
    private ResourceSet parchment4;
    private ResourceSet parchment5;
    // uso uno scanner per imitare la view, andrà sostituito poi con la view vera
    private Scanner view = new Scanner(System.in);
    private String choice;

    public EffectOnParchment(ResourceSet parchment1, ResourceSet parchment2, ResourceSet parchment3, ResourceSet parchment4, ResourceSet parchment5) {

        this.parchment1 = parchment1;
        this.parchment2 = parchment2;
        this.parchment3 = parchment3;
        this.parchment4 = parchment4;
        this.parchment5 = parchment5;


    }

    @Override
    public void execute (Player player){
            // TODO quando sarà fatta la view notify(player.view())
        System.out.print("selecet: parchment1, parchmnent2, parchment3, parchment4, parchment5");
        choice = view.next();
        switch (choice) {
            case "parchment1":
                    applicationeEffect = new EffectOnResources(parchment1);
                    applicationeEffect.execute(player);
                    System.out.print("applicated parchment1");
            case "parchment2":
                    applicationeEffect = new EffectOnResources(parchment2);
                    applicationeEffect.execute(player);
                System.out.print("applicated parchment2");
            case "parchment3":
                    applicationeEffect = new EffectOnResources(parchment3);
                    applicationeEffect.execute(player);
                System.out.print("applicated parchment3");
            case "parchment4":
                    applicationeEffect = new EffectOnResources(parchment4);
                    applicationeEffect.execute(player);
                System.out.print("applicated parchment4");
            case "parchment5":
                    applicationeEffect = new EffectOnResources(parchment5);
                    applicationeEffect.execute(player);
                System.out.print("applicated parchment5");
            }
        }
    }