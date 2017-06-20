package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Tower;

import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by massimo on 20/06/17.
 */
public class TowerPresenter {

    @Inject private String color;
    @Inject private MainClientModel mainClientModel;


    @PostConstruct
    public void init()
    {
        System.out.println("TORRE: "+color);
      // List<ClientTowerFloor> mainClientModel.getClientBoardGame().getTowersClient().get(color);
    }

}
