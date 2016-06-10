package elementum.models;

import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Cards {
    public static ArrayList<Card> getAllCards() {
        ArrayList<Card> cards = new ArrayList<>();

        try {
            Card bear = new Card();
            bear.setName("Bär");
            bear.setAttack(10);
            bear.setHealth(10);
            bear.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/bear.png")));
            bear.setColor("#573c21");
            bear.paint();

            Card bear2 = new Card();
            bear2.setName("Bär 2");
            bear2.setAttack(15);
            bear2.setHealth(5);
            bear2.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/bear.png")));
            bear2.setColor("#573c21");
            bear2.paint();

            Card bear3 = new Card();
            bear3.setName("Bär 3");
            bear3.setAttack(5);
            bear3.setHealth(15);
            bear3.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/bear.png")));
            bear3.setColor("#573c21");
            bear3.paint();

            cards.add(bear);
            cards.add(bear2);
            cards.add(bear3);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return cards;
    }
}
