package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.util.*;

/**
 * Created by massimo on 27/05/17.
 * @author massimo
 * This is the main Game file
 * SINGLETON
 */
public class Game {
    private final int id;
    private final Board board;
    private final DiceSet diceSet;
    private final RoundManager roundManager;
    private static Game game;

    //params
    private final int neutralFamilyMembers;
    private final GameStatus gameStatus;
    private final Map<StateName, FsmNode> statuses;

    //keys
    private static final String NEUTRALFAMILYMEMBERSKEY = "neutral_family_members";


    /**
     * Load from file the needed values. If it can't do this, throws an exception
     * @throws IOException
     */
    public Game() throws IOException {
        FileLoader f = FileLoader.getFileLoader();
        board = f.loadBoard();
        diceSet = f.loadDiceSet();
        neutralFamilyMembers = Integer.parseInt(Setting.getInstance().getProperty(NEUTRALFAMILYMEMBERSKEY));
        //load from file
        this.statuses = new HashMap<>();
        this.generateStatuses();
        gameStatus = new GameStatus(this.statuses.get(StateName.IDLE));
        roundManager = new RoundManager(board, neutralFamilyMembers+diceSet.getDices().length);
        id = 1;     //TODO
    }

    public void addPlayer (String p) throws IllegalStateException, IllegalArgumentException
    {
        if (p==null)
            throw new NullPointerException();
        Player player = new Player(p, this.createFamilyMembers(p));
        gameStatus.addPlayer(player);
        roundManager.addPlayer(player);

    }

    public void start(){
        gameStatus.start();
        roundManager.start();
    }

    public int getPlayerNumber(){
        return gameStatus.getPlayers().size();
    }

    public void endTurn()
    {
        roundManager.endTurn();
    }


    public Player getCurrentPlayer()
    {
        return roundManager.getCurrentPlayer();
    }

    /**
     * Generate all the familymembers of the player (with an observer on the relative dice)
     * @param playerID
     * @return
     */
    private FamilyMember[] createFamilyMembers(String playerID)
    {
        int i=0;
        FamilyMember[] familyMembers = new FamilyMember[diceSet.getDices().length+neutralFamilyMembers];

        for (i=0;i<diceSet.getDices().length;i++) {
            familyMembers[i] = new FamilyMember(diceSet.getDices()[i].getColor(), playerID);
            diceSet.getDices()[i].addObserver(familyMembers[i]);
        }
        for (i=0;i<neutralFamilyMembers;i++)
        {
            familyMembers[diceSet.getDices().length+i]=new FamilyMember("",playerID);
        }
        return familyMembers;
    }

    public int getId() {
        return id;
    }

    /**
     * Generates the state of the game
     * //TODO load from file.
     * @return
     */
    private FsmNode generateStatuses()
    {
        //Loaded from file?

        //Init states and state transition table for each state
        State idle  = new State(StateName.IDLE);
        State memberOnTower = new State(StateName.MEMBER_ON_TOWER);
        State choosingPayment = new State(StateName.CHOOSING_PAYMENT);
        State cardReady = new State(StateName.CARD_READY);
        State cardToUser = new State(StateName.CARD_TO_USER);
        State choosingCard = new State(StateName.CHOOSING_CARD);
        State memberOnProdHarv = new State(StateName.MEMBER_ON_PROFHARV);
        State choosingProdHarvCards = new State(StateName.CHOOSING_PRODHARV_CARDS);
        State memberOnMarketCounsil = new State(StateName.MEMBER_ON_MARKETCOUNSIL);
        State choosingParchment = new State(StateName.CHOOSING_PARCHMENT);
        State choosingSupportVatican = new State(StateName.CHOOSING_SUPPORT_VATICAN);

        idle.addTransition(TransitionType.EXECUTE_EFFECT, idle);
        idle.addTransition(TransitionType.ACTION_ON_TOWER, memberOnTower);
        idle.addTransition(TransitionType.ACTION_ON_PRODHARV, memberOnProdHarv);
        idle.addTransition(TransitionType.ACTION_ON_MARKETCOUNSIL, memberOnMarketCounsil);
        idle.addTransition(TransitionType.SIDE_WITH_VATICAN, choosingSupportVatican);
        memberOnTower.addTransition(TransitionType.ASK_PAYMENT, choosingPayment);
        memberOnTower.addTransition(TransitionType.PAY_CARD, cardReady);
        choosingPayment.addTransition(TransitionType.PAY_CARD, cardReady);
        cardReady.addTransition(TransitionType.PICK_CARD, cardToUser);
        cardToUser.addTransition(TransitionType.BONUS_CARD, choosingCard);
        cardToUser.addTransition(TransitionType.EXECUTE_EFFECT, idle);
        choosingCard.addTransition(TransitionType.EXECUTE_EFFECT, idle);
        memberOnProdHarv.addTransition(TransitionType.START_PRODHARV, choosingProdHarvCards);
        memberOnProdHarv.addTransition(TransitionType.PRODHARV, idle);
        choosingProdHarvCards.addTransition(TransitionType.PRODHARV, idle);
        memberOnMarketCounsil.addTransition(TransitionType.EXECUTE_EFFECT, idle);
        memberOnMarketCounsil.addTransition(TransitionType.CHOOSE_PARCHMENT, choosingParchment);
        choosingParchment.addTransition(TransitionType.PARCHMENT_CHOICE_DONE, idle);
        choosingSupportVatican.addTransition(TransitionType.PAY_VATICAN, idle);
        choosingSupportVatican.addTransition(TransitionType.GET_EXCOMUNICATION, idle);

        statuses.put(idle.getID(), idle);
        statuses.put(memberOnTower.getID(), memberOnTower);
        statuses.put(choosingPayment.getID(), choosingPayment);
        statuses.put(cardReady.getID(), cardReady);
        statuses.put(cardToUser.getID(), cardToUser);
        statuses.put(choosingCard.getID(), choosingCard);
        statuses.put(memberOnProdHarv.getID(), memberOnProdHarv);
        statuses.put(choosingProdHarvCards.getID(), choosingProdHarvCards);
        statuses.put(memberOnMarketCounsil.getID(), memberOnMarketCounsil);
        statuses.put(choosingParchment.getID(), choosingParchment);
        statuses.put(choosingSupportVatican.getID(), choosingSupportVatican);

        return idle;

    }

    RoundManager getRoundManager() {
        return roundManager;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void roll()
    {
        diceSet.roll();
    }

    public Board getBoard() {
        return board;
    }

    public static String getNEUTRALFAMILYMEMBERSKEY() {
        return NEUTRALFAMILYMEMBERSKEY;
    }
}
