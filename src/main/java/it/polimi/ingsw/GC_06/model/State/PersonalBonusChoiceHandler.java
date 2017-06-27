package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageChoosePersonalBonus;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 27/06/17.
 */
public class PersonalBonusChoiceHandler /*implements Blocking*/ {
/**
    private List<Player> players;
    Integer choice;

    public PersonalBonusChoiceHandler(List<Player> players)
    {
        this.players = players;
    }

    public synchronized void execute(Game game, ServerOrchestrator serverOrchestrator){
        List<Map<ActionType, Map<Integer, Effect>>> boards = new LinkedList<>();
        List<Integer> freeIndexes = new LinkedList<>();

        for (int i=0;i<boards.size();i++)
        {
            freeIndexes.add(i);
        }

        for (int i=players.size()-1;i>=0;i--)
        {
            choice = null;
            MessageServer messageServer = new MessageChoosePersonalBonus(freeIndexes);
  //TODO da sistemare con urgenza!!!!             serverOrchestrator.send(players.get(i).getPLAYER_ID(), messageServer);
            GameList.getInstance().setCurrentBlocking(game, this);
            try {
                while (choice != null) {
                    wait();
                }
            }catch (InterruptedException e)
            {

            }
            Map<ActionType, Map<Integer, Effect>> choosen = boards.get(freeIndexes.get(choice));
            players.get(i).setPersonalBonus(choosen);
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

    }*/
}
