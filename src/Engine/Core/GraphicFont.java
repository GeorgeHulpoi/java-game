package Engine.Core;

import java.awt.*;
import java.io.FileInputStream;


public class GraphicFont
{
    private static Font font = null;

    public static Font get()
    {
        if (font == null)
        {
            try
            {
                // Nu-mi vine sa cred ca e asa pixelat..
                font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("./assets/fonts/Bebas-Regular.ttf"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                font = new Font("Arial", Font.PLAIN, 0);
            }

        }

        return font;
    }
}
