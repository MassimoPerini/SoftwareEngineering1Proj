package it.polimi.ingsw.GC_06.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.View.CmdView;
import it.polimi.ingsw.GC_06.View.CommandView;
import it.polimi.ingsw.GC_06.ViewController.MainController;
import it.polimi.ingsw.GC_06.ViewController.ViewController;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.Cancel.NetworkAdapter;
import it.polimi.ingsw.GC_06.model.Cancel.TestAdapter;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 27/05/17.
 */
public class PowerUpFamilyMemberViewController /**implements ViewController*/ {

    /**private CommandView commandView;
    private Player player;

    public PowerUpFamilyMemberViewController()
    {
        super();
        commandView = new CmdView();
    }


    @Override
    public void viewWillAppear() {
        System.out.println("DEBUG - viewWillAppear invoked!");

        new PlayerStatusViewController().viewWillAppear();

        commandView.addLocalizedText("powerup_qst_fam");

        String input = commandView.getString();


        //TODO centralizzare il controllo

        while (!CmdViewUtils.validateInt(input, 0 , CmdViewUtils.getCurrentPlayer().getFamilyMembers().length))
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
        ClientOrchestrator mainController = new ClientOrchestrator();
        Action action = new PowerUpFamilyMember(player,points,familyMember);;

        if (action.isAllowed())
        {
            NetworkAdapter networkAdapter = new TestAdapter();
            networkAdapter.send(action);
        }
        else
        {
            commandView.addLocalizedText("action_wrong");
        }


    }*/

}
