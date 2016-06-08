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
        bear.setImage(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/bear.png")));
        bear.setColor("#573c21");
        bear.paint();

        Card bear2 = new Card();
        bear2.setName("Bär 2");
        bear2.setAttack(15);
        bear2.setHealth(5);
        bear2.setImage(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/bear.png")));
        bear2.setColor("#573c21");
        bear2.paint();

        Card bear3 = new Card();
        bear3.setName("Bär 3");
        bear3.setAttack(5);
        bear3.setHealth(15);
        bear3.setImage(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/bear.png")));
        bear3.setColor("#573c21");
        bear3.paint();

        cards.add(bear);
        cards.add(bear2);
        cards.add(bear3);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
