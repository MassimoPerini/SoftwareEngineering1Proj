package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.util.*;

/**
 * Created by massimo on 06/06/17.
 */
public class RoundManager {

    private int currentPlayer,familyMembersPlaced, turn, era, nMaxFamilyMembers;
    private List<Player> players;

    private static final String MAXERA = "eras";
    private static final String MAXTURNS = "turns";
    private final ResourceSet[] startResources;
    private final List<DevelopmentCard> developmentCards;
    private final Board board;

    private final int maxEras;
    private final int maxTurns;


    public RoundManager(Board board, int nFamilyMembersMax) throws IOException {
        turn = 1;
        era = 1;
        currentPlayer = 0;
        familyMembersPlaced = 0;
        developmentCards = Arrays.asList(FileLoader.getFileLoader().loadCards());
        maxEras = Integer.parseInt(Setting.getInstance().getProperty(MAXERA));
        maxTurns = Integer.parseInt(Setting.getInstance().getProperty(MAXTURNS));
        this.players = new ArrayList<>();
        startResources = FileLoader.getFileLoader().loadDefaultResourceSets();
        this.nMaxFamilyMembers = nFamilyMembersMax;
        this.board = board;

    }

    void endTurn()
    {
        if (players.size()==0)
            throw new IllegalStateException();

        int newPlayer = (++currentPlayer) % players.size();
        if (currentPlayer > newPlayer)      //means that I'm starting again the "cycle"
        {
            int newFamilyMemberPlaced = (++familyMembersPlaced) % nMaxFamilyMembers;
            if (newFamilyMemberPlaced < familyMembersPlaced) {      //I've placed the last family member (usually 4 familymembers for turn)
                //Finito un giro, ne rimangono n_familiari giri
                int newTurn = (++turn) % (maxTurns+1);
                if (turn > newTurn) {       //I've resetted the turn, so I have to change the era
                    newTurn++;
                    era = (++era) % (maxEras+1);
                    if (era == 0) {
                        era=1;
                        endGame();      //Do here what you need to do when the game finished
                    } else {
                        newEra();       //A new era started
                    }
                }
                turn = newTurn;
                this.newTurn();
            }
            familyMembersPlaced = newFamilyMemberPlaced;
        }
        currentPlayer = newPlayer;
//        Game.getInstance().getGameStatus().changeState(TransitionType.ROUNDFINISHED);
    }

    private void newTurn()
    {
        List<Tower> towers = board.getTowers();
        for (Tower tower: towers)
        {
        //    tower.shuffle(); TODO REIMPLEMENT
        }
    }

    private void newEra()
    {
     //   disposeCards(); TODO REIMPLEMENT
    }

    private void endGame()
    {
        System.out.println("ENDGAME REACHED!!!");
    //    Game.getInstance().getGameStatus().changeState(TransitionType.ROUNDFINISHED);

    }

    private void disposeCards()
    {
        Collections.shuffle(developmentCards);
        //multiplo di TowerFloor

        List <DevelopmentCard> useful = new LinkedList<>();
        for (DevelopmentCard developmentCard:developmentCards)
        {
            if (era == developmentCard.getEra())
                useful.add(developmentCard);
        }

        Map<String, List<DevelopmentCard>> cards = new HashMap<>();
        for (DevelopmentCard developmentCard:useful)
        {
            List <DevelopmentCard> tmp = cards.get(developmentCard.getIdColour());
            if (tmp == null)
            {
                tmp = new LinkedList<>();
                tmp.add(developmentCard);
                cards.put(developmentCard.getIdColour(), tmp);
            }
            else {
                tmp.add(developmentCard);
            }
        }

        for (Tower tower: board.getTowers())
        {
            tower.setCards(cards.get(tower.getColor()));
            tower.shuffle();
        }
    }

    void addPlayer (Player player)
    {
        this.players.add(player);
    }

    void start()
    {
        Collections.shuffle(this.players);
        int i = 0;

        for (Player player:this.players)
        {
            player.variateResource(startResources[i]);
        }
    //    disposeCards();

    }
    public Player getCurrentPlayer()
    {
        return players.get(currentPlayer);
    }

    public int getTurn() {
        return turn;
    }

    public int getEra() {
        return era;
    }

    public int getFamilyMembersPlaced() {
        return familyMembersPlaced;
    }

    public int getnMaxFamilyMembers() {
        return nMaxFamilyMembers;
    }

    public int getMaxEras() {
        return maxEras;
    }

    public int getMaxTurns() {
        return maxTurns;
    }
}