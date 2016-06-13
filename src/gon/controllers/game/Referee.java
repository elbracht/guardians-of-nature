package gon.controllers.game;

import gon.models.Card;

import java.util.Observable;

/**
 * @author Alexander Elbracht
 *
 * This class control the truns of the game and watch for a game over.
 */
public class Referee extends Observable {
    private Player player;
    private Computer computer;
    private boolean playersTurn;

    /**
     * Constructor
     * @param player Player
     * @param computer Computer
     */
    public Referee(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
    }

    /**
     * Check if it's players turn
     * @return Players turn
     */
    public boolean isPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(boolean playersTurn) {
        this.playersTurn = playersTurn;
        setChanged();
        notifyObservers();
    }

    /**
     * Check if game is over
     * @return Game over
     */
    public boolean isGameOver() {
        if (isPlayerGameOver() || isComputerGameOver()) {
            return true;
        }

        return false;
    }

    /**
     * Check if computer is game over
     * @return Computer is game over
     */
    public boolean isComputerGameOver() {
        boolean isGameOver = true;

        for (Card card : computer.getCards()) {
            if (card.getHealth() > 0) {
                isGameOver = false;
            }
        }

        return isGameOver;
    }

    /**
     * Check if player is game over
     * @return Player is game over
     */
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
