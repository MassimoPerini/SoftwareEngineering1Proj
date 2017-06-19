package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientBoardGame;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.ClientSpaceAction;
import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Resource.Resource;

import java.util.List;

/**
 * Created by massimo on 18/06/17.
 */
public class MessageChangePlayer implements MessageServer {
    private String newPlayer;
    private int era;
    private int turn;

    public MessageChangePlayer(String newPlayer, int era, int turn) {
        this.newPlayer = newPlayer;
        this.era = era;
        this.turn = turn;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().updateStatus(turn, era, newPlayer);

        //CANCELLARE!!!!!

        ClientBoardGame clientBoardGame = clientController.getMainClientModel().getClientBoardGame();

        for (String s : clientBoardGame.getTowersClient().keySet()) {
            System.out.println("TORRE: "+s);
            for (ClientTowerFloor clientTowerFloor : clientBoardGame.getTowersClient().get(s)) {
                System.out.println("CARTA PIANO "+clientTowerFloor.getCard());
                System.out.println("FAMILY MEMBERS: ");
                for (ClientFamilyMember clientFamilyMember : clientTowerFloor.getSpaceAction().getFamilyMembers()) {
                    System.out.println("FAMILY MEMBER");
                }
            }

        }

        System.out.println("ORA MOSTRO GLI SPAZI PROD: HARV:\n");
        for (List<ClientSpaceAction> clientSpaceActions : clientBoardGame.getProductionHarvest()) {
            System.out.println("SPAZIO PROD: HARV:");
            for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                System.out.println("SPAZIO AZIONE: PEDINE SOPRA: ");
                for (ClientFamilyMember clientFamilyMember : clientSpaceAction.getFamilyMembers()) {
                    System.out.println("PEDINA TROVATA "+clientFamilyMember.getPlayer());
                }
            }
        }


        System.out.println("ORA MOSTRO GLI SPAZI MERCATO:\n");
        for (List<ClientSpaceAction> clientSpaceActions : clientBoardGame.getMarket()) {
            System.out.println("SPAZIO MARKET:");
            for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                System.out.println("SPAZIO AZIONE MARKET: ");
                for (ClientFamilyMember clientFamilyMember : clientSpaceAction.getFamilyMembers()) {
                    System.out.println("PEDINA TROVATA "+clientFamilyMember.getPlayer());
                }
            }
        }

        System.out.println("ORA MOSTRO GLI SPAZI CONSIGLIO:\n");
        for (List<ClientSpaceAction> clientSpaceActions : clientBoardGame.getCouncil()) {
            System.out.println("SPAZIO CONSIGLIO:");
            for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                System.out.println("SPAZIO AZIONE: PEDINE SOPRA: ");
                for (ClientFamilyMember clientFamilyMember : clientSpaceAction.getFamilyMembers()) {
                    System.out.println("PEDINA TROVATA "+clientFamilyMember.getPlayer());
                }
            }
        }

        System.out.println("ORA MOSTRO I GIOCATORI:\n");
        for (String s : clientController.getMainClientModel().getClientPlayerBoard().keySet()) {
            System.out.println("GIOCATORE :"+s);

            System.out.println("RISORSE :");
            for (Resource resource : clientController.getMainClientModel().getClientPlayerBoard().get(s).getResourceSet().keySet()) {
                System.out.println("RISORSA :"+resource.name()+" QUANTITA': "+clientController.getMainClientModel().getClientPlayerBoard().
                        get(s).getResourceSet().get(resource));
            }
            System.out.println("FAMILIARI :");
            for (ClientFamilyMember clientFamilyMember : clientController.getMainClientModel().getClientPlayerBoard().get(s).getFamilyMembers()) {
                System.out.println("FAMILIARE :"+clientFamilyMember.getColor()+" "+clientFamilyMember.getValue());
            }

            System.out.println("CARTE :");
            for (String s1 : clientController.getMainClientModel().getClientPlayerBoard().get(s).getCards().keySet()) {
                System.out.println("CARTE :"+s1);
                for (String s2 : clientController.getMainClientModel().getClientPlayerBoard().get(s).getCards().get(s1)) {
                    System.out.println(s2+"\n");
                }
            }

        }




    }
}
