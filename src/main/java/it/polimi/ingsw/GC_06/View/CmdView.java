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
    public int getInt(int start, int end) {
        boolean ok = true;
        int res=0;

        do {
            ok = true;
            try {
                res = input.nextInt();
                if (res<start || res > end)
                {
                    this.addLocalizedText("error_input");
                    this.print();
                }
            } catch (Exception e) {
                this.addLocalizedText("error_input");
                this.print();
                ok = false;
            }
        }while(!ok);

        return res;

    }

    @Override
    public String getString() {
        print();
        return input.next();
    }
}
