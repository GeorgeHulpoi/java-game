import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Main
{
    public static void main(String[] args)
    {
        GameWindow.getInstance().setTitle("Java game");

        GameComponent a = new Patratel();
        a.setVisible(true);
        GameComponent b = new FPS();
        b.setVisible(true);
    }
}
