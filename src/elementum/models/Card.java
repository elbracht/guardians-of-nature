package elementum.models;

import elementum.controllers.utils.FontLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Card {
    private String name;
    private int attack;
    private int health;
    private BufferedImage imageDefault;
    private BufferedImage image;
    private Color color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public BufferedImage getImageDefault() {
        return imageDefault;
    }

    public void setImageDefault(BufferedImage imageDefault) {
        this.imageDefault = imageDefault;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = Color.decode(color);
    }

    public void paint() {
        int width = getImageDefault().getWidth();
        int height = getImageDefault().getHeight();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        g2d.drawImage(getImageDefault(), 0, 0, null);
        g2d.setPaint(getColor());
        g2d.setFont(FontLoader.getOpenSans(FontLoader.FontType.BOLD, 60.0f));

        // Draw name
        FontMetrics fm = g2d.getFontMetrics();
        int nameX = (width / 2) - (fm.stringWidth(getName()) / 2);
        int nameY = 330;
        g2d.drawString(getName(), nameX, nameY);

        // Draw attach
        g2d.drawString(String.valueOf(getAttack()), 90, 420);

        // Draw health
        g2d.drawString(String.valueOf(getHealth()), 290, 420);

        g2d.dispose();

        this.image = img;
    }
}
