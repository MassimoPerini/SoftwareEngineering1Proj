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
    private final List<ProdHarvZone> productionZones;
    private final List<MarketAndCouncil> councils;
    private final List<ProdHarvZone> harvestZones;

    public Board(Map<String, Tower> towers, List<MarketAndCouncil> market, List<ProdHarvZone> prodHarvZones,List<ProdHarvZone> harvestZones, List<MarketAndCouncil> councils)
    {
        super();
        this.towers = towers;
        this.market = market;
        this.productionZones = prodHarvZones;
        this.harvestZones = harvestZones;
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
        for (ProdHarvZone prodHarvZone : productionZones) {
            prodHarvZone.removeFamilyMembers();
        }

        for (ProdHarvZone harvestZone : harvestZones) {
            harvestZone.removeFamilyMembers();
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


    public List<ProdHarvZone> getProductionZones() {
        return Collections.unmodifiableList(productionZones);
    }

    public List<ProdHarvZone> getHarvestZones() {
        return Collections.unmodifiableList(harvestZones);
    }


    /**
    public List<ProdHarvZone> getProdHarvZones() {
        return Collections.unmodifiableList(prodHarvZones);
    }*/

    public List<MarketAndCouncil> getCouncils() {
        return Collections.unmodifiableList(councils);
    }

}
