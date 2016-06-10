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

            Card bird = new Card();
            bird.setName("Vogel");
            bird.setAttack(10);
            bird.setHealth(10);
            bird.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/bird.png")));
            bird.setColor("#d3811f");
            bird.paint();

            Card fox = new Card();
            fox.setName("Fuchs");
            fox.setAttack(10);
            fox.setHealth(10);
            fox.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/fox.png")));
            fox.setColor("#e5751f");
            fox.paint();

            Card giraffe = new Card();
            giraffe.setName("Giraffe");
            giraffe.setAttack(10);
            giraffe.setHealth(10);
            giraffe.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/giraffe.png")));
            giraffe.setColor("#fdb72b");
            giraffe.paint();

            Card monkey = new Card();
            monkey.setName("Affe");
            monkey.setAttack(10);
            monkey.setHealth(10);
            monkey.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/monkey.png")));
            monkey.setColor("#392214");
            monkey.paint();

            Card mouse = new Card();
            mouse.setName("Maus");
            mouse.setAttack(10);
            mouse.setHealth(10);
            mouse.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/mouse.png")));
            mouse.setColor("#6f6f6f");
            mouse.paint();

            Card owl = new Card();
            owl.setName("Eule");
            owl.setAttack(10);
            owl.setHealth(10);
            owl.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/owl.png")));
            owl.setColor("#75554a");
            owl.paint();

            Card panda = new Card();
            panda.setName("Panda");
            panda.setAttack(10);
            panda.setHealth(10);
            panda.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/panda.png")));
            panda.setColor("#000000");
            panda.paint();

            Card pig = new Card();
            pig.setName("Schwein");
            pig.setAttack(10);
            pig.setHealth(10);
            pig.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/pig.png")));
            pig.setColor("#e68c8c");
            pig.paint();

            Card pufferfish = new Card();
            pufferfish.setName("Kugelfisch");
            pufferfish.setAttack(10);
            pufferfish.setHealth(10);
            pufferfish.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/pufferfish.png")));
            pufferfish.setColor("#7f6669");
            pufferfish.paint();

            Card tiger = new Card();
            tiger.setName("Tiger");
            tiger.setAttack(10);
            tiger.setHealth(10);
            tiger.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/tiger.png")));
            tiger.setColor("#fdaf2a");
            tiger.paint();

            Card turtle = new Card();
            turtle.setName("Schildkröte");
            turtle.setAttack(10);
            turtle.setHealth(10);
            turtle.setImageDefault(ImageIO.read(Cards.class.getResourceAsStream("/elementum/assets/cards/turtle.png")));
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
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return cards;
    }
}
