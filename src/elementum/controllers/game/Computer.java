package elementum.controllers.game;

import elementum.models.Card;
import elementum.models.Cards;

import java.util.ArrayList;
import java.util.Random;

public class Computer {
    private ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Computer() throws Exception {
        addRandomCards();
    }

    private void addRandomCards() {
        Random random = new Random();
        ArrayList<Card> allCards = Cards.getAllCards();
        Card randomCard = allCards.get(random.nextInt(allCards.size()));

        if (cards.size() < 3) {
            if (!cards.contains(randomCard)) {
                cards.add(randomCard);
            }
            addRandomCards();
        }
    }
}
