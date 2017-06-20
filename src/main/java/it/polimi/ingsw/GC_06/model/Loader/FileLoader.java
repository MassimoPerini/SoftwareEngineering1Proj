package it.polimi.ingsw.GC_06.model.Loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.model.Board.*;
import it.polimi.ingsw.GC_06.model.Card.Card;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Effect.*;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
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
                .registerSubtype(EffectOnParchment.class);        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();

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

    public void writeBoard() throws IOException {
        int [] values = {1,3,5,7};

        Map<String, Tower> towers = new HashMap<>();

        //Generate towers
        //TODO una torre contiene carte solo di un solo tipo
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
        for (int i=0;i<2;i++) {
            ArrayList<ActionPlace> prodHarvActionPlaces = new ArrayList<>();
            prodHarvActionPlaces.add(new ActionPlaceFixed(new ArrayList<Effect>(), 1, 1));
            prodHarvActionPlaces.add(new ActionPlace(new ArrayList<Effect>(), 1));
            ProdHarvZone prodHarvZone = new ProdHarvZone(prodHarvActionPlaces);
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
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class).registerSubtype(EffectOnAction.class);

        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory1).create();
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
        List requirements = new ArrayList();
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
        EffectOnParchment variationParchment = new EffectOnParchment();
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
                .registerSubtype(EffectOnParchment.class);

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
        cost.variateResource(Resource.WOOD, 1);
        cost.variateResource(Resource.STONE, 3);
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
                .registerSubtype(EffectOnParchment.class);

        Gson gson=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();
        FileWriter fw = new FileWriter("src/main/resources/model/cards3.txt");
        gson.toJson(cards, fw);
        fw.close();
    }


}
