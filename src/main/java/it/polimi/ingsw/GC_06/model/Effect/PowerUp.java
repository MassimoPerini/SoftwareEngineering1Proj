package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageChoosePowerUp;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Action.Actions.PowerUpFamilyMember;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 23/06/17.
 */
public class PowerUp implements Blocking {

    private Integer powerUp;

    @Override
    public synchronized void setOptionalParams(Object object) {
        powerUp = (Integer) object;
        notifyAll();
    }

    public synchronized int execute(Game game, Player player) {

        MessageServer messageServer = new MessageChoosePowerUp();
        GameList.getInstance().setCurrentBlocking(game, this, messageServer);

        while (powerUp==null)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (powerUp>0)
        {
            FamilyMember familyMember = new FamilyMember("", "");
            PowerUpFamilyMember powerUpFamilyMember = new PowerUpFamilyMember(player, familyMember, powerUp);
            if (powerUpFamilyMember.isAllowed())
                powerUpFamilyMember.execute();
            //TODO richedere se non è consentita
            return familyMember.getValue();
        }

        return powerUp;
    }
}
