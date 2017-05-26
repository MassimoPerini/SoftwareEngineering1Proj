package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by giuseppe on 5/19/17.
 */
public interface Status {

    void statusCard(StatusController statusController);
    void statusProduction(StatusController statusController);
    void skipTurn(StatusController statusController);

    void nextStatus(StatusController statusController);
}

