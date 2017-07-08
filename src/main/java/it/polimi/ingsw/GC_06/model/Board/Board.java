package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageClearBoard;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Created by massimo on 13/05/17.
 */

/**
 * @author massimo
 * This class represents the main board of the game, and contains all the elements
 */
public class Board extends Observable{
    private final Map<String, Tower> towers;
    private final List<MarketAndCouncil> market;
    private final List<ProdHarvZone> prodHarvZones;
    private final List<MarketAndCouncil> councils;

    public Board(Map<String, Tower> towers, List<MarketAndCouncil> marketAndCouncils, List<ProdHarvZone> prodHarvZones, List<MarketAndCouncil> councils)
    {
        super();
        this.towers = towers;
        this.market = marketAndCouncils;
        this.prodHarvZones = prodHarvZones;
        this.councils = councils;

    }

    //TODO remove FIX here!
    public Map<String, Tower> getTowers() {
        return towers;
    }

    /**
     * elimina i familiari associati a tutti gli spazi della board
     */
    public void resetFamilyMembers()
    {
        for (Tower tower : towers.values()) {
            tower.removeFamilyMembers();
        }
        for (MarketAndCouncil marketAndCouncil : market) {
            marketAndCouncil.removeFamilyMembers();
        }
        for (ProdHarvZone prodHarvZone : prodHarvZones) {
            prodHarvZone.removeFamilyMembers();
        }
        for (MarketAndCouncil council : councils) {
            council.removeFamilyMembers();
        }


        MessageServer messageClearBoard = new MessageClearBoard();
        setChanged();
        notifyObservers(messageClearBoard);
    }

    public List<MarketAndCouncil> getMarket() {
        return Collections.unmodifiableList(market);
    }

    public List<ProdHarvZone> getProdHarvZones() {
        return Collections.unmodifiableList(prodHarvZones);
    }

    public List<MarketAndCouncil> getMarketAndCouncils() {
        return Collections.unmodifiableList(councils);
    }
}
