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
import it.polimi.ingsw.GC_06.model.Card.ExcomunicationCard;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Effect.*;
import it.polimi.ingsw.GC_06.model.PersonalBonusTile;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
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
 */

public class FileLoader {

    private static volatile FileLoader instance;

    private static final String CARDS_PATH = "cards_path";
    private static final String PARCHMENTS_PATH = "parchments_path";
    private static final String BOARD_PATH_MEDIUM = "board_medium";
    private static final String BOARD_PATH_SMALL = "board_small";
    private static final String BOARD_PATH_BIG = "board_big";
    private static final String DEFAULT_RES = "default_resource_path";
    private static final String DICES = "dices_path";
    private static final String PLAYER_BOARD = "player_board_path";
    private static final String CHURCH = "church";
    private static final String EXCOMMUNICATION = "excomm";
    private static final String PERSONAL_BONUS = "personal_bonus";
    private static final String END_GAME_MAP = "end_game_map";
    private static final String HERO_CARDS = "hero_cards";
    private static final String RANKING_POINTS = "ranking_points";
    // private static final String GAME_MAP = "end_game_map";


    private final String cardsRootPath;
    private final String parchmentsPath;
    private final String defaultResourceRootPath;
    private final String dicePath;
    // private String endGameMap;
    private final String playerBoardPath;
    private final String church;
    private final String excommunication;
    private final String personalBonus;
    private final String endGameMap;
    private final String heroCards;
    private final String rankingPoints;
    private Gson gson;


    private FileLoader ()
    {
        super();
        this.gson = new Gson();
        parchmentsPath = Setting.getInstance().getProperty(PARCHMENTS_PATH);
        cardsRootPath = Setting.getInstance().getProperty(CARDS_PATH);
        defaultResourceRootPath = Setting.getInstance().getProperty(DEFAULT_RES);
        dicePath = Setting.getInstance().getProperty(DICES);
        playerBoardPath = Setting.getInstance().getProperty(PLAYER_BOARD);
        church = Setting.getInstance().getProperty(CHURCH);
        excommunication = Setting.getInstance().getProperty(EXCOMMUNICATION);
        personalBonus = Setting.getInstance().getProperty(PERSONAL_BONUS);
        endGameMap = Setting.getInstance().getProperty(END_GAME_MAP);
        rankingPoints = Setting.getInstance().getProperty(RANKING_POINTS);
        heroCards = Setting.getInstance().getProperty(HERO_CARDS);
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

    public void writeEndGameMap() throws IOException{
        FileWriter fw = new FileWriter("src/main/resources/model/end_game_map.txt");
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        Map<String, List<Integer>> endGameMap = new HashMap<>();

        List<Integer> extraPoints = new LinkedList<>();
        List<Integer> extraPoints1 = new LinkedList<>();
        Integer[] array = {1,2,5,10,15,21};
        Integer[] array1 = {0,0,3,4,5,6};
        extraPoints.addAll(Arrays.asList(array));
        extraPoints1.addAll(Arrays.asList(array1));

        endGameMap.put("BLUE",extraPoints);
        endGameMap.put("GREEN",extraPoints1);

        gson.toJson(endGameMap, fw);

        fw.close();
    }

    public void writeRankingPoints() throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/model/ranking_points.txt");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<Resource,List<Integer>> rankingPoints = new HashMap<>();
        Integer[] extraPoints = {5,2};
        List<Integer> rankingBonus = new LinkedList<>();
        rankingBonus.addAll(Arrays.asList(extraPoints));
        rankingPoints.put(Resource.MILITARYPOINT,rankingBonus);

        gson.toJson(rankingPoints,fw);
        fw.close();
    }

    public Map<Resource,List<Integer>> loadRankingPoints(){

        InputStreamReader sr = new InputStreamReader(this.getClass().getResourceAsStream(rankingPoints));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type mapType = new TypeToken<Map<Resource,List<Integer>>>(){}.getType();

        Map<Resource,List<Integer>> rankingPoints = gson.fromJson(sr,mapType);
        return rankingPoints;

    }




    public Map<String,List<Integer>> loadEndGameMap(){

        InputStreamReader sr = new InputStreamReader(this.getClass().getResourceAsStream(endGameMap));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type mapType = new TypeToken<Map<String,List<Integer>>>(){}.getType();

        Map<String,List<Integer>> endGameMap = gson.fromJson(sr,mapType);
        return endGameMap;
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


    public DevelopmentCard[] loadCards() throws IOException {

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class).registerSubtype(DonateProdHarv.class).registerSubtype(ProdHarvMalusEffect.class).registerSubtype(EffectOnResources.class);

        RuntimeTypeAdapterFactory typeAdapterFactory3 = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class, "type").registerSubtype(EffectOnResources.class);
        Gson gson2 = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory3).create();

        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(cardsRootPath));
        DevelopmentCard [] cards = gson2.fromJson(fr , DevelopmentCard [].class);
        fr.close();
        return cards;
        //    System.out.println(cards[3].toString());
    }

    public Board loadBoard(int nPlayers) {
        try {
            String boardPath = "";
            if (nPlayers <= 2)
                boardPath = BOARD_PATH_SMALL;
            else if (nPlayers <= 4)
                boardPath = BOARD_PATH_MEDIUM;
            else
                boardPath = BOARD_PATH_BIG;

            InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(Setting.getInstance().getProperty(boardPath)));

            RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(ActionPlace.class, "type").registerSubtype(ActionPlace.class).registerSubtype(ActionPlaceFixed.class);
            RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                    .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class).registerSubtype(DonateProdHarv.class).registerSubtype(ProdHarvMalusEffect.class).registerSubtype(EffectOnResources.class);
            RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class, "type").registerSubtype(EffectOnResources.class);

            Gson gson2 = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory1).registerTypeAdapterFactory(typeAdapterFactory).create();
            Board board = gson2.fromJson(fr, Board.class);

            fr.close();
            return board;
        }
        catch (IOException e){}
        return null;
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


    public List<PersonalBonusTile> loadPersonalBonus()
    {
        InputStreamReader sr = new InputStreamReader(this.getClass().getResourceAsStream(personalBonus));
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class).registerSubtype(DonateProdHarv.class).registerSubtype(ProdHarvMalusEffect.class).registerSubtype(EffectOnResources.class);
        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class).registerSubtype(EffectOnResources.class);
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory).create();
        PersonalBonusTile [] bonusTiles = gson.fromJson(sr, PersonalBonusTile[].class);
        return Arrays.asList(bonusTiles);
    }


    public ExcomunicationCard [] loadExcommunication()
    {
        try {
            RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                    .registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                    .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class).registerSubtype(ProdHarvMalusEffect.class);
            RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class, "type").registerSubtype(EffectOnResources.class);

            Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();
            InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream(excommunication));


            ExcomunicationCard[] excomunicationCard = gson.fromJson(inputStreamReader, ExcomunicationCard[].class);
            inputStreamReader.close();
            return excomunicationCard;
        }
        catch (IOException e)
        {}
        return null;
    }

    public HeroCard [] loadHeroCards()
    {
        try {
            RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class)
                    .registerSubtype(EffectOnAction.class)
                    .registerSubtype(EffectOnConditions.class)
                    .registerSubtype(EffectOnEnd.class)
                    .registerSubtype(EffectOnNewCards.class)
                    .registerSubtype(EffectOnParchment.class)
                    .registerSubtype(DonateBonusMalusEffect.class)
                    .registerSubtype(DonateProdHarv.class)
                    .registerSubtype(ProdHarvMalusEffect.class)
                    ;
            RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class, "type").registerSubtype(EffectOnResources.class);

            Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory).create();
            InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream(heroCards));


            HeroCard[] heroCards = gson.fromJson(inputStreamReader, HeroCard[].class);
            inputStreamReader.close();
            return heroCards;
        }
        catch (IOException e)
        {}
        return null;
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

        /**adesso genero la zona produzione*/

        ArrayList<ProdHarvZone> prodZones = new ArrayList<>(); /** sarà la mia produzione */

        ActionType actionType = ActionType.BOARD_ACTION_ON_PROD;
        ArrayList<ActionPlace> prodActionPlaces = new ArrayList<>(); /** la lista con cui si riempie la produzione */
        prodActionPlaces.add(new ActionPlaceFixed(new ArrayList<Effect>(), 1, 1));/** slot della produzione*/
        //TODO capire se il malus sulla produzione va bene scritto cosi

        /** lista dei familiari su cui il malus impatta */
        LinkedList<String> familyMemberColours = new LinkedList<>();
        familyMemberColours.add("");
        familyMemberColours.add("RED");
        familyMemberColours.add("WHITE");
        familyMemberColours.add("BLACK");
        BonusMalusOnAction bonusMalusOnAction = new BonusMalusOnAction("", familyMemberColours, actionType, false, -3);

        BonusMalusSet malusProduction = new BonusMalusSet(); /** malus sullo spazio */

        /** aggiungiamo il malus a una lista */
        List<BonusMalusOnAction> requestedList = new ArrayList<>();
        requestedList.add(bonusMalusOnAction);

        /**creo il malus */
        malusProduction.addActionBonusMalus(requestedList);
        DonateBonusMalusEffect malusOnProduction = new DonateBonusMalusEffect(malusProduction);

        List<Effect> effectsBigSpaceProd = new ArrayList<>();
        effectsBigSpaceProd.add(malusOnProduction);
        prodActionPlaces.add(new ActionPlace(effectsBigSpaceProd, 1));
        ProdHarvZone prodZone = new ProdHarvZone(prodActionPlaces, actionType, 1);
        prodZones.add(prodZone);


        /** adesso genero la zona raccolto */

        ArrayList<ProdHarvZone> harvZones = new ArrayList<>(); /** sarà il mio raccolto*/

        ActionType actionType1 = ActionType.BOARD_ACTION_ON_HARV;
        ArrayList<ActionPlace> harvActionPlaces = new ArrayList<>();
        harvActionPlaces.add(new ActionPlaceFixed(new ArrayList<Effect>(), 1, 1));

        //TODO capire se il malus sul raccolto va bene scritto cosi
        BonusMalusOnAction bonusMalusOnAction1 = new BonusMalusOnAction("", familyMemberColours, actionType1, false, -3);
        BonusMalusSet malusHarvest = new BonusMalusSet();
        List<BonusMalusOnAction> requestedList1 = new ArrayList<>();
        requestedList1.add(bonusMalusOnAction1);
        malusHarvest.addActionBonusMalus(requestedList1);
        DonateBonusMalusEffect malusOnHarvest = new DonateBonusMalusEffect(malusHarvest);
        List<Effect> effectsBigSpaceHarv = new ArrayList<>();
        effectsBigSpaceHarv.add(malusOnHarvest);
        harvActionPlaces.add(new ActionPlace(effectsBigSpaceHarv, 1));
        ProdHarvZone harvZone = new ProdHarvZone(harvActionPlaces, ActionType.BOARD_ACTION_ON_HARV, 1);
        harvZones.add(harvZone);


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
        MarketAndCouncil market = new MarketAndCouncil(placesMarket, ActionType.BOARD_ACTION_ON_MARKET);
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
        MarketAndCouncil counsil = new MarketAndCouncil(placesCounsil, ActionType.COUNCIL_ACTION);
        counsils.add(counsil);
        //posso finalmente creare una board per poi scriverla

        Board board = new Board(towers, markets, prodZones,harvZones, counsils);

        FileWriter fw = new FileWriter("src/main/resources/model/finalBoard.txt");

        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(ActionPlace.class, "type").registerSubtype(ActionPlace.class).registerSubtype(ActionPlaceFixed.class);
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class).registerSubtype(DonateProdHarv.class).registerSubtype(ProdHarvMalusEffect.class).registerSubtype(EffectOnResources.class);
        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class, "type").registerSubtype(EffectOnResources.class);

        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory1).registerTypeAdapterFactory(typeAdapterFactory).create();
        gson2.toJson(board, fw);
        fw.close();

    }



}