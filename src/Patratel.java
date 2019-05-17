import java.awt.*;
import Engine.Camera;

public class Patratel //extends GameObject
{
    /*private Image img;
    public int screen_x;
    public int screen_y;
    private Camera cam;

    public Patratel(Camera cam)
    {
        this.cam = cam;
        img = new Image("D:\\poze\\as.png");
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.GRAY);
        g.drawLine(320, 0, 320, 640);
        g.drawLine(0, 320, 640, 320);

        g.drawImage(this.img.getBufferedImage(), screen_x, screen_y, 100, 64, null);

        g.setColor(Color.WHITE);
        g.drawRect(screen_x, screen_y, 100, 64);
    }

    public void tick()
    {
        if (cam.seeRect(x, y, 100,64))
        {
            screen_x = x - cam.getX();
            screen_y = cam.getY() - y;
            setVisible(true);
        }
        else
        {
            setVisible(false);
        }
    }*/
}
