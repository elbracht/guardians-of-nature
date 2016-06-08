package elementum.controllers.Utils;

import java.awt.*;
import java.io.InputStream;

public class FontLoader {
    public enum FontType { BOLD, ITALIC, LIGHT, REGULAR }

    public Font getOpenSans(FontType fontType, float size) {
        try {
            String fontName = String.format("/elementum/assets/OpenSans-%s.ttf", Text.capitalize(fontType.toString()));
            InputStream inputStream = getClass().getResourceAsStream(fontName);
            Font font = Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            return font.deriveFont(size);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            // TODO: Catch exception
        }

        return null;
    }
}
