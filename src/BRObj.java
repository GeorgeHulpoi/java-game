import Engine.Camera;
import Engine.Controller;
import Engine.Game.Object;

import java.awt.*;

public class BRObj extends Object
{
    private int screen_x;
    private int screen_y;
    private Image img;

    public BRObj()
    {
        width = 246;
        height = 26;

        x = 640*3/2 - 15 - width;
        y = -640*3/2 + 15 + height;

        img = new Image("./assets/images/bottom_right.png");
    }

    @Override
    public void render(Graphics g)
    {
        Camera cam = Controller.getInstance().getCamera();

        screen_x = x - cam.getX();
        screen_y = cam.getY() - y;

        g.drawImage(this.img.getBufferedImage(), screen_x, screen_y, width, height, null);
    }
}
