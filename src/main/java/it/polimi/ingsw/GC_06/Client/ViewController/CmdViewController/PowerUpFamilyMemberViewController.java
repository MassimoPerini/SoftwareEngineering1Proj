package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

/**
 * Created by massimo on 27/05/17.
 */
public class PowerUpFamilyMemberViewController /**implements ViewPresenterCLI*/ {

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
        ClientNetworkOrchestrator mainController = new ClientNetworkOrchestrator();
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
