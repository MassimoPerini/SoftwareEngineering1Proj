package it.polimi.ingsw.GC_06.model;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 27/06/17.
 */
public class PersonalBonusTile {

    private final String id;
    private final Map<ActionType, Map<Integer, ProdHarvEffect>>boards;

    public PersonalBonusTile(String id, Map<ActionType, Map<Integer, ProdHarvEffect>>boards)
    {
        this.id = id;
        this.boards = boards;
    }

    public String getId() {
        return id;
    }

    public List<ProdHarvEffect> getBonus(ActionType actionType, int val) {
        //Da 0 ad int

        Map<Integer, ProdHarvEffect> effects = boards.get(actionType);
        List<ProdHarvEffect> result = new LinkedList<>();
        for (Integer integer : effects.keySet()) {
            if (integer <= val)
            {
                result.add(effects.get(integer));
            }
        }

        return result;
    }
}
