package Game;

import Engine.Core.Camera;
import Engine.EngineController;
import Engine.Forms.WorldObject;

import java.awt.*;
import java.io.IOException;

public class Background extends WorldObject
{
    private Image img;

    public Background()
    {
        try
        {
            img = new Image("./assets/images/game_bg.png");
        }
        catch (IOException e)
        {
            System.exit(-1);
        }

        width = 640;
        height = 640;
    }

    @Override
    public void render(Graphics g)
    {
        if (isVisible())
        {
            Graphics2D g2d = (Graphics2D) g;
            Camera cam = EngineController.getInstance().getCamera();
            int screen_x = x - cam.getX();
            int screen_y = cam.getY() - y;

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(this.img.getBufferedImage(), screen_x, screen_y, width, height, null);
        }
    }
}
