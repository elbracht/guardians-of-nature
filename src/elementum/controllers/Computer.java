package elementum.controllers;

import elementum.models.Card;
import elementum.models.Cards;

import java.util.ArrayList;
import java.util.Random;

public class Computer {
    private Cards cards;
    private ArrayList<Card> cardsSelected;

    public ArrayList<Card> getCardsSelected() {
        return cardsSelected;
    }

    public Computer() throws Exception {
        cards = new Cards();
        cardsSelected = new ArrayList<>();

        addRandomCards();
    }

    private void addRandomCards() {
        Random random = new Random();
        Card randomCard = cards.getCards().get(random.nextInt(cards.getCards().size()));

        if (cardsSelected.size() < 3) {
            if (!cardsSelected.contains(randomCard)) {
                cardsSelected.add(randomCard);
            }
            addRandomCards();
        }
    }
}
