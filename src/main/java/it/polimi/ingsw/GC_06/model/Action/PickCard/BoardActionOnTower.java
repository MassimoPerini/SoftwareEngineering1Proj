package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public class BoardActionOnTower implements Action {

    private Tower tower;
    private int index;
    private Action pickCard;
    private FamilyMember familyMember;
    private Game game;
    private Player player;
    private final ActionType ACTION_TYPE = ActionType.TOWER_ACTION;

    public BoardActionOnTower(Player player, int index, Tower tower, FamilyMember familyMember,Game game) {
        super();
        if (player==null || tower==null)
            throw new NullPointerException();

        this.familyMember = familyMember;
        this.index = index;
        this.tower = tower;
        this.familyMember = familyMember;
        this.pickCard = new PickCard(player, tower, index, familyMember.getValue(),game);
        this.player = player;
    }

    @Override
    public void execute() {

        // farei l'eventuale modifica dell'azione qui tramite i bonus e malus
        // al momento modifichiamo il valore dell'azione che per come sono strutturate le azioni non cambia i controlli


     //   super.getBonusMalusHandler().filter(super.getPlayer(),super.getPlayType(),this);

        // soluzione temporanea = in questa azione di fatto posizioniamo soltanto il familiare

        //this.familyMember.getValue() = super.getValueAction();

        game.getGameStatus().changeState(TransitionType.ACTION_ON_TOWER);
        // qui modifichiamo il valore dell'azione prima che si compia
        //BonusMalusHandler.filter(player,ACTION_TYPE,tower.getColor(),familyMember);

        if (!isAllowed())
            throw new IllegalStateException();

        pickCard.execute();

        // qui faccio il malus
        tower.addFamilyMember(familyMember, index);

    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean isAllowed() {

        /** è permessa solo quando non c'è un familiare sulla torre*/

        return pickCard.isAllowed();

    }
}
