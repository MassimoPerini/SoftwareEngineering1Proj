package it.polimi.ingsw.GC_06.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.View.CmdView;
import it.polimi.ingsw.GC_06.View.CommandView;
import it.polimi.ingsw.GC_06.ViewController.MainController;
import it.polimi.ingsw.GC_06.ViewController.NavigationController;
import it.polimi.ingsw.GC_06.ViewController.ViewController;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Network.NetworkAdapter;
import it.polimi.ingsw.GC_06.model.Network.TestAdapter;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 27/05/17.
 */
public class PowerUpFamilyMemberViewController implements ViewController {

    private CommandView commandView;
    private Player player;
    private boolean firstTime;

    public PowerUpFamilyMemberViewController()
    {
        super();
        commandView = new CmdView();
    }

    @Override
    public void viewDidLoad() {
        System.out.println("DEBUG - viewDidLoad invoked!");
        firstTime = true;
    }

    @Override
    public void viewWillAppear() {
        System.out.println("DEBUG - viewWillAppear invoked!");
        if (firstTime)
        {
            NavigationController.getInstance().pushViewController(new PlayerStatusViewController());
        }


        commandView.addLocalizedText("powerup_qst_fam");

        String input = commandView.getString();


        //TODO centralizzare il controllo

        while (!CmdViewUtils.validateInt(input, 0 , NavigationController.getInstance().getPlayer().getFamilyMembers().length))
        {
            commandView.addLocalizedText("type_again");
            input = commandView.getString();
        }

        int familyMember = Integer.parseInt(input);
        familyMember--;

        commandView.addLocalizedText("powerup_qst_val");
        input = commandView.getString();

        while (!CmdViewUtils.validateInt(input, 0 ,Integer.MAX_VALUE))
        {
            commandView.addLocalizedText("type_again");
            input = commandView.getString();
        }

        int points = Integer.parseInt(input);
        MainController mainController = new MainController();
        Action action = mainController.powerUpFamilyMember(points, familyMember);

        if (action.isAllowed())
        {
            NetworkAdapter networkAdapter = new TestAdapter();
            networkAdapter.send(action);
        }
        else
        {
            commandView.addLocalizedText("action_wrong");
            NavigationController.getInstance().dismissViewController();
        }


    }

    @Override
    public void viewWillDisappear() {
        firstTime = false;
        System.out.println("DEBUG - viewWillDisappear invoked!");
    }

    @Override
    public void viewDidUnload() {
        System.out.println("DEBUG - viewDidUnload invoked!");

    }
}
