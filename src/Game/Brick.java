package Game;

import Engine.Core.Camera;
import Engine.EngineController;
import Engine.Forms.WorldObject;

import java.awt.*;
import java.io.IOException;

public class Brick extends WorldObject
{
    private Image img = null;

    public Brick()
    {
        width = 80;
        height = 80;

        try
        {
            img = new Image("./assets/images/brick.png");
        }
        catch (IOException e)
        {
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics g)
    {
        Camera cam = EngineController.getInstance().getCamera();
        int screen_x = x - cam.getX();
        int screen_y = cam.getY() - y;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(this.img.getBufferedImage(), screen_x, screen_y, width, height, null);
    }
}
