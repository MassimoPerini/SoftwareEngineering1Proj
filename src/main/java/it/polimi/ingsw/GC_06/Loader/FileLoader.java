package it.polimi.ingsw.GC_06.Loader;

import com.google.gson.Gson;
import it.polimi.ingsw.GC_06.Card.CardType;
import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.Requirements;
import it.polimi.ingsw.GC_06.Resource.Resource;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.io.*;

/**
 * Created by massimo on 17/05/17.
 */

public class FileLoader {

    private static FileLoader instance;

    private static final String KEY_PATH = "cards_path";

    private String rootPath;
    private Gson gson;


    private FileLoader ()
    {
        super();
        this.gson = new Gson();
        rootPath = Setting.getInstance().getProperty(KEY_PATH);
    }

    public static FileLoader getFileLoader ()
    {
        if (instance == null)
            instance = new FileLoader();
        return instance;
    }

    public void loadResources() throws IOException {

        InputStreamReader fr = new InputStreamReader(this.getClass().getResourceAsStream(rootPath));
        DevelopmentCard [] cards = gson.fromJson(fr , DevelopmentCard [].class);
        fr.close();
        System.out.println(cards[3].toString());
    }

    public void writeResources() throws IOException {

        DevelopmentCard card1 = new DevelopmentCard(CardType.GREEN, "Villaggio minerario", 2);

        ResourceSet res1 = new ResourceSet();
        res1.addResource(Resource.MONEY, 6);

        DevelopmentCard card2 = new DevelopmentCard(CardType.BLUE, "Nobile", 3);
        Requirements r = new Requirements(new ResourceSet(), res1);
        card2.addRequirements(r);

        DevelopmentCard card3 = new DevelopmentCard(CardType.YELLOW, "Accademia militare", 3);
        ResourceSet res2 = new ResourceSet();
        res2.addResource(Resource.SERVANT, 1);
        res2.addResource(Resource.WOOD, 2);
        res2.addResource(Resource.STONE, 2);
        r = new Requirements(new ResourceSet(), res2);
        card3.addRequirements(r);


        DevelopmentCard card4 = new DevelopmentCard(CardType.PURPLE, "Sostegno al papa", 3);
        res2 = new ResourceSet();
        res2.addResource(Resource.WOOD, 3);
        res2.addResource(Resource.MONEY, 4);
        res2.addResource(Resource.STONE, 3);
        r = new Requirements(new ResourceSet(), res2);
        card4.addRequirements(r);

        res2 = new ResourceSet();
        res2.addResource(Resource.MILITARYPOINT, 5);
        res1 = new ResourceSet();
        res1.addResource(Resource.MILITARYPOINT, 10);
        r = new Requirements(res1, res2);
        card4.addRequirements(r);

        DevelopmentCard [] cards = {card1, card2, card3, card4};

        //TODO FIX HERE (relative path!)

        FileWriter fw = new FileWriter("src/main/resources/cards/cards.txt");
        this.gson.toJson(cards, fw);
        fw.close();
    }


}
