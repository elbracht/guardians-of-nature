package elementum.controllers.game;

import elementum.models.Card;

import java.util.ArrayList;

public class Player {
    public static final int CARD_LIMIT = 3;

    private ArrayList<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int getCardsCount() {
        return cards.size();
    }
}
