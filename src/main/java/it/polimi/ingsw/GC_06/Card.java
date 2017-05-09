package it.polimi.ingsw.GC_06;

/**
 * Created by massimo on 09/05/17.
 */


class GetRequirements{
    private int wood, stone, money;

    @Override
    public String toString() {
        return "GetRequirements{" +
                "wood=" + wood +
                ", stone=" + stone +
                ", money=" + money +
                '}';
    }
}
class PermanentRequirements {
    private int value, money;

    @Override
    public String toString() {
        return "PermanentRequirements{" +
                "value=" + value +
                ", money=" + money +
                '}';
    }
}

class Requirements {
    PermanentRequirements permanent;
    GetRequirements get;

    @Override
    public String toString() {
        return "Requirements: requisiti istantanei:" +get.toString() +"Requisiti per azioni permanenti:"+permanent.toString()+
                '}';
    }
}

class InstantObj {
    int money, victory, military;

    @Override
    public String toString() {
        return "InstantObj{" +
                "money=" + money +
                ", victory=" + victory +
                ", military=" + military +
                '}';
    }
}
class PermanentObj {
    int money, wood, stone, victory;

    @Override
    public String toString() {
        return "PermanentObj{" +
                "money=" + money +
                ", wood=" + wood +
                ", stone=" + stone +
                ", victory=" + victory +
                '}';
    }
}

public class Card {
    private String name, type;
    private int era;
    private Requirements requirements;
    private InstantObj instant;
    private PermanentObj permanent;

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", era=" + era + ",\n Requirements: "+
                requirements.toString() + "\n Azioni istantantee:"+
                instant.toString() +"\n Azioni permanenti:"+
                permanent.toString()+
                '}';
    }
}