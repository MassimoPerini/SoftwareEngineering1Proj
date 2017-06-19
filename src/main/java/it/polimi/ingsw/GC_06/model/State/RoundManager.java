package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.Server.Message.Server.MessageChangePlayer;
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
public class RoundManager extends Observable {

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
        reset();
        developmentCards = Arrays.asList(FileLoader.getFileLoader().loadCards());
        maxEras = Integer.parseInt(Setting.getInstance().getProperty(MAXERA));
        maxTurns = Integer.parseInt(Setting.getInstance().getProperty(MAXTURNS));
        this.players = new ArrayList<>();
        startResources = FileLoader.getFileLoader().loadDefaultResourceSets();
        this.nMaxFamilyMembers = nFamilyMembersMax;
        this.board = board;

    }

    private void reset()
    {
        turn = 1;
        era = 1;
        currentPlayer = 0;
        familyMembersPlaced = 0;
    }

    void endTurn()
    {
        if (players.size()==0)
            throw new IllegalStateException();

        int newPlayer = (currentPlayer+1) % players.size();
        if (currentPlayer >= newPlayer)      //means that I'm starting again the "cycle"
        {
            int newFamilyMemberPlaced = (++familyMembersPlaced) % nMaxFamilyMembers;
            if (newFamilyMemberPlaced < familyMembersPlaced) {      //I've placed the last family member (usually 4 familymembers for turn)
                //Finito un giro, ne rimangono n_familiari giri
                int newTurn = (turn+1) % (maxTurns+1);
                if (turn > newTurn) {       //I've resetted the turn, so I have to change the era
                    newTurn++;
                    era = (era+1) % (maxEras+1);
                    if (era == 0) {
                        era=1;
                        endGame();      //Do here what you need to do when the game finished
                        reset();
                        return;
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


        MessageChangePlayer messageChangePlayer = new MessageChangePlayer(this.getCurrentPlayer().getPLAYER_ID(), era, turn);
        setChanged();
        notifyObservers(messageChangePlayer);

//        Game.getInstance().getGameStatus().changeState(TransitionType.ROUNDFINISHED);
    }

    private void shuffleCards()
    {
        Map<String, Tower> towers = board.getTowers();
        for (Tower tower : towers.values()) {
            tower.shuffle();
        }
    }

    private void newTurn()
    {
        // resetBonusMalus
        shuffleCards();
    }

    private void newEra()
    {
        disposeCards();
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

        for (Tower tower: board.getTowers().values())
        {
            tower.setCards(cards.get(tower.getColor()));
            if (cards.get(tower.getColor()).size() < tower.getTowerFloor().size()*maxTurns)
                throw new IllegalArgumentException("No enaugh cards");
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
            i++;
        }
        reset();
        newEra();
        newTurn();

        MessageChangePlayer messageChangePlayer = new MessageChangePlayer(this.getCurrentPlayer().getPLAYER_ID(), era, turn);
        setChanged();
        notifyObservers(messageChangePlayer);

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
