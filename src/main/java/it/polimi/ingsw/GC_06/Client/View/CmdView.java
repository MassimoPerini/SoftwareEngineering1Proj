package it.polimi.ingsw.GC_06.Client.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 16/05/17.
 */
public class CmdView implements CommandView {

    private BufferedReader input;
    private String text;


    public CmdView()
    {
        super();
        text = "";
        input =  new BufferedReader(new InputStreamReader(System.in));
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
        boolean ok = true;
        int res=getInt(start);
        while (res > end)
        {
            this.addLocalizedText("error_input");
            this.print();
            res = getInt(start);
        }
        return res;

    }

    @Override
    public List<Integer> getList(int min, int max) throws InterruptedException {

        boolean bool;
        List<Integer> finalChoices;

        do {
            bool = true;
            String choice = this.getString();
            String[] choices = choice.split(" ");
            finalChoices = new LinkedList<>();

            for (String s : choices) {
                try {
                    int value = Integer.parseInt(s);
                    if (value >= min && value <= max) {
                        finalChoices.add(value);
                    }
                    else{
                        bool = false;
                        this.addLocalizedText("Error Input");
                    }
                } catch (Exception e) {
                    bool = false;
                }
            }
        }while(!bool);

        return finalChoices;
    }

    @Override
    public int getInt(int start) {
        this.print();
        boolean ok = true;
        int res = 0;
        do {
            ok=true;
            try {
                while (!input.ready() && !Thread.currentThread().isInterrupted()) {
                    Thread.sleep(200);
                }
                if (Thread.currentThread().isInterrupted()) {
                    return -1;
                }
                res = Integer.parseInt(input.readLine());
            }
            catch (Exception e)
            {
                this.addLocalizedText("error_input");
                this.print();
                ok = false;
            }
        }
        while (!ok);
        return res;
    }

    @Override
    public String getString() throws InterruptedException {

        print();
        try {
            while (!input.ready()) {
                Thread.sleep(200);
            }
            return input.readLine();
        }
        catch (InterruptedException e) {
            System.out.println("View... Interrupting");
            throw new InterruptedException();
        } catch (IOException e) {}
        return null;
    }

    @Override
    public void sleep() {

    }

    @Override
    public void unload() {

    }

    @Override
    public synchronized void run() {

    }
}
