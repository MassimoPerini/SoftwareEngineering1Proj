package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageClearBoard;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by massimo on 13/05/17.
 */

/**
 * @author massimo
 * This class represents the main board of the game
 */
public class Board extends Observable{
    @NotNull private final Map<String, Tower> towers;
    @NotNull private final List<MarketAndCouncil> market;
    @NotNull private final List<ProdHarvZone> prodHarvZones;
    @NotNull private final List<MarketAndCouncil> councils;

    public Board(Map<String, Tower> towers, List<MarketAndCouncil> marketAndCouncils, List<ProdHarvZone> prodHarvZones, List<MarketAndCouncil> councils)
    {
        super();
        this.towers = towers;
        this.market = marketAndCouncils;
        this.prodHarvZones = prodHarvZones;
        this.councils = councils;

    }//TODO da integrare con il caricamento degli effetti da file, in modo da mettere gli effetti sui vari actionplace

    //TODO remove FIX here!
    public Map<String, Tower> getTowers() {
        return towers;
    }

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

    public List<MarketAndCouncil> getCouncils() {
        return Collections.unmodifiableList(councils);
    }
}
