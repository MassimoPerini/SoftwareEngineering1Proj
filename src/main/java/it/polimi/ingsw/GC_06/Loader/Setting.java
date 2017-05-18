package it.polimi.ingsw.GC_06.Loader;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by massimo on 18/05/17.
 */
public class Setting {

    //check http://stackoverflow.com/questions/17977539/static-resourcebundle

    private final static String PATH = "settings/bundle";
    private static Setting instance;
    private ResourceBundle rb;

    private Setting()
    {
        super();
        rb = ResourceBundle.getBundle(PATH);
    }

    public static Setting getInstance()
    {
        if (instance==null)
            instance = new Setting();
        return instance;
    }

    public String getProperty(String key)
    {
        return rb.getString(key);

    }

}
