package elementum.controllers.game;

import elementum.models.Card;
import elementum.models.Cards;

import java.util.ArrayList;
import java.util.Random;

public class Computer {
    private ArrayList<Card> cardArray = new ArrayList<>();

    public Card getCard(int index) {
        return cardArray.get(index);
    }

    public Computer(Cards cards) throws Exception {
        addRandomCards(cards);
    }

    private void addRandomCards(Cards cards) {
        Random random = new Random();
        ArrayList<Card> allCards = cards.getCards();
        Card randomCard = allCards.get(random.nextInt(allCards.size()));

        if (cardArray.size() < 3) {
            if (!cardArray.contains(randomCard)) {
                cardArray.add(randomCard);
            }
            addRandomCards(cards);
        }
    }
}
