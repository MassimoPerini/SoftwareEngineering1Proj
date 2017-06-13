package it.polimi.ingsw.GC_06.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.View.CmdView;
import it.polimi.ingsw.GC_06.View.CommandView;
import it.polimi.ingsw.GC_06.ViewController.ViewController;

/**
 * Created by massimo on 27/05/17.
 */
public class StartViewController /**implements ViewController*/{
/**
    private CommandView commandView;
    private ViewController [] nextController = new ViewController[6];


    public StartViewController()
    {
        super();
        commandView = new CmdView();
    }


    private void init()
    {
        nextController[0] = new TowerActionViewController();
        nextController[1] = new PowerUpFamilyMemberViewController();
        nextController[2] = null;
        nextController[3] = null;
        nextController[4] = null;
        nextController[5] = null;
    }

    @Override
    public void viewWillAppear() {
        System.out.println("DEBUG - viewWillAppear invoked!");

        init();

        commandView.addLocalizedText("welcome_msg");
        commandView.addLocalizedText("start_qst");
        commandView.addText("\n"+"1: ");
        commandView.addLocalizedText("action_place_familymember");
        commandView.addText("\n"+"2: ");
        commandView.addLocalizedText("action_power_up");
        commandView.addText("\n"+"3: ");
        commandView.addLocalizedText("action_play_hero");
        commandView.addText("\n"+"4: ");
        commandView.addLocalizedText("action_discard_hero");
        commandView.addText("\n"+"5: ");
        commandView.addLocalizedText("action_next_player");

        String answ = commandView.getString();
        while (!CmdViewUtils.validateInt(answ, 1, 5))
        {
            commandView.addLocalizedText("type_again");
            answ = commandView.getString();
        }

        int res = Integer.parseInt(answ);
        res--;

        //Unload...
        for (int i=0;i<nextController.length;i++)
        {
            if (i!=res)
                nextController[i]=null;
        }
        System.gc();

        nextController[res].viewWillAppear();

    }*/
}
