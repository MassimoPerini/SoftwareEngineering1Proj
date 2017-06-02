package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

/**
 * Created by massimo on 13/05/17.
 */
public class Board {
    private final ArrayList<Tower> towers;
    private final ArrayList<MarketAndCouncil> marketAndCouncils;
    private final ArrayList<ProdHarvZone> prodHarvZones;
    private final ArrayList<MarketAndCouncil> councils;

    public Board(ArrayList<Tower> towers, ArrayList<MarketAndCouncil> marketAndCouncils, ArrayList<ProdHarvZone> prodHarvZones, ArrayList<MarketAndCouncil> councils)
    {
        super();
        this.towers = towers;
        this.marketAndCouncils = marketAndCouncils;
        this.prodHarvZones = prodHarvZones;
        this.councils = councils;

    }//TODO da integrare con il caricamento degli effetti da file, in modo da mettere gli effetti sui vari actionplace

    //TODO remove FIX here!
    public ArrayList<Tower> getTowers() {
        return towers;
    }


    public ArrayList<MarketAndCouncil> getMarketAndCouncils() {
        return marketAndCouncils;
    }

    public ArrayList<ProdHarvZone> getProdHarvZones() {
        return prodHarvZones;
    }

    public ArrayList<MarketAndCouncil> getCouncils() {
        return councils;
    }
}
