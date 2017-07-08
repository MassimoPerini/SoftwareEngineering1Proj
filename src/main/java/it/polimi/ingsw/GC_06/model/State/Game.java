package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageUpdateState;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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


    //params
    private final int neutralFamilyMembers;
    private  GameStatus gameStatus;
    private final Map<StateName, FsmNode> statuses;

    //keys
    private static final String NEUTRALFAMILYMEMBERSKEY = "neutral_family_members";


    /**
     * Load from file the needed values. If it can't do this, throws an exception
     * @throws IOException
     */
    public Game(int id) throws IOException {
        FileLoader f = FileLoader.getFileLoader();
        board = f.loadBoard();
        diceSet = f.loadDiceSet();
        neutralFamilyMembers = Integer.parseInt(Setting.getInstance().getProperty(NEUTRALFAMILYMEMBERSKEY));
        //load from file
        this.statuses = new HashMap<>();
        this.generateStatuses();
        roundManager = new RoundManager(board, neutralFamilyMembers+diceSet.getDices().length);
        this.id = id;
        //THE LAST
        gameStatus = new GameStatus(this.statuses.get(StateName.IDLE));
    }

    public void addPlayer (String p) throws IllegalStateException, IllegalArgumentException
    {
        if (p==null)
            throw new NullPointerException();
        Player player = new Player(p, this.createFamilyMembers(p));
        gameStatus.addPlayer(player);
        roundManager.addPlayer(player);

    }

    public void start(GameEventManager gameEventManager){
        roundManager.setGameEventManager(gameEventManager);
        roundManager.start();
        gameStatus.start();
    }

    public int getPlayerNumber(){
        return gameStatus.getPlayers().size();
    }

    public void endTurn()
    {
        if (roundManager.endTurn()) {
            getGameStatus().changeState(TransitionType.NEXT_PLAYER);
        }
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

    public Map<StateName, FsmNode> getStatuses() {
        return statuses;
    }

    /**
     * Generates the state of the game
     * //TODO load from file.
     * @return
     */

    private FsmNode generateStatuses()
    {
        //Init states and state transition table for each state
        State idle  = new State(StateName.IDLE, new MessageUpdateState(ClientStateName.MY_TURN));
        State pickedCard = new State(StateName.PICKED_CARD);
        State productionHarvest = new State(StateName.PRODUCED_HARVESTED);
        State marketCouncil = new State(StateName.MARKET_COUNCIL);
        State choosingSupportVatican = new State(StateName.CHOOSING_SUPPORT_VATICAN);
        State turnActionCompleted = new State(StateName.TURN_ACTION_COMPLETED, new MessageUpdateState(ClientStateName.ACTION_FINISHED));
        State playHeroCard = new State(StateName.CHOOSE_HERO_CARD);

        idle.addTransition(TransitionType.ACTION_ON_TOWER, pickedCard);
        idle.addTransition(TransitionType.PLAY_HERO_CARD,playHeroCard);
        idle.addTransition(TransitionType.ACTION_ON_PRODHARV, productionHarvest);
        idle.addTransition(TransitionType.ACTION_ON_MARKETCOUNSIL, marketCouncil);
        idle.addTransition(TransitionType.ERROR,idle);
        pickedCard.addTransition(TransitionType.END_ACTION, turnActionCompleted);
        productionHarvest.addTransition(TransitionType.END_ACTION, turnActionCompleted);
        marketCouncil.addTransition(TransitionType.END_ACTION, turnActionCompleted);
        playHeroCard.addTransition(TransitionType.END_ACTION,idle);

        turnActionCompleted.addTransition(TransitionType.ERROR,turnActionCompleted);
        turnActionCompleted.addTransition(TransitionType.START_VATICAN, choosingSupportVatican);
        turnActionCompleted.addTransition(TransitionType.NEXT_PLAYER, idle);
        idle.addTransition(TransitionType.START_VATICAN, choosingSupportVatican);
        idle.addTransition(TransitionType.NEXT_PLAYER, idle);
        choosingSupportVatican.addTransition(TransitionType.NEXT_PLAYER, idle);

        statuses.put(StateName.CHOOSING_SUPPORT_VATICAN, choosingSupportVatican);
        statuses.put(StateName.MARKET_COUNCIL, marketCouncil);
        statuses.put(StateName.TURN_ACTION_COMPLETED, turnActionCompleted);
        statuses.put(StateName.IDLE, idle);
        statuses.put(StateName.PICKED_CARD, pickedCard);
        statuses.put(StateName.PRODUCED_HARVESTED, productionHarvest);

        return idle;

    }

    public RoundManager getRoundManager() {
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

    public DiceSet getDiceSet() {return diceSet;}
}
