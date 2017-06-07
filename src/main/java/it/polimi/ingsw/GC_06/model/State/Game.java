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

    //TODO TO REMOVE (KEEP SINGLETON)
    public static void clearForTesting()
    {
        game=null;
    }

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

    public void endTurn()
    {
        roundManager.endTurn();
    }

    public static Game getInstance(){
        if (game==null) {
            try {
                game = new Game();
            }catch (Exception e)
            {
                System.out.println("Game class: error reading a file");
            }
        }
        return game;
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

    /**
     * Generates the state of the game
     * //TODO load from file.
     * @return
     */
    private FsmNode generateStatuses()
    {
        //Loaded from file?

        //Init states and state transition table for each state
        State node1 = new State(StateName.IDLE);
        State node2 = new State(StateName.PLACE_FAM_MEM);
        State node3 = new State(StateName.PICK_CARD);
        State node4 = new State(StateName.CHOOSE_PARCHMENT);

        node1.addTransition(TransitionType.ADDFAMILYMEMBER, node2);
        node2.addTransition(TransitionType.PAYCARD, node3);
        node3.addTransition(TransitionType.EXECUTEEFFECT, node1);
        node4.addTransition(TransitionType.CHOOSING_PARCHMENT, node1);

        statuses.put(node1.getID(), node1);
        statuses.put(node2.getID(), node2);
        statuses.put(node3.getID(), node3);
        statuses.put(node4.getID(), node4);

        return node1;

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
