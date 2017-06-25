package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public class BoardActionOnTower implements Action, Runnable {

    private final Tower tower;
    private final int index;
    private final Action payCard;
    private final FamilyMember familyMember;
    private final Game game;
    private final Player player;
    private final ActionType ACTION_TYPE = ActionType.TOWER_ACTION;

    public BoardActionOnTower(Player player, int floor, Tower tower, FamilyMember familyMember,Game game) {
        super();
        if (player==null || tower==null)
            throw new NullPointerException();

        this.familyMember = familyMember;
        this.index = floor;
        this.tower = tower;
        this.payCard = new PayCard(tower,floor, player, game);
        this.player = player;
        this.game = game;
    }

    @Override
    public void execute() {

        // farei l'eventuale modifica dell'azione qui tramite i bonus e malus
        // al momento modifichiamo il valore dell'azione che per come sono strutturate le azioni non cambia i controlli


        // qui modifichiamo il valore dell'azione prima che si compia

        game.getGameStatus().changeState(TransitionType.ACTION_ON_TOWER);

        payCard.execute();
        tower.addFamilyMember(familyMember, index);

        game.getGameStatus().changeState(TransitionType.END_ACTION);

    }

    @Override
    public boolean isAllowed() {

        /** è permessa solo quando non c'è un familiare NON NEUTRO sulla torre*/

        //Can add in PlayerBoard

        if (!game.getGameStatus().getCurrentStatus().canConsume(TransitionType.ACTION_ON_TOWER))
        {
            return false;
        }

        if (!tower.isAllowed(familyMember, index))
            return false;

        return payCard.isAllowed();

    }

    @Override
    public void run() {
        System.out.println("ACTION STARTED");
        if (this.isAllowed()) {
            this.execute();
        }
        else{
            System.out.println("ERRORE, NON POSSO ESEGUIRE L'AZIONE");
        }
    }
}
