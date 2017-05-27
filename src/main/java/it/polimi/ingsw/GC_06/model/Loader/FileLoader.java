package it.polimi.ingsw.GC_06.model.Loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Board.*;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnAction;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by massimo on 17/05/17.
 */

public class FileLoader {

    private static FileLoader instance;

    private static final String CARDS_PATH = "cards_path";
    private static final String BOARD_PATH = "board_path";

    private String cardsRootPath;
    private String boardRootPath;
    private Gson gson;


    private FileLoader ()
    {
        super();
        this.gson = new Gson();
        cardsRootPath = Setting.getInstance().getProperty(CARDS_PATH);
        boardRootPath = Setting.getInstance().getProperty(BOARD_PATH);
    }

    public static FileLoader getFileLoader ()
    {
        if (instance == null)
            instance = new FileLoader();
        return instance;
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

        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(SmallActionPlace.class, "type").registerSubtype(SmallActionPlace.class).registerSubtype(ActionPlaceFixed.class);
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
            malus.addResource(Resource.MONEY, 3);
            for (int i = 0; i < 4; i++) {
                ResourceSet resourceSet = new ResourceSet();
                if (i>2)
                    resourceSet.addResource(resources[j], i-2);

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
            ArrayList<SmallActionPlace> prodHarvSmallActionPlaces = new ArrayList<>();
            prodHarvSmallActionPlaces.add(new ActionPlaceFixed(new ArrayList<Effect>(), 1, 1));
            prodHarvSmallActionPlaces.add(new SmallActionPlace(new ArrayList<Effect>(), 1));
            ProdHarvZone prodHarvZone = new ProdHarvZone(prodHarvSmallActionPlaces);
            prodHarvZones.add(prodHarvZone);
        }

        ArrayList<Market> markets = new ArrayList<>();
        ArrayList <SmallActionPlace> marketSmallActionPlaces = new ArrayList<>();
        for (int i=0;i<5;i++)
        {
            marketSmallActionPlaces.add(new ActionPlaceFixed(new ArrayList<>(), 1, 1));    //TODO EFFECT???
        }
        markets.add(new Market(marketSmallActionPlaces));

        ArrayList<Council> councils = new ArrayList<>();
        ArrayList<SmallActionPlace> smallActionPlaces = new ArrayList<SmallActionPlace>();
        smallActionPlaces.add(new SmallActionPlace(new ArrayList<Effect>(), 1));
        councils.add(new Council(smallActionPlaces));

        Board b = new Board(towers, markets, prodHarvZones, councils);

        FileWriter fw = new FileWriter("src/main/resources/model/board.txt");

        RuntimeTypeAdapterFactory typeAdapterFactory1 = RuntimeTypeAdapterFactory.of(SmallActionPlace.class, "type").registerSubtype(SmallActionPlace.class).registerSubtype(ActionPlaceFixed.class);
        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class).registerSubtype(EffectOnAction.class);

        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).registerTypeAdapterFactory(typeAdapterFactory1).create();
        gson2.toJson(b, fw);
        fw.close();
    }

    public void writeCards() throws IOException {

        DevelopmentCard card1 = new DevelopmentCard("Villaggio minerario", 2, new ArrayList<Effect>(), new ArrayList<Requirement>(),new ArrayList<Effect>(), "green");

        ResourceSet res1 = new ResourceSet();
        res1.addResource(Resource.MONEY, 6);

        ArrayList<Requirement> r = new ArrayList<>();
        r.add(new Requirement(new ResourceSet(), res1));
        DevelopmentCard card2 = new DevelopmentCard("Nobile",3, new ArrayList<Effect>(), r,new ArrayList<Effect>(), "blue");

        ResourceSet res2 = new ResourceSet();
        res2.addResource(Resource.SERVANT, 1);
        res2.addResource(Resource.WOOD, 2);
        res2.addResource(Resource.STONE, 2);
        r = new ArrayList<>();
        r.add(new Requirement(new ResourceSet(), res2));
        DevelopmentCard card3 = new DevelopmentCard("Accademia militare", 3, new ArrayList<Effect>(), r,new ArrayList<Effect>(), "yellow");


        res2 = new ResourceSet();
        res2.addResource(Resource.WOOD, 3);
        res2.addResource(Resource.MONEY, 4);
        res2.addResource(Resource.STONE, 3);
        r = new ArrayList<>();
        r.add(new Requirement(new ResourceSet(), res2));



        res2 = new ResourceSet();
        res2.addResource(Resource.MILITARYPOINT, 5);
        res1 = new ResourceSet();
        res1.addResource(Resource.MILITARYPOINT, 10);
        r.add(new Requirement(res1, res2));
        DevelopmentCard card4 = new DevelopmentCard( "Sostegno al papa", 3, new ArrayList<Effect>(), r, new ArrayList<Effect>(),"purple");

        DevelopmentCard [] cards = {card1, card2, card3, card4};

        //TODO FIX HERE (relative path!)

        RuntimeTypeAdapterFactory typeAdapterFactory2 = RuntimeTypeAdapterFactory.of(Effect.class, "type").registerSubtype(EffectOnResources.class).registerSubtype(EffectOnAction.class);
        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory2).create();

        FileWriter fw = new FileWriter("src/main/resources/model/cards.txt");
        gson2.toJson(cards, fw);
        fw.close();
    }


}
