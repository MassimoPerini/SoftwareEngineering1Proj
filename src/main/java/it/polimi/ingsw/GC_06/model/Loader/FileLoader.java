package it.polimi.ingsw.GC_06.model.Loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.model.Action.Actions.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.Board.*;
import it.polimi.ingsw.GC_06.model.BonusMalus.*;
import it.polimi.ingsw.GC_06.model.Card.*;
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
 * Remove the write functions before submit!
 */

public class FileLoader {

    private static volatile FileLoader instance;

    private static final String CARDS_PATH = "cards_path";
    private static final String PARCHMENTS_PATH = "parchments_path";
    private static final String BOARD_PATH = "board_path";
    private static final String DEFAULT_RES = "default_resource_path";
    private static final String DICES = "dices_path";
    private static final String PLAYER_BOARD = "player_board_path";
    private static final String CHURCH = "church";
    private static final String EXCOMMUNICATION = "excomm";
    private static final String PERSONAL_BONUS = "personal_bonus";
    private static final String END_GAME_MAP = "end_game_map";
    // private static final String GAME_MAP = "end_game_map";


    private final String cardsRootPath;
    private final String parchmentsPath;
    private final String boardRootPath;
    private final String defaultResourceRootPath;
    private final String dicePath;
    // private String endGameMap;
    private final String playerBoardPath;
    private final String church;
    private final String excommunication;
    private final String personalBonus;
    private final String endGameMap;
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
        excommunication = Setting.getInstance().getProperty(EXCOMMUNICATION);
        personalBonus = Setting.getInstance().getProperty(PERSONAL_BONUS);
        endGameMap = Setting.getInstance().getProperty(END_GAME_MAP);
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
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class).registerSubtype(DonateProdHarv.class);


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
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class).registerSubtype(DonateProdHarv.class);

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


    public List<PersonalBonusTile> loadPersonalBonus()
    {
        InputStreamReader sr = new InputStreamReader(this.getClass().getResourceAsStream(personalBonus));
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();
        PersonalBonusTile [] bonusTiles = gson.fromJson(sr, PersonalBonusTile[].class);
        return Arrays.asList(bonusTiles);
    }


    public ExcomunicationCard [] loadExcommunication()
    {
        try {
            RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                    .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);
            RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class, "type").registerSubtype(EffectOnResources.class);

            Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory).create();
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
            RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnAction.class).registerSubtype(EffectOnConditions.class).registerSubtype(EffectOnEnd.class).registerSubtype(EffectOnNewCards.class)
                    .registerSubtype(EffectOnParchment.class).registerSubtype(DonateBonusMalusEffect.class);
            RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(ProdHarvMalusEffect.class, "type").registerSubtype(EffectOnResources.class);

            Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory).create();
            InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("src/main/resources/model/heroCards.txt"));


            HeroCard[] heroCards = gson.fromJson(inputStreamReader, HeroCard[].class);
            inputStreamReader.close();
            return heroCards;
        }
        catch (IOException e)
        {}
        return null;
    }

    /*public Map<String, String> loadMapNamesDescriptions() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("src/main/resources/model/mapNamesHeroes.txt"));

            Map<String, String> mapNamesDescriptions = gson.toJson(inputStreamReader, Map<String, String>.class);
            inputStreamReader.close();
            return mapNamesDescriptions;
        } catch (IOException e) {
        }
        return null;
    }*/
}






