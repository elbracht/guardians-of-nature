package elementum.controllers.game;

import java.util.Observable;

public class Referee extends Observable {
    private boolean playersTurn;

    public boolean isPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(boolean playersTurn) {
        this.playersTurn = playersTurn;
        setChanged();
        notifyObservers();
    }
}
