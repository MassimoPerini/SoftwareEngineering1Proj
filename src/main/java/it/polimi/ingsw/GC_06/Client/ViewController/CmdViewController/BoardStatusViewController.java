package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.*;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageBoardActionTower;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageThrowDice;
import it.polimi.ingsw.GC_06.model.Resource.Resource;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 01/06/17.
 */
public class BoardStatusViewController implements ViewPresenterCLI {

    private ClientBoardGame clientBoardGame;
    private Map<String, ClientPlayerBoard> clientPlayerBoard;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;

    public BoardStatusViewController(ClientBoardGame clientBoardGame, Map<String, ClientPlayerBoard> clientPlayerBoard, ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.clientBoardGame = clientBoardGame;
        this.clientPlayerBoard = clientPlayerBoard;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    @Override
    public void viewWillAppear() {
        System.out.println("CLIENTBOARDGAME INVOKED");

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
        for (String s : clientPlayerBoard.keySet()) {
            System.out.println("GIOCATORE :"+s);

            System.out.println("RISORSE :");
            for (Resource resource : clientPlayerBoard.get(s).getResourceSet().keySet()) {
                System.out.println("RISORSA :"+resource.name()+" QUANTITA': "+clientPlayerBoard.
                        get(s).getResourceSet().get(resource));
            }
            System.out.println("FAMILIARI :");
            for (ClientFamilyMember clientFamilyMember : clientPlayerBoard.get(s).getFamilyMembers()) {
                System.out.println("FAMILIARE :"+clientFamilyMember.getColor()+" "+clientFamilyMember.getValue());
            }

            System.out.println("CARTE :");
            for (String s1 : clientPlayerBoard.get(s).getCards().keySet()) {
                System.out.println("CARTE :"+s1);
                for (String s2 : clientPlayerBoard.get(s).getCards().get(s1)) {
                    System.out.println(s2+"\n");
                }
            }

        }

        String input = JOptionPane.showInputDialog("SCRIVI COMANDO");
        if (input.equals("TIRA DADI")){
            MessageThrowDice messageThrowDice = new MessageThrowDice();
            clientNetworkOrchestrator.send(messageThrowDice);
        }

        if(input.equals("BA")){
            String inp = JOptionPane.showInputDialog("Torre Piano Familiare");
            String[] inpList;
            inpList = inp.split(" ");
            MessageBoardActionTower messageBoardActionTower = new MessageBoardActionTower(inpList[0],Integer.parseInt(inpList[1]),Integer.parseInt(inpList[2]));
            clientNetworkOrchestrator.send(messageBoardActionTower);
        }

    }

    @Override
    public void addText(String txt) {

    }
}
