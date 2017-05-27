package it.polimi.ingsw.GC_06.ViewController.CmdViewController;

/**
 * Created by massimo on 27/05/17.
 */
public class CmdViewUtils {

    public static boolean validateInt(String answ, int min, int max)
    {
        boolean ok=true;
        int res=0;
        try {
            res = Integer.parseInt(answ);
        }
        catch (Exception e) {
            ok=false;
        }
        if (!ok || res>max || res <min)
            ok=false;
        return ok;
    }

}
