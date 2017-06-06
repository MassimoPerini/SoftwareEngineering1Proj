package it.polimi.ingsw.GC_06.model.Action.PickCard;

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

    private final Tower tower;
    private final int index;
    private final Action pickCard;
    private final FamilyMember familyMember;

    public BoardActionOnTower(Player player, int index, Tower tower, FamilyMember familyMember) {
        super();
        if (player==null || tower==null)
            throw new NullPointerException();

        this.familyMember = familyMember;
        this.index = index;
        this.tower = tower;
        this.pickCard = new PickCard(player, tower, tower.getTowerFloor().get(index), familyMember.getValue());
    }

    @Override
    public void execute() {

        // farei l'eventuale modifica dell'azione qui tramite i bonus e malus
        // al momento modifichiamo il valore dell'azione che per come sono strutturate le azioni non cambia i controlli


     //   super.getBonusMalusHandler().filter(super.getPlayer(),super.getPlayType(),this);

        // soluzione temporanea = in questa azione di fatto posizioniamo soltanto il familiare

        //this.familyMember.getValue() = super.getValueAction();

        Game.getInstance().getGameStatus().changeState(TransitionType.ADDFAMILYMEMBER);

        if (!isAllowed())
            throw new IllegalStateException();


        tower.getTowerFloor().get(index).addFamilyMember(familyMember);

        pickCard.execute();

    }

    @Override
    public boolean isAllowed() {

        /** è permessa solo quando non c'è un familiare sulla torre*/

        return pickCard.isAllowed();

    }
}
