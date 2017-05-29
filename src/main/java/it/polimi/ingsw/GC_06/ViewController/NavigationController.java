package it.polimi.ingsw.GC_06.ViewController;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.GameStatus;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import javax.swing.text.View;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 27/05/17.
 */
public class NavigationController implements NavigationControllerInterface {

    private List<ViewController> viewControllers;
    private static NavigationController navigationController;

    private NavigationController()
    {
        viewControllers = new LinkedList<>();
    }

    public static NavigationController getInstance()
    {
        if (navigationController==null){
            navigationController = new NavigationController();
        }
        return navigationController;
    }

    /**
     * A new ViewController is pushed
     * @param viewController
     */
    public void pushViewController(final ViewController viewController) {

        if (viewControllers.size()>0)
        {
            ViewController currentController = viewControllers.get(viewControllers.size()-1);
            currentController.viewWillDisappear();
        }

        viewController.viewDidLoad();
        viewControllers.add(viewController);
        viewController.viewWillAppear();

    }

    /**
     * Expected at least 1 viewController
     */

    public void dismissViewController(){

        ViewController removeController = viewControllers.get(viewControllers.size()-1);
        removeController.viewWillDisappear();
        removeController.viewDidUnload();
        viewControllers.remove(viewControllers.size()-1);

        if (viewControllers.size()>0)
        {
            viewControllers.get(viewControllers.size()-1).viewWillAppear();
        }

    }

    @Override
    public Player getPlayer() {
        return Game.getInstance().getGameStatus().getCurrentPlayer();
    }
}