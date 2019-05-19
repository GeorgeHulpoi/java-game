import Engine.Core.Camera;
import Engine.EngineController;
import Engine.Forms.WorldObject;

import java.awt.*;

public class BLObj extends WorldObject
{
    private int screen_x;
    private int screen_y;
    private Image img;

    public BLObj()
    {
        width = 222;
        height = 26;

        x = -640*3/2 + 15;
        y = -640*3/2 + 15 + height;

        img = new Image("./assets/images/bottom_left.png");
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
