package it.polimi.ingsw.GC_06.model.Loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import java.lang.reflect.Type;
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
    private static final String CHURCH = "church";
    // private static final String GAME_MAP = "end_game_map";


    private String cardsRootPath;
    private String parchmentsPath;
    private String boardRootPath;
    private String defaultResourceRootPath;
    private String dicePath;
    // private String endGameMap;
    private String playerBoardPath;
    private String church;
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
        church = Setting.getInstance().getProperty(CHURCH);
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

    public void writeChurchRequirement() throws IOException {


        FileWriter fw = new FileWriter("src/main/resources/model/churchRequirement.txt");
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        Map<Integer, ResourceSet> requirements = new HashMap<>();

        ResourceSet start = new ResourceSet();
        start.variateResource(Resource.FAITHPOINT, 3);
        requirements.put(1, start);

        start = new ResourceSet();
        start.variateResource(Resource.FAITHPOINT, 4);
        requirements.put(2, start);

        start = new ResourceSet();
        start.variateResource(Resource.FAITHPOINT, 5);
        requirements.put(3, start);

/*
        JsonElement jsonTree = gson.toJsonTree(requirements, Map.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("ChurchRequirement", jsonTree);
*/
        gson.toJson(requirements, fw);

        fw.close();
    }

    public Map<Integer, ResourceSet> loadChurchRequirement()
    {
        InputStreamReader sr = new InputStreamReader(this.getClass().getResourceAsStream(church));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type mapType = new TypeToken<Map<Integer, ResourceSet>>(){}.getType();

        Map<Integer, ResourceSet> son = gson.fromJson(sr, mapType);

                //new Gson().fromJson(easyString, mapType);
/*
        JsonElement jelement = new JsonParser().parse(sr);
        JsonObject  jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("ChurchRequirement");
*/
        return son;
    }

    public DiceSet loadDiceSet() throws IOException {
        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(dicePath));
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        DiceSet diceSet = gson.fromJson(fr , DiceSet.class);
        fr.close();
        return diceSet;
    }

    //TODO remove it
    public void testCard() throws IOException {

        DevelopmentCard [] developmentCards = new DevelopmentCard[10];
        for (int i=0;i<10;i++)
        {
            Map <Integer, List<ProdHarvEffect>> effettiProdRaccolto = new HashMap<>();


            List<ProdHarvEffect> prodHarvEffects1 = new LinkedList<>();

            List<ProdHarvMalusEffect> prodHarvMalusEffects = new LinkedList<>();

            //malus per 1

            ResourceSet resourceSet = new ResourceSet();
            resourceSet.variateResource(Resource.MONEY, -10);
            resourceSet.variateResource(Resource.MILITARYPOINT, -15);
            EffectOnResources effectOnResources = new EffectOnResources(resourceSet);
            prodHarvMalusEffects.add(effectOnResources);

            List<Effect> prodHarvEffects = new LinkedList<>();
            resourceSet = new ResourceSet();
            resourceSet.variateResource(Resource.WOOD, 10);
            resourceSet.variateResource(Resource.STONE, 15);

            ProdHarvEffect prodHarvEffect = new ProdHarvEffect(prodHarvMalusEffects, prodHarvEffects);

            prodHarvEffects1.add(prodHarvEffect);

            // ---- ------ -----
            //bonus per 4
            ResourceSet resourceSet1 = new ResourceSet();
            resourceSet1.variateResource(Resource.MILITARYPOINT, 100);
            resourceSet1.variateResource(Resource.SERVANT, 50);
            EffectOnResources effectOnResources1 = new EffectOnResources(resourceSet1);
            LinkedList<Effect> effectOnResources2 = new LinkedList<>();
            effectOnResources2.add(effectOnResources1);
            prodHarvEffect =new ProdHarvEffect(new LinkedList<>(), effectOnResources2);
            LinkedList<ProdHarvEffect> effectOnResources3 = new LinkedList<>();
            effectOnResources3.add(prodHarvEffect);
            effettiProdRaccolto.put(4, effectOnResources3);

            //----- ----- ------- ------ ---------------------- ---------------
            //Bonus per 1

            resourceSet1 = new ResourceSet();
            resourceSet1.variateResource(Resource.MILITARYPOINT, 100);
            resourceSet1.variateResource(Resource.SERVANT, 50);
            effectOnResources1 = new EffectOnResources(resourceSet1);
            effectOnResources2 = new LinkedList<>();
            effectOnResources2.add(effectOnResources1);
            prodHarvEffect =new ProdHarvEffect(new LinkedList<>(), effectOnResources2);
            prodHarvEffects1.add(prodHarvEffect);

            //----------
            prodHarvMalusEffects = new LinkedList<>();

            resourceSet = new ResourceSet();
            resourceSet.variateResource(Resource.MONEY, -20);
            resourceSet.variateResource(Resource.MILITARYPOINT, -30);
            effectOnResources = new EffectOnResources(resourceSet);
            prodHarvMalusEffects.add(effectOnResources);

            prodHarvEffects = new LinkedList<>();
            resourceSet = new ResourceSet();
            resourceSet.variateResource(Resource.WOOD, 10);
            resourceSet.variateResource(Resource.STONE, 15);
            EffectOnResources effectOnResources4 = new EffectOnResources(resourceSet);
            prodHarvEffects.add(effectOnResources4);

            prodHarvEffect = new ProdHarvEffect(prodHarvMalusEffects, prodHarvEffects);
            prodHarvEffects1.add(prodHarvEffect);

            prodHarvEffect = new ProdHarvEffect(new LinkedList<>(), prodHarvEffects);
            prodHarvEffects1.add(prodHarvEffect);


            //------ ------

            resourceSet1 = new ResourceSet();
            resourceSet1.variateResource(Resource.MILITARYPOINT, 100);
            resourceSet1.variateResource(Resource.SERVANT, 50);
            effectOnResources1 = new EffectOnResources(resourceSet1);
            effectOnResources2 = new LinkedList<>();
            effectOnResources2.add(effectOnResources1);
            prodHarvEffect =new ProdHarvEffect(new LinkedList<>(), effectOnResources2);
            prodHarvEffects1.add(prodHarvEffect);

            //------ -------

            effettiProdRaccolto.put(1, prodHarvEffects1);
            developmentCards[i] = new DevelopmentCard("Produzione problematica", 1, new LinkedList<Requirement>(), new LinkedList<Effect>(), "YELLOW", effettiProdRaccolto);
        }

/*
        DevelopmentCard [] developmentCards = new DevelopmentCard[10];
        for (int i=0;i<10;i++) {
            ResourceSet requirement = new ResourceSet();
            requirement.variateResource(Resource.MILITARYPOINT, -10);
            requirement.variateResource(Resource.SERVANT, -5);

            ResourceSet cost = new ResourceSet();
            cost.variateResource(Resource.WOOD, -8);
            cost.variateResource(Resource.MONEY, -12);

            //----

            ResourceSet requirement2 = new ResourceSet();
            requirement2.variateResource(Resource.MILITARYPOINT, -15);
            requirement2.variateResource(Resource.SERVANT, -8);

            ResourceSet cost2 = new ResourceSet();
            cost2.variateResource(Resource.WOOD, -10);
            cost2.variateResource(Resource.STONE, -18);


            List<Requirement> requirements = new LinkedList<>();
            requirements.add(new Requirement(requirement, cost));
            requirements.add(new Requirement(requirement2, cost2));

            developmentCards[i] = new DevelopmentCard("Risorse multiple", 1, requirements, new LinkedList<>(), "GREEN", new HashMap<>());
        }*/
/*
        for (int i=0;i<10;i++) {
            Map<String, Integer> take = new HashMap<>();
            take.put("GREEN", 7);
            take.put("YELLOW", 5);
            take.put("BLUE", 3);
            take.put("PURPLE", 7);
            EffectOnNewCards effectOnNewCards = new EffectOnNewCards(take);
            List<Effect> effects = new LinkedList();
            effects.add(effectOnNewCards);
            DevelopmentCard developmentCard = new DevelopmentCard("Prendi qualche carta!", 1, new LinkedList<>(), effects, "BLUE", new HashMap<>());
            developmentCards[i] = developmentCard;
        }
*/
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class);
        RuntimeTypeAdapterFactory typeAdapterFactory3 = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class, "type").registerSubtype(EffectOnResources.class);
        Gson gson2 = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory3).create();

        FileWriter fw = new FileWriter("src/main/resources/model/testCardsMultipleProduction.txt");
        gson2.toJson(developmentCards, fw);
        fw.close();

    }

    public DevelopmentCard[] loadCards() throws IOException {

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);


        RuntimeTypeAdapterFactory typeAdapterFactory3 = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class, "type").registerSubtype(EffectOnResources.class);
        Gson gson2 = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory3).create();

        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(cardsRootPath));
        DevelopmentCard [] cards = gson2.fromJson(fr , DevelopmentCard [].class);
        fr.close();
        return cards;
        //    System.out.println(cards[3].toString());
    }

    public Board loadBoard() throws IOException {
        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(boardRootPath));

        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(ActionPlace.class, "type").registerSubtype(ActionPlace.class).registerSubtype(ActionPlaceFixed.class);
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);

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
        ProdHarvZone prodZone = new ProdHarvZone(prodActionPlaces, actionType, 1);
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
        ProdHarvZone harvZone = new ProdHarvZone(harvActionPlaces, actionType, 1);
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
            ProdHarvZone prodHarvZone = new ProdHarvZone(prodHarvActionPlaces,actionTypes[i], 1);
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
        int requiredValue9 = 1;
        int era9 = 2;
        String name9 = "devcards_f_en_c_9";
        List requirements9 = new ArrayList();
        List effects9 = new ArrayList();
        String idColour9 = "GREEN";
        List<Effect> immediateEffects9 = new ArrayList<>();
        List bonusEffects9 = new ArrayList();
        List malusEffects9 = new ArrayList();
        List prodHarvEffects9 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap9 = new HashMap<>();
        ResourceSet variation9 = new ResourceSet();
        variation9.variateResource(Resource.MONEY, 2);
        ResourceSet immediateVariation9 = new ResourceSet();
        immediateVariation9.variateResource(Resource.MONEY, 1);
        EffectOnResources immediateEffect9 = new EffectOnResources(immediateVariation9);
        immediateEffects9.add(immediateEffect9);
        EffectOnResources effect9 = new EffectOnResources( variation9);
        bonusEffects9.add(effect9);
        ProdHarvEffect prodHarvEffect9 = new ProdHarvEffect(malusEffects9, bonusEffects9);
        prodHarvEffects9.add(prodHarvEffect9);
        requestedMap9.put(requiredValue9, prodHarvEffects9);
        DevelopmentCard card9 = new DevelopmentCard(name9,era9, requirements9, immediateEffects9, idColour9, requestedMap9);
        cards.add(card9);
        int requiredValue10 = 3;
        int era10 = 2;
        String name10 = "devcards_f_en_c_10";
        List requirements10 = new ArrayList();
        String idColour10 = "GREEN";
        List<Effect> immediateEffects10 = new ArrayList<>();
        List bonusEffects10 = new ArrayList();
        List malusEffects10= new ArrayList();
        List prodHarvEffects10 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap10 = new HashMap<>();
        ResourceSet variation10 = new ResourceSet();
        variation10.variateResource(Resource.WOOD, 2);
        variation10.variateResource(Resource.MILITARYPOINT, 1);
        ResourceSet immediateVariation10 = new ResourceSet();
        immediateVariation10.variateResource(Resource.SERVANT, 1);
        EffectOnResources immediateEffect10 = new EffectOnResources(immediateVariation10);
        immediateEffects10.add(immediateEffect10);
        EffectOnResources effect10 = new EffectOnResources( variation10);
        bonusEffects10.add(effect10);
        ProdHarvEffect prodHarvEffect10 = new ProdHarvEffect(malusEffects10, bonusEffects10);
        prodHarvEffects10.add(prodHarvEffect10);
        requestedMap10.put(requiredValue10, prodHarvEffects10);
        DevelopmentCard card10 = new DevelopmentCard(name10,era10, requirements10, immediateEffects10, idColour10, requestedMap10);
        cards.add(card10);
        int requiredValue11 = 4;
        int era11 = 2;
        String name11 = "devcards_f_en_c_11";
        List requirements11 = new ArrayList();
        String idColour11 = "GREEN";
        List<Effect> immediateEffects11 = new ArrayList<>();
        List bonusEffects11 = new ArrayList();
        List malusEffects11= new ArrayList();
        List prodHarvEffects11 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap11 = new HashMap<>();
        ResourceSet variation11 = new ResourceSet();
        variation11.variateResource(Resource.STONE, 2);
        variation11.variateResource(Resource.SERVANT, 1);
        ResourceSet immediateVariation11 = new ResourceSet();
        immediateVariation11.variateResource(Resource.SERVANT, 2);
        immediateVariation11.variateResource(Resource.STONE, 1);
        EffectOnResources immediateEffect11 = new EffectOnResources(immediateVariation11);
        immediateEffects11.add(immediateEffect11);
        EffectOnResources effect11 = new EffectOnResources( variation11);
        bonusEffects11.add(effect11);
        ProdHarvEffect prodHarvEffect11 = new ProdHarvEffect(malusEffects11, bonusEffects11);
        prodHarvEffects11.add(prodHarvEffect11);
        requestedMap11.put(requiredValue11, prodHarvEffects11);
        DevelopmentCard card11 = new DevelopmentCard(name11,era11, requirements11, immediateEffects11, idColour11, requestedMap11);
        cards.add(card11);
        int requiredValue12 = 3;
        int era12 = 2;
        String name12 = "devcards_f_en_c_12";
        List requirements12 = new ArrayList();
        String idColour12 = "GREEN";
        List<Effect> immediateEffects12 = new ArrayList<>();
        List bonusEffects12 = new ArrayList();
        List malusEffects12= new ArrayList();
        List prodHarvEffects12 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap12 = new HashMap<>();
        ResourceSet variation12 = new ResourceSet();
        variation12.variateResource(Resource.STONE, 3);
        ResourceSet immediateVariation12 = new ResourceSet();
        immediateVariation12.variateResource(Resource.WOOD, 1);
        EffectOnResources immediateEffect12 = new EffectOnResources(immediateVariation12);
        immediateEffects12.add(immediateEffect12);
        EffectOnResources effect12 = new EffectOnResources( variation12);
        bonusEffects12.add(effect12);
        ProdHarvEffect prodHarvEffect12 = new ProdHarvEffect(malusEffects12, bonusEffects12);
        prodHarvEffects12.add(prodHarvEffect12);
        requestedMap12.put(requiredValue12, prodHarvEffects12);
        DevelopmentCard card12 = new DevelopmentCard(name12,era12, requirements12, immediateEffects12, idColour12, requestedMap12);
        cards.add(card12);
        int requiredValue13 = 4;
        int era13 = 2;
        String name13 = "devcards_f_en_c_13";
        List requirements13 = new ArrayList();
        String idColour13 = "GREEN";
        List<Effect> immediateEffects13 = new ArrayList<>();
        List bonusEffects13 = new ArrayList();
        List malusEffects13= new ArrayList();
        List prodHarvEffects13 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap13 = new HashMap<>();
        ResourceSet variation13 = new ResourceSet();
        variation13.variateResource(Resource.WOOD, 2);
        variation13.variateResource(Resource.MONEY, 1);
        ResourceSet immediateVariation13 = new ResourceSet();
        immediateVariation13.variateResource(Resource.WOOD, 1);
        immediateVariation13.variateResource(Resource.SERVANT, 2);
        EffectOnResources immediateEffect13 = new EffectOnResources(immediateVariation13);
        immediateEffects13.add(immediateEffect13);
        EffectOnResources effect13 = new EffectOnResources( variation13);
        bonusEffects13.add(effect13);
        ProdHarvEffect prodHarvEffect13 = new ProdHarvEffect(malusEffects13, bonusEffects13);
        prodHarvEffects13.add(prodHarvEffect13);
        requestedMap13.put(requiredValue13, prodHarvEffects13);
        DevelopmentCard card13 = new DevelopmentCard(name13,era13, requirements13, immediateEffects13, idColour13, requestedMap13);
        cards.add(card13);
        int requiredValue14 = 2;
        int era14 = 2;
        String name14 = "devcards_f_en_c_14";
        List requirements14 = new ArrayList();
        String idColour14 = "GREEN";
        List<Effect> immediateEffects14 = new ArrayList<>();
        List bonusEffects14 = new ArrayList();
        List malusEffects14= new ArrayList();
        List prodHarvEffects14 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap14 = new HashMap<>();
        ResourceSet variation14 = new ResourceSet();
        variation14.variateResource(Resource.FAITHPOINT, 1);
        ResourceSet immediateVariation14 = new ResourceSet();
        immediateVariation14.variateResource(Resource.FAITHPOINT, 1);
        EffectOnResources immediateEffect14 = new EffectOnResources(immediateVariation14);
        immediateEffects14.add(immediateEffect14);
        EffectOnResources effect14 = new EffectOnResources( variation14);
        bonusEffects14.add(effect14);
        ProdHarvEffect prodHarvEffect14 = new ProdHarvEffect(malusEffects14, bonusEffects14);
        prodHarvEffects14.add(prodHarvEffect14);
        requestedMap14.put(requiredValue14, prodHarvEffects14);
        DevelopmentCard card14 = new DevelopmentCard(name14,era14, requirements14, immediateEffects14, idColour14, requestedMap14);
        cards.add(card14);
        int requiredValue15 = 5;
        int era15 = 2;
        String name15 = "devcards_f_en_c_15";
        List requirements15 = new ArrayList();
        String idColour15 = "GREEN";
        List<Effect> immediateEffects15 = new ArrayList<>();
        List bonusEffects15 = new ArrayList();
        List malusEffects15= new ArrayList();
        List prodHarvEffects15 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap15 = new HashMap<>();
        ResourceSet variation15 = new ResourceSet();
        variation15.variateResource(Resource.SERVANT, 1);
        variation15.variateResource(Resource.MILITARYPOINT, 2);
        ResourceSet immediateVariation15 = new ResourceSet();
        EffectOnResources immediateEffect15 = new EffectOnResources(immediateVariation15);
        immediateEffects15.add(immediateEffect15);
        EffectOnResources effect15 = new EffectOnResources( variation15);
        bonusEffects15.add(effect15);
        ProdHarvEffect prodHarvEffect15 = new ProdHarvEffect(malusEffects15, bonusEffects15);
        prodHarvEffects15.add(prodHarvEffect15);
        requestedMap15.put(requiredValue15, prodHarvEffects15);
        DevelopmentCard card15 = new DevelopmentCard(name15,era15, requirements15, immediateEffects15, idColour15, requestedMap15);
        cards.add(card15);
        int requiredValue16 = 6;
        int era16 = 2;
        String name16 = "devcards_f_en_c_16";
        List requirements16 = new ArrayList();
        String idColour16 = "GREEN";
        List<Effect> immediateEffects16 = new ArrayList<>();
        List bonusEffects16 = new ArrayList();
        List malusEffects16= new ArrayList();
        List prodHarvEffects16 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap16 = new HashMap<>();
        ResourceSet variation16 = new ResourceSet();
        variation16.variateResource(Resource.STONE, 1);
        variation16.variateResource(Resource.MONEY, 1);
        variation16.variateResource(Resource.WOOD, 2);
        ResourceSet immediateVariation16 = new ResourceSet();
        immediateVariation16.variateResource(Resource.MONEY, 4);
        EffectOnResources immediateEffect16 = new EffectOnResources(immediateVariation16);
        immediateEffects16.add(immediateEffect16);
        EffectOnResources effect16 = new EffectOnResources( variation16);
        bonusEffects16.add(effect16);
        ProdHarvEffect prodHarvEffect16 = new ProdHarvEffect(malusEffects16, bonusEffects16);
        prodHarvEffects16.add(prodHarvEffect16);
        requestedMap16.put(requiredValue16, prodHarvEffects16);
        DevelopmentCard card16 = new DevelopmentCard(name16,era16, requirements16, immediateEffects16, idColour16, requestedMap16);
        cards.add(card16);
        int requiredValue17 = 1;
        int era17 = 3;
        String name17 = "devcards_f_en_c_17";
        List requirements17 = new ArrayList();
        String idColour17 = "GREEN";
        List<Effect> immediateEffects17 = new ArrayList<>();
        List bonusEffects17 = new ArrayList();
        List malusEffects17= new ArrayList();
        List prodHarvEffects17 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap17 = new HashMap<>();
        ResourceSet variation17 = new ResourceSet();
        variation17.variateResource(Resource.MONEY, 3);
        ResourceSet immediateVariation17 = new ResourceSet();
        immediateVariation17.variateResource(Resource.MONEY, 1);
        immediateVariation17.variateResource(Resource.SERVANT, 1);
        EffectOnResources immediateEffect17 = new EffectOnResources(immediateVariation17);
        immediateEffects17.add(immediateEffect17);
        EffectOnResources effect17 = new EffectOnResources( variation17);
        bonusEffects17.add(effect17);
        ProdHarvEffect prodHarvEffect17 = new ProdHarvEffect(malusEffects17, bonusEffects17);
        prodHarvEffects17.add(prodHarvEffect17);
        requestedMap17.put(requiredValue17, prodHarvEffects17);
        DevelopmentCard card17 = new DevelopmentCard(name17,era17, requirements17, immediateEffects17, idColour17, requestedMap17);
        cards.add(card17);
        int requiredValue18 = 3;
        int era18 = 3;
        String name18 = "devcards_f_en_c_18";
        List requirements18 = new ArrayList();
        String idColour18 = "GREEN";
        List<Effect> immediateEffects18 = new ArrayList<>();
        List bonusEffects18 = new ArrayList();
        List malusEffects18= new ArrayList();
        List prodHarvEffects18 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap18 = new HashMap<>();
        ResourceSet variation18 = new ResourceSet();
        variation18.variateResource(Resource.VICTORYPOINT, 2);
        variation18.variateResource(Resource.WOOD,2);
        ResourceSet immediateVariation18 = new ResourceSet();
        immediateVariation18.variateResource(Resource.VICTORYPOINT, 1);
        immediateVariation18.variateResource(Resource.WOOD, 1);
        EffectOnResources immediateEffect18 = new EffectOnResources(immediateVariation18);
        immediateEffects18.add(immediateEffect18);
        EffectOnResources effect18 = new EffectOnResources( variation18);
        bonusEffects18.add(effect18);
        ProdHarvEffect prodHarvEffect18 = new ProdHarvEffect(malusEffects18, bonusEffects18);
        prodHarvEffects18.add(prodHarvEffect18);
        requestedMap18.put(requiredValue18, prodHarvEffects18);
        DevelopmentCard card18 = new DevelopmentCard(name18,era18, requirements18, immediateEffects18, idColour18, requestedMap18);
        cards.add(card18);
        int requiredValue19 = 5;
        int era19 = 3;
        String name19 = "devcards_f_en_c_19";
        List requirements19 = new ArrayList();
        String idColour19 = "GREEN";
        List<Effect> immediateEffects19 = new ArrayList<>();
        List bonusEffects19 = new ArrayList();
        List malusEffects19= new ArrayList();
        List prodHarvEffects19 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap19 = new HashMap<>();
        ResourceSet variation19 = new ResourceSet();
        variation19.variateResource(Resource.VICTORYPOINT, 4);
        variation19.variateResource(Resource.WOOD,1);
        ResourceSet immediateVariation19 = new ResourceSet();
        immediateVariation19.variateResource(Resource.MILITARYPOINT, 2);
        EffectOnResources immediateEffect19 = new EffectOnResources(immediateVariation19);
        immediateEffects19.add(immediateEffect19);
        EffectOnResources effect19 = new EffectOnResources( variation19);
        bonusEffects19.add(effect19);
        ProdHarvEffect prodHarvEffect19 = new ProdHarvEffect(malusEffects19, bonusEffects19);
        prodHarvEffects19.add(prodHarvEffect19);
        requestedMap19.put(requiredValue19, prodHarvEffects19);
        DevelopmentCard card19 = new DevelopmentCard(name19,era19, requirements19, immediateEffects19, idColour19, requestedMap19);
        cards.add(card19);
        int requiredValue20 = 2;
        int era20 = 3;
        String name20 = "devcards_f_en_c_20";
        List requirements20 = new ArrayList();
        String idColour20 = "GREEN";
        List<Effect> immediateEffects20 = new ArrayList<>();
        List bonusEffects20 = new ArrayList();
        List malusEffects20= new ArrayList();
        List prodHarvEffects20 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap20 = new HashMap<>();
        ResourceSet variation20 = new ResourceSet();
        variation20.variateResource(Resource.VICTORYPOINT, 1);
        variation20.variateResource(Resource.STONE,2);
        ResourceSet immediateVariation20 = new ResourceSet();
        immediateVariation20.variateResource(Resource.VICTORYPOINT, 3);
        EffectOnResources immediateEffect20 = new EffectOnResources(immediateVariation20);
        immediateEffects20.add(immediateEffect20);
        EffectOnResources effect20 = new EffectOnResources( variation20);
        bonusEffects20.add(effect20);
        ProdHarvEffect prodHarvEffect20 = new ProdHarvEffect(malusEffects20, bonusEffects20);
        prodHarvEffects20.add(prodHarvEffect20);
        requestedMap20.put(requiredValue20, prodHarvEffects20);
        DevelopmentCard card20 = new DevelopmentCard(name20,era20, requirements20, immediateEffects20, idColour20, requestedMap20);
        cards.add(card20);
        int requiredValue21 = 6;
        int era21 = 3;
        String name21 = "devcards_f_en_c_21";
        List requirements21 = new ArrayList();
        String idColour21 = "GREEN";
        List<Effect> immediateEffects21 = new ArrayList<>();
        List bonusEffects21 = new ArrayList();
        List malusEffects21= new ArrayList();
        List prodHarvEffects21 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap21 = new HashMap<>();
        ResourceSet variation21 = new ResourceSet();
        variation21.variateResource(Resource.VICTORYPOINT, 4);
        variation21.variateResource(Resource.STONE,1);
        ResourceSet immediateVariation21 = new ResourceSet();
        immediateVariation21.variateResource(Resource.STONE, 1);
        EffectOnResources immediateEffect21 = new EffectOnResources(immediateVariation21);
        immediateEffects21.add(immediateEffect21);
        EffectOnParchment immediateEffectOnParchment = new EffectOnParchment(1,false);
        immediateEffects21.add(immediateEffectOnParchment);
        EffectOnResources effect21 = new EffectOnResources( variation21);
        bonusEffects21.add(effect21);
        ProdHarvEffect prodHarvEffect21 = new ProdHarvEffect(malusEffects21, bonusEffects21);
        prodHarvEffects21.add(prodHarvEffect21);
        requestedMap21.put(requiredValue21, prodHarvEffects21);
        DevelopmentCard card21 = new DevelopmentCard(name21,era21, requirements21, immediateEffects21, idColour21, requestedMap21);
        cards.add(card21);
        int requiredValue22 = 1;
        int era22 = 3;
        String name22 = "devcards_f_en_c_22";
        List requirements22 = new ArrayList();
        String idColour22 = "GREEN";
        List<Effect> immediateEffects22 = new ArrayList<>();
        List bonusEffects22 = new ArrayList();
        List malusEffects22= new ArrayList();
        List prodHarvEffects22 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap22 = new HashMap<>();
        ResourceSet variation22 = new ResourceSet();
        variation22.variateResource(Resource.FAITHPOINT, 1);
        variation22.variateResource(Resource.MONEY,1);
        ResourceSet immediateVariation22 = new ResourceSet();
        immediateVariation22.variateResource(Resource.FAITHPOINT, 1);
        EffectOnResources immediateEffect22 = new EffectOnResources(immediateVariation22);
        immediateEffects22.add(immediateEffect22);
        EffectOnResources effect22 = new EffectOnResources( variation22);
        bonusEffects22.add(effect22);
        ProdHarvEffect prodHarvEffect22 = new ProdHarvEffect(malusEffects22, bonusEffects22);
        prodHarvEffects22.add(prodHarvEffect22);
        requestedMap22.put(requiredValue22, prodHarvEffects22);
        DevelopmentCard card22 = new DevelopmentCard(name22,era22, requirements22, immediateEffects22, idColour22, requestedMap22);
        cards.add(card22);
        int requiredValue23 = 4;
        int era23 = 3;
        String name23 = "devcards_f_en_c_23";
        List requirements23 = new ArrayList();
        String idColour23 = "GREEN";
        List<Effect> immediateEffects23 = new ArrayList<>();
        List bonusEffects23 = new ArrayList();
        List malusEffects23= new ArrayList();
        List prodHarvEffects23 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap23 = new HashMap<>();
        ResourceSet variation23 = new ResourceSet();
        variation23.variateResource(Resource.MILITARYPOINT, 3);
        variation23.variateResource(Resource.SERVANT,1);
        ResourceSet immediateVariation23 = new ResourceSet();
        immediateVariation23.variateResource(Resource.VICTORYPOINT, 2);
        immediateVariation23.variateResource(Resource.MONEY, 2);
        EffectOnResources immediateEffect23 = new EffectOnResources(immediateVariation23);
        immediateEffects23.add(immediateEffect23);
        EffectOnResources effect23 = new EffectOnResources( variation23);
        bonusEffects23.add(effect23);
        ProdHarvEffect prodHarvEffect23 = new ProdHarvEffect(malusEffects23, bonusEffects23);
        prodHarvEffects23.add(prodHarvEffect23);
        requestedMap23.put(requiredValue23, prodHarvEffects23);
        DevelopmentCard card23 = new DevelopmentCard(name23,era23, requirements23, immediateEffects23, idColour23, requestedMap23);
        cards.add(card23);
        int requiredValue24 = 2;
        int era24 = 3;
        String name24 = "devcards_f_en_c_24";
        List requirements24 = new ArrayList();
        String idColour24 = "GREEN";
        List<Effect> immediateEffects24 = new ArrayList<>();
        List bonusEffects24 = new ArrayList();
        List malusEffects24= new ArrayList();
        List prodHarvEffects24 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap24 = new HashMap<>();
        ResourceSet variation24 = new ResourceSet();
        variation24.variateResource(Resource.MILITARYPOINT, 1);
        variation24.variateResource(Resource.SERVANT,2);
        ResourceSet immediateVariation24 = new ResourceSet();
        immediateVariation24.variateResource(Resource.MILITARYPOINT, 2);
        immediateVariation24.variateResource(Resource.SERVANT, 1);
        EffectOnResources immediateEffect24 = new EffectOnResources(immediateVariation24);
        immediateEffects24.add(immediateEffect24);
        EffectOnResources effect24 = new EffectOnResources( variation24);
        bonusEffects24.add(effect24);
        ProdHarvEffect prodHarvEffect24 = new ProdHarvEffect(malusEffects24, bonusEffects24);
        prodHarvEffects24.add(prodHarvEffect24);
        requestedMap24.put(requiredValue24, prodHarvEffects24);
        DevelopmentCard card24 = new DevelopmentCard(name24,era24, requirements24, immediateEffects24, idColour24, requestedMap24);
        cards.add(card24);


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
        int requiredValue25 = 5;
        int era25 = 1;
        String name25 = "devcards_f_en_c_25";
        ResourceSet cost25 = new ResourceSet();
        String idColour25 = "YELLOW";
        List<Requirement> requirements25 = new ArrayList<>();
        List<Effect> immediateEffects25 = new ArrayList<>();
        List bonusEffects25 = new ArrayList();
        List malusEffects25 = new ArrayList();
        List prodHarvEffects25 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap25 = new HashMap<>();
        EffectOnConditions effectOnCondition25 = new EffectOnConditions(Resource.MONEY, 1, "YELLOW");
        bonusEffects25.add(effectOnCondition25);
        ProdHarvEffect prodHarvEffect25 = new ProdHarvEffect(malusEffects25, bonusEffects25);
        prodHarvEffects25.add(prodHarvEffect25);
        requestedMap25.put(requiredValue25, prodHarvEffects25);
        cost25.variateResource(Resource.WOOD, -1);
        cost25.variateResource(Resource.STONE, -3);
        ResourceSet demiRequirement25 = new ResourceSet();
        Requirement requirement25 = new Requirement(demiRequirement25, cost25);
        requirements25.add(requirement25);
        ResourceSet immediate25 = new ResourceSet();
        immediate25.variateResource(Resource.VICTORYPOINT, 5);
        EffectOnResources immediateEffect25 = new EffectOnResources(immediate25);
        immediateEffects25.add(immediateEffect25);
        DevelopmentCard card25 = new DevelopmentCard(name25,era25, requirements25, immediateEffects25, idColour25, requestedMap25);
        cards.add(card25);
        int requiredValue26 = 5;
        int era26 = 1;
        String name26 = "devcards_f_en_c_26";
        ResourceSet cost26 = new ResourceSet();
        String idColour26 = "YELLOW";
        List<Requirement> requirements26 = new ArrayList<>();
        List<Effect> immediateEffects26 = new ArrayList<>();
        List bonusEffects26 = new ArrayList();
        List malusEffects26 = new ArrayList();
        List prodHarvEffects26 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap26 = new HashMap<>();
        EffectOnConditions effectOnCondition26 = new EffectOnConditions(Resource.MONEY, 1, "GREEN");
        bonusEffects26.add(effectOnCondition26);
        ProdHarvEffect prodHarvEffect26 = new ProdHarvEffect(malusEffects26, bonusEffects26);
        prodHarvEffects26.add(prodHarvEffect26);
        requestedMap26.put(requiredValue26, prodHarvEffects26);
        cost26.variateResource(Resource.WOOD, -3);
        cost26.variateResource(Resource.STONE, -1);
        ResourceSet demiRequirement26 = new ResourceSet();
        Requirement requirement26 = new Requirement(demiRequirement26, cost26);
        requirements26.add(requirement26);
        ResourceSet immediate26 = new ResourceSet();
        immediate26.variateResource(Resource.VICTORYPOINT, 5);
        EffectOnResources immediateEffect26 = new EffectOnResources(immediate26);
        immediateEffects26.add(immediateEffect26);
        DevelopmentCard card26 = new DevelopmentCard(name26,era26, requirements26, immediateEffects26, idColour26, requestedMap26);
        cards.add(card26);
        int requiredValue27 = 6;
        int era27 = 1;
        String name27 = "devcards_f_en_c_27";
        ResourceSet cost27 = new ResourceSet();
        String idColour27 = "YELLOW";
        List<Requirement> requirements27 = new ArrayList<>();
        List<Effect> immediateEffects27 = new ArrayList<>();
        List bonusEffects27 = new ArrayList();
        List malusEffects27 = new ArrayList();
        List prodHarvEffects27 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap27 = new HashMap<>();
        EffectOnConditions effectOnCondition27 = new EffectOnConditions(Resource.VICTORYPOINT, 1, "PURPLE");
        bonusEffects27.add(effectOnCondition27);
        ProdHarvEffect prodHarvEffect27 = new ProdHarvEffect(malusEffects27, bonusEffects27);
        prodHarvEffects27.add(prodHarvEffect27);
        requestedMap27.put(requiredValue27, prodHarvEffects27);
        cost27.variateResource(Resource.MONEY, -2);
        cost27.variateResource(Resource.STONE, -2);
        ResourceSet demiRequirement27 = new ResourceSet();
        Requirement requirement27 = new Requirement(demiRequirement27, cost27);
        requirements27.add(requirement27);
        ResourceSet immediate27 = new ResourceSet();
        immediate27.variateResource(Resource.VICTORYPOINT, 6);
        EffectOnResources immediateEffect27 = new EffectOnResources(immediate27);
        immediateEffects27.add(immediateEffect27);
        DevelopmentCard card27 = new DevelopmentCard(name27,era27, requirements27, immediateEffects27, idColour27, requestedMap27);
        cards.add(card27);
        int requiredValue28 = 6;
        int era28 = 1;
        String name28 = "devcards_f_en_c_28";
        ResourceSet cost28 = new ResourceSet();
        String idColour28 = "YELLOW";
        List<Requirement> requirements28 = new ArrayList<>();
        List<Effect> immediateEffects28 = new ArrayList<>();
        List bonusEffects28 = new ArrayList();
        List malusEffects28 = new ArrayList();
        List prodHarvEffects28 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap28 = new HashMap<>();
        EffectOnConditions effectOnCondition28 = new EffectOnConditions(Resource.VICTORYPOINT, 1, "BLUE");
        bonusEffects28.add(effectOnCondition28);
        ProdHarvEffect prodHarvEffect28 = new ProdHarvEffect(malusEffects28, bonusEffects28);
        prodHarvEffects28.add(prodHarvEffect28);
        requestedMap28.put(requiredValue28, prodHarvEffects28);
        cost28.variateResource(Resource.MONEY, -2);
        cost28.variateResource(Resource.WOOD, -2);
        ResourceSet demiRequirement28 = new ResourceSet();
        Requirement requirement28 = new Requirement(demiRequirement28, cost28);
        requirements28.add(requirement28);
        ResourceSet immediate28 = new ResourceSet();
        immediate28.variateResource(Resource.VICTORYPOINT, 6);
        EffectOnResources immediateEffect28 = new EffectOnResources(immediate28);
        immediateEffects28.add(immediateEffect28);
        DevelopmentCard card28 = new DevelopmentCard(name28,era28, requirements28, immediateEffects28, idColour28, requestedMap28);
        cards.add(card28);
        int requiredValue29 = 4;
        int era29 = 1;
        String name29 = "devcards_f_en_c_29";
        ResourceSet cost29 = new ResourceSet();
        String idColour29 = "YELLOW";
        List<Requirement> requirements29 = new ArrayList<>();
        List<Effect> immediateEffects29 = new ArrayList<>();
        List bonusEffects29 = new ArrayList();
        List malusEffects29 = new ArrayList();
        List bonusEffects29b = new ArrayList();
        List malusEffects29b = new ArrayList();
        List prodHarvEffects29 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap29 = new HashMap<>();
        ResourceSet bonusPart29a = new ResourceSet();
        bonusPart29a.variateResource(Resource.MONEY, 3);
        EffectOnResources effectBonus29a = new EffectOnResources(bonusPart29a);
        bonusEffects29.add(effectBonus29a);
        ResourceSet malusPart29a = new ResourceSet();
        malusPart29a.variateResource(Resource.WOOD, -1);
        EffectOnResources effectMalus29a = new EffectOnResources(malusPart29a);
        malusEffects29.add(effectMalus29a);
        ProdHarvEffect prodHarvEffect29a = new ProdHarvEffect(malusEffects29, bonusEffects29);
        prodHarvEffects29.add(prodHarvEffect29a);
        ResourceSet bonusPart29b = new ResourceSet();
        bonusPart29b.variateResource(Resource.MONEY, 5);
        EffectOnResources effectBonus29b = new EffectOnResources(bonusPart29b);
        bonusEffects29b.add(effectBonus29b);
        ResourceSet malusPart29b = new ResourceSet();
        malusPart29b.variateResource(Resource.WOOD, -2);
        EffectOnResources effectMalus29b = new EffectOnResources(malusPart29b);
        malusEffects29b.add(effectMalus29b);
        ProdHarvEffect prodHarvEffect29b = new ProdHarvEffect(malusEffects29b, bonusEffects29b);
        prodHarvEffects29.add(prodHarvEffect29b);
        requestedMap29.put(requiredValue29, prodHarvEffects29);
        cost29.variateResource(Resource.MONEY, -1);
        cost29.variateResource(Resource.WOOD, -2);
        ResourceSet demiRequirement29 = new ResourceSet();
        Requirement requirement29 = new Requirement(demiRequirement29, cost29);
        requirements29.add(requirement29);
        ResourceSet immediate29 = new ResourceSet();
        immediate29.variateResource(Resource.VICTORYPOINT, 3);
        EffectOnResources immediateEffect29 = new EffectOnResources(immediate29);
        immediateEffects29.add(immediateEffect29);
        DevelopmentCard card29 = new DevelopmentCard(name29,era29, requirements29, immediateEffects29, idColour29, requestedMap29);
        cards.add(card29);
        int requiredValue30 = 3;
        int era30 = 1;
        String name30 = "devcards_f_en_c_30";
        ResourceSet cost30 = new ResourceSet();
        String idColour30 = "YELLOW";
        List<Requirement> requirements30 = new ArrayList<>();
        List<Effect> immediateEffects30 = new ArrayList<>();
        List bonusEffects30 = new ArrayList();
        List malusEffects30 = new ArrayList();
        List bonusEffects30b = new ArrayList();
        List malusEffects30b = new ArrayList();
        List prodHarvEffects30 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap30 = new HashMap<>();
        ResourceSet bonusPart30a = new ResourceSet();
        bonusPart30a.variateResource(Resource.MONEY, 3);
        EffectOnResources effectBonus30a = new EffectOnResources(bonusPart30a);
        bonusEffects30.add(effectBonus30a);
        ResourceSet malusPart30a = new ResourceSet();
        malusPart30a.variateResource(Resource.STONE, -1);
        EffectOnResources effectMalus30a = new EffectOnResources(malusPart30a);
        malusEffects30.add(effectMalus30a);
        ProdHarvEffect prodHarvEffect30a = new ProdHarvEffect(malusEffects30, bonusEffects30);
        prodHarvEffects30.add(prodHarvEffect30a);
        ResourceSet bonusPart30b = new ResourceSet();
        bonusPart30b.variateResource(Resource.MONEY, 5);
        EffectOnResources effectBonus30b = new EffectOnResources(bonusPart30b);
        bonusEffects30b.add(effectBonus30b);
        ResourceSet malusPart30b = new ResourceSet();
        malusPart30b.variateResource(Resource.STONE, -2);
        EffectOnResources effectMalus30b = new EffectOnResources(malusPart30b);
        malusEffects30b.add(effectMalus30b);
        ProdHarvEffect prodHarvEffect30b = new ProdHarvEffect(malusEffects30b, bonusEffects30b);
        prodHarvEffects30.add(prodHarvEffect30b);
        requestedMap30.put(requiredValue30, prodHarvEffects30);
        cost30.variateResource(Resource.MONEY, -1);
        cost30.variateResource(Resource.STONE, -2);
        ResourceSet demiRequirement30 = new ResourceSet();
        Requirement requirement30 = new Requirement(demiRequirement30, cost30);
        requirements30.add(requirement30);
        ResourceSet immediate30 = new ResourceSet();
        immediate30.variateResource(Resource.VICTORYPOINT, 2);
        EffectOnResources immediateEffect30 = new EffectOnResources(immediate30);
        immediateEffects30.add(immediateEffect30);
        DevelopmentCard card30 = new DevelopmentCard(name30,era30, requirements30, immediateEffects30, idColour30, requestedMap30);
        cards.add(card30);
        int requiredValue31 = 2;
        int era31 = 1;
        String name31 = "devcards_f_en_c_31";
        ResourceSet cost31 = new ResourceSet();
        String idColour31 = "YELLOW";
        List<Requirement> requirements31 = new ArrayList<>();
        List<Effect> immediateEffects31 = new ArrayList<>();
        List bonusEffects31 = new ArrayList();
        List malusEffects31 = new ArrayList();
        List prodHarvEffects31 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap31 = new HashMap<>();
        ResourceSet bonusPart31a = new ResourceSet();
        bonusPart31a.variateResource(Resource.FAITHPOINT, 1);
        EffectOnResources effectBonus31a = new EffectOnResources(bonusPart31a);
        bonusEffects31.add(effectBonus31a);
        ResourceSet malusPart31a = new ResourceSet();
        malusPart31a.variateResource(Resource.MONEY, -1);
        EffectOnResources effectMalus31a = new EffectOnResources(malusPart31a);
        malusEffects31.add(effectMalus31a);
        ProdHarvEffect prodHarvEffect31a = new ProdHarvEffect(malusEffects31, bonusEffects31);
        prodHarvEffects31.add(prodHarvEffect31a);
        requestedMap31.put(requiredValue31, prodHarvEffects31);
        cost31.variateResource(Resource.WOOD, -2);
        ResourceSet demiRequirement31 = new ResourceSet();
        Requirement requirement31 = new Requirement(demiRequirement31, cost31);
        requirements31.add(requirement31);
        ResourceSet immediate31 = new ResourceSet();
        immediate31.variateResource(Resource.FAITHPOINT, 1);
        EffectOnResources immediateEffect31 = new EffectOnResources(immediate31);
        immediateEffects31.add(immediateEffect31);
        DevelopmentCard card31 = new DevelopmentCard(name31,era31, requirements31, immediateEffects31, idColour31, requestedMap31);
        cards.add(card31);
        int requiredValue32 = 1;
        int era32 = 1;
        String name32 = "devcards_f_en_c_32";
        ResourceSet cost32 = new ResourceSet();
        String idColour32 = "YELLOW";
        List<Requirement> requirements32 = new ArrayList<>();
        List<Effect> immediateEffects32 = new ArrayList<>();
        List bonusEffects32 = new ArrayList();
        List malusEffects32 = new ArrayList();
        List prodHarvEffects32 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap32 = new HashMap<>();
        EffectOnParchment effectBonus32a = new EffectOnParchment(1, false);
        bonusEffects32.add(effectBonus32a);
        ResourceSet malusPart32a = new ResourceSet();
        malusPart32a.variateResource(Resource.MONEY, -1);
        EffectOnResources effectMalus32a = new EffectOnResources(malusPart32a);
        malusEffects32.add(effectMalus32a);
        ProdHarvEffect prodHarvEffect32a = new ProdHarvEffect(malusEffects32, bonusEffects32);
        prodHarvEffects32.add(prodHarvEffect32a);
        requestedMap32.put(requiredValue32, prodHarvEffects32);
        cost32.variateResource(Resource.STONE, -2);
        ResourceSet demiRequirement32 = new ResourceSet();
        Requirement requirement32 = new Requirement(demiRequirement32, cost32);
        requirements32.add(requirement32);
        ResourceSet immediate32 = new ResourceSet();
        immediate32.variateResource(Resource.VICTORYPOINT, 1);
        EffectOnResources immediateEffect32 = new EffectOnResources(immediate32);
        immediateEffects32.add(immediateEffect32);
        DevelopmentCard card32 = new DevelopmentCard(name32,era32, requirements32, immediateEffects32, idColour32, requestedMap32);
        cards.add(card32);
        int requiredValue33 = 3;
        int era33 = 2;
        String name33 = "devcards_f_en_c_33";
        ResourceSet cost33 = new ResourceSet();
        String idColour33 = "YELLOW";
        List<Requirement> requirements33 = new ArrayList<>();
        List<Effect> immediateEffects33 = new ArrayList<>();
        List bonusEffects33 = new ArrayList();
        List malusEffects33 = new ArrayList();
        List bonusEffects33b = new ArrayList();
        List malusEffects33b = new ArrayList();
        List prodHarvEffects33 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap33 = new HashMap<>();
        ResourceSet bonusPart33a = new ResourceSet();
        bonusPart33a.variateResource(Resource.WOOD, 2);
        bonusPart33a.variateResource(Resource.STONE, 2);
        EffectOnResources effectBonus33a = new EffectOnResources(bonusPart33a);
        bonusEffects33.add(effectBonus33a);
        ResourceSet malusPart33a = new ResourceSet();
        malusPart33a.variateResource(Resource.MONEY, -3);
        EffectOnResources effectMalus33a = new EffectOnResources(malusPart33a);
        malusEffects33.add(effectMalus33a);
        ProdHarvEffect prodHarvEffect33a = new ProdHarvEffect(malusEffects33, bonusEffects33);
        prodHarvEffects33.add(prodHarvEffect33a);
        requestedMap33.put(requiredValue33, prodHarvEffects33);
        cost33.variateResource(Resource.STONE, -1);
        cost33.variateResource(Resource.WOOD, -2);
        ResourceSet demiRequirement33 = new ResourceSet();
        Requirement requirement33 = new Requirement(demiRequirement33, cost33);
        requirements33.add(requirement33);
        ResourceSet immediate33 = new ResourceSet();
        immediate33.variateResource(Resource.VICTORYPOINT, 3);
        EffectOnResources immediateEffect33 = new EffectOnResources(immediate33);
        immediateEffects33.add(immediateEffect33);
        DevelopmentCard card33 = new DevelopmentCard(name33,era33, requirements33, immediateEffects33, idColour33, requestedMap33);
        cards.add(card33);
        int requiredValue34 = 3;
        int era34 = 2;
        String name34 = "devcards_f_en_c_34";
        ResourceSet cost34 = new ResourceSet();
        String idColour34 = "YELLOW";
        List<Requirement> requirements34 = new ArrayList<>();
        List<Effect> immediateEffects34 = new ArrayList<>();
        List bonusEffects34 = new ArrayList();
        List malusEffects34 = new ArrayList();
        List bonusEffects34b = new ArrayList();
        List malusEffects34b = new ArrayList();
        List prodHarvEffects34 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap34 = new HashMap<>();
        ResourceSet bonusPart34a = new ResourceSet();
        bonusPart34a.variateResource(Resource.VICTORYPOINT, 3);
        EffectOnResources effectBonus34a = new EffectOnResources(bonusPart34a);
        bonusEffects34.add(effectBonus34a);
        ResourceSet malusPart34a = new ResourceSet();
        malusPart34a.variateResource(Resource.MONEY, -1);
        EffectOnResources effectMalus34a = new EffectOnResources(malusPart34a);
        malusEffects34.add(effectMalus34a);
        ProdHarvEffect prodHarvEffect34a = new ProdHarvEffect(malusEffects34, bonusEffects34);
        prodHarvEffects34.add(prodHarvEffect34a);
        ResourceSet bonusPart34b = new ResourceSet();
        bonusPart34b.variateResource(Resource.VICTORYPOINT, 5);
        EffectOnResources effectBonus34b = new EffectOnResources(bonusPart34b);
        bonusEffects34b.add(effectBonus34b);
        ResourceSet malusPart34b = new ResourceSet();
        malusPart34b.variateResource(Resource.MONEY, -2);
        EffectOnResources effectMalus34b = new EffectOnResources(malusPart34b);
        malusEffects34b.add(effectMalus34b);
        ProdHarvEffect prodHarvEffect34b = new ProdHarvEffect(malusEffects34b, bonusEffects34b);
        prodHarvEffects34.add(prodHarvEffect34b);
        requestedMap34.put(requiredValue34, prodHarvEffects34);
        cost34.variateResource(Resource.WOOD, -3);
        ResourceSet demiRequirement34 = new ResourceSet();
        Requirement requirement34 = new Requirement(demiRequirement34, cost34);
        requirements34.add(requirement34);
        ResourceSet immediate34 = new ResourceSet();
        immediate34.variateResource(Resource.VICTORYPOINT, 4);
        EffectOnResources immediateEffect34 = new EffectOnResources(immediate34);
        immediateEffects34.add(immediateEffect34);
        DevelopmentCard card34 = new DevelopmentCard(name34,era34, requirements34, immediateEffects34, idColour34, requestedMap34);
        cards.add(card34);
        int requiredValue35 = 4;
        int era35 = 2;
        String name35 = "devcards_f_en_c_35";
        ResourceSet cost35 = new ResourceSet();
        String idColour35 = "YELLOW";
        List<Requirement> requirements35 = new ArrayList<>();
        List<Effect> immediateEffects35 = new ArrayList<>();
        List bonusEffects35 = new ArrayList();
        List malusEffects35 = new ArrayList();
        List bonusEffects35b = new ArrayList();
        List malusEffects35b = new ArrayList();
        List prodHarvEffects35 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap35 = new HashMap<>();
        ResourceSet bonusPart35a = new ResourceSet();
        bonusPart35a.variateResource(Resource.VICTORYPOINT, 3);
        EffectOnResources effectBonus35a = new EffectOnResources(bonusPart35a);
        bonusEffects35.add(effectBonus35a);
        ResourceSet malusPart35a = new ResourceSet();
        malusPart35a.variateResource(Resource.WOOD, -1);
        EffectOnResources effectMalus35a = new EffectOnResources(malusPart35a);
        malusEffects35.add(effectMalus35a);
        ProdHarvEffect prodHarvEffect35a = new ProdHarvEffect(malusEffects35, bonusEffects35);
        prodHarvEffects35.add(prodHarvEffect35a);
        ResourceSet bonusPart35b = new ResourceSet();
        bonusPart35b.variateResource(Resource.VICTORYPOINT, 7);
        EffectOnResources effectBonus35b = new EffectOnResources(bonusPart35b);
        bonusEffects35b.add(effectBonus35b);
        ResourceSet malusPart35b = new ResourceSet();
        malusPart35b.variateResource(Resource.WOOD, -3);
        EffectOnResources effectMalus35b = new EffectOnResources(malusPart35b);
        malusEffects35b.add(effectMalus35b);
        ProdHarvEffect prodHarvEffect35b = new ProdHarvEffect(malusEffects35b, bonusEffects35b);
        prodHarvEffects35.add(prodHarvEffect35b);
        requestedMap35.put(requiredValue35, prodHarvEffects35);
        cost35.variateResource(Resource.WOOD, -4);
        ResourceSet demiRequirement35 = new ResourceSet();
        Requirement requirement35 = new Requirement(demiRequirement35, cost35);
        requirements35.add(requirement35);
        ResourceSet immediate35 = new ResourceSet();
        immediate35.variateResource(Resource.VICTORYPOINT, 5);
        EffectOnResources immediateEffect35 = new EffectOnResources(immediate35);
        immediateEffects35.add(immediateEffect35);
        DevelopmentCard card35 = new DevelopmentCard(name35,era35, requirements35, immediateEffects35, idColour35, requestedMap35);
        cards.add(card35);
        int requiredValue36 = 5;
        int era36 = 2;
        String name36 = "devcards_f_en_c_36";
        ResourceSet cost36 = new ResourceSet();
        String idColour36 = "YELLOW";
        List<Requirement> requirements36 = new ArrayList<>();
        List<Effect> immediateEffects36 = new ArrayList<>();
        List bonusEffects36 = new ArrayList();
        List malusEffects36 = new ArrayList();
        List bonusEffects36b = new ArrayList();
        List malusEffects36b = new ArrayList();
        List prodHarvEffects36 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap36 = new HashMap<>();
        ResourceSet bonusPart36a = new ResourceSet();
        bonusPart36a.variateResource(Resource.VICTORYPOINT, 3);
        EffectOnResources effectBonus36a = new EffectOnResources(bonusPart36a);
        bonusEffects36.add(effectBonus36a);
        ResourceSet malusPart36a = new ResourceSet();
        malusPart36a.variateResource(Resource.STONE, -1);
        EffectOnResources effectMalus36a = new EffectOnResources(malusPart36a);
        malusEffects36.add(effectMalus36a);
        ProdHarvEffect prodHarvEffect36a = new ProdHarvEffect(malusEffects36, bonusEffects36);
        prodHarvEffects36.add(prodHarvEffect36a);
        ResourceSet bonusPart36b = new ResourceSet();
        bonusPart36b.variateResource(Resource.VICTORYPOINT, 7);
        EffectOnResources effectBonus36b = new EffectOnResources(bonusPart36b);
        bonusEffects36b.add(effectBonus36b);
        ResourceSet malusPart36b = new ResourceSet();
        malusPart36b.variateResource(Resource.STONE, -3);
        EffectOnResources effectMalus36b = new EffectOnResources(malusPart36b);
        malusEffects36b.add(effectMalus36b);
        ProdHarvEffect prodHarvEffect36b = new ProdHarvEffect(malusEffects36b, bonusEffects36b);
        prodHarvEffects36.add(prodHarvEffect36b);
        requestedMap36.put(requiredValue36, prodHarvEffects36);
        cost36.variateResource(Resource.STONE, -4);
        ResourceSet demiRequirement36 = new ResourceSet();
        Requirement requirement36 = new Requirement(demiRequirement36, cost36);
        requirements36.add(requirement36);
        ResourceSet immediate36 = new ResourceSet();
        immediate36.variateResource(Resource.VICTORYPOINT, 6);
        EffectOnResources immediateEffect36 = new EffectOnResources(immediate36);
        immediateEffects36.add(immediateEffect36);
        DevelopmentCard card36 = new DevelopmentCard(name36,era36, requirements36, immediateEffects36, idColour36, requestedMap36);
        cards.add(card36);
        int requiredValue37 = 4;
        int era37 = 2;
        String name37 = "devcards_f_en_c_37";
        ResourceSet cost37 = new ResourceSet();
        String idColour37 = "YELLOW";
        List<Requirement> requirements37 = new ArrayList<>();
        List<Effect> immediateEffects37 = new ArrayList<>();
        List bonusEffects37 = new ArrayList();
        List malusEffects37 = new ArrayList();
        List prodHarvEffects37 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap37 = new HashMap<>();
        ResourceSet bonusPart37a = new ResourceSet();
        bonusPart37a.variateResource(Resource.VICTORYPOINT, 6);
        EffectOnResources effectBonus37a = new EffectOnResources(bonusPart37a);
        bonusEffects37.add(effectBonus37a);
        ResourceSet malusPart37a = new ResourceSet();
        malusPart37a.variateResource(Resource.STONE, -1);
        malusPart37a.variateResource(Resource.WOOD, -1);
        malusPart37a.variateResource(Resource.SERVANT, -1);
        EffectOnResources effectMalus37a = new EffectOnResources(malusPart37a);
        malusEffects37.add(effectMalus37a);
        ProdHarvEffect prodHarvEffect37a = new ProdHarvEffect(malusEffects37, bonusEffects37);
        prodHarvEffects37.add(prodHarvEffect37a);
        requestedMap37.put(requiredValue37, prodHarvEffects37);
        cost37.variateResource(Resource.STONE, -2);
        cost37.variateResource(Resource.WOOD, -1);
        ResourceSet demiRequirement37 = new ResourceSet();
        Requirement requirement37 = new Requirement(demiRequirement37, cost37);
        requirements37.add(requirement37);
        ResourceSet immediate37 = new ResourceSet();
        immediate37.variateResource(Resource.VICTORYPOINT, 4);
        EffectOnResources immediateEffect37 = new EffectOnResources(immediate37);
        immediateEffects37.add(immediateEffect37);
        DevelopmentCard card37 = new DevelopmentCard(name37,era37, requirements37, immediateEffects37, idColour37, requestedMap37);
        cards.add(card37);
        int requiredValue38 = 2;
        int era38 = 2;
        String name38 = "devcards_f_en_c_38";
        ResourceSet cost38 = new ResourceSet();
        String idColour38 = "YELLOW";
        List<Requirement> requirements38 = new ArrayList<>();
        List<Effect> immediateEffects38 = new ArrayList<>();
        List bonusEffects38 = new ArrayList();
        List malusEffects38 = new ArrayList();
        List bonusEffects38b = new ArrayList();
        List malusEffects38b = new ArrayList();
        List prodHarvEffects38 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap38 = new HashMap<>();
        ResourceSet bonusPart38a = new ResourceSet();
        bonusPart38a.variateResource(Resource.MONEY, 2);
        bonusPart38a.variateResource(Resource.VICTORYPOINT, 2);
        EffectOnResources effectBonus38a = new EffectOnResources(bonusPart38a);
        bonusEffects38.add(effectBonus38a);
        ResourceSet malusPart38a = new ResourceSet();
        malusPart38a.variateResource(Resource.FAITHPOINT, -1);
        EffectOnResources effectMalus38a = new EffectOnResources(malusPart38a);
        malusEffects38.add(effectMalus38a);
        ProdHarvEffect prodHarvEffect38a = new ProdHarvEffect(malusEffects38, bonusEffects38);
        prodHarvEffects38.add(prodHarvEffect38a);
        requestedMap38.put(requiredValue38, prodHarvEffects38);
        cost38.variateResource(Resource.STONE, -3);
        ResourceSet demiRequirement38 = new ResourceSet();
        Requirement requirement38 = new Requirement(demiRequirement38, cost38);
        requirements38.add(requirement38);
        ResourceSet immediate38 = new ResourceSet();
        immediate38.variateResource(Resource.VICTORYPOINT, 2);
        immediate38.variateResource(Resource.FAITHPOINT, 1);
        EffectOnResources immediateEffect38 = new EffectOnResources(immediate38);
        immediateEffects38.add(immediateEffect38);
        DevelopmentCard card38 = new DevelopmentCard(name38,era38, requirements38, immediateEffects38, idColour38, requestedMap38);
        cards.add(card38);
        int requiredValue39 = 1;
        int era39 = 2;
        String name39 = "devcards_f_en_c_39";
        ResourceSet cost39 = new ResourceSet();
        String idColour39 = "YELLOW";
        List<Requirement> requirements39 = new ArrayList<>();
        List<Effect> immediateEffects39 = new ArrayList<>();
        List bonusEffects39 = new ArrayList();
        List malusEffects39 = new ArrayList();
        List prodHarvEffects39 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap39 = new HashMap<>();
        ResourceSet bonusPart39a = new ResourceSet();
        bonusPart39a.variateResource(Resource.MILITARYPOINT, 3);
        EffectOnResources effectBonus39a = new EffectOnResources(bonusPart39a);
        bonusEffects39.add(effectBonus39a);
        ResourceSet malusPart39a = new ResourceSet();
        malusPart39a.variateResource(Resource.SERVANT, -1);
        EffectOnResources effectMalus39a = new EffectOnResources(malusPart39a);
        malusEffects39.add(effectMalus39a);
        ProdHarvEffect prodHarvEffect39a = new ProdHarvEffect(malusEffects39, bonusEffects39);
        prodHarvEffects39.add(prodHarvEffect39a);
        requestedMap39.put(requiredValue39, prodHarvEffects39);
        cost39.variateResource(Resource.STONE, -1);
        cost39.variateResource(Resource.WOOD, -1);
        ResourceSet demiRequirement39 = new ResourceSet();
        Requirement requirement39 = new Requirement(demiRequirement39, cost39);
        requirements39.add(requirement39);
        ResourceSet immediate39 = new ResourceSet();
        immediate39.variateResource(Resource.VICTORYPOINT, 3);
        EffectOnResources immediateEffect39 = new EffectOnResources(immediate39);
        immediateEffects39.add(immediateEffect39);
        DevelopmentCard card39 = new DevelopmentCard(name39,era39, requirements39, immediateEffects39, idColour39, requestedMap39);
        cards.add(card39);
        int requiredValue40 = 6;
        int era40 = 2;
        String name40 = "devcards_f_en_c_40";
        ResourceSet cost40 = new ResourceSet();
        String idColour40 = "YELLOW";
        List<Requirement> requirements40 = new ArrayList<>();
        List<Effect> immediateEffects40 = new ArrayList<>();
        List bonusEffects40 = new ArrayList();
        List malusEffects40 = new ArrayList();
        List prodHarvEffects40 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap40 = new HashMap<>();
        ResourceSet bonusPart40a = new ResourceSet();
        bonusPart40a.variateResource(Resource.MILITARYPOINT, 2);
        bonusPart40a.variateResource(Resource.VICTORYPOINT, 2);
        EffectOnResources effectBonus40a = new EffectOnResources(bonusPart40a);
        bonusEffects40.add(effectBonus40a);
        ProdHarvEffect prodHarvEffect40a = new ProdHarvEffect(malusEffects40, bonusEffects40);
        prodHarvEffects40.add(prodHarvEffect40a);
        requestedMap40.put(requiredValue40, prodHarvEffects40);
        cost40.variateResource(Resource.STONE, -2);
        cost40.variateResource(Resource.WOOD, -2);
        cost40.variateResource(Resource.MONEY, -2);
        ResourceSet demiRequirement40 = new ResourceSet();
        Requirement requirement40 = new Requirement(demiRequirement40, cost40);
        requirements40.add(requirement40);
        ResourceSet immediate40 = new ResourceSet();
        immediate40.variateResource(Resource.VICTORYPOINT, 8);
        EffectOnResources immediateEffect40 = new EffectOnResources(immediate40);
        immediateEffects40.add(immediateEffect40);
        DevelopmentCard card40 = new DevelopmentCard(name40,era40, requirements40, immediateEffects40, idColour40, requestedMap40);
        cards.add(card40);
        int requiredValue41 = 2;
        int era41 = 3;
        String name41 = "devcards_f_en_c_41";
        ResourceSet cost41 = new ResourceSet();
        String idColour41 = "YELLOW";
        List<Requirement> requirements41 = new ArrayList<>();
        List<Effect> immediateEffects41 = new ArrayList<>();
        List bonusEffects41 = new ArrayList();
        List malusEffects41 = new ArrayList();
        List prodHarvEffects41 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap41 = new HashMap<>();
        ResourceSet bonusPart41a = new ResourceSet();
        bonusPart41a.variateResource(Resource.MONEY, 5);
        EffectOnResources effectBonus41a = new EffectOnResources(bonusPart41a);
        bonusEffects41.add(effectBonus41a);
        ProdHarvEffect prodHarvEffect41a = new ProdHarvEffect(malusEffects41, bonusEffects41);
        prodHarvEffects41.add(prodHarvEffect41a);
        requestedMap41.put(requiredValue41, prodHarvEffects41);
        cost41.variateResource(Resource.STONE, -3);
        cost41.variateResource(Resource.WOOD, -1);
        cost41.variateResource(Resource.MONEY, -3);
        ResourceSet demiRequirement41 = new ResourceSet();
        Requirement requirement41 = new Requirement(demiRequirement41, cost41);
        requirements41.add(requirement41);
        ResourceSet immediate41 = new ResourceSet();
        immediate41.variateResource(Resource.VICTORYPOINT, 7);
        EffectOnResources immediateEffect41 = new EffectOnResources(immediate41);
        immediateEffects41.add(immediateEffect41);
        DevelopmentCard card41 = new DevelopmentCard(name41,era41, requirements41, immediateEffects41, idColour41, requestedMap41);
        cards.add(card41);
        int requiredValue42 = 4;
        int era42 = 3;
        String name42 = "devcards_f_en_c_42";
        ResourceSet cost42 = new ResourceSet();
        String idColour42 = "YELLOW";
        List<Requirement> requirements42 = new ArrayList<>();
        List<Effect> immediateEffects42 = new ArrayList<>();
        List bonusEffects42 = new ArrayList();
        List malusEffects42 = new ArrayList();
        List prodHarvEffects42 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap42 = new HashMap<>();
        ResourceSet bonusPart42a = new ResourceSet();
        bonusPart42a.variateResource(Resource.WOOD, 3);
        bonusPart42a.variateResource(Resource.STONE, 3);
        EffectOnResources effectBonus42a = new EffectOnResources(bonusPart42a);
        bonusEffects42.add(effectBonus42a);
        ResourceSet malusPart42a = new ResourceSet();
        malusPart42a.variateResource(Resource.MONEY, -4);
        EffectOnResources effectMalus42a = new EffectOnResources(malusPart42a);
        malusEffects42.add(effectMalus42a);
        ProdHarvEffect prodHarvEffect42a = new ProdHarvEffect(malusEffects42, bonusEffects42);
        prodHarvEffects42.add(prodHarvEffect42a);
        requestedMap42.put(requiredValue42, prodHarvEffects42);
        cost42.variateResource(Resource.MONEY, -4);
        cost42.variateResource(Resource.WOOD, -3);
        ResourceSet demiRequirement42 = new ResourceSet();
        Requirement requirement42 = new Requirement(demiRequirement42, cost42);
        requirements42.add(requirement42);
        ResourceSet immediate42 = new ResourceSet();
        immediate42.variateResource(Resource.VICTORYPOINT, 8);
        EffectOnResources immediateEffect42 = new EffectOnResources(immediate42);
        immediateEffects42.add(immediateEffect42);
        DevelopmentCard card42 = new DevelopmentCard(name42,era42, requirements42, immediateEffects42, idColour42, requestedMap42);
        cards.add(card42);
        int requiredValue43 = 1;
        int era43 = 3;
        String name43 = "devcards_f_en_c_43";
        ResourceSet cost43 = new ResourceSet();
        String idColour43 = "YELLOW";
        List<Requirement> requirements43 = new ArrayList<>();
        List<Effect> immediateEffects43 = new ArrayList<>();
        List bonusEffects43 = new ArrayList();
        List malusEffects43 = new ArrayList();
        List prodHarvEffects43 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap43 = new HashMap<>();
        ResourceSet bonusPart43a = new ResourceSet();
        bonusPart43a.variateResource(Resource.VICTORYPOINT, 3);
        EffectOnResources effectBonus43a = new EffectOnResources(bonusPart43a);
        bonusEffects43.add(effectBonus43a);
        ProdHarvEffect prodHarvEffect43a = new ProdHarvEffect(malusEffects43, bonusEffects43);
        prodHarvEffects43.add(prodHarvEffect43a);
        requestedMap43.put(requiredValue43, prodHarvEffects43);
        cost43.variateResource(Resource.SERVANT, -2);
        cost43.variateResource(Resource.STONE, -2);
        cost43.variateResource(Resource.WOOD, -4);
        ResourceSet demiRequirement43 = new ResourceSet();
        Requirement requirement43 = new Requirement(demiRequirement43, cost43);
        requirements43.add(requirement43);
        ResourceSet immediate43 = new ResourceSet();
        immediate43.variateResource(Resource.VICTORYPOINT, 10);
        EffectOnResources immediateEffect43 = new EffectOnResources(immediate43);
        immediateEffects43.add(immediateEffect43);
        DevelopmentCard card43 = new DevelopmentCard(name43,era43, requirements43, immediateEffects43, idColour43, requestedMap43);
        cards.add(card43);
        int requiredValue44 = 5;
        int era44 = 3;
        String name44 = "devcards_f_en_c_44";
        ResourceSet cost44 = new ResourceSet();
        String idColour44 = "YELLOW";
        List<Requirement> requirements44 = new ArrayList<>();
        List<Effect> immediateEffects44 = new ArrayList<>();
        List bonusEffects44 = new ArrayList();
        List malusEffects44 = new ArrayList();
        List prodHarvEffects44 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap44 = new HashMap<>();
        ResourceSet bonusPart44a = new ResourceSet();
        bonusPart44a.variateResource(Resource.VICTORYPOINT, 2);
        EffectOnResources effectBonus44a = new EffectOnResources(bonusPart44a);
        bonusEffects44.add(effectBonus44a);
        EffectOnParchment effectBonusParchment44 = new EffectOnParchment(1, false);
        bonusEffects44.add(effectBonusParchment44);
        ProdHarvEffect prodHarvEffect44a = new ProdHarvEffect(malusEffects44, bonusEffects44);
        prodHarvEffects44.add(prodHarvEffect44a);
        requestedMap44.put(requiredValue44, prodHarvEffects44);
        cost44.variateResource(Resource.MONEY, -2);
        cost44.variateResource(Resource.WOOD, -2);
        cost44.variateResource(Resource.STONE, -4);
        ResourceSet demiRequirement44 = new ResourceSet();
        Requirement requirement44 = new Requirement(demiRequirement44, cost44);
        requirements44.add(requirement44);
        ResourceSet immediate44 = new ResourceSet();
        immediate44.variateResource(Resource.VICTORYPOINT, 9);
        EffectOnResources immediateEffect44 = new EffectOnResources(immediate44);
        immediateEffects44.add(immediateEffect44);
        DevelopmentCard card44 = new DevelopmentCard(name44,era44, requirements44, immediateEffects44, idColour44, requestedMap44);
        cards.add(card44);
        int requiredValue45 = 6;
        int era45 = 3;
        String name45 = "devcards_f_en_c_45";
        ResourceSet cost45 = new ResourceSet();
        String idColour45 = "YELLOW";
        List<Requirement> requirements45 = new ArrayList<>();
        List<Effect> immediateEffects45 = new ArrayList<>();
        List bonusEffects45 = new ArrayList();
        List malusEffects45 = new ArrayList();
        List prodHarvEffects45 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap45 = new HashMap<>();
        ResourceSet bonusPart45a = new ResourceSet();
        bonusPart45a.variateResource(Resource.SERVANT, 2);
        bonusPart45a.variateResource(Resource.VICTORYPOINT, 4);
        EffectOnResources effectBonus45a = new EffectOnResources(bonusPart45a);
        bonusEffects45.add(effectBonus45a);
        ResourceSet malusPart45a = new ResourceSet();
        malusPart45a.variateResource(Resource.MONEY, -1);
        EffectOnResources effectMalus45a = new EffectOnResources(malusPart45a);
        malusEffects45.add(effectMalus45a);
        ProdHarvEffect prodHarvEffect45a = new ProdHarvEffect(malusEffects45, bonusEffects45);
        prodHarvEffects45.add(prodHarvEffect45a);
        requestedMap45.put(requiredValue45, prodHarvEffects45);
        cost45.variateResource(Resource.MONEY, -3);
        cost45.variateResource(Resource.WOOD, -3);
        cost45.variateResource(Resource.STONE, -1);
        ResourceSet demiRequirement45 = new ResourceSet();
        Requirement requirement45 = new Requirement(demiRequirement45, cost45);
        requirements45.add(requirement45);
        ResourceSet immediate45 = new ResourceSet();
        immediate45.variateResource(Resource.VICTORYPOINT, 9);
        EffectOnResources immediateEffect45 = new EffectOnResources(immediate45);
        immediateEffects45.add(immediateEffect45);
        DevelopmentCard card45 = new DevelopmentCard(name45,era45, requirements45, immediateEffects45, idColour45, requestedMap45);
        cards.add(card45);
        int requiredValue46 = 1;
        int era46 = 3;
        String name46 = "devcards_f_en_c_46";
        ResourceSet cost46 = new ResourceSet();
        String idColour46 = "YELLOW";
        List<Requirement> requirements46 = new ArrayList<>();
        List<Effect> immediateEffects46 = new ArrayList<>();
        List bonusEffects46 = new ArrayList();
        List malusEffects46 = new ArrayList();
        List bonusEffects46b = new ArrayList();
        List malusEffects46b = new ArrayList();
        List prodHarvEffects46 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap46 = new HashMap<>();
        ResourceSet bonusPart46a = new ResourceSet();
        bonusPart46a.variateResource(Resource.FAITHPOINT, 2);
        EffectOnResources effectBonus46a = new EffectOnResources(bonusPart46a);
        bonusEffects46.add(effectBonus46a);
        ResourceSet malusPart46a = new ResourceSet();
        malusPart46a.variateResource(Resource.WOOD, -1);
        EffectOnResources effectMalus46a = new EffectOnResources(malusPart46a);
        malusEffects46.add(effectMalus46a);
        ProdHarvEffect prodHarvEffect46a = new ProdHarvEffect(malusEffects46, bonusEffects46);
        prodHarvEffects46.add(prodHarvEffect46a);
        ResourceSet bonusPart46b = new ResourceSet();
        bonusPart46b.variateResource(Resource.FAITHPOINT, 2);
        EffectOnResources effectBonus46b = new EffectOnResources(bonusPart46b);
        bonusEffects46b.add(effectBonus46b);
        ResourceSet malusPart46b = new ResourceSet();
        malusPart46b.variateResource(Resource.STONE, -1);
        EffectOnResources effectMalus46b = new EffectOnResources(malusPart46b);
        malusEffects46b.add(effectMalus46b);
        ProdHarvEffect prodHarvEffect46b = new ProdHarvEffect(malusEffects46b, bonusEffects46b);
        prodHarvEffects46.add(prodHarvEffect46b);
        requestedMap46.put(requiredValue46, prodHarvEffects46);
        cost46.variateResource(Resource.STONE, -4);
        cost46.variateResource(Resource.WOOD, -1);
        ResourceSet demiRequirement46 = new ResourceSet();
        Requirement requirement46 = new Requirement(demiRequirement46, cost46);
        requirements46.add(requirement46);
        ResourceSet immediate46 = new ResourceSet();
        immediate46.variateResource(Resource.VICTORYPOINT, 5);
        immediate46.variateResource(Resource.FAITHPOINT, 1);
        EffectOnResources immediateEffect46 = new EffectOnResources(immediate46);
        immediateEffects46.add(immediateEffect46);
        DevelopmentCard card46 = new DevelopmentCard(name46,era46, requirements46, immediateEffects46, idColour46, requestedMap46);
        cards.add(card46);
        int requiredValue47 = 3;
        int era47 = 3;
        String name47 = "devcards_f_en_c_47";
        ResourceSet cost47 = new ResourceSet();
        String idColour47 = "YELLOW";
        List<Requirement> requirements47 = new ArrayList<>();
        List<Effect> immediateEffects47 = new ArrayList<>();
        List bonusEffects47 = new ArrayList();
        List malusEffects47 = new ArrayList();
        List prodHarvEffects47 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap47 = new HashMap<>();
        ResourceSet bonusPart47a = new ResourceSet();
        bonusPart47a.variateResource(Resource.MILITARYPOINT, 3);
        bonusPart47a.variateResource(Resource.VICTORYPOINT, 1);
        EffectOnResources effectBonus47a = new EffectOnResources(bonusPart47a);
        bonusEffects47.add(effectBonus47a);
        ResourceSet malusPart47a = new ResourceSet();
        malusPart47a.variateResource(Resource.SERVANT, -1);
        EffectOnResources effectMalus47a = new EffectOnResources(malusPart47a);
        malusEffects47.add(effectMalus47a);
        ProdHarvEffect prodHarvEffect47a = new ProdHarvEffect(malusEffects47, bonusEffects47);
        prodHarvEffects47.add(prodHarvEffect47a);
        requestedMap47.put(requiredValue47, prodHarvEffects47);
        cost47.variateResource(Resource.SERVANT, -1);
        cost47.variateResource(Resource.WOOD, -2);
        cost47.variateResource(Resource.STONE, -2);
        ResourceSet demiRequirement47 = new ResourceSet();
        Requirement requirement47 = new Requirement(demiRequirement47, cost47);
        requirements47.add(requirement47);
        ResourceSet immediate47 = new ResourceSet();
        immediate47.variateResource(Resource.VICTORYPOINT, 7);
        EffectOnResources immediateEffect47 = new EffectOnResources(immediate47);
        immediateEffects47.add(immediateEffect47);
        DevelopmentCard card47 = new DevelopmentCard(name47,era47, requirements47, immediateEffects47, idColour47, requestedMap47);
        cards.add(card47);
        int requiredValue48 = 2;
        int era48 = 3;
        String name48 = "devcards_f_en_c_48";
        ResourceSet cost48 = new ResourceSet();
        String idColour48 = "YELLOW";
        List<Requirement> requirements48 = new ArrayList<>();
        List<Effect> immediateEffects48 = new ArrayList<>();
        List bonusEffects48 = new ArrayList();
        List malusEffects48 = new ArrayList();
        List prodHarvEffects48 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap48 = new HashMap<>();
        ResourceSet bonusPart48a = new ResourceSet();
        bonusPart48a.variateResource(Resource.VICTORYPOINT, 1);
        EffectOnResources effectBonus48a = new EffectOnResources(bonusPart48a);
        bonusEffects48.add(effectBonus48a);
        ProdHarvEffect prodHarvEffect48a = new ProdHarvEffect(malusEffects48, bonusEffects48);
        prodHarvEffects48.add(prodHarvEffect48a);
        requestedMap48.put(requiredValue48, prodHarvEffects48);
        cost48.variateResource(Resource.WOOD, -4);
        cost48.variateResource(Resource.STONE, -4);
        ResourceSet demiRequirement48 = new ResourceSet();
        Requirement requirement48 = new Requirement(demiRequirement48, cost48);
        requirements48.add(requirement48);
        ResourceSet immediate48 = new ResourceSet();
        immediate48.variateResource(Resource.VICTORYPOINT, 7);
        immediate48.variateResource(Resource.FAITHPOINT, 3);
        EffectOnResources immediateEffect48 = new EffectOnResources(immediate48);
        immediateEffects48.add(immediateEffect48);
        DevelopmentCard card48 = new DevelopmentCard(name48,era48, requirements48, immediateEffects48, idColour48, requestedMap48);
        cards.add(card48);

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