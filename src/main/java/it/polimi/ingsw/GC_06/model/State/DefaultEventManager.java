package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageGameStarted;
import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageActivatePopup;
import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageRankingPopUp;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Action.Actions.EndGameAction;
import it.polimi.ingsw.GC_06.model.Action.Actions.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.Action.EndGame.PersonalStatistics;
import it.polimi.ingsw.GC_06.model.Action.EndGame.Ranking;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Card.ExcomunicationCard;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.*;

/**
 * Created by massimo on 24/06/17.
 * this class represents the event manager for an entire game
 */
public class DefaultEventManager implements GameEventManager, Blocking {

    private final ServerOrchestrator serverOrchestrator;
    private final Game game;
    private final Map<Integer, ResourceSet> requirements;
    private int lastEra;
    private Map<String, Boolean> answersExcommunication = new HashMap<>();
    private final Map<Integer, List<ExcomunicationCard>> excomunicationCards;
    private List<Map<ActionType, Map<Integer, Effect>>> boards;
    private HeroCard[] heroCards;
    private int heroCardsNumber = Integer.parseInt(Setting.getInstance().getProperty("hero_cards_number"));


    public DefaultEventManager(ServerOrchestrator serverOrchestrator, Game game)
    {
        this.serverOrchestrator = serverOrchestrator;
        this.game = game;
        this.requirements = FileLoader.getFileLoader().loadChurchRequirement();
        this.heroCards = FileLoader.getFileLoader().loadHeroCards();


        List<ExcomunicationCard> excommCards = Arrays.asList(FileLoader.getFileLoader().loadExcommunication());


        // creiamo la lista con la lista



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

        // qui dovremmo caricare le carte


    }

    /**
     * this method is responsible for starting and getting the game in a ready to go status
     */
    public void start()
    {
        //Scegli i bonus personali

        List<Player> players = game.getRoundManager().getPlayers();
        PersonalBonusChoiceHandler personalBonusChoiceHandler = new PersonalBonusChoiceHandler(players);
        personalBonusChoiceHandler.execute(game, serverOrchestrator);



        //---- Notificare l'init
        MessageGameStarted messageGameStarted = new MessageGameStarted(game);
        //In futuro da togliere
        serverOrchestrator.send(game.getId(), messageGameStarted);

        //------- END MESSAGE



        List<HeroCard> heroCardList = new LinkedList<>();
        heroCardList.addAll(Arrays.asList(heroCards));

        List<HeroCard> selectedHeroCard = new LinkedList<>();

        for (Player player : players) {

            for (int i = 0; i < heroCardsNumber; i++) {
                Random rand = new Random();
                int n = rand.nextInt(heroCardList.size());
                HeroCard heroCard = heroCardList.get(n);
                selectedHeroCard.add(heroCard);
                heroCardList.remove(heroCardList.get(n));
            }

            player.setHeroCard(selectedHeroCard);
            selectedHeroCard = new LinkedList<>();

        }


        game.roll();

        for (Integer integer : excomunicationCards.keySet()) {
            List<ExcomunicationCard> excomunicationCardList = excomunicationCards.get(integer);
            Random random = new Random();
            int rndVal = random.nextInt(excomunicationCardList.size());

            ExcomunicationCard sortedCard = excomunicationCardList.get(rndVal);
            List<ExcomunicationCard> choosenCards = new LinkedList<>();
            choosenCards.add(sortedCard);
            excomunicationCards.replace(integer, choosenCards);

            // qui dovremmo distribuire ai giocatori le carte eroe


        }

    }

    /**
     * this method handles the succession of turns during the game
     * @param turn
     * @return the list of players affected by the method
     */
    @Override
    public synchronized List<Player> newTurn(int turn)
    {
        System.out.println("Nuovo turno");
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
            player.resetHeroCard();
        }

        game.getBoard().resetFamilyMembers();

        return players;
    }

    /**
     * this method handles the succession of eras during the game
     * @param era
     */
    @Override
    public synchronized void newEra(int era) {
        System.out.println("Nuova era");
        //gestione scomunica
        lastEra = era;
        handleExcomm();
    }

    /**
     * this method handles the succession of actions needed during the EndGame
     */
    @Override
    public synchronized void endGame() {
        lastEra++;
        handleExcomm();


        /** prendiamo la lista dei players*/
        List<Player> gamers = new LinkedList<>();
        Set<String> players = game.getGameStatus().getPlayers().keySet();

        for (String player : players) {
            gamers.add(game.getGameStatus().getPlayers().get(player));
        }
        EndGameAction endGameAction = new EndGameAction(gamers, Resource.VICTORYPOINT);
        List<PersonalStatistics> finalRanking = Ranking.getInstance().produceCurrentGameRanking(game);
        MessageRankingPopUp messageRanking = new MessageRankingPopUp(finalRanking);
        serverOrchestrator.send(game.getId(),messageRanking);

    }

    /**
     * this method handles the "rapporto al vaticano"
     */
    private synchronized void handleExcomm()
    {
        System.out.println("Invio scomuniche");
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
                serverOrchestrator.send(player, new MessageActivatePopup(ClientStateName.EXCOMMUNICATION));
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

    /**
     * this method associates an excomunication to a player
     * @param player the player that gets an excomunication
     */
    private void giveExcummunication(Player player)
    {
        try {
            List<ExcomunicationCard> excomunicationCard = excomunicationCards.get(lastEra-1);       //Ricevo la nuova era (es. alla fine della prima ho 2)
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
