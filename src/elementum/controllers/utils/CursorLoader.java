package elementum.controllers.utils;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

/**
 * @author Alexander Elbracht
 *
 * This class load different images as cursor.
 */
public class CursorLoader {
    public static Cursor getDefault() {
        Image image = new Image(CursorLoader.class.getResourceAsStream("/elementum/assets/cursor/default.png"));
        return new ImageCursor(image, 5, 5);
    }

    public static Cursor getSelect() {
        Image image = new Image(CursorLoader.class.getResourceAsStream("/elementum/assets/cursor/select.png"));
        return new ImageCursor(image, 16, 16);
    }

    public static Cursor getAttack() {
        Image image = new Image(CursorLoader.class.getResourceAsStream("/elementum/assets/cursor/attack.png"));
        return new ImageCursor(image, 5, 5);
    }
}
