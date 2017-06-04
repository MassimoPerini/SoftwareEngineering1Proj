package it.polimi.ingsw.GC_06.model.Loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.model.Effect.*;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Board.*;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by massimo on 17/05/17.
 * @author massimo
 * This class reads and writes JSON files, thanks to Google GSON library.
 * Remove the write functions before submit!
 */

public class FileLoader {

    private static FileLoader instance;

    private static final String CARDS_PATH = "cards_path";
    private static final String BOARD_PATH = "board_path";
    private static final String DEFAULT_RES = "default_resource_path";
    private static final String DICES = "dices_path";


    private String cardsRootPath;
    private String boardRootPath;
    private String defaultResourceRootPath;
    private String dicePath;
    private Gson gson;


    private FileLoader ()
    {
        super();
        this.gson = new Gson();
        cardsRootPath = Setting.getInstance().getProperty(CARDS_PATH);
        boardRootPath = Setting.getInstance().getProperty(BOARD_PATH);
        defaultResourceRootPath = Setting.getInstance().getProperty(DEFAULT_RES);
        dicePath = Setting.getInstance().getProperty(DICES);
    }

    public static FileLoader getFileLoader ()
    {
        if (instance == null)
            instance = new FileLoader();
        return instance;
    }

    public void writeResourceSet(ResourceSet [] resourceSet) throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/model/default_res.txt");
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

    public void writeDiceSet(DiceSet diceSet) throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/model/dices.txt");
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(diceSet, fw);
        fw.close();
    }

    public DiceSet loadDiceSet() throws IOException {
        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(dicePath));
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        DiceSet diceSet = gson.fromJson(fr , DiceSet.class);
        fr.close();
        return diceSet;
    }

    public DevelopmentCard[] loadCards() throws IOException {

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class).registerSubtype(EffectOnAction.class);
        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();

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


    public void writeBoard() throws IOException {
        int [] values = {1,3,5,7};

        ArrayList<Tower> towers = new ArrayList<>();

        //Generate towers
        //TODO una torre contiene carte solo di un solo tipo
        Resource [] resources = {Resource.WOOD, Resource.STONE, Resource.MILITARYPOINT, Resource.MONEY};

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

            Tower tower = new Tower(towerFloors, 1, 1);
            towers.add(tower);
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

    /*    List<ProdHarvEffect> prodHarvEffects = new ArrayList<>();

        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.SERVANT, 2);
        resourceSet.variateResource(Resource.STONE, 1);
        Effect immediateEffect = new EffectOnResources(resourceSet);


        DevelopmentCard card1 = new DevelopmentCard("Villaggio minerario", 2, new ArrayList<Requirement>(), new ArrayList<Effect>(),new ArrayList<ProdHarvEffect>(), "green");

        ResourceSet res1 = new ResourceSet();
        res1.variateResource(Resource.MONEY, 6);

        ArrayList<Requirement> r = new ArrayList<>();
        r.add(new Requirement(new ResourceSet(), res1));
        DevelopmentCard card2 = new DevelopmentCard("Nobile",3, new ArrayList<Requirement>(), new ArrayList<Effect>(),new ArrayList<ProdHarvEffect>(), "blue");

        ResourceSet res2 = new ResourceSet();
        res2.variateResource(Resource.SERVANT, 1);
        res2.variateResource(Resource.WOOD, 2);
        res2.variateResource(Resource.STONE, 2);
        r = new ArrayList<>();
        r.add(new Requirement(new ResourceSet(), res2));
        DevelopmentCard card3 = new DevelopmentCard("Accademia militare", 3,new ArrayList<Requirement>(), new ArrayList<Effect>(),new ArrayList<ProdHarvEffect>(), "yellow");


        res2 = new ResourceSet();
        res2.variateResource(Resource.WOOD, 3);
        res2.variateResource(Resource.MONEY, 4);
        res2.variateResource(Resource.STONE, 3);
        r = new ArrayList<>();
        r.add(new Requirement(new ResourceSet(), res2));



        res2 = new ResourceSet();
        res2.variateResource(Resource.MILITARYPOINT, 5);
        res1 = new ResourceSet();
        res1.variateResource(Resource.MILITARYPOINT, 10);
        r.add(new Requirement(res1, res2));
        DevelopmentCard card4 = new DevelopmentCard( "Sostegno al papa", 3, new ArrayList<Requirement>(), new ArrayList<Effect>(),new ArrayList<ProdHarvEffect>(),"purple");

        DevelopmentCard [] cards = {card1, card2, card3, card4};

        //TODO FIX HERE (relative path!)

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class).registerSubtype(EffectOnAction.class);
        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();

        FileWriter fw = new FileWriter("src/main/resources/model/cards.txt");
        gson2.toJson(cards, fw);
        fw.close();*/
    }


}
