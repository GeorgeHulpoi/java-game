package Game;

import Engine.Core.Camera;
import Engine.EngineController;
import Engine.Forms.WorldObject;

import java.awt.*;
import java.io.IOException;

public class BRObj extends WorldObject
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

        try {
            img = new Image("./assets/images/bottom_right.png");
        }
        catch (IOException e) {}
    }

    @Override
    public void render(Graphics g)
    {
        Camera cam = EngineController.getInstance().getCamera();

        screen_x = x - cam.getX();
        screen_y = cam.getY() - y;

        g.drawImage(this.img.getBufferedImage(), screen_x, screen_y, width, height, null);
    }
}
