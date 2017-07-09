package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.*;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.model.Resource.Resource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by massimo on 01/06/17.
 * this class is used to manage the client's visualization of both general board and personal board with the view
 */
public class BoardStatusViewController implements ViewPresenterCLI {

    private ClientBoardGame clientBoardGame;
    private Map<String, ClientPlayerBoard> clientPlayerBoard;
    private Future future;
    private final CommandView commandView;
    private ExecutorService executor;


    public BoardStatusViewController(ClientBoardGame clientBoardGame, Map<String, ClientPlayerBoard> clientPlayerBoard)
    {
        this.clientBoardGame = clientBoardGame;
        this.clientPlayerBoard = clientPlayerBoard;
        this.commandView = new CmdView();
    }

    /**
     * waits for another player or, based on the choice of the client, shows the genral board or the personal board
     * @throws InterruptedException
     */
    @Override
    public void viewWillAppear() throws InterruptedException {
        commandView.addLocalizedText("Le tue scelte sono: \n 1)Inserire Board per vedere il campo da gioco , \n 2) Inserire Plancia per vedere la personal Board  \n 3) STOP per aspettare che l'altro giocatore termini il turno");
        String answ = commandView.getString();
        if (answ == null) {
            return;
        }

        while(!answ.equals("STOP")){

        if (answ.equals("STOP")){
            System.out.println("Aspettando l'altro giocatore");
            return;
        }

        if (answ.equals("Board")) {
            for (String s : clientBoardGame.getTowersClient().keySet()) {
                System.out.println("TORRE: " + s);
                for (ClientTowerFloor clientTowerFloor : clientBoardGame.getTowersClient().get(s)) {
                    System.out.println("CARTA PIANO " + clientTowerFloor.getCard());
                    System.out.println("FAMILY MEMBERS: ");
                    for (ClientFamilyMember clientFamilyMember : clientTowerFloor.getSpaceAction().getFamilyMembers()) {
                        System.out.println("FAMILY MEMBER: " + clientFamilyMember.getPlayer() + " " + clientFamilyMember.getValue() + " " + clientFamilyMember.getColor());
                    }
                }

            }

            System.out.println("ORA MOSTRO GLI SPAZI PROD:\n");
            for (List<ClientSpaceAction> clientSpaceActions : clientBoardGame.getProductionZone()) {
                System.out.println("SPAZIO PROD:");
                for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                    System.out.println("SPAZIO AZIONE: PEDINE SOPRA: ");
                    for (ClientFamilyMember clientFamilyMember : clientSpaceAction.getFamilyMembers()) {
                        System.out.println("PEDINA TROVATA " + clientFamilyMember.getPlayer());
                    }
                }
            }


            System.out.println("ORA MOSTRO GLI SPAZI HARV:\n");
            for (List<ClientSpaceAction> clientSpaceActions : clientBoardGame.getHarvestZone()) {
                System.out.println("SPAZIO PROD: HARV:");
                for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                    System.out.println("SPAZIO AZIONE: PEDINE SOPRA: ");
                    for (ClientFamilyMember clientFamilyMember : clientSpaceAction.getFamilyMembers()) {
                        System.out.println("PEDINA TROVATA " + clientFamilyMember.getPlayer());
                    }
                }
            }



            System.out.println("ORA MOSTRO GLI SPAZI MERCATO:\n");
            for (List<ClientSpaceAction> clientSpaceActions : clientBoardGame.getMarket()) {
                System.out.println("SPAZIO MARKET:");
                for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                    System.out.println("SPAZIO AZIONE MARKET: ");
                    for (ClientFamilyMember clientFamilyMember : clientSpaceAction.getFamilyMembers()) {
                        System.out.println("PEDINA TROVATA " + clientFamilyMember.getPlayer());
                    }
                }
            }
            System.out.println("ORA MOSTRO GLI SPAZI CONSIGLIO:\n");
            for (List<ClientSpaceAction> clientSpaceActions : clientBoardGame.getCouncil()) {
                System.out.println("SPAZIO CONSIGLIO:");
                for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                    System.out.println("SPAZIO AZIONE: PEDINE SOPRA: ");
                    for (ClientFamilyMember clientFamilyMember : clientSpaceAction.getFamilyMembers()) {
                        System.out.println("PEDINA TROVATA " + clientFamilyMember.getPlayer());
                    }
                }
            }
        }

        if (answ.equals("Plancia")) {
            System.out.println("ORA MOSTRO I GIOCATORI:\n");
            for (String s : clientPlayerBoard.keySet()) {
                System.out.println("GIOCATORE :" + s);

                System.out.println("BONUS SCELTO: ");
                for (String bonus : clientPlayerBoard.get(s).getPlayerProdHarvBonus()) {
                    System.out.print(bonus + ", ");
                }

                System.out.println("RISORSE :");
                for (Resource resource : clientPlayerBoard.get(s).getResourceSet().keySet()) {
                    System.out.println("RISORSA :" + resource.name() + " QUANTITA': " + clientPlayerBoard.
                            get(s).getResourceSet().get(resource));
                }
                System.out.println("FAMILIARI :");
                for (ClientFamilyMember clientFamilyMember : clientPlayerBoard.get(s).getFamilyMembers()) {
                    System.out.println("FAMILIARE :" + clientFamilyMember.getColor() + " " + clientFamilyMember.getValue());
                }

                System.out.println("CARTE :");
                for (String s1 : clientPlayerBoard.get(s).getCards().keySet()) {
                    System.out.println("CARTE :" + s1);
                    for (String s2 : clientPlayerBoard.get(s).getCards().get(s1)) {
                        System.out.println(s2 + "\n");
                    }
                }

                System.out.println("CARTE EROE :");

                List<String> heroCardsName = clientPlayerBoard.get(s).getHeroCards();

                for (String heroName : heroCardsName) {
                    System.out.println("NOME:" + heroName);
                }

            }
        }
            commandView.addLocalizedText("Le tue scelte sono: \n 1)Inserire Board per vedere il campo da gioco , \n 2) Inserire Plancia per vedere la personal Board \n 3) Terminare La visualizzazione ");
            answ = commandView.getString();

        }
    }

}
