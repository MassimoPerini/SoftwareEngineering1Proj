package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.HeroCardUploadMessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageAddCard;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageUpdateResource;
import it.polimi.ingsw.GC_06.model.Action.EndGame.PersonalStatistics;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.PersonalBonusTile;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.*;

/**
 * Created by giuseppe on 5/19/17.
 * @author giuseppe
 * This class is a Player of the game
 */
public class Player extends Observable {

    private final PlayerBoard playerBoard;
    private final FamilyMember[] familyMembers;
    private final ResourceSet resourceSet;
    private final String PLAYER_ID;
    private List<Effect> bonusMalus;
    private ResourceSet addAtTheEnd;
    private BonusMalusSet bonusMalusSet;
    private boolean connected;
    private final List<PersonalBonusTile> personalBonus;
    private List<HeroCard> heroCard;
    private PersonalStatistics personalStatistics;

    //TODO sistemare la questione dei malus e bonus sul player

    public Player(String PLAYER_ID,FamilyMember[] familyMembers) {

        this.bonusMalus = new ArrayList<>();
        this.addAtTheEnd = new ResourceSet();
        this.PLAYER_ID = PLAYER_ID;
        this.resourceSet = new ResourceSet();
        this.playerBoard = FileLoader.getFileLoader().loadPlayerBoard();
        this.familyMembers = familyMembers;
        this.bonusMalusSet = new BonusMalusSet();
        this.personalBonus = new LinkedList<>();
        this.connected = true;
        this.heroCard = new LinkedList<>();
    }

    public Player (Player p)
    {
        this.resourceSet = new ResourceSet(p.getResourceSet());
        this.PLAYER_ID = p.getPLAYER_ID();
        this.playerBoard = p.playerBoard;
        this.familyMembers = p.familyMembers;
        this.addAtTheEnd = new ResourceSet(p.addAtTheEnd);
        this.bonusMalusSet = new BonusMalusSet();
        this.personalBonus = p.getPersonalBonus();  //FIX IT
        //TODO complete here...
    }

    /**
     *
     * @return returns the personal playerBoard of the player
     */
    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    /**
     *
     * @return returns the complete resourceset of the player
     */
    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    public String getPLAYER_ID() {
        return PLAYER_ID;
    }

    /**
     *
     * @return returns the complete bonusMalusSet associated to the player
     */
    public BonusMalusSet getBonusMalusSet() {
        return bonusMalusSet;
    }


    /**
     *
     * @return returns all the familyMembers associated to the player
     */
    public FamilyMember[] getFamilyMembers() {
        return familyMembers;
    }

    /**
     * this method variates the resourceset of the player based on the requirements, so it only variates based on the cost
     * @param requirement requirements to be evaluated
     */
    public void variateResource(Requirement requirement)
    {
        this.variateResource(requirement.getCost());
    }

    /**
     *
     * @param resourceSet resourceset to add to the player
     * @throws IllegalArgumentException
     */
    public void variateResource(ResourceSet resourceSet) throws IllegalArgumentException
    {
        if (this.getResourceSet().isIncluded(resourceSet)) {
            this.getResourceSet().variateResource(resourceSet);

            MessageServer messageServer = new MessageUpdateResource(PLAYER_ID, this.resourceSet);
            setChanged();
            notifyObservers(messageServer);
        }
        else {
            throw new IllegalArgumentException();
        }
    }


    public boolean isAllowedVariate(List<Requirement> requirements) {
        boolean result = true;
        for (Requirement requirement:requirements)
        {
            result = false;
            if (requirement.isSatisfied(resourceSet))
                return true;
        }
        return result;
    }

    public boolean isAllowedVariate(ResourceSet requirement) {
        return this.resourceSet.isIncluded(requirement);
    }

    /**
     * this method adds a development card to the ones owned by the player
     * @param developmentCard card to be added to the player
     */
        public void addCard(DevelopmentCard developmentCard)
    {
        playerBoard.addCard(developmentCard, resourceSet);

        MessageServer messageServer = new MessageAddCard(developmentCard, PLAYER_ID);
        setChanged();
        notifyObservers(messageServer);
    }

    public boolean canAdd (DevelopmentCard cardId)
    {
        return playerBoard.canAdd(cardId, resourceSet);
    }

    public void variateAddAtTheEnd(ResourceSet resourceSet) {
        this.addAtTheEnd.variateResource(resourceSet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;
        return PLAYER_ID.equals(player.PLAYER_ID);
    }


    public void setHeroCard(List<HeroCard> heroCard) {

        this.heroCard = heroCard;
        List<String> heroCardsName = new LinkedList<>();
        for (HeroCard card : this.heroCard) {
            heroCardsName.add(card.getPath());
        }
        MessageServer messageServer = new HeroCardUploadMessageServer(this.PLAYER_ID,heroCardsName);
        setChanged();
        notifyObservers(messageServer);
    }

    public void  removeHeroCard(int value){
        this.heroCard.remove(value);
        List<String> heroCardsName = new LinkedList<>();

        for (HeroCard card : this.heroCard) {
            heroCardsName.add(card.getPath());
        }
        MessageServer messageServer = new HeroCardUploadMessageServer(this.PLAYER_ID,heroCardsName);
        setChanged();
        notifyObservers(messageServer);

    }


    /**
     *
     * @return returns true if the player is connected to the game
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     *
     * @param disconnected sets the state of the player's connection
     */
    public void setConnected(boolean disconnected) {
        this.connected = disconnected;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public void setBonusMalusSet(BonusMalusSet bonusMalusSet) {
        this.bonusMalusSet = bonusMalusSet;
    }

    public List<PersonalBonusTile> getPersonalBonus() {
        return Collections.unmodifiableList(personalBonus);
    }

    public void addPersonalBonus(PersonalBonusTile personalBonusTile) {
        this.personalBonus.add(personalBonusTile);
    }

    public List<HeroCard> getHeroCard() {
        return heroCard;
    }

    public void resetHeroCard(){
        for (HeroCard heroCard : this.heroCard) {
            heroCard.setCardStatus(true);
        }
    }

    public ResourceSet getAddAtTheEnd() {
        return addAtTheEnd;
    }

    public PersonalStatistics getPersonalStatistics() {
        return personalStatistics;
    }

    public void resetFamilyMember(){
        for(int i = 0; i< familyMembers.length; i++){
            if(familyMembers[i].isAlreadyUsed()){
                familyMembers[i].setAlreadyUsed(false);
            }
        }
    }

    /**
     *
     * @return returns the statistics generated for the current player
     */
    public PersonalStatistics createPersonalStatistics(){

        personalStatistics = new PersonalStatistics(this.PLAYER_ID,this.getResourceSet().getResourceAmount(Resource.FAITHPOINT),
                this.getResourceSet().getResourceAmount(Resource.MILITARYPOINT),this.getResourceSet().getResourceAmount(Resource.VICTORYPOINT),
                this.getResourceSet().totalResourceQuantity());
        return personalStatistics;
    }







}
