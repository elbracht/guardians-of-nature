package elementum.controllers.utils;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public class CursorLoader {
    public Cursor getDefault() {
        Image image = new Image(getClass().getResourceAsStream("/elementum/assets/cursor/default.png"));
        return new ImageCursor(image, 5, 5);
    }

    public Cursor getSelect() {
        Image image = new Image(getClass().getResourceAsStream("/elementum/assets/cursor/select.png"));
        return new ImageCursor(image, 16, 16);
    }

    public Cursor getAttack() {
        Image image = new Image(getClass().getResourceAsStream("/elementum/assets/cursor/attack.png"));
        return new ImageCursor(image, 5, 5);
    }
}
