package it.polimi.ingsw.GC_06.model.Board;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 13/05/17.
 */

/**
 * @author massimo
 * This class represents the main board of the game
 */
public class Board {
    @NotNull private final Map<String, Tower> towers;
    @NotNull private final ArrayList<MarketAndCouncil> marketAndCouncils;
    @NotNull private final ArrayList<ProdHarvZone> prodHarvZones;
    @NotNull private final ArrayList<MarketAndCouncil> councils;

    public Board(Map<String, Tower> towers, ArrayList<MarketAndCouncil> marketAndCouncils, ArrayList<ProdHarvZone> prodHarvZones, ArrayList<MarketAndCouncil> councils)
    {
        super();
        this.towers = towers;
        this.marketAndCouncils = marketAndCouncils;
        this.prodHarvZones = prodHarvZones;
        this.councils = councils;

    }//TODO da integrare con il caricamento degli effetti da file, in modo da mettere gli effetti sui vari actionplace

    //TODO remove FIX here!
    public Map<String, Tower> getTowers() {
        return towers;
    }


    public List<MarketAndCouncil> getMarketAndCouncils() {
        return Collections.unmodifiableList(marketAndCouncils);
    }

    public List<ProdHarvZone> getProdHarvZones() {
        return Collections.unmodifiableList(prodHarvZones);
    }

    public List<MarketAndCouncil> getCouncils() {
        return Collections.unmodifiableList(councils);
    }
}
