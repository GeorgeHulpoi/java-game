import Engine.Core.Camera;
import Engine.EngineController;
import Engine.Forms.WorldObject;

import java.awt.*;

public class TLObj extends WorldObject
{
    private int screen_x;
    private int screen_y;
    private Image img;

    public TLObj()
    {
        x = -640*3/2 + 15;
        y = 640*3/2 - 15;
        width = 148;
        height = 26;

        img = new Image("./assets/images/top_left.png");
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
