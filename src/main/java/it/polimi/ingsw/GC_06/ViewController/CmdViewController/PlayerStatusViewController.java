package it.polimi.ingsw.GC_06.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.View.CmdView;
import it.polimi.ingsw.GC_06.View.CommandView;
import it.polimi.ingsw.GC_06.ViewController.NavigationController;
import it.polimi.ingsw.GC_06.ViewController.ViewController;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 27/05/17.
 */
public class PlayerStatusViewController implements ViewController {

    private CommandView commandView;

    public PlayerStatusViewController()
    {
        commandView = new CmdView();
    }

    @Override
    public void viewDidLoad() {

    }

    @Override
    public void viewWillAppear() {
        Player player = NavigationController.getInstance().getPlayer();
        int i=1;
        for (FamilyMember familyMember : player.getFamilyMembers())
        {
            commandView.addLocalizedText("family_member");
            commandView.addText(": "+i+"\n");
            commandView.addLocalizedText("value");
            commandView.addText(": "+familyMember.getValue()+"\n");
            commandView.addLocalizedText("color");
            commandView.addText(" "+familyMember.getPlayerUserName()+"\n");

            i++;
        }

        //TODO KEEP IMPL...

        commandView.print();
        NavigationController.getInstance().dismissViewController();
    }

    @Override
    public void viewWillDisappear() {

    }

    @Override
    public void viewDidUnload() {

    }
}
