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

        if (getCardsCount() < CARD_LIMIT) {
            if (!containCard(randomCard)) {
                addCard(0, randomCard);
            }
            addRandomCards(allCards);
        }
    }

    public Card[] makeTurn(Player player) {
        int[] cardIds = chooseCards(player);

        if (cardIds != null) {
            Card attackCard = getCard(cardIds[0]);
            Card defenseCard = player.getCard(cardIds[1]);

            player.attack(defenseCard, attackCard.getAttack());

            return new Card[] { attackCard, defenseCard };
        }

        return null;
    }

    public int[] chooseCards(Player player) {
        for (int i = 0; i < getCards().length; i++) {
            Card card = getCard(i);

            if (card.getHealth() > 0) {
                int[] depth = createAttackTree(card, player.getCards());

                for (int j = 0; j < depth.length; j++) {
                    if (depth[j] > 0) {
                        return new int[] { i, j };
                    }
                }
            }
        }

        return null;
    }

    private int[] createAttackTree(Card attackCard, Card[] defenseCards) {
        int[] depth = new int[] { 0, 0, 0 };
        for (int i = 0; i < defenseCards.length; i++) {
            depth[i] = createAttackTree(depth[i], attackCard.getAttack(), defenseCards[i].getHealth());
        }

        return depth;
    }

    private int createAttackTree(int depth, int attack, int health) {
        if (health > 0) {
            depth += 1;
            depth = createAttackTree(depth, attack, health - attack);
        }

        return depth;
    }
}
