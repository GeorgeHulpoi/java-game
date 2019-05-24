package Engine.Forms;

import Engine.Core.Camera;
import Engine.Core.Component;

import java.awt.*;

public class WorldObject extends Component
{
    protected int x;
    protected int y;

    public boolean isWorldObject()
    {
        return true;
    }

    public Rectangle toRectangle()
    {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public Point toScreen(Camera cam)
    {
        return new Point(x - cam.getX(), cam.getY() - y);
    }

    public static Point toScreen(Point p, Camera cam)
    {
        return new Point((int) p.getX() - cam.getX(), cam.getY() - (int) p.getY());
    }
}
