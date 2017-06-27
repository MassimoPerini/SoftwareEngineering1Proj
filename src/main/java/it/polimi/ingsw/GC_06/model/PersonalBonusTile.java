package it.polimi.ingsw.GC_06.model;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Effect.Effect;

import java.util.Map;

/**
 * Created by massimo on 27/06/17.
 */
public class PersonalBonusTile {

    private final String id;
    private final Map<ActionType, Map<Integer, Effect>>boards;

    public PersonalBonusTile(String id, Map<ActionType, Map<Integer, Effect>>boards)
    {
        this.id = id;
        this.boards = boards;
    }




}
