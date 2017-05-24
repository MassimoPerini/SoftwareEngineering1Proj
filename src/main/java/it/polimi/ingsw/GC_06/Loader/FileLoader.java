package it.polimi.ingsw.GC_06.Loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.Board.*;
import it.polimi.ingsw.GC_06.Card.CardType;
import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.Card.Requirement;
import it.polimi.ingsw.GC_06.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.Resource.Resource;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

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

        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(cardsRootPath));
        DevelopmentCard [] cards = gson.fromJson(fr , DevelopmentCard [].class);
        fr.close();
        return cards;
    //    System.out.println(cards[3].toString());
    }

    public Board loadBoard() throws IOException {
        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(boardRootPath));

        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(ActionPlace.class, "type").registerSubtype(ActionPlace.class).registerSubtype(ActionPlaceFixed.class);
        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory).create();
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

            Tower tower = new Tower(towerFloors, 1, 1, malus);
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

        ArrayList<Market> markets = new ArrayList<>();
        ArrayList <ActionPlace> marketActionPlaces = new ArrayList<>();
        for (int i=0;i<5;i++)
        {
            marketActionPlaces.add(new ActionPlaceFixed(new ArrayList<>(), 1, 1));    //TODO EFFECT???
        }
        markets.add(new Market(marketActionPlaces));

        ArrayList<Council> councils = new ArrayList<>();
        ArrayList<ActionPlace> actionPlaces = new ArrayList<ActionPlace>();
        actionPlaces.add(new ActionPlace(new ArrayList<Effect>(), 1));
        councils.add(new Council(actionPlaces));

        Board b = new Board(towers, markets, prodHarvZones, councils);

        FileWriter fw = new FileWriter("src/main/resources/model/board.txt");

        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(ActionPlace.class, "type").registerSubtype(ActionPlace.class).registerSubtype(ActionPlaceFixed.class);
        Gson gson2=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory).create();
        gson2.toJson(b, fw);
        fw.close();
    }

    public void writeCards() throws IOException {

        DevelopmentCard card1 = new DevelopmentCard("Villaggio minerario", 2, new ArrayList<Effect>(), new ArrayList<Requirement>(), "green");

        ResourceSet res1 = new ResourceSet();
        res1.addResource(Resource.MONEY, 6);

        ArrayList<Requirement> r = new ArrayList<>();
        r.add(new Requirement(new ResourceSet(), res1));
        DevelopmentCard card2 = new DevelopmentCard("Nobile",3, new ArrayList<Effect>(), r, "blue");

        ResourceSet res2 = new ResourceSet();
        res2.addResource(Resource.SERVANT, 1);
        res2.addResource(Resource.WOOD, 2);
        res2.addResource(Resource.STONE, 2);
        r = new ArrayList<>();
        r.add(new Requirement(new ResourceSet(), res2));
        DevelopmentCard card3 = new DevelopmentCard("Accademia militare", 3, new ArrayList<Effect>(), r, "yellow");


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
        DevelopmentCard card4 = new DevelopmentCard( "Sostegno al papa", 3, new ArrayList<Effect>(), r, "purple");

        DevelopmentCard [] cards = {card1, card2, card3, card4};

        //TODO FIX HERE (relative path!)

        FileWriter fw = new FileWriter("src/main/resources/model/cards.txt");
        this.gson.toJson(cards, fw);
        fw.close();
    }


}
