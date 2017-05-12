package it.polimi.ingsw.GC_06;

/**
 * Created by massimo on 12/05/17.
 */
public class Player
{
    //TODO FIX IT!!! (GENERIC, NOT 4)
    private FamilyMember [] familyMembers=new FamilyMember[4];
    private PlayerId id;
    private ResourceSet resources;


    public Player(PlayerId id, ResourceSet resources, FamilyMember [] familyMembers)
    {
        super ();

        this.id=id;
        this.resources=resources;

        this.familyMembers = familyMembers;

        for (FamilyMember familyMember: this.familyMembers)
            familyMember.setPlayerColor(this.id);

    }

    public ResourceSet getResources()
    {
        return resources;
    }


}
