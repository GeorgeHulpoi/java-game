import Engine.Camera;
import Engine.Controller;
import Engine.Game.Object;

import java.awt.*;

public class ImgObj extends Object
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
        img = new Image("./assets/images/logo.png");
    }

    @Override
    public void render(Graphics g)
    {
        Camera cam = Controller.getInstance().getCamera();
        screen_x = x - cam.getX();
        screen_y = cam.getY() - y;

        g.drawImage(this.img.getBufferedImage(), screen_x, screen_y, width, height, null);
    }

    @Override
    public void tick()
    {

    }
}
