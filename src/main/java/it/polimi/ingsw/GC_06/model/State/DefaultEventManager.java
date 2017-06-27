package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageUpdateState;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Action.Actions.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Card.ExcomunicationCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.*;

/**
 * Created by massimo on 24/06/17.
 */
public class DefaultEventManager implements GameEventManager, Blocking {

    private final ServerOrchestrator serverOrchestrator;
    private final Game game;
    private final Map<Integer, ResourceSet> requirements;
    private int lastEra;
    private Map<String, Boolean> answersExcommunication = new HashMap<>();
    private final Map<Integer, List<ExcomunicationCard>> excomunicationCards;
    private List<Map<ActionType, Map<Integer, Effect>>> boards;


    public DefaultEventManager(ServerOrchestrator serverOrchestrator, Game game)
    {
        this.serverOrchestrator = serverOrchestrator;
        this.game = game;
        this.requirements = FileLoader.getFileLoader().loadChurchRequirement();


        List<ExcomunicationCard> excommCards = Arrays.asList(FileLoader.getFileLoader().loadExcommunication());

        excomunicationCards = new HashMap<>();
        for (ExcomunicationCard excommCard : excommCards) {
            List<ExcomunicationCard> excomunicationCardList = this.excomunicationCards.get(excommCard.getEra());
            if (excomunicationCardList ==null)
            {
                excomunicationCardList = new ArrayList<>();
                excomunicationCards.put(excommCard.getEra(), excomunicationCardList);
            }
            excomunicationCardList.add(excommCard);
        }


    }

    public void start()
    {
        //Scegli i bonus personali

        List<Player> players = game.getRoundManager().getPlayers();
        PersonalBonusChoiceHandler personalBonusChoiceHandler = new PersonalBonusChoiceHandler(players);
        personalBonusChoiceHandler.execute(game, serverOrchestrator);

        game.roll();

        for (Integer integer : excomunicationCards.keySet()) {
            List<ExcomunicationCard> excomunicationCardList = excomunicationCards.get(integer);
            Random random = new Random();
            int rndVal = random.nextInt(excomunicationCardList.size());

            ExcomunicationCard sortedCard = excomunicationCardList.get(rndVal);
            List<ExcomunicationCard> choosenCards = new LinkedList<>();
            choosenCards.add(sortedCard);
            excomunicationCards.replace(integer, choosenCards);
        }

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
                //Execute excommunications
                giveExcummunication(realPlayer);
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
                giveExcummunication(currPlayer);
            }
            else
            {
                //No scomunica
                currPlayer.variateResource(excomm);
            }
        }
        answersExcommunication = new HashMap<>();
    }

    private void giveExcummunication(Player player)
    {
        try {
            List<ExcomunicationCard> excomunicationCard = excomunicationCards.get(lastEra);
            for (ExcomunicationCard card : excomunicationCard) {
                List<Effect> effects = card.getEffects();
                ExecuteEffects executor = new ExecuteEffects(effects, player, game);
                executor.execute();
            }
        }
        catch (InterruptedException e){}
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
