package elementum.controllers.game;

import elementum.models.Card;

import java.util.Observable;

public class Referee extends Observable {
    private Player player;
    private Computer computer;
    private boolean playersTurn;

    public Referee(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
    }

    public boolean isPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(boolean playersTurn) {
        this.playersTurn = playersTurn;
        setChanged();
        notifyObservers();
    }

    public boolean isGameOver() {
        if (isPlayerGameOver() || isComputerGameOver()) {
            return true;
        }

        return false;
    }

    public boolean isComputerGameOver() {
        boolean isGameOver = true;

        for (Card card : computer.getCards()) {
            if (card.getHealth() > 0) {
                isGameOver = false;
            }
        }

        return isGameOver;
    }

    public boolean isPlayerGameOver() {
        boolean isGameOver = true;

        for (Card card : player.getCards()) {
            if (card.getHealth() > 0) {
                isGameOver = false;
            }
        }

        return isGameOver;
    }
}
