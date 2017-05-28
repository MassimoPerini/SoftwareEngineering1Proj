package it.polimi.ingsw.GC_06.View;

/**
 * Created by massimo on 27/05/17.
 */
public interface CommandView {

    void addLocalizedText(String string);
    void setLocalizedText(String string);
    void flush();
    void addText(String string);
    void print();
    String getString();

}
