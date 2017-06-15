package it.polimi.ingsw.GC_06.Client.View;

/**
 * Created by massimo on 27/05/17.
 */
public interface CommandView {

    void addLocalizedText(String string);
    void flush();
    void addText(String string);
    void print();
    int getInt(int start, int end);
    String getString();

}
