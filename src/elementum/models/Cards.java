package elementum.models;

import elementum.controllers.utils.Locale;

import javax.imageio.ImageIO;
import java.util.ArrayList;

/**
 * @author Alexander Elbracht
 *
 * This class create and save all cards of the game.
 */
public class Cards {
    private ArrayList<Card> cards;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Cards(Locale locale) throws Exception {
        cards = new ArrayList<>();

        Card bear = new Card();
        bear.setName(locale.getString("cards", "bear"));
        bear.setAttack(6);
        bear.setHealth(80);
        bear.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/bear.png")));
        bear.setColor("#573c21");
        bear.paint();

        Card bird = new Card();
        bird.setName(locale.getString("cards", "bird"));
        bird.setAttack(14);
        bird.setHealth(65);
        bird.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/bird.png")));
        bird.setColor("#d3811f");
        bird.paint();

        Card fox = new Card();
        fox.setName(locale.getString("cards", "fox"));
        fox.setAttack(20);
        fox.setHealth(42);
        fox.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/fox.png")));
        fox.setColor("#e5751f");
        fox.paint();

        Card giraffe = new Card();
        giraffe.setName(locale.getString("cards", "giraffe"));
        giraffe.setAttack(10);
        giraffe.setHealth(58);
        giraffe.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/giraffe.png")));
        giraffe.setColor("#fdb72b");
        giraffe.paint();

        Card monkey = new Card();
        monkey.setName(locale.getString("cards", "monkey"));
        monkey.setAttack(9);
        monkey.setHealth(60);
        monkey.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/monkey.png")));
        monkey.setColor("#392214");
        monkey.paint();

        Card mouse = new Card();
        mouse.setName(locale.getString("cards", "mouse"));
        mouse.setAttack(6);
        mouse.setHealth(55);
        mouse.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/mouse.png")));
        mouse.setColor("#6f6f6f");
        mouse.paint();

        Card owl = new Card();
        owl.setName(locale.getString("cards", "owl"));
        owl.setAttack(16);
        owl.setHealth(42);
        owl.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/owl.png")));
        owl.setColor("#75554a");
        owl.paint();

        Card panda = new Card();
        panda.setName(locale.getString("cards", "panda"));
        panda.setAttack(10);
        panda.setHealth(60);
        panda.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/panda.png")));
        panda.setColor("#000000");
        panda.paint();

        Card pig = new Card();
        pig.setName(locale.getString("cards", "pig"));
        pig.setAttack(8);
        pig.setHealth(62);
        pig.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/pig.png")));
        pig.setColor("#e68c8c");
        pig.paint();

        Card pufferfish = new Card();
        pufferfish.setName(locale.getString("cards", "pufferfish"));
        pufferfish.setAttack(22);
        pufferfish.setHealth(26);
        pufferfish.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/pufferfish.png")));
        pufferfish.setColor("#7f6669");
        pufferfish.paint();

        Card tiger = new Card();
        tiger.setName(locale.getString("cards", "tiger"));
        tiger.setAttack(24);
        tiger.setHealth(22);
        tiger.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/tiger.png")));
        tiger.setColor("#fdaf2a");
        tiger.paint();

        Card turtle = new Card();
        turtle.setName(locale.getString("cards", "turtle"));
        turtle.setAttack(10);
        turtle.setHealth(59);
        turtle.setImageDefault(ImageIO.read(getClass().getResourceAsStream("/elementum/assets/cards/turtle.png")));
        turtle.setColor("#708a18");
        turtle.paint();

        cards.add(bear);
        cards.add(bird);
        cards.add(fox);
        cards.add(giraffe);
        cards.add(monkey);
        cards.add(mouse);
        cards.add(owl);
        cards.add(panda);
        cards.add(pig);
        cards.add(pufferfish);
        cards.add(tiger);
        cards.add(turtle);
    }
}
