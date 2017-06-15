package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

/**
 * Created by massimo on 27/05/17.
 */
public class PlayerStatusViewController /**implements ViewController*/ {

    /**private CommandView commandView;

    public PlayerStatusViewController()
    {
        commandView = new CmdView();
    }


    @Override
    public void viewWillAppear() {
        Player player = CmdViewUtils.getCurrentPlayer();
        int i=1;
        for (FamilyMember familyMember : player.getFamilyMembers())
        {
            commandView.addLocalizedText("family_member");
            commandView.addText(": "+i+"\n");
            commandView.addLocalizedText("value");
            commandView.addText(": "+familyMember.getValue()+"\n");
            commandView.addLocalizedText("color");
            commandView.addText(" "+familyMember.getDiceColor()+"\n");

            i++;
        }

        //TODO KEEP IMPL...

        commandView.print();
    }*/

}
