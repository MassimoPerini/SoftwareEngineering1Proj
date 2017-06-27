package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageUpdateState;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 24/06/17.
 */
public class DefaultEventManager implements GameEventManager, Blocking {

    private final ServerOrchestrator serverOrchestrator;
    private final Game game;
    private final Map<Integer, ResourceSet> requirements;
    private int lastEra;
    private Map<String, Boolean> answersExcommunication = new HashMap<>();

    public DefaultEventManager(ServerOrchestrator serverOrchestrator, Game game)
    {
        this.serverOrchestrator = serverOrchestrator;
        this.game = game;
        this.requirements = FileLoader.getFileLoader().loadChurchRequirement();
    }

    public void start()
    {
        game.roll();
    }

    @Override
    public synchronized List<Player> newTurn(int turn)
    {
        //reset bonus malus o altro
        game.roll();

        //Handle the new order
        List<FamilyMember> familyMembersCouncil = game.getBoard().getCouncils().get(0).getActionPlaces().get(0).getMembers();
        List<String> playersTurn = new LinkedList<>();
        //Save the name of the players inside the council
        for (FamilyMember familyMember : familyMembersCouncil) {
            if (!playersTurn.contains(familyMember.getPlayerUserName()))
            {
                playersTurn.add(familyMember.getPlayerUserName());
            }
        }

        //saving those who are not in the council
        for (Player player : game.getRoundManager().getPlayers()) {
            String playerUser = player.getPLAYER_ID();
            if (!playersTurn.contains(playerUser))
            {
                playersTurn.add(playerUser);
            }
        }

        List<Player> players = new LinkedList<>();
        //Getting the Players
        for (String namePlayer : playersTurn) {
            players.add(game.getGameStatus().getPlayers().get(namePlayer));
        }

        for (Player player : players) {

            int lenght =  player.getFamilyMembers().length;
            for(int i = 0; i < lenght; i++){
                FamilyMember familyMember = player.getFamilyMembers()[i];
                int value = BonusMalusHandler.filter(player, ActionType.STARTING_SETTING,familyMember.getValue(),familyMember.getDiceColor());
                familyMember.setValue(value);
            }
        }

        return players;
    }

    @Override
    public synchronized void newEra(int era) {
        //gestione scomunica
        lastEra = era;
        handleExcomm();
    }

    @Override
    public synchronized void endGame() {
        lastEra++;
        handleExcomm();

    }

    private synchronized void handleExcomm()
    {
        game.getGameStatus().changeState(TransitionType.START_VATICAN);
        ResourceSet excomm = this.requirements.get(lastEra);
        if (excomm==null)
        {
            return;
        }
        int playerAskExcomm = 0;
        for (String player : game.getGameStatus().getPlayers().keySet()) {
            Player realPlayer = game.getGameStatus().getPlayers().get(player);
            if (realPlayer.isAllowedVariate(excomm) && realPlayer.isConnected())
            {
                serverOrchestrator.send(player, new MessageUpdateState(ClientStateName.EXCOMMUNICATION));
                playerAskExcomm++;
            }
            else
            {
                // TODO ATTIVA SCOMUNICA AL GIOCATORE
            }
        }
        while (answersExcommunication.size()<playerAskExcomm)
        {
            GameList.getInstance().setCurrentBlocking(game, this);
            try {
                wait();
            } catch (InterruptedException e) {
                playerAskExcomm--;
            }
        }

        for (String player : answersExcommunication.keySet()) {
            Player currPlayer = game.getGameStatus().getPlayers().get(player);
            if (answersExcommunication.get(player))
            {
                //TODO l'utente vuole essere scomunicato
            }
            else
            {
                //No scomunica
                currPlayer.variateResource(excomm);
            }
        }
        answersExcommunication = new HashMap<>();
    }

    @Override
    public synchronized void setOptionalParams(Object object) {
        Map<String, Boolean> answer = (Map<String, Boolean>) object;
        answersExcommunication.putAll(answer);
        notifyAll();
    }

    @Override
    public void userLoggedOut(String user) {
        answersExcommunication.put(user, true);
        notifyAll();
    }
}
