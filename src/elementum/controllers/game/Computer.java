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
                super.addCard(0, randomCard);
            }
            addRandomCards(allCards);
        }
    }

    public Card makeTurn(Player player) {
        // 1. Choose a card for attack
        Card computerCard = chooseComputerCard(player);

        // 2. Choose a card to attack
        Card playerCard = choosePlayerCard(player);

        // 3. Attack
        player.attack(playerCard, computerCard.getAttack());

        return playerCard;
    }

    public Card chooseComputerCard(Player player) {
        return super.getCard(0);
    }

    public Card choosePlayerCard(Player player) {
        return player.getCard(0);
    }
}
