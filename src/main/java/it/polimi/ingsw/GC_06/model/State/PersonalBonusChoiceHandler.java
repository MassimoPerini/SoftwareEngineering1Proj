package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageChoosePersonalBonus;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.PersonalBonusTile;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 27/06/17.
 */
public class PersonalBonusChoiceHandler implements Blocking {

    private List<Player> players;
    Integer choice;

    public PersonalBonusChoiceHandler(List<Player> players)
    {
        this.players = players;
    }

    public synchronized void execute(Game game, ServerOrchestrator serverOrchestrator){
        List<PersonalBonusTile> boards = FileLoader.getFileLoader().loadPersonalBonus();
        List<String> freeIndexes = new LinkedList<>();
        for (int i=0;i<boards.size();i++)
        {
            freeIndexes.add(boards.get(i).getId());
        }

        for (int i=players.size()-1;i>=0;i--)
        {
            choice = null;
            MessageServer messageServer = new MessageChoosePersonalBonus(freeIndexes);
            serverOrchestrator.send(players.get(i).getPLAYER_ID(), messageServer);
            GameList.getInstance().setCurrentBlocking(game, this);

            try {
                while (choice == null) {
                    System.out.println("Waiting!, game "+game.getId());
                    wait();
                }
            }catch (InterruptedException e)
            {
                System.out.println("EXCEPTION!");
                continue;
            }
            System.out.println("Thread wake up");
            for (PersonalBonusTile board : boards) {
                if (board.getId().equals(freeIndexes.get(choice)))
                {
                    players.get(i).addPersonalBonus(board);
                    freeIndexes.remove(board.getId());
                    break;
                }
            }
        }


    }


    @Override
    public synchronized void setOptionalParams(Object object)
    {
        choice = (Integer) object;
        notifyAll();
    }

    @Override
    public void userLoggedOut(String user) {

    }
}
