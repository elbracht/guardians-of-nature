package elementum.models;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class Cards {
    private ArrayList<Card> cards;

    public Cards() throws IOException {
        cards = new ArrayList<>();

        Card bear = new Card();
        bear.setName("Bär");
        bear.setAttack(10);
        bear.setHealth(10);
        bear.setImage(ImageIO.read(getClass().getResourceAsStream("../assets/cards/bear.png")));

        Card bear2 = new Card();
        bear2.setName("Bär 2");
        bear2.setAttack(10);
        bear2.setHealth(10);
        bear2.setImage(ImageIO.read(getClass().getResourceAsStream("../assets/cards/bear.png")));

        Card bear3 = new Card();
        bear3.setName("Bär 3");
        bear3.setAttack(10);
        bear3.setHealth(10);
        bear3.setImage(ImageIO.read(getClass().getResourceAsStream("../assets/cards/bear.png")));

        cards.add(bear);
        cards.add(bear2);
        cards.add(bear3);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
