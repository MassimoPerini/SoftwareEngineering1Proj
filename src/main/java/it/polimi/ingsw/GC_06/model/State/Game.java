package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 27/05/17.
 */
public class Game {

    private final Board board;
    private final DiceSet diceSet;
    private final DevelopmentCard[] developmentCards;
    private static Game game;

    //params
    private final int maxPlayers, minPlayers;
    private final int neutralFamilyMembers;
    private final GameStatus gameStatus;
    private final ResourceSet [] startResources;

    //keys
    private static final String MINPLAYERSKEY = "min_players";
    private static final String MAXPLAYERSKEY = "max_players";
    private static final String NEUTRALFAMILYMEMBERSKEY = "neutral_family_members";

    //TODO TO REMOVE
    public static void clearForTesting()
    {
        game=null;
    }

    private Game() throws IOException {
        FileLoader f = FileLoader.getFileLoader();
        board = f.loadBoard();
        developmentCards = f.loadCards();
        diceSet = f.loadDiceSet();
        maxPlayers = Integer.parseInt(Setting.getInstance().getProperty(MAXPLAYERSKEY));
        minPlayers = Integer.parseInt(Setting.getInstance().getProperty(MINPLAYERSKEY));
        neutralFamilyMembers = Integer.parseInt(Setting.getInstance().getProperty(NEUTRALFAMILYMEMBERSKEY));
        startResources = f.loadDefaultResourceSets();
        //load from file
        gameStatus = new GameStatus(this.generateStatuses());
    }




    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void start()
    {
        //mix the players
        Collections.shuffle(gameStatus.getPlayers());
        int i=0;

        //Adding start resources
        for (Player player : gameStatus.getPlayers())
        {
            player.variateResource(startResources[i]);
            i++;
        }

        //set the first player "ready", the others busy
    }

    void newTurn()
    {

    }

    void newEra()
    {

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


    public void addPlayer (String p) throws IllegalStateException
    {
        if (p==null)
            throw new NullPointerException();
        if (gameStatus.getPlayers().size() >= maxPlayers)
            throw new IllegalStateException("too many players");
        if (!gameStatus.isAllowedAddPlayer(p))
            throw new IllegalStateException("username already present");
        Player player = new Player(p, this.createFamilyMembers(p));
        gameStatus.addPlayer(player);
    }

    private FamilyMember[] createFamilyMembers(String playerID)
    {
        int i=0;
        FamilyMember[] familyMembers = new FamilyMember[diceSet.getDices().length+neutralFamilyMembers];

        for (i=0;i<diceSet.getDices().length;i++) {
            familyMembers[i] = new FamilyMember(diceSet.getDices()[i].getColor().name(), playerID);
            diceSet.getDices()[i].addObserver(familyMembers[i]);
        }
        for (i=0;i<neutralFamilyMembers;i++)
        {
            familyMembers[diceSet.getDices().length+i]=new FamilyMember("",playerID);
        }
        return familyMembers;
    }

    public FsmNode generateStatuses()
    {
        //Loaded from file?

        //Init states and state transition table for each state
        FsmNode node1 = new State(StateName.IDLE);
        FsmNode node2 = new State(StateName.PLACE_FAM_MEM);
        FsmNode node3 = new State(StateName.PICK_CARD);

        node1.addTransition(TransitionType.ADDFAMILYMEMBER, node2);
        node2.addTransition(TransitionType.PAYCARD, node3);
        node3.addTransition(TransitionType.END, node1);

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
}
