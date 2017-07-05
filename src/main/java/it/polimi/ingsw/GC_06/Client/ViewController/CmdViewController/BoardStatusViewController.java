package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import it.polimi.ingsw.GC_06.Client.Model.*;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.model.Resource.Resource;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by massimo on 01/06/17.
 */
public class BoardStatusViewController implements ViewPresenterCLI, Runnable {

    private ClientBoardGame clientBoardGame;
    private Map<String, ClientPlayerBoard> clientPlayerBoard;
    private Future future;
    private final CommandView commandView;


    public BoardStatusViewController(ClientBoardGame clientBoardGame, Map<String, ClientPlayerBoard> clientPlayerBoard)
    {
        this.clientBoardGame = clientBoardGame;
        this.clientPlayerBoard = clientPlayerBoard;
        this.commandView = new CmdView();
    }

    @Override
    public void viewWillAppear() {
        System.out.println("ciao");
        ExecutorService executor = Executors.newCachedThreadPool();
        this.future = executor.submit(this);
    }

    @Override
    public void addText(String txt) {

    }

    @Override
    public void viewWillDisappear() {
        System.out.println("VIEWWILLDISAPPEAR INVOKED!!!!");
        this.future.cancel(true);
    }

    @Override
    public void run() {
        System.out.println("CLIENTBOARDGAME INVOKED");
        try {
            while (!Thread.currentThread().isInterrupted()) {
                showStatus();
                Thread.sleep(200);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("HAI FATTO UNA AZIONE A CUI NON POTEVI AVERE ACCESSO");
            return;
        }
    }

    void showStatus() throws InterruptedException {
        commandView.addLocalizedText("Sei in sola lettura. Inserire b per vedere la board, p per vedere le board dei players");
        String answ = commandView.getString();
        if(answ==null){
            return;
        }

        if (answ.equals("b")) {
            for (String s : clientBoardGame.getTowersClient().keySet()) {
                System.out.println("TORRE: " + s);
                for (ClientTowerFloor clientTowerFloor : clientBoardGame.getTowersClient().get(s)) {
                    System.out.println("CARTA PIANO " + clientTowerFloor.getCard());
                    System.out.println("FAMILY MEMBERS: ");
                    for (ClientFamilyMember clientFamilyMember : clientTowerFloor.getSpaceAction().getFamilyMembers()) {
                        System.out.println("FAMILY MEMBER: "+clientFamilyMember.getPlayer()+" "+clientFamilyMember.getValue()+" "+clientFamilyMember.getColor());
                    }
                }

            }

            System.out.println("ORA MOSTRO GLI SPAZI PROD: HARV:\n");
            for (List<ClientSpaceAction> clientSpaceActions : clientBoardGame.getProductionHarvest()) {
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

        if (answ.equals("p")) {
            System.out.println("ORA MOSTRO I GIOCATORI:\n");
            for (String s : clientPlayerBoard.keySet()) {
                System.out.println("GIOCATORE :" + s);

                System.out.println("BONUS SCELTO: ");
                for (String bonus : clientPlayerBoard.get(s).getPlayerProdHarvBonus()) {
                    System.out.print(bonus+", ");
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
    }
}
