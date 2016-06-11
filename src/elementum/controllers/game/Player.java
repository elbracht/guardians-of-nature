package elementum.controllers.game;

import elementum.models.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class Player {
    public static final int CARD_LIMIT = 3;

    private Hashtable<Card, Integer> cards;

    public Player() {
        cards = new Hashtable<>();
    }

    public void addCard(int cardId, Card card) {
        cards.put(card, cardId);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void updateCard(int cardId, Card card) {
        cards.replace(card, cardId);
    }

    public int getId(Card card) {
        return cards.get(card);
    }

    public Card getCard(int index) {
        ArrayList<Card> keys = Collections.list(cards.keys());
        return keys.get(index);
    }

    public Card[] getCards() {
        ArrayList<Card> keys = Collections.list(cards.keys());
        return keys.toArray(new Card[0]);
    }

    public int getCardsCount() {
        return cards.size();
    }

    public Boolean containCard(Card card) {
        ArrayList<Card> keys = Collections.list(cards.keys());
        return keys.contains(card);
    }

    public void attack(int index, int damage) {
        attack(getCard(index), damage);
    }

    public void attack(Card card, int damage) {
        card.setHealth(card.getHealth() - damage);
        card.paint();
    }
}
