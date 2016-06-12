package elementum.controllers.game;

import elementum.models.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * @author Alexander Elbracht
 *
 * Class for all player actions
 */
public class Player {
    public static final int CARD_LIMIT = 3;

    private Hashtable<Card, Integer> cards;

    /**
     * Constructor
     */
    public Player() {
        cards = new Hashtable<>();
    }

    /**
     * Add a new card to card stack
     * @param cardId Id to identify the card in view
     * @param card The card to add to stack
     */
    public void addCard(int cardId, Card card) {
        cards.put(card, cardId);
    }

    /**
     * Remove a card from card stack
     * @param card
     */
    public void removeCard(Card card) {
        cards.remove(card);
    }

    /**
     * Change id for a card in card stack
     * @param cardId Id to identify the card in view
     * @param card Card in card stack
     */
    public void updateCard(int cardId, Card card) {
        cards.replace(card, cardId);
    }

    /**
     * Get id for a card in card stack
     * @param card Id for this card
     * @return Id
     */
    public int getId(Card card) {
        return cards.get(card);
    }

    /**
     * Get card with index from card stack
     * @param index Index
     * @return Card
     */
    public Card getCard(int index) {
        ArrayList<Card> keys = Collections.list(cards.keys());
        return keys.get(index);
    }

    /**
     * Get all cards in card stack
     * @return All cards
     */
    public Card[] getCards() {
        ArrayList<Card> keys = Collections.list(cards.keys());
        return keys.toArray(new Card[0]);
    }

    /**
     * Get count of cards in card stack
     * @return Count of cards
     */
    public int getCardsCount() {
        return cards.size();
    }

    public boolean containCard(Card card) {
        ArrayList<Card> keys = Collections.list(cards.keys());
        return keys.contains(card);
    }

    /**
     * Attack a card in card stack
     * @param index Index of card
     * @param damage Damage value
     */
    public void attack(int index, int damage) throws Exception {
        attack(getCard(index), damage);
    }

    /**
     * Attack a card in card stack
     * @param card Card
     * @param damage Damage value
     */
    public void attack(Card card, int damage) throws Exception {
        card.setHealth(card.getHealth() - damage);
        card.paint();
    }
}
