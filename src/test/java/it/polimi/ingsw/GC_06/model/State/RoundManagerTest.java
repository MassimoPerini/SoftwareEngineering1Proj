package it.polimi.ingsw.GC_06.model.State;




/**
 * Created by massimo on 06/06/17.
 */
/**
public class RoundManagerTest {
    /**

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game(1);

    }

    @Test
    public void testInitValues()
    {
        game.addPlayer("Massimo");
        game.start(new DefaultEventManager(new ServerOrchestrator(), game));
        checkInitValues();
    }


    private void checkInitValues()
    {
        RoundManager roundManager = game.getRoundManager();

        assertTrue(roundManager.getEra() == 1);
        assertTrue(roundManager.getTurn() == 1);
        assertTrue(roundManager.getFamilyMembersPlaced() == 0);
    }
/**
    @Test (expected=IllegalArgumentException.class)
    public void testNoPlayers()
    {
        game.start(new DefaultEventManager(new ServerOrchestrator(), game));
        RoundManager roundManager = game.getRoundManager();

        testValues(1, roundManager);
    }

    /**@Test
    public void testOnePlayer()
    {
        game.addPlayer("Massimo");

        game.start(new DefaultEventManager(new ServerOrchestrator(), game));

        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.FAITHPOINT, 0);
        game.getGameStatus().getPlayers().get("Massimo").variateResource(resourceSet);

        RoundManager roundManager = game.getRoundManager();
        testValues(1, roundManager);
    }

    @Test
    public void testMorePlayers()
    {
        game.addPlayer("Massimo");
        game.addPlayer("Perini");
        game.addPlayer("ciao");

        game.start(new DefaultEventManager(new ServerOrchestrator(), game));


        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.FAITHPOINT, -5);
        game.getRoundManager().getCurrentPlayer().variateResource(resourceSet);

        game.getRoundManager().getPlayers().get(1).setConnected(false);

        RoundManager roundManager = game.getRoundManager();

        testValues(2, roundManager);
    }*/

/**
    private void testValues(int nPlayers, RoundManager roundManager)
    {
        for (int i=1;i<=roundManager.getMaxEras();i++)
        {
            assertTrue(roundManager.getEra() == i);
            for (int j=1;j<=roundManager.getMaxTurns();j++)
            {
                assertTrue(roundManager.getTurn() == j);
                for (int k=0;k<roundManager.getnMaxFamilyMembers();k++)
                {
                    assertTrue(roundManager.getFamilyMembersPlaced() == k);
                    for (int a=0;a<nPlayers;a++){
                        game.endTurn();
                     //   roundManager.endTurn();
                    }
                }
                assertTrue(roundManager.getFamilyMembersPlaced() == 0);
            }
            assertTrue(roundManager.getFamilyMembersPlaced() == 0);
            assertTrue(roundManager.getTurn() == 1);
        }

        checkInitValues();
    }*/

  /**  @Test
    public void testStart() throws IOException {
        ResourceSet [] resourceSets = FileLoader.getFileLoader().loadDefaultResourceSets();
        game.addPlayer("Massimo");
        game.addPlayer("Perini");
        game.addPlayer("ciao");
        game.start(new DefaultEventManager(new ServerOrchestrator(), game));
        RoundManager roundManager = game.getRoundManager();

        assertTrue(roundManager.getCurrentPlayer().getResourceSet().isIncluded(resourceSets[0]));
        assertTrue(resourceSets[0].isIncluded(roundManager.getCurrentPlayer().getResourceSet()));

    }

}*/