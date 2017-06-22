package it.polimi.ingsw.GC_06.model.Loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.model.Board.*;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import it.polimi.ingsw.GC_06.model.Card.Card;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Effect.*;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.PlayerBoard;
import it.polimi.ingsw.GC_06.model.playerTools.PlayerBoardSlot;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by massimo on 17/05/17.
 * @author massimo
 * This class reads and writes JSON files, thanks to Google GSON library.
 * Remove the write functions before submit!
 */

public class FileLoader {

    private static FileLoader instance;

    private static final String CARDS_PATH = "cards_path";
    private static final String PARCHMENTS_PATH = "parchments_path";
    private static final String BOARD_PATH = "board_path";
    private static final String DEFAULT_RES = "default_resource_path";
    private static final String DICES = "dices_path";
    private static final String PLAYER_BOARD = "player_board_path";
   // private static final String GAME_MAP = "end_game_map";


    private String cardsRootPath;
    private String parchmentsPath;
    private String boardRootPath;
    private String defaultResourceRootPath;
    private String dicePath;
   // private String endGameMap;
    private String playerBoardPath;
    private Gson gson;


    private FileLoader ()
    {
        super();
        this.gson = new Gson();
        parchmentsPath = Setting.getInstance().getProperty(PARCHMENTS_PATH);
        cardsRootPath = Setting.getInstance().getProperty(CARDS_PATH);
        boardRootPath = Setting.getInstance().getProperty(BOARD_PATH);
        defaultResourceRootPath = Setting.getInstance().getProperty(DEFAULT_RES);
        dicePath = Setting.getInstance().getProperty(DICES);
        playerBoardPath = Setting.getInstance().getProperty(PLAYER_BOARD);
//        endGameMap = Setting.getInstance().getProperty(GAME_MAP);
    }

    public static FileLoader getFileLoader ()
    {
        if (instance == null)
            instance = new FileLoader();
        return instance;
    }

    public void writeResourceSet(ResourceSet [] resourceSet) throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/model/parchments.txt");
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(resourceSet, fw);
        fw.close();
    }

    public ResourceSet[] loadDefaultResourceSets() throws IOException {
        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(defaultResourceRootPath));
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        ResourceSet [] defaultResourceSets = gson.fromJson(fr , ResourceSet [].class);
        fr.close();
        return defaultResourceSets;
    }

    public ResourceSet[] loadParchments() {
        InputStreamReader sr = new InputStreamReader(this.getClass().getResourceAsStream(parchmentsPath));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ResourceSet [] parchments = gson.fromJson(sr, ResourceSet [].class);
        try {
            sr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parchments;
    }

    public void writeDiceSet(DiceSet diceSet) throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/model/dices.txt");
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(diceSet, fw);
        fw.close();
    }

    /**public void writeEndMap() throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/model/end_game_map.txt");
        Map<String,List<Integer>> endGameMap = new HashMap<>();
        List list = new ArrayList();
        list.addAll(Arrays.asList(1,2,3,4,5));
        endGameMap.put("BLUE",list);
        endGameMap.put("GREEN",list);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(endGameMap, fw);
        fw.close();
    }*/


    public DiceSet loadDiceSet() throws IOException {
        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(dicePath));
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        DiceSet diceSet = gson.fromJson(fr , DiceSet.class);
        fr.close();
        return diceSet;
    }

    public DevelopmentCard[] loadCards() throws IOException {

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();

        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(cardsRootPath));
        DevelopmentCard [] cards = gson2.fromJson(fr , DevelopmentCard [].class);
        fr.close();
        return cards;
    //    System.out.println(cards[3].toString());
    }

    public Board loadBoard() throws IOException {
        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(boardRootPath));

        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(ActionPlace.class, "type").registerSubtype(ActionPlace.class).registerSubtype(ActionPlaceFixed.class);
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class).registerSubtype(EffectOnAction.class);

        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory1).create();
        Board board = gson2.fromJson(fr, Board.class);

        fr.close();
        return board;
    }

    public PlayerBoard loadPlayerBoard()
    {
        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(playerBoardPath));
        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(Card.class, "type").registerSubtype(DevelopmentCard.class);
        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory1).create();
        PlayerBoard playerBoard = gson2.fromJson(fr, PlayerBoard.class);
        return playerBoard;
    }

    public void writePlayerBoard() throws IOException {
        Map<String, List<PlayerBoardSlot>> result = new HashMap<>();

        int [] malus = {0,0,-3,-7,-12,-18};
        int [] res = {0,0,1,4,10,20};

        List slots = new LinkedList();
        ResourceSet requirements = new ResourceSet();
        ResourceSet bonus = new ResourceSet();
        PlayerBoardSlot playerBoardSlot;

        for (int i=0;i<6;i++) {
            requirements.variateResource(Resource.MILITARYPOINT, malus[i]);
            bonus.variateResource(Resource.VICTORYPOINT, res[i]);
            slots.add(new PlayerBoardSlot(requirements, bonus));
            requirements = new ResourceSet();
            bonus = new ResourceSet();
        }
        result.put("GREEN", slots);
        slots = new LinkedList();

        String [] colors = {"BLUE","YELLOW", "PURPLE"};

        for (int j=0;j<colors.length;j++) {
            slots = new LinkedList();
            for (int i = 0; i < 6; i++) {
                requirements = new ResourceSet();
                bonus = new ResourceSet();
                playerBoardSlot = new PlayerBoardSlot(requirements, bonus);
                slots.add(playerBoardSlot);
            }
            result.put(colors[j], slots);
            slots = new LinkedList();
        }

        PlayerBoard playerBoard = new PlayerBoard(result);

        FileWriter fw = new FileWriter("src/main/resources/model/playerBoard.txt");
        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(Card.class, "type").registerSubtype(DevelopmentCard.class);
        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory1).create();
        gson2.toJson(playerBoard, fw);
        fw.close();

    }

    public void writeBoardWithEffects() throws IOException {
        Map<String, Tower> towers = new HashMap<>();
        //genero un piano alla volta i piani per ogni torre, da sinistra verso destra della gameBoard e dal basso verso l'alto
        ArrayList<TowerFloor> towerFloors = new ArrayList<>();
        ResourceSet malus = new ResourceSet();
        malus.variateResource(Resource.MONEY, -3);
        ResourceSet bonus = new ResourceSet();
        EffectOnResources bonusEffect = new EffectOnResources(bonus);
        ArrayList<Effect> bonusEffects = new ArrayList<>();
        bonusEffects.add(bonusEffect);
        TowerFloor towerFloor1Green = new TowerFloor(new ActionPlaceFixed(bonusEffects, 1, 1), null);
        towerFloors.add(towerFloor1Green);
        TowerFloor towerFloor2Green = new TowerFloor(new ActionPlaceFixed(bonusEffects, 3, 1), null);
        towerFloors.add(towerFloor2Green);
        ResourceSet bonus3pianogreen = new ResourceSet();
        bonus3pianogreen.variateResource(Resource.WOOD, 1);
        EffectOnResources bonusEffect3 = new EffectOnResources(bonus3pianogreen);
        ArrayList<Effect> bonusEffects3 = new ArrayList<>();
        bonusEffects3.add(bonusEffect3);
        TowerFloor towerFloor3Green = new TowerFloor(new ActionPlaceFixed(bonusEffects3, 5, 1), null);
        towerFloors.add(towerFloor3Green);
        ResourceSet bonus4pianogreen = new ResourceSet();
        bonus4pianogreen.variateResource(Resource.WOOD, 2);
        EffectOnResources bonusEffect4 = new EffectOnResources(bonus4pianogreen);
        ArrayList<Effect> bonusEffects4 = new ArrayList<>();
        bonusEffects4.add(bonusEffect4);
        TowerFloor towerFloor4Green = new TowerFloor(new ActionPlaceFixed(bonusEffects4, 5, 1), null);
        towerFloors.add(towerFloor4Green);
        //ora posso genrare la torre verde
        Tower greenTower = new Tower(towerFloors, 1, 1, "GREEN", malus);
        towers.put(greenTower.getColor(), greenTower);
        //passo a genrare i towerFloor per la torre blu
        ArrayList<TowerFloor> towerFloors1 = new ArrayList<>();
        ResourceSet bonusBlu = new ResourceSet();
        EffectOnResources bonusEffectBlu = new EffectOnResources(bonusBlu);
        ArrayList<Effect> bonusEffectsBlu = new ArrayList<>();
        bonusEffectsBlu.add(bonusEffectBlu);
        TowerFloor towerFloor1Blu = new TowerFloor(new ActionPlaceFixed(bonusEffectsBlu, 1, 1), null);
        towerFloors1.add(towerFloor1Blu);
        TowerFloor towerFloor2Blu = new TowerFloor(new ActionPlaceFixed(bonusEffectsBlu, 3, 1), null);
        towerFloors1.add(towerFloor2Blu);
        ResourceSet bonus3pianoBlu = new ResourceSet();
        bonus3pianoBlu.variateResource(Resource.STONE, 1);
        EffectOnResources bonusEffectBlu3 = new EffectOnResources(bonus3pianoBlu);
        ArrayList<Effect> bonusEffectsBlu3 = new ArrayList<>();
        bonusEffectsBlu3.add(bonusEffectBlu3);
        TowerFloor towerFloor3Blu = new TowerFloor(new ActionPlaceFixed(bonusEffectsBlu3, 5, 1), null);
        towerFloors1.add(towerFloor3Blu);
        ResourceSet bonus4pianoBlu = new ResourceSet();
        bonus4pianoBlu.variateResource(Resource.STONE, 2);
        EffectOnResources bonusEffectBlu4 = new EffectOnResources(bonus4pianoBlu);
        ArrayList<Effect> bonusEffectsBlu4 = new ArrayList<>();
        bonusEffectsBlu4.add(bonusEffectBlu4);
        TowerFloor towerFloor4Blu = new TowerFloor(new ActionPlaceFixed(bonusEffectsBlu4, 5, 1), null);
        towerFloors1.add(towerFloor4Blu);
        //ora posso generare la torre blu
        Tower blueTower = new Tower(towerFloors1, 1, 1, "BLUE", malus);
        towers.put(blueTower.getColor(), blueTower);
        //passo a generare i toweFloor per la torre gialla
        ArrayList<TowerFloor> towerFloors2 = new ArrayList<>();
        ResourceSet bonusYellow = new ResourceSet();
        EffectOnResources bonusEffectYellow = new EffectOnResources(bonusYellow);
        ArrayList<Effect> bonusEffectsYellow = new ArrayList<>();
        bonusEffectsYellow.add(bonusEffectYellow);
        TowerFloor towerFloor1Yellow = new TowerFloor(new ActionPlaceFixed(bonusEffectsYellow, 1, 1), null);
        towerFloors2.add(towerFloor1Yellow);
        TowerFloor towerFloor2Yellow = new TowerFloor(new ActionPlaceFixed(bonusEffectsYellow, 3, 1), null);
        towerFloors2.add(towerFloor2Yellow);
        ResourceSet bonus3pianoYellow = new ResourceSet();
        bonus3pianoYellow.variateResource(Resource.MILITARYPOINT, 1);
        EffectOnResources bonusEffectYellow3 = new EffectOnResources(bonus3pianoYellow);
        ArrayList<Effect> bonusEffectsYellow3 = new ArrayList<>();
        bonusEffectsYellow3.add(bonusEffectYellow3);
        TowerFloor towerFloor3Yellow = new TowerFloor(new ActionPlaceFixed(bonusEffectsYellow3, 5, 1), null);
        towerFloors2.add(towerFloor3Yellow);
        ResourceSet bonus4pianoYellow = new ResourceSet();
        bonus4pianoYellow.variateResource(Resource.MILITARYPOINT, 2);
        EffectOnResources bonusEffectYellow4 = new EffectOnResources(bonus4pianoYellow);
        ArrayList<Effect> bonusEffectsYellow4 = new ArrayList<>();
        bonusEffectsYellow4.add(bonusEffectYellow4);
        TowerFloor towerFloor4Yellow = new TowerFloor(new ActionPlaceFixed(bonusEffectsYellow4, 5, 1), null);
        towerFloors2.add(towerFloor4Yellow);
        //ora posso generare la torre gialla
        Tower yellowTower = new Tower(towerFloors2, 1, 1, "YELLOW", malus);
        towers.put(yellowTower.getColor(), yellowTower);
        //passo a generare i towerFloor per la torre viola
        ArrayList<TowerFloor> towerFloors3 = new ArrayList<>();
        ResourceSet bonusPurple = new ResourceSet();
        EffectOnResources bonusEffectPurple = new EffectOnResources(bonusPurple);
        ArrayList<Effect> bonusEffectsPurple = new ArrayList<>();
        bonusEffectsPurple.add(bonusEffectPurple);
        TowerFloor towerFloor1Purple = new TowerFloor(new ActionPlaceFixed(bonusEffectsPurple, 1, 1), null);
        towerFloors3.add(towerFloor1Purple);
        TowerFloor towerFloor2Purple = new TowerFloor(new ActionPlaceFixed(bonusEffectsPurple, 3, 1), null);
        towerFloors3.add(towerFloor2Purple);
        ResourceSet bonus3pianoPurple = new ResourceSet();
        bonus3pianoPurple.variateResource(Resource.MONEY, 1);
        EffectOnResources bonusEffectPurple3 = new EffectOnResources(bonus3pianoPurple);
        ArrayList<Effect> bonusEffectsPurple3 = new ArrayList<>();
        bonusEffectsPurple3.add(bonusEffectPurple3);
        TowerFloor towerFloor3Purple = new TowerFloor(new ActionPlaceFixed(bonusEffectsPurple3, 5, 1), null);
        towerFloors3.add(towerFloor3Purple);
        ResourceSet bonus4pianoPurple = new ResourceSet();
        bonus4pianoPurple.variateResource(Resource.MONEY, 2);
        EffectOnResources bonusEffectPurple4 = new EffectOnResources(bonus4pianoPurple);
        ArrayList<Effect> bonusEffectsPurple4 = new ArrayList<>();
        bonusEffectsPurple4.add(bonusEffectPurple4);
        TowerFloor towerFloor4Purple = new TowerFloor(new ActionPlaceFixed(bonusEffectsPurple4, 5, 1), null);
        towerFloors3.add(towerFloor4Purple);
        //ora posso generare la torre viola
        Tower purpleTower = new Tower(towerFloors3, 1, 1, "PURPLE", malus);
        towers.put(purpleTower.getColor(), purpleTower);
        //adesso genero la zona produzione
        ArrayList<ProdHarvZone> prodHarvZones = new ArrayList<>();
        ActionType actionType = ActionType.PRODUCTION_ACTION;
        ArrayList<ActionPlace> prodActionPlaces = new ArrayList<>();
        prodActionPlaces.add(new ActionPlaceFixed(new ArrayList<Effect>(), 1, 1));
        //TODO capire se il malus sulla produzione va bene scritto cosi
        LinkedList<String> familyMemberColours = new LinkedList<>();
        BonusMalusOnAction bonusMalusOnAction = new BonusMalusOnAction("", familyMemberColours, actionType, false, -3);
        BonusMalusSet malusProduction = new BonusMalusSet();
        List<BonusMalusOnAction> requestedList = new ArrayList<>();
        requestedList.add(bonusMalusOnAction);
        malusProduction.addActionBonusMalus(requestedList);
        DonateBonusMalusEffect malusOnProduction = new DonateBonusMalusEffect(malusProduction);
        List<Effect> effectsBigSpaceProd = new ArrayList<>();
        effectsBigSpaceProd.add(malusOnProduction);
        prodActionPlaces.add(new ActionPlace(effectsBigSpaceProd, 1));
        ProdHarvZone prodZone = new ProdHarvZone(prodActionPlaces, actionType);
        prodHarvZones.add(prodZone);
        //adesso genero la zona raccolto
        ActionType actionType1 = ActionType.HARVEST_ACTION;
        ArrayList<ActionPlace> harvActionPlaces = new ArrayList<>();
        harvActionPlaces.add(new ActionPlaceFixed(new ArrayList<Effect>(), 1, 1));
        //TODO capire se il malus sul raccolto va bene scritto cosi
        familyMemberColours.add("");
        BonusMalusOnAction bonusMalusOnAction1 = new BonusMalusOnAction("", familyMemberColours, actionType1, false, -3);
        BonusMalusSet malusHarvest = new BonusMalusSet();
        List<BonusMalusOnAction> requestedList1 = new ArrayList<>();
        requestedList1.add(bonusMalusOnAction1);
        malusHarvest.addActionBonusMalus(requestedList1);
        DonateBonusMalusEffect malusOnHarvest = new DonateBonusMalusEffect(malusHarvest);
        List<Effect> effectsBigSpaceHarv = new ArrayList<>();
        effectsBigSpaceHarv.add(malusOnHarvest);
        harvActionPlaces.add(new ActionPlace(effectsBigSpaceHarv, 1));
        ProdHarvZone harvZone = new ProdHarvZone(harvActionPlaces, actionType);
        prodHarvZones.add(harvZone);
        //adesso genero il mercato con gli actionPlace differenziati
        ArrayList<MarketAndCouncil> markets = new ArrayList<>();
        ResourceSet bonusMarket1 = new ResourceSet();
        bonusMarket1.variateResource(Resource.MONEY, 5);
        EffectOnResources marketEffect1 = new EffectOnResources(bonusMarket1);
        ArrayList<Effect> marketEffects1 = new ArrayList<>();
        marketEffects1.add(marketEffect1);
        ArrayList<ActionPlace> placesMarket = new ArrayList<>();
        ActionPlaceFixed market1 = new ActionPlaceFixed(marketEffects1, 1, 1);
        placesMarket.add(market1);
        ResourceSet bonusMarket2 = new ResourceSet();
        bonusMarket2.variateResource(Resource.SERVANT, 5);
        EffectOnResources marketEffect2 = new EffectOnResources(bonusMarket2);
        ArrayList<Effect> marketEffects2 = new ArrayList<>();
        marketEffects2.add(marketEffect2);
        ActionPlaceFixed market2 = new ActionPlaceFixed(marketEffects2, 1, 1);
        placesMarket.add(market2);
        ResourceSet bonusMarket3 = new ResourceSet();
        bonusMarket3.variateResource(Resource.MILITARYPOINT, 3);
        bonusMarket3.variateResource(Resource.MONEY, 2);
        EffectOnResources marketEffect3 = new EffectOnResources(bonusMarket3);
        ArrayList<Effect> marketEffects3 = new ArrayList<>();
        marketEffects3.add(marketEffect3);
        ActionPlaceFixed market3 = new ActionPlaceFixed(marketEffects3, 1, 4);
        placesMarket.add(market3);
        EffectOnParchment marketEffect4 = new EffectOnParchment(2,true );
        ArrayList<Effect> marketEffects4 = new ArrayList<>();
        marketEffects4.add(marketEffect4);
        ActionPlaceFixed market4 = new ActionPlaceFixed(marketEffects4, 1, 4);
        placesMarket.add(market4);
        MarketAndCouncil market = new MarketAndCouncil(placesMarket);
        markets.add(market);
        //adesso genero lo spazio del consiglio
        ArrayList<MarketAndCouncil> counsils = new ArrayList<>();
        ArrayList<ActionPlace> placesCounsil = new ArrayList<>();
        ResourceSet counsilResources = new ResourceSet();
        counsilResources.variateResource(Resource.MONEY, 1);
        EffectOnResources counsilEffectResources = new EffectOnResources(counsilResources);
        EffectOnParchment cousilEffect = new EffectOnParchment(1, false);
        List<Effect> counsilEffects = new ArrayList<>();
        counsilEffects.add(counsilEffectResources);
        counsilEffects.add(cousilEffect);
        ActionPlace cousilPlace = new ActionPlace(counsilEffects, 1);
        placesCounsil.add(cousilPlace);
        MarketAndCouncil counsil = new MarketAndCouncil(placesCounsil);
        counsils.add(counsil);
        //posso finalmente creare una board per poi scriverla
        Board board = new Board(towers, markets, prodHarvZones, counsils);

        FileWriter fw = new FileWriter("src/main/resources/model/board2.txt");

        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(ActionPlace.class, "type").registerSubtype(ActionPlace.class).registerSubtype(ActionPlaceFixed.class);
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);

        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory1).create();
        gson2.toJson(board, fw);
        fw.close();

    }

    public void writeBoard() throws IOException {
        int [] values = {1,3,5,7};

        Map<String, Tower> towers = new HashMap<>();

        //Generate towers
        //TODO una torre contiene carte solo di un tipo
        Resource [] resources = {Resource.WOOD, Resource.STONE, Resource.MILITARYPOINT, Resource.MONEY};

        String colors [] = {"GREEN", "BLUE", "YELLOW", "PURPLE"};

        for (int j=0;j<4;j++) {
            ArrayList<TowerFloor> towerFloors = new ArrayList<>();
            ResourceSet malus = new ResourceSet();
            malus.variateResource(Resource.MONEY, -3);
            for (int i = 0; i < 4; i++) {
                ResourceSet resourceSet = new ResourceSet();
                if (i>2)
                    resourceSet.variateResource(resources[j], i-2);

                EffectOnResources effectOnResources = new EffectOnResources(resourceSet);
                ArrayList<Effect> effectOnResources1 = new ArrayList<>();
                effectOnResources1.add(effectOnResources);
                TowerFloor towerFloor = new TowerFloor(new ActionPlaceFixed(effectOnResources1, values[i], 1), null);
                towerFloors.add(towerFloor);
            }

            ResourceSet malusResourceSet = new ResourceSet();
            malusResourceSet.variateResource(Resource.MONEY, -3);

            Tower tower = new Tower(towerFloors, 1, 1, colors[j], malusResourceSet);
            towers.put(tower.getColor(), tower);
        }

        //TODO: NEI COSTRUTTORI RICHIEDERE ARRAY, non LINKEDLIST

        //Generate production/harvest
        ArrayList<ProdHarvZone> prodHarvZones = new ArrayList<>();
        ActionType[] actionTypes = {ActionType.PRODUCTION_ACTION,ActionType.HARVEST_ACTION};
        for (int i=0;i<2;i++) {
            ArrayList<ActionPlace> prodHarvActionPlaces = new ArrayList<>();
            prodHarvActionPlaces.add(new ActionPlaceFixed(new ArrayList<Effect>(), 1, 1));
            prodHarvActionPlaces.add(new ActionPlace(new ArrayList<Effect>(), 1));
            ProdHarvZone prodHarvZone = new ProdHarvZone(prodHarvActionPlaces,actionTypes[i]);
            prodHarvZones.add(prodHarvZone);
        }

        ArrayList<MarketAndCouncil> marketAndCouncils = new ArrayList<>();
        ArrayList <ActionPlace> marketActionPlaces = new ArrayList<>();
        for (int i=0;i<5;i++)
        {
            marketActionPlaces.add(new ActionPlaceFixed(new ArrayList<>(), 1, 1));    //TODO EFFECT???
        }
        marketAndCouncils.add(new MarketAndCouncil(marketActionPlaces));

        ArrayList<MarketAndCouncil> councils = new ArrayList<>();
        ArrayList<ActionPlace> actionPlaces = new ArrayList<ActionPlace>();
        actionPlaces.add(new ActionPlace(new ArrayList<Effect>(), 1));
        councils.add(new MarketAndCouncil(actionPlaces));

        Board b = new Board(towers, marketAndCouncils, prodHarvZones, councils);

        FileWriter fw = new FileWriter("src/main/resources/model/board.txt");

        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(ActionPlace.class, "type").registerSubtype(ActionPlace.class).registerSubtype(ActionPlaceFixed.class);
        RuntimeTypeAdapterFactory typeAdapterFactory3 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);

        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory1).registerTypeAdapterFactory(typeAdapterFactory3).create();
        gson2.toJson(b, fw);
        fw.close();
    }


    public void writeCards() throws IOException {

        List<ProdHarvEffect> prodHarvEffects = new ArrayList<>();

        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.SERVANT, 2);
        resourceSet.variateResource(Resource.STONE, 1);
        Effect immediateEffect = new EffectOnResources(resourceSet);

        List<DevelopmentCard> cards = new LinkedList<>();

        ArrayList<Requirement> r;
        ResourceSet res1;
        for (int j=1;j<=3;j++) {
            for (int k=0;k<2;k++) {
                for (int i = 0; i < 4; i++) {
                    DevelopmentCard card1 = new DevelopmentCard("devcards_f_en_c_1" + String.valueOf(i) +String.valueOf(k) +String.valueOf(j), j, new ArrayList<Requirement>(), new ArrayList<Effect>(), "GREEN", new HashMap<>());
                    r = new ArrayList<>();
                    res1 = new ResourceSet();
                    res1.variateResource(Resource.MONEY, 6);

                    r.add(new Requirement(new ResourceSet(), res1));
                    cards.add(card1);
                }

                ResourceSet res2 = new ResourceSet();

                for (int i = 0; i < 4; i++) {
                    DevelopmentCard card2 = new DevelopmentCard("devcards_f_en_c_65" + String.valueOf(i) +String.valueOf(k)+ String.valueOf(j), j, new ArrayList<Requirement>(), new ArrayList<Effect>(), "BLUE", new HashMap<>());

                    res2.variateResource(Resource.SERVANT, 1);
                    res2.variateResource(Resource.WOOD, 2);
                    res2.variateResource(Resource.STONE, 2);
                    r = new ArrayList<>();
                    r.add(new Requirement(new ResourceSet(), res2));
                    cards.add(card2);
                }

                for (int i = 0; i < 4; i++) {

                    DevelopmentCard card3 = new DevelopmentCard("devcards_f_en_c_47" + String.valueOf(i) + String.valueOf(k)+String.valueOf(j), j, new ArrayList<Requirement>(), new ArrayList<Effect>(), "YELLOW", new HashMap<>());

                    res2 = new ResourceSet();
                    res2.variateResource(Resource.WOOD, 3);
                    res2.variateResource(Resource.MONEY, 4);
                    res2.variateResource(Resource.STONE, 3);
                    r = new ArrayList<>();
                    r.add(new Requirement(new ResourceSet(), res2));
                    cards.add(card3);
                }

                for (int i = 0; i < 4; i++) {
                    res2 = new ResourceSet();
                    res2.variateResource(Resource.MILITARYPOINT, 5);
                    res1 = new ResourceSet();
                    res1.variateResource(Resource.MILITARYPOINT, 10);
                    r = new ArrayList<>();
                    r.add(new Requirement(res1, res2));
                    DevelopmentCard card4 = new DevelopmentCard("devcards_f_en_c_96" + String.valueOf(i) +String.valueOf(k)+ String.valueOf(j), j, new ArrayList<Requirement>(), new ArrayList<Effect>(), "PURPLE", new HashMap<>());
                    cards.add(card4);
                }
            }
        }


        //TODO FIX HERE (relative path!)

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class);
        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();

        FileWriter fw = new FileWriter("src/main/resources/model/cards.txt");
        gson2.toJson(cards, fw);
        fw.close();
    }

    public void writeGreenCard() throws IOException {
        List<DevelopmentCard> cards = new LinkedList<>();
        int requiredValue = 1;
        int era = 1;
        String name = "devcards_f_en_c_1";
        List<Requirement> requirements = new ArrayList<>();
        List effects = new ArrayList();
        String idColour = "GREEN";
        List<Effect> immediateEffects = new ArrayList<>();
        List bonusEffects = new ArrayList();
        List malusEffects = new ArrayList();
        List prodHarvEffects = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap = new HashMap<>();
        ResourceSet variation = new ResourceSet();
        variation.variateResource(Resource.MONEY, 1);
        EffectOnResources effect = new EffectOnResources(variation);
        bonusEffects.add(effect);
        ProdHarvEffect prodHarvEffect = new ProdHarvEffect(malusEffects, bonusEffects);
        prodHarvEffects.add(prodHarvEffect);
        requestedMap.put(requiredValue, prodHarvEffects);
        DevelopmentCard card = new DevelopmentCard(name,era, requirements, immediateEffects, idColour, requestedMap);
        cards.add(card);
        int requiredValue2 = 2;
        int era2 = 1;
        String name2 = "devcards_f_en_c_2";
        List requirements2 = new ArrayList();
        List effects2 = new ArrayList();
        String idColour2 = "GREEN";
        List<Effect> immediateEffects2 = new ArrayList<>();
        List bonusEffects2 = new ArrayList();
        List malusEffects2 = new ArrayList();
        List prodHarvEffects2 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap2 = new HashMap<>();
        ResourceSet variation2 = new ResourceSet();
        variation2.variateResource(Resource.WOOD, 1);
        EffectOnResources effectOnResources2 = new EffectOnResources(variation2);
        immediateEffects2.add(effectOnResources2);
        EffectOnResources effect2 = new EffectOnResources( variation2);
        bonusEffects2.add(effect2);
        ProdHarvEffect prodHarvEffect2 = new ProdHarvEffect(malusEffects2, bonusEffects2);
        prodHarvEffects2.add(prodHarvEffect2);
        requestedMap2.put(requiredValue2, prodHarvEffects2);
        DevelopmentCard card2 = new DevelopmentCard(name2,era2, requirements2, immediateEffects2, idColour2, requestedMap2);
        cards.add(card2);
        int requiredValue3 = 3;
        int era3 = 1;
        String name3 = "devcards_f_en_c_3";
        List requirements3 = new ArrayList();
        List effects3 = new ArrayList();
        String idColour3 = "GREEN";
        List<Effect> immediateEffects3 = new ArrayList<>();
        List bonusEffects3 = new ArrayList();
        List malusEffects3 = new ArrayList();
        List prodHarvEffects3 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap3 = new HashMap<>();
        ResourceSet variation3 = new ResourceSet();
        variation3.variateResource(Resource.MONEY, 1);
        variation3.variateResource(Resource.SERVANT, 1);
        //immediateEffects.add(variation);
        EffectOnResources effect3 = new EffectOnResources( variation3);
        bonusEffects3.add(effect3);
        ProdHarvEffect prodHarvEffect3 = new ProdHarvEffect(malusEffects3, bonusEffects3);
        prodHarvEffects3.add(prodHarvEffect3);
        requestedMap3.put(requiredValue3, prodHarvEffects3);
        DevelopmentCard card3 = new DevelopmentCard(name3,era3, requirements3, immediateEffects3, idColour3, requestedMap3);
        cards.add(card3);
        int requiredValue4 = 4;
        int era4 = 1;
        String name4 = "devcards_f_en_c_4";
        List requirements4 = new ArrayList();
        List effects4 = new ArrayList();
        String idColour4 = "GREEN";
        List<Effect> immediateEffects4 = new ArrayList<>();
        List bonusEffects4 = new ArrayList();
        List malusEffects4 = new ArrayList();
        List prodHarvEffects4 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap4 = new HashMap<>();
        ResourceSet variation4 = new ResourceSet();
        variation4.variateResource(Resource.STONE, 2);
        EffectOnResources effectOnResources4 = new EffectOnResources(variation4);
        immediateEffects4.add(effectOnResources4);
        EffectOnResources effect4 = new EffectOnResources( variation4);
        bonusEffects4.add(effect4);
        ProdHarvEffect prodHarvEffect4 = new ProdHarvEffect(malusEffects4, bonusEffects4);
        prodHarvEffects4.add(prodHarvEffect4);
        requestedMap4.put(requiredValue4, prodHarvEffects4);
        DevelopmentCard card4 = new DevelopmentCard(name4,era4, requirements4, immediateEffects4, idColour4, requestedMap4);
        cards.add(card4);
        int requiredValue5 = 5;
        int era5 = 1;
        String name5 = "devcards_f_en_c_5";
        List requirements5 = new ArrayList();
        List effects5 = new ArrayList();
        String idColour5 = "GREEN";
        List<Effect> immediateEffects5 = new ArrayList<>();
        List bonusEffects5 = new ArrayList();
        List malusEffects5 = new ArrayList();
        List prodHarvEffects5 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap5 = new HashMap<>();
        ResourceSet variation5 = new ResourceSet();
        variation5.variateResource(Resource.WOOD, 3);
        ResourceSet immediateVariation5 = new ResourceSet();
        immediateVariation5.variateResource(Resource.WOOD, 1);
        EffectOnResources effectOnResources5 = new EffectOnResources(variation5);
        immediateEffects5.add(effectOnResources5);
        EffectOnResources effect5 = new EffectOnResources(variation5);
        bonusEffects5.add(effect5);
        ProdHarvEffect prodHarvEffect5 = new ProdHarvEffect(malusEffects5, bonusEffects5);
        prodHarvEffects5.add(prodHarvEffect5);
        requestedMap5.put(requiredValue5, prodHarvEffects5);
        DevelopmentCard card5 = new DevelopmentCard(name5,era5, requirements5, immediateEffects5, idColour5, requestedMap5);
        cards.add(card5);
        int requiredValue6 = 6;
        int era6 = 1;
        String name6 = "devcards_f_en_c_6";
        List requirements6 = new ArrayList();
        List effects6 = new ArrayList();
        String idColour6 = "GREEN";
        List<Effect> immediateEffects6 = new ArrayList<>();
        List bonusEffects6 = new ArrayList();
        List malusEffects6 = new ArrayList();
        List prodHarvEffects6 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap6 = new HashMap<>();
        ResourceSet variation6 = new ResourceSet();
        variation6.variateResource(Resource.FAITHPOINT, 1);
        variation6.variateResource(Resource.STONE,1);
        ResourceSet immediateVariation6 = new ResourceSet();
        immediateVariation6.variateResource(Resource.MILITARYPOINT, 2);
        immediateVariation6.variateResource(Resource.SERVANT, 1);
        EffectOnResources effectOnResources6 = new EffectOnResources(immediateVariation6);
        immediateEffects6.add(effectOnResources6);
        EffectOnResources effect6 = new EffectOnResources( variation6);
        bonusEffects6.add(effect6);
        ProdHarvEffect prodHarvEffect6 = new ProdHarvEffect(malusEffects6, bonusEffects6);
        prodHarvEffects6.add(prodHarvEffect6);
        requestedMap6.put(requiredValue6, prodHarvEffects6);
        DevelopmentCard card6 = new DevelopmentCard(name6,era6, requirements6, immediateEffects6, idColour6, requestedMap6);
        cards.add(card6);
        int requiredValue7 = 5;
        int era7 = 1;
        String name7 = "devcards_f_en_c_7";
        List requirements7 = new ArrayList();
        List effects7 = new ArrayList();
        String idColour7 = "GREEN";
        List<Effect> immediateEffects7 = new ArrayList<>();
        List bonusEffects7 = new ArrayList();
        List malusEffects7 = new ArrayList();
        List prodHarvEffects7 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap7 = new HashMap<>();
        ResourceSet variation7 = new ResourceSet();
        variation7.variateResource(Resource.MILITARYPOINT, 2);
        variation7.variateResource(Resource.STONE, 1);
        //ResourceSet immediateVariation7 = new ResourceSet();
        //immediateVariation5.variateResource(Resource.WOOD, 1);
        //immediateEffects7.add(immediateVariation7);
        EffectOnResources effect7 = new EffectOnResources( variation7);
        bonusEffects7.add(effect7);
        ProdHarvEffect prodHarvEffect7 = new ProdHarvEffect(malusEffects7, bonusEffects7);
        prodHarvEffects7.add(prodHarvEffect7);
        requestedMap7.put(requiredValue7, prodHarvEffects7);
        DevelopmentCard card7 = new DevelopmentCard(name7,era7, requirements7, immediateEffects7, idColour7, requestedMap7);
        cards.add(card7);
        int requiredValue8 = 6;
        int era8 = 1;
        String name8 = "devcards_f_en_c_8";
        List requirements8 = new ArrayList();
        List effects8 = new ArrayList();
        String idColour8 = "GREEN";
        List<Effect> immediateEffects8 = new ArrayList<>();
        List bonusEffects8 = new ArrayList();
        List malusEffects8 = new ArrayList();
        List prodHarvEffects8 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap8 = new HashMap<>();
        ResourceSet variation8 = new ResourceSet();
        EffectOnParchment variationParchment = new EffectOnParchment(1, false);
        ResourceSet immediateVariation8 = new ResourceSet();
        immediateVariation8.variateResource(Resource.MONEY, 3);
        EffectOnResources immediateEffect = new EffectOnResources(immediateVariation8);
        immediateEffects8.add(immediateEffect);
        EffectOnResources effect8 = new EffectOnResources( variation8);
        bonusEffects8.add(variationParchment);
        ProdHarvEffect prodHarvEffect8 = new ProdHarvEffect(malusEffects8, bonusEffects8);
        prodHarvEffects8.add(prodHarvEffect8);
        requestedMap8.put(requiredValue8, prodHarvEffects8);
        DevelopmentCard card8 = new DevelopmentCard(name8,era8, requirements8, immediateEffects8, idColour8, requestedMap8);
        cards.add(card8);

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);

        Gson gson=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();
        FileWriter fw = new FileWriter("src/main/resources/model/cards2.txt");
        gson.toJson(cards, fw);
        fw.close();
    }

    public void writeYellowCard() throws IOException {
        List cards = new ArrayList();
        int requiredValue = 5;
        int era = 1;
        String name = "devcards_f_en_c_25";
        ResourceSet cost = new ResourceSet();
        String idColour = "YELLOW";
        List<Requirement> requirements = new ArrayList<>();
        List<Effect> immediateEffects = new ArrayList<>();
        List bonusEffects = new ArrayList();
        List malusEffects = new ArrayList();
        List prodHarvEffects = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap = new HashMap<>();
        EffectOnConditions effectOnCondition = new EffectOnConditions(Resource.MONEY, 1, "YELLOW");
        bonusEffects.add(effectOnCondition);
        ProdHarvEffect prodHarvEffect = new ProdHarvEffect(malusEffects, bonusEffects);
        prodHarvEffects.add(prodHarvEffect);
        requestedMap.put(requiredValue, prodHarvEffects);
        cost.variateResource(Resource.WOOD, -1);
        cost.variateResource(Resource.STONE, -3);
        ResourceSet demiRequirement = new ResourceSet();
        Requirement requirement = new Requirement(demiRequirement, cost);
        requirements.add(requirement);
        ResourceSet immediate = new ResourceSet();
        immediate.variateResource(Resource.VICTORYPOINT, 5);
        EffectOnResources immediateEffect = new EffectOnResources(immediate);
        immediateEffects.add(immediateEffect);
        DevelopmentCard card = new DevelopmentCard(name,era, requirements, immediateEffects, idColour, requestedMap);
        cards.add(card);

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);

        Gson gson=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();
        FileWriter fw = new FileWriter("src/main/resources/model/cards3.txt");
        gson.toJson(cards, fw);
        fw.close();
    }

    public void writePurpleCard() throws IOException {
        List cards = new ArrayList();
        int era = 1;
        int requiredValue = 0;
        String name = "devcards_f_en_c_73";
        ResourceSet cost = new ResourceSet();
        ResourceSet requirement = new ResourceSet();
        String idColour = "PURPLE";
        List<Requirement> requirements = new ArrayList<>();
        List<Effect> immediateEffects = new ArrayList<>();
        Map<Integer, List<ProdHarvEffect>> requestedMap = new HashMap<>();
        List prodHarvEffects = new ArrayList();
        ResourceSet immediate = new ResourceSet();
        immediate.variateResource(Resource.MILITARYPOINT, 5);
        EffectOnResources immediateEffect = new EffectOnResources(immediate);
        immediateEffects.add(immediateEffect);
        ResourceSet onEnd = new ResourceSet();
        onEnd.variateResource(Resource.VICTORYPOINT, 4);
        EffectOnEnd effectOnEnd = new EffectOnEnd(onEnd);
        immediateEffects.add(effectOnEnd);
        cost.variateResource(Resource.MONEY, -4);
        Requirement require = new Requirement(requirement, cost );
        requirements.add(require);
        requestedMap.put(requiredValue, prodHarvEffects);
        DevelopmentCard card = new DevelopmentCard(name, era, requirements, immediateEffects, idColour,  requestedMap);
        cards.add(card);
        int era2 = 1;
        int requiredValue2 = 0;
        String name2 = "devcards_f_en_c_80";
        ResourceSet cost2 = new ResourceSet();
        ResourceSet cost2b = new ResourceSet();
        ResourceSet requirement2 = new ResourceSet();
        ResourceSet requirement2b = new ResourceSet();
        String idColour2 = "PURPLE";
        List<Requirement> requirements2 = new ArrayList<>();
        List<Effect> immediateEffects2 = new ArrayList<>();
        Map<Integer, List<ProdHarvEffect>> requestedMap2 = new HashMap<>();
        List prodHarvEffects2 = new ArrayList();
        ResourceSet immediate2 = new ResourceSet();
        immediate2.variateResource(Resource.FAITHPOINT, 3);
        EffectOnResources immediateEffect2 = new EffectOnResources(immediate2);
        immediateEffects2.add(immediateEffect2);
        ResourceSet onEnd2 = new ResourceSet();
        onEnd2.variateResource(Resource.VICTORYPOINT, 1);
        EffectOnEnd effectOnEnd2 = new EffectOnEnd(onEnd);
        immediateEffects2.add(effectOnEnd2);
        cost2.variateResource(Resource.MONEY, -2);
        cost2.variateResource(Resource.STONE, -1);
        cost2.variateResource(Resource.WOOD, -1);
        Requirement require2 = new Requirement(requirement2, cost2 );
        requirements2.add(require);
        cost2b.variateResource(Resource.MILITARYPOINT, -2);
        requirement2b.variateResource(Resource.MILITARYPOINT, 4);
        Requirement require2b = new Requirement(requirement2b, cost2b);
        requirements2.add(require2b);
        requestedMap2.put(requiredValue2, prodHarvEffects2);
        DevelopmentCard card2 = new DevelopmentCard(name2, era2, requirements2, immediateEffects2, idColour2,  requestedMap2);
        cards.add(card2);
        int era3 = 2;
        int requiredValue3 = 0;
        String name3 = "devcards_f_en_c_84";
        ResourceSet cost3 = new ResourceSet();
        ResourceSet requirement3 = new ResourceSet();
        String idColour3 = "PURPLE";
        List<Requirement> requirements3 = new ArrayList<>();
        List<Effect> immediateEffects3 = new ArrayList<>();
        Map<Integer, List<ProdHarvEffect>> requestedMap3 = new HashMap<>();
        List prodHarvEffects3 = new ArrayList();
        ResourceSet immediate3 = new ResourceSet();
        immediate3.variateResource(Resource.MONEY, 5);
        EffectOnResources immediateEffect3 = new EffectOnResources(immediate3);
        immediateEffects3.add(immediateEffect3);
        EffectOnParchment immediateParchment = new EffectOnParchment(1, false);
        immediateEffects3.add(immediateParchment);
        ResourceSet onEnd3 = new ResourceSet();
        onEnd3.variateResource(Resource.VICTORYPOINT, 3);
        EffectOnEnd effectOnEnd3 = new EffectOnEnd(onEnd3);
        immediateEffects3.add(effectOnEnd3);
        cost3.variateResource(Resource.MILITARYPOINT, -3);
        requirement3.variateResource(Resource.MILITARYPOINT, 6);
        Requirement require3 = new Requirement(requirement3, cost3 );
        requirements3.add(require3);
        requestedMap3.put(requiredValue3, prodHarvEffects3);
        DevelopmentCard card3 = new DevelopmentCard(name3, era3, requirements3, immediateEffects3, idColour3,  requestedMap3);
        cards.add(card3);

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);

        Gson gson=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();
        FileWriter fw = new FileWriter("src/main/resources/model/cards4.txt");
        gson.toJson(cards, fw);
        fw.close();

    }

    public void writeBlueCard() throws IOException {
        List cards = new ArrayList();
        int era = 1;
        int requiredValue = 0;
        String name = "devcards_f_en_c_49";
        ResourceSet cost = new ResourceSet();
        ResourceSet requirement = new ResourceSet();
        String idColour = "BLUE";
        List<Requirement> requirements = new ArrayList<>();
        List<Effect> immediateEffects = new ArrayList<>();
        Map<Integer, List<ProdHarvEffect>> requestedMap = new HashMap<>();
        List prodHarvEffects = new ArrayList();
        ResourceSet immediate = new ResourceSet();
        immediate.variateResource(Resource.MILITARYPOINT, 3);
        EffectOnResources immediateEffect = new EffectOnResources(immediate);
        immediateEffects.add(immediateEffect);
        LinkedList<String> familyMemberColours = new LinkedList<>();
        familyMemberColours.add("WHITE");
        familyMemberColours.add("RED");
        familyMemberColours.add("BLACK");
        boolean permanent = true;
        BonusMalusOnAction bonusMalusOnAction = new BonusMalusOnAction("GREEN", familyMemberColours, ActionType.TOWER_ACTION, permanent, 2 );
        BonusMalusSet bonusMalusSet = new BonusMalusSet();
        List<BonusMalusOnAction> bonusMalusOnActions = new ArrayList<>();
        bonusMalusOnActions.add(bonusMalusOnAction);
        bonusMalusSet.addActionBonusMalus(bonusMalusOnActions);
        DonateBonusMalusEffect donateBonusMalusEffect = new DonateBonusMalusEffect(bonusMalusSet);
        immediateEffects.add(donateBonusMalusEffect);
        cost.variateResource(Resource.MONEY, -2);
        Requirement require = new Requirement(requirement, cost );
        requirements.add(require);
        requestedMap.put(requiredValue, prodHarvEffects);
        DevelopmentCard card = new DevelopmentCard(name, era, requirements, immediateEffects, idColour,  requestedMap);
        cards.add(card);
        int era2 = 2;
        int requiredValue2 = 0;
        String name2 = "devcards_f_en_c_57";
        ResourceSet cost2 = new ResourceSet();
        ResourceSet requirement2 = new ResourceSet();
        String idColour2 = "BLUE";
        List<Requirement> requirements2 = new ArrayList<>();
        List<Effect> immediateEffects2 = new ArrayList<>();
        Map<Integer, List<ProdHarvEffect>> requestedMap2 = new HashMap<>();
        List prodHarvEffects2 = new ArrayList();
        ResourceSet immediate2 = new ResourceSet();
        immediate2.variateResource(Resource.MILITARYPOINT, 2);
        EffectOnResources immediateEffect2 = new EffectOnResources(immediate2);
        immediateEffects2.add(immediateEffect2);
        String targetColour = "GREEN";
        EffectOnAction immediateAction = new EffectOnAction(TransitionType.ACTION_ON_TOWER, targetColour);
        immediateEffects2.add(immediateAction);
        cost2.variateResource(Resource.MONEY, -4);
        Requirement require2 = new Requirement(requirement2, cost2);
        requirements2.add(require2);
        requestedMap.put(requiredValue2, prodHarvEffects2);
        DevelopmentCard card2 = new DevelopmentCard(name2, era2, requirements2, immediateEffects2, idColour2, requestedMap2);
        cards.add(card2);


        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);

        Gson gson=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();
        FileWriter fw = new FileWriter("src/main/resources/model/cards5.txt");
        gson.toJson(cards, fw);
        fw.close();
    }




}
