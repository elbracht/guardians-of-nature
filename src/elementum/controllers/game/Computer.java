package elementum.controllers.game;

import elementum.models.Card;
import elementum.models.Cards;

import java.util.Random;

public class Computer extends Player{
    public Computer(Cards allCards) {
        super();

        addRandomCards(allCards);
    }

    public void addRandomCards(Cards allCards) {
        Random random = new Random();
        Card randomCard = allCards.getCards().get(random.nextInt(allCards.getCards().size()));

        if (super.getCardsCount() < super.CARD_LIMIT) {
            if (!super.containCard(randomCard)) {
                super.addCard(randomCard);
            }
            addRandomCards(allCards);
        }
    }
}
