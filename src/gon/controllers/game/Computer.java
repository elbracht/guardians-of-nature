package gon.controllers.game;

import gon.models.Card;
import gon.models.Cards;

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
    public Computer(Cards cards) throws Exception {
        super();

        addRandomCards(cards);
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
    public Card[] makeTurn(Player player) throws Exception {
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
        int attack = chooseAttackCard();
        int defense = chooseDefenseCard(getCard(attack), player);

        return new int[] { attack, defense };
    }

    /**
     * Choose the attack card
     * @return Id of attack card
     */
    private int chooseAttackCard() {
        Card card0 = getCard(0);
        Card card1 = getCard(1);
        Card card2 = getCard(2);

        // No card is destroyed
        if (card0.getHealth() > 0 && card1.getHealth() > 0 && card2.getHealth() > 0) {
            if (card0.getAttack() == card1.getAttack() && card1.getAttack() == card2.getAttack()) {
                Random random = new Random();
                return random.nextInt(3);
            }
            else if (card0.getAttack() == card1.getAttack() && card0.getAttack() > card2.getAttack()) {
                if (Math.random() < 0.5) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
            else if (card1.getAttack() == card2.getAttack() && card1.getAttack() > card0.getAttack()) {
                if (Math.random() < 0.5) {
                    return 1;
                }
                else {
                    return 2;
                }
            }
            else if (card0.getAttack() == card2.getAttack() && card2.getAttack() > card1.getAttack()) {
                if (Math.random() < 0.5) {
                    return 0;
                }
                else {
                    return 2;
                }
            }
            else if (card0.getAttack() > card1.getAttack() && card0.getAttack() > card2.getAttack()) {
                return 0;
            }
            else if (card1.getAttack() > card0.getAttack() && card1.getAttack() > card2.getAttack()) {
                return 1;
            }
            else if (card2.getAttack() > card0.getAttack() && card2.getAttack() > card1.getAttack()) {
                return 2;
            }
        }
        // Only card 0 is destroyed
        else if (card0.getHealth() <= 0 && card1.getHealth() > 0 && card2.getHealth() > 0) {
            if (card1.getAttack() == card2.getAttack()) {
                if (Math.random() < 0.5) {
                    return 1;
                }
                else {
                    return 2;
                }
            }
            else if (card1.getAttack() > card2.getAttack()) {
                return 1;
            }
            else {
                return 2;
            }
        }
        // Only card 1 is destroyed
        else if (card0.getHealth() > 0 && card1.getHealth() <= 0 && card2.getHealth() > 0) {
            if (card0.getAttack() == card1.getAttack()) {
                if (Math.random() < 0.5) {
                    return 0;
                }
                else {
                    return 2;
                }
            }
            else if (card0.getAttack() > card2.getAttack()) {
                return 0;
            }
            else {
                return 2;
            }
        }
        // Only card 2 is destroyed
        else if (card0.getHealth() > 0 && card1.getHealth() > 0 && card2.getHealth() <= 0) {
            if (card0.getAttack() == card1.getAttack()) {
                if (Math.random() < 0.5) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
            else if (card0.getAttack() > card1.getAttack()) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // Card 0 and card 1 are destroyed
        else if (card0.getHealth() <= 0 && card1.getHealth() <= 0 && card2.getHealth() > 0) {
            return 2;
        }
        // Card 1 and card 2 are destroyed
        else if (card0.getHealth() > 0 && card1.getHealth() <= 0 && card2.getHealth() <= 0) {
            return 0;
        }
        // Card 0 and card 2 are destroyed
        else if (card0.getHealth() <= 0 && card1.getHealth() > 0 && card2.getHealth() <= 0) {
            return 1;
        }

        return 0;
    }

    /**
     * Choose the defense card
     * @param attackCard Attack card
     * @param player Player
     * @return Id of defense card
     */
    private int chooseDefenseCard(Card attackCard, Player player) {
        int[] depth = createAttackTree(attackCard, player.getCards());

        // Everything is different
        if (depth[0] != depth[1] && depth[1] != depth[2] && depth[0] != depth[2]) {
            if (depth[0] > 0 && depth[1] > 0 && depth[2] > 0) {
                if (depth[0] < depth[1] && depth[0] < depth[2]) {
                    return 0;
                }
                else if (depth[1] < depth[0] && depth[1] < depth[2]) {
                    return 1;
                }
                else if (depth[2] < depth[0] && depth[2] < depth[1]) {
                    return 2;
                }
            }
            else if (depth[0] <= 0 && depth[1] > 0 && depth[2] > 0) {
                if (depth[1] < depth[2]) {
                    return 1;
                }
                else {
                    return 2;
                }
            }
            else if (depth[0] > 0 && depth[1] <= 0 && depth[2] > 0) {
                if (depth[0] < depth[2]) {
                    return 0;
                }
                else {
                    return 2;
                }
            }
            else if (depth[0] > 0 && depth[1] > 0 && depth[2] <= 0) {
                if (depth[0] < depth[1]) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
        }
        // Everything is same
        else if (depth[0] == depth[1] && depth[1] == depth[2] && depth[0] == depth[2]) {
            Random random = new Random();
            return random.nextInt(3);
        }
        // 0 and 1 are the same
        else if (depth[0] == depth[1] && depth[0] != depth[2] && depth[1] != depth[2]) {
            if (depth[0] > 0 && depth[2] > 0) {
                if (depth[0] < depth[2]) {
                    if (Math.random() < 0.5) {
                        return 0;
                    }
                    else {
                        return 1;
                    }
                }
                else {
                    return 2;
                }
            }
            else if (depth[0] > 0 && depth[2] <= 0) {
                if (Math.random() < 0.5) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
            else {
                return 2;
            }
        }
        // 1 and 2 are the same
        else if (depth[1] == depth[2] && depth[1] != depth[0] && depth[2] != depth[0]) {
            if (depth[1] > 0 && depth[0] > 0) {
                if (depth[1] < depth[0]) {
                    if (Math.random() < 0.5) {
                        return 1;
                    }
                    else {
                        return 2;
                    }
                }
                else {
                    return 0;
                }
            }
            else if (depth[1] > 0 && depth[0] <= 0) {
                if (Math.random() < 0.5) {
                    return 1;
                }
                else {
                    return 2;
                }
            }
            else {
                return 0;
            }
        }
        // 0 and 2 are the same
        else if (depth[0] == depth[2] && depth[0] != depth[1] && depth[2] != depth[1]) {
            if (depth[0] > 0 && depth[1] > 0) {
                if (depth[0] < depth[1]) {
                    if (Math.random() < 0.5) {
                        return 0;
                    }
                    else {
                        return 2;
                    }
                }
                else {
                    return 1;
                }
            }
            else if (depth[0] > 0 && depth[1] <= 0) {
                if (Math.random() < 0.5) {
                    return 0;
                }
                else {
                    return 2;
                }
            }
            else {
                return 1;
            }
        }

        return 0;
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
