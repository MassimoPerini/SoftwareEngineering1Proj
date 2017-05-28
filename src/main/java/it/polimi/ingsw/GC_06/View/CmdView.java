package it.polimi.ingsw.GC_06.View;

import java.util.Scanner;

/**
 * Created by massimo on 16/05/17.
 */
public class CmdView implements CommandView {

    private Scanner input;
    String text;


    public CmdView()
    {
        super();
        text = "";
        input = new Scanner (System.in);
    }

    @Override
    public void addLocalizedText(String string) {
        text = text.concat(string);
    }

    @Override
    public void setLocalizedText(String string) {
        text = string;
    }

    @Override
    public void flush() {
        text="";
    }

    @Override
    public void addText(String string) {
        text = text.concat(string);
    }

    @Override
    public void print() {
        System.out.println(text);
        flush();
    }

    @Override
    public String getString() {
        print();
        return input.next();
    }
}
