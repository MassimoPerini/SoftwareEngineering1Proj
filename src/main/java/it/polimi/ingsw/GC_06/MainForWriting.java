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
        fileLoader.writeGreenCard();
        System.out.println("factored green cards");
    }
}
