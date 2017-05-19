package it.polimi.ingsw.GC_06.State;

/**
 * Created by giuseppe on 5/19/17.
 */
public interface Status {

    public void statusCard(StatusController statusController);
    public void statusProduction(StatusController statusController);
    public void skipTurn(StatusController statusController);

    public void nextStatus(StatusController statusController);
}

