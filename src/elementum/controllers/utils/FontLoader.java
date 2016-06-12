package elementum.controllers.utils;

import java.awt.*;
import java.io.InputStream;

/**
 * @author Alexander Elbracht
 *
 * This class load different fonts with font type.
 */
public class FontLoader {
    public enum FontType { BOLD, ITALIC, LIGHT, REGULAR }

    public static Font getOpenSans(FontType fontType, float size) throws Exception {
        String fontName = String.format("/elementum/assets/OpenSans-%s.ttf", Text.capitalize(fontType.toString()));
        InputStream inputStream = FontLoader.class.getResourceAsStream(fontName);
        Font font = Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
        return font.deriveFont(size);
    }
}
