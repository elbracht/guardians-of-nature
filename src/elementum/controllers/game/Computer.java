package elementum.controllers.game;

import elementum.models.Card;
import elementum.models.Cards;

import java.util.Random;

/**
 * @author Alexander Elbracht
 *
 * Class for all computer actions.
 * This class extends the Player class, because a Computer
 * is just a player with artificial intelligence.
 */
public class Computer extends Player{
    /**
     * Constructor
     */
    public Computer() {
        super();

        addRandomCards(new Cards());
    }

    /**
     * Add three random cards to computer cards
     * @param allCards Stack of all cards
     */
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

    /**
     * The computer choose a card for attack and his target
     * @param player Player for attack
     * @return Array of cards (attack and target)
     */
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

    /**
     * Choose a card for attack and his target
     * @param player Player for attack
     * @return Array of ids (id for attack card and id for target card)
     */
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

    /**
     * Build a tree with all next attack possibilities
     * @param attackCard Card for attack
     * @param defenseCards Array of cards for defense
     * @return Repeats to destroy the defense cards
     */
    private int[] createAttackTree(Card attackCard, Card[] defenseCards) {
        int[] depth = new int[] { 0, 0, 0 };
        for (int i = 0; i < defenseCards.length; i++) {
            depth[i] = createAttackTree(depth[i], attackCard.getAttack(), defenseCards[i].getHealth());
        }

        return depth;
    }

    /**
     * Build a tree with all next attack possibilities
     * @param depth Repeat depth to start with
     * @param attack Attack value
     * @param health Health value
     * @return Repeat depth to destroy the card
     */
    private int createAttackTree(int depth, int attack, int health) {
        if (health > 0) {
            depth += 1;
            depth = createAttackTree(depth, attack, health - attack);
        }

        return depth;
    }
}
