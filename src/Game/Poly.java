package Game;

import Engine.EngineController;
import Engine.Forms.WorldObject;
import Engine.Forms.WorldPolygon;

import java.awt.*;

public class Poly extends WorldPolygon
{
    public Poly()
    {
        int x[] = {-50, 0, 50};
        int y[] = {-128, -64, -128};

        try
        {
            this.set(x, y, 3);
        }
        catch(Exception e) {}
    }

    @Override
    public void render(Graphics g)
    {
        int[] sx = new int[this.n];
        int[] sy = new int[this.n];

        for (int i = 0; i < this.n; ++i)
        {
            Point p = WorldObject.toScreen(new Point(this.points_x[i], this.points_y[i]), EngineController.getInstance().getCamera());

            sx[i] = (int) p.getX();
            sy[i] = (int) p.getY();
        }

        g.setColor(Color.BLUE);
        g.drawPolygon(sx, sy, this.n);

        g.setColor(Color.GRAY);
        Point s = this.toScreen(EngineController.getInstance().getCamera());
        g.drawRect((int)  s.getX(), (int) s.getY(), this.width, this.height);
    }

}
