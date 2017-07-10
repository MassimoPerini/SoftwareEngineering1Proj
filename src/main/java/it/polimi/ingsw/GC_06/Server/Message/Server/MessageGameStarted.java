package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientBoardGame;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Board.MarketAndCouncil;
import it.polimi.ingsw.GC_06.model.Board.ProdHarvZone;
import it.polimi.ingsw.GC_06.model.Card.ExcomunicationCard;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.PersonalBonusTile;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.*;

/**
 * Created by massimo on 17/06/17.
 */
public class MessageGameStarted implements MessageServer {

    private Map<String, Integer> towers;
    private List<Integer> councils;
    private List<Integer> production;
    private List<Integer> harvest;
    private List<Integer> market;
    private Map<String, List<String>> players;
    private Map<String, List<ClientFamilyMember>> familyMembers;
    private Map <String,List<String>> heroCardsMap;
    private Map<Integer, List<String>> excomunicationCards;

    public MessageGameStarted(Game game)
    {
        towers = new HashMap<>();
        councils = new LinkedList<>();
        production = new LinkedList<>();
        harvest = new LinkedList<>();
        market = new LinkedList<>();
        players = new HashMap<>();
        familyMembers = new HashMap<>();
        heroCardsMap = new HashMap<>();


        for (String s : game.getGameStatus().getPlayers().keySet()) {
            List<PersonalBonusTile> personalBonusTiles = game.getGameStatus().getPlayers().get(s).getPersonalBonus();
            List<String> clientPersonalBonus = new LinkedList<>();
            for (PersonalBonusTile personalBonusTile : personalBonusTiles) {
                clientPersonalBonus.add(personalBonusTile.getId());
            }
            players.put(s, clientPersonalBonus);

            int sizeFamMemb = game.getGameStatus().getPlayers().get(s).getFamilyMembers().length;
            List<ClientFamilyMember> clientFamilyMembers = new LinkedList<>();
            for (int i=0;i<sizeFamMemb;i++)
            {
                FamilyMember familyMember = game.getGameStatus().getPlayers().get(s).getFamilyMembers()[i];
                ClientFamilyMember clientFamilyMember = new ClientFamilyMember(s, familyMember.getValue(), familyMember.getDiceColor());
                clientFamilyMembers.add(clientFamilyMember);
            }
            familyMembers.put(s, clientFamilyMembers);

        }

        for (String s : game.getBoard().getTowers().keySet()) {
            towers.put(s, game.getBoard().getTowers().get(s).getTowerFloor().size());       //Color - tower floors
        }

        for (MarketAndCouncil marketAndCouncil : game.getBoard().getCouncils()) {
            councils.add(marketAndCouncil.getActionPlaces().size());            //Il primo council ha x actionplaces...
        }

        /** qui creiamo le x produzioni */
        for (ProdHarvZone prodZone : game.getBoard().getProductionZones()) {
            production.add(prodZone.getActionPlaces().size());
        }

        for (ProdHarvZone harvZone : game.getBoard().getHarvestZones()) {
            harvest.add(harvZone.getActionPlaces().size());
        }

        for (MarketAndCouncil marketAndCouncil : game.getBoard().getMarket()) {
            market.add(marketAndCouncil.getActionPlaces().size());
        }

        // qui creiamo la nostra lista con i nomi delle carte

       Set<String> gamers =  game.getGameStatus().getPlayers().keySet();
        for (String gamer : gamers) {
            List<String> heroCardsName = new LinkedList<>();
            Player player = game.getGameStatus().getPlayers().get(gamer);
            heroCardsMap.put(gamer,heroCardsName);
            for (HeroCard heroCard : player.getHeroCard()) {
                heroCardsMap.get(gamer).add(heroCard.getPath());
            }
        }

        excomunicationCards = new HashMap();

        for (Integer integer : game.getRoundManager().getGameEventManager().getExcomunicationCards().keySet()) {

            List<String> cards = new LinkedList<>();

            for (ExcomunicationCard excomunicationCard : game.getRoundManager().getGameEventManager().getExcomunicationCards().get(integer)) {
                cards.add(excomunicationCard.getPath());
            }
            excomunicationCards.put(integer, cards);
        }

    }

    @Override
    public void execute(ClientController clientController) {

        ClientBoardGame clientBoardGame = clientController.getMainClientModel().getClientBoardGame();

        for (int i=0;i<councils.size();i++) {
            clientBoardGame.createCouncil(i, councils.get(i));
        }
        /**
        for (int i=0;i<prodHarv.size();i++) {
            clientBoardGame.createProdHarv(i, prodHarv.get(i));
        }*/

        for (int i=0;i<market.size();i++) {
            clientBoardGame.createMarket(i, market.get(i));
        }

        for(int i = 0; i< harvest.size(); i++){
            clientBoardGame.createHarvestZone(i,harvest.get(i));
        }

        for(int i = 0; i < production.size();i++){
            clientBoardGame.createProductionZone(i,production.get(i));
        }

        for (String s : towers.keySet()) {
            clientBoardGame.createTower(s, towers.get(s));
        }

        for (String player : players.keySet()) {
            clientController.getMainClientModel().generateNewPlayerBoard(player, players.get(player));
        }


        for (String s : familyMembers.keySet()) {
            for (ClientFamilyMember clientFamilyMember : familyMembers.get(s)) {
                clientController.getMainClientModel().getClientPlayerBoard(s).addFamilyMember(clientFamilyMember);
            }
        }

        clientBoardGame.setExcomunicationCards(this.excomunicationCards);

        // creiamo la mappa di heroCards

        /**
        for (String gamer : heroCardsMap.keySet()) {

            clientController.getMainClientModel().getClientPlayerBoard(gamer).setHeroCards(heroCardsMap.get(gamer));
        }

         */

        clientController.getMainClientModel().changeMyState(ClientStateName.GAME_INIT);

     //   clientController.getMainClientModel().changeMyState(ClientStateName.WAIT_TURN);

    }
}
