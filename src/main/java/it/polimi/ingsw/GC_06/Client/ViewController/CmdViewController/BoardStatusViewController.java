package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

/**
 * Created by massimo on 01/06/17.
 */
public class BoardStatusViewController /**implements ViewController*/ {
    /**
    AsciiTable at;

    public BoardStatusViewController()
    {
        AsciiTable at = new AsciiTable();
    }

    @Override
    public void viewWillAppear() {

        Board board = Game.getInstance().getBoard();
        List<Tower> towers = board.getTowers();

        for (Tower tower : towers)
        {
            int max = tower.getTowerFloor().size();

            for (int i=0;i<max;i++)
            {
                TowerFloor towerFloor = tower.getTowerFloor().get(i);
                if (towerFloor.getCard() != null) {
                    List<Requirement> requirements = towerFloor.getCard().getRequirements();
                    towerFloor.getCard().getIdColour();
                    //TODO description
                }
                List<FamilyMember> familyMembers = towerFloor.getActionPlace().getMembers();
            }
        }

        List<ProdHarvZone> prodHarvZones = board.getProdHarvZones();
        for (ProdHarvZone prodHarvZone : prodHarvZones)
        {
            List<ActionPlace> actionPlaces = prodHarvZone.getActionPlaces();
        }

        //ecc...


    }*/
}