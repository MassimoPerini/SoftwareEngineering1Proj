package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.io.IOException;
import java.lang.reflect.Array;

/**
 * Created by gabri on 20/06/2017.
 */
public class MainForWriting {
    private static FileLoader fileLoader;

    public static void main(String[] args) throws IOException {
        fileLoader = FileLoader.getFileLoader();
        /*fileLoader.writeGreenCard();
        System.out.println("factored green cards");*/

        /*ResourceSet parchment1 = new ResourceSet();
        parchment1.variateResource(Resource.STONE, 1);
        parchment1.variateResource(Resource.WOOD, 1);
        ResourceSet parchment2 = new ResourceSet();
        parchment2.variateResource(Resource.SERVANT, 2);
        ResourceSet parchment3 = new ResourceSet();
        parchment3.variateResource(Resource.MONEY, 2);
        ResourceSet parchment4 = new ResourceSet();
        parchment4.variateResource(Resource.MILITARYPOINT, 2);
        ResourceSet parchment5 = new ResourceSet();
        parchment5.variateResource(Resource.FAITHPOINT, 1);
        ResourceSet [] parchments = {parchment1, parchment2, parchment3, parchment4, parchment5};
        fileLoader.writeResourceSet(parchments);
        System.out.println("factored parchments");*/

        /*fileLoader.writeYellowCard();
        System.out.println("factored yellow cards");*/

        fileLoader.writePurpleCard();
        System.out.println("factored Purple cards");

        fileLoader.writeBlueCard();
        System.out.println("factored blue cards");
    }
}
