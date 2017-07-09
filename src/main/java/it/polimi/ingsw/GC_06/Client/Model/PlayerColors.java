package it.polimi.ingsw.GC_06.Client.Model;

import java.util.*;

/**
 * Created by massimo on 07/07/17.
 * this class handles the association of players and colours
 */
public class PlayerColors {

    private Map<String, String> colors;
    private List<String> colorList;
    private Random random;

    /**
     * initialize and populate the array of colours
     */
    public PlayerColors()
    {
        super();
        colors = new HashMap<>();
        colorList = new LinkedList<>();
        String [] colorsInit = {"#39add1", // light blue
                "#3079ab", // dark blue
                "#c25975", // mauve
                "#e15258", // red
                "#f9845b", // orange
                "#838cc7", // lavender
                "#7d669e", // purple
                "#53bbb4", // aqua
                "#51b46d", // green
                "#e0ab18", // mustard
                "#637a91", // dark gray
                "#f092b0", // pink
                "#b7c0c7"  // light gray}
        };
        colorList.addAll(Arrays.asList(colorsInit));
        random = new Random();
    }



    public String getPlayerColor(String player)
    {
        return colors.get(player);
    }

    /**
     * associates a random colour to a player
     * @param player player to be given a colour
     */
    public void addPlayer(String player)
    {
        int rnd = random.nextInt(colorList.size());
        colors.put(player, colorList.get(rnd));
        colorList.remove(rnd);
    }

}
