package it.polimi.ingsw.GC_06;


import it.polimi.ingsw.GC_06.model.Loader.FileLoader;

import java.io.IOException;

/**
 * Created by gabri on 20/06/2017.
 */
public class MainForWriting {
    private static FileLoader fileLoader;


    public static void main(String[] args) throws IOException {
        fileLoader = FileLoader.getFileLoader();

        //fileLoader.writeEndGameMap();

        /*fileLoader.writePersonalBonus();


        fileLoader.writeGreenCard();
        System.out.println("factored green cards");
        
        fileLoader.writeYellowCard();
        System.out.println("factored yellow cards");

        fileLoader.writePurpleCard();
        System.out.println("factored Purple cards");

        fileLoader.writeBlueCard();
        System.out.println("factored blue cards");

        fileLoader.writeBoardWithEffects();
        System.out.println("factored fucking board");

        fileLoader.writeHeroCards();
        System.out.println("factored hero cards");

        fileLoader.writeExcomunications();
        System.out.println("factored excomunication cards");

        fileLoader.writeGreyCard();
        System.out.println("factored grey cards");

        fileLoader.writeBoardFivePlayers();
        System.out.println("factored board 5");



        fileLoader.writeMapFile();
        System.out.println("factored map for heroes names");*/

        fileLoader.writeGreenCard();
        System.out.print("done");
        //fileLoader.writeBoardWithEffects();
    }
}
