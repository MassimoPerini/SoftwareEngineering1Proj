package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Tower;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.LinkedList;

/**
 * Created by massimo on 20/06/17.
 */
public class TowerPresenter {

    @Inject private LinkedList<String> tower;
 //   @Inject private MainClientModel mainClientModel;


    @PostConstruct public void init()
    {
        System.out.println(tower.get(0));
        //Injector.setConfigurationSource(getParameters().getNamed()::get);

      // List<ClientTowerFloor> mainClientModel.getClientBoardGame().getTowersClient().get(color);
    }

}
