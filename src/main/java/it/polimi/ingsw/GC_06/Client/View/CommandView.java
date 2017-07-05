package it.polimi.ingsw.GC_06.Client.View;

import java.util.List;

/**
 * Created by massimo on 27/05/17.
 */
public interface CommandView extends Runnable{

    void addLocalizedText(String string);
    void flush();
    void addText(String string);
    void print();
    int getInt(int start, int end);

    List<Integer> getList(int min, int max) throws InterruptedException;

    int getInt(int start);
    String getString() throws InterruptedException;
    void sleep();
    void unload();

}
