package it.polimi.ingsw.GC_06.Client.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 16/05/17.
 * this class handles the functioning logic of the view, managing client's input
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

    /**
     *
     * @param string text to be shown
     */
    @Override
    public synchronized void addLocalizedText(String string) {
        text = text.concat(string);
    }

    /**
     * clears all the text
     */
    @Override
    public synchronized void flush() {
        text="";
    }

    /**
     *
     * @param string text to be shown
     */
    @Override
    public synchronized void addText(String string) {
        text = text.concat(string);
    }

    /**
     * shows all text memorized
     */
    @Override
    public synchronized void print() {
        System.out.println(text);
        flush();
    }

    @Override
    public int getInt(int start, int end) throws InterruptedException {
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

    /**
     *
     * @param min
     * @param max
     * @return returns client input as a list of Integers
     * @throws InterruptedException
     */
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

    /**
     *
     * @param start
     * @return returns the client input as an int
     * @throws InterruptedException
     */
    @Override
    public int getInt(int start) throws InterruptedException {
        this.print();
        boolean ok = true;
        int res = 0;
        do {
            ok=true;
            try {
                while (!input.ready()) {
                    Thread.sleep(200);
                }
                if (Thread.currentThread().isInterrupted()) {
                    return -1;
                }
                res = Integer.parseInt(input.readLine());
            }
            catch (NumberFormatException | IOException e)
            {
                this.addLocalizedText("error_input");
                this.print();
                ok = false;
            }
        }
        while (!ok);
        return res;
    }

    /**
     *
     * @return return client input as a string
     * @throws InterruptedException
     */
    @Override
    public String getString() throws InterruptedException {

        print();
        try {
            while (!input.ready()) {
                Thread.sleep(200);
            }
            return input.readLine();
        }
        catch (IOException e) {}
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
