package Game;

import Engine.Core.Camera;
import Engine.EngineController;
import Engine.Forms.WorldObject;

import java.awt.*;
import java.io.IOException;

public class ImgObj extends WorldObject
{
    private int screen_x;
    private int screen_y;
    private Image img;

    public ImgObj()
    {
        width = 184;
        height = 157;
        x = -width/2;
        y = height/2;
        try
        {
            img = new Image("./assets/images/logo.png");
        }
        catch(IOException e)
        {

        }
    }

    @Override
    public void render(Graphics g)
    {
        Camera cam = EngineController.getInstance().getCamera();
        screen_x = x - cam.getX();
        screen_y = cam.getY() - y;

        g.drawImage(this.img.getBufferedImage(), screen_x, screen_y, width, height, null);
    }

    @Override
    public void tick()
    {

    }
}
