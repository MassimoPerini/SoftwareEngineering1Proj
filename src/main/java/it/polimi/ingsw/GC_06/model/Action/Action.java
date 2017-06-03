package it.polimi.ingsw.GC_06.model.Action;

/**
 * Created by massimo on 26/05/17.
 */
public abstract class Action {

    private final String idAction;
    private int valueAction;

    public Action(String idAction, int valueAction)
    {
        super();
        this.valueAction = valueAction;
        this.idAction = idAction;
    }

    public abstract void execute();
    public abstract boolean isAllowed();

    public int getValueAction() {
        return valueAction;
    }

    public void setValueAction(int valueAction) {
        this.valueAction = valueAction;
    }

    public String getIdAction() {
        return idAction;
    }
}
