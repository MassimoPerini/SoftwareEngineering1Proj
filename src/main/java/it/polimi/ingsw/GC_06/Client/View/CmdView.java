package it.polimi.ingsw.GC_06.Client.View;

import java.util.Scanner;

/**
 * Created by massimo on 16/05/17.
 */
public class CmdView implements CommandView {

    private Scanner input;
    private String text;


    public CmdView()
    {
        super();
        text = "";
        input = new Scanner (System.in);
    }

    @Override
    public synchronized void addLocalizedText(String string) {
        text = text.concat(string);
    }

    @Override
    public synchronized void flush() {
        text="";
    }

    @Override
    public synchronized void addText(String string) {
        text = text.concat(string);
    }

    @Override
    public synchronized void print() {
        System.out.println(text);
        flush();
    }

    @Override
    public int getInt(int start, int end) {
        this.print();
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
                    ok = false;
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

    @Override
    public void sleep() {

    }

    @Override
    public void unload() {

    }

    @Override
    public void run() {

        System.out.print(text);
        this.flush();
    }
}
