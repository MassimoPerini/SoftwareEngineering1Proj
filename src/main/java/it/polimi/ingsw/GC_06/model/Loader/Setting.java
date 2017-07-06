package it.polimi.ingsw.GC_06.model.Loader;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by massimo on 18/05/17.
 * @author massimo
 * Reads the Settings bundle
 * singleton!
 */
public class Setting {

    //check http://stackoverflow.com/questions/17977539/static-resourcebundle

    private static volatile Setting instance;
    private List<ResourceBundle> rb;
 //   private ResourceBundle rb;

    private Setting()
    {
        super();
     //   rb = ResourceBundle.getBundle(PATH);
        rb = new ArrayList<>();
        System.out.println("Settings init");
    }

    public static Setting getInstance()
    {
        if (instance==null)
            instance = new Setting();
        return instance;
    }

    public void addPath(String path)
    {
        this.rb.add(ResourceBundle.getBundle(path));
    }

    public String getProperty(String key)
    {
        for (ResourceBundle resourceBundle : rb) {
            try{
                String answ = resourceBundle.getString(key);
                return answ;

            }
            catch (MissingResourceException e)
            {
                continue;
            }
        }
        return "";

    }

    public String [] getListProperty(String key)
    {
        return this.getProperty(key).split(",");
    }

}
