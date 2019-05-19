package Engine.Forms;

import java.awt.*;

public class Rectangle
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Rectangle(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public static boolean collision(Rectangle r1, Rectangle r2)
    {
        int x1 = r1.getX();
        int y1 = r1.getY();

        int _x1 = x1 + r1.getWidth();
        int _y1 = y1 - r1.getHeight();

        int x2 = r2.getX();
        int y2 = r2.getY();

        int _x2 = x2 + r2.getWidth();
        int _y2 = y2 - r2.getHeight();

        return (_x2 > x1 && _y2 < y1 && x2 < _x1 && y2 > _y1);
    }

    // Este r2 in interiorul lui r1
    public static boolean inside(Rectangle r1, Rectangle r2)
    {
        int x1 = r1.getX();
        int y1 = r1.getY();

        int _x1 = x1 + r1.getWidth();
        int _y1 = y1 - r1.getHeight();

        int x2 = r2.getX();
        int y2 = r2.getY();

        int _x2 = x2 + r2.getWidth();
        int _y2 = y2 - r2.getHeight();

        return (x2 >= x1 && y2 <= y1 && _x2 <= _x1 && _y2 >= _y1);
    }

    public static boolean inside(Rectangle r, Point p)
    {
        int x1 = r.getX();
        int y1 = r.getY();

        int _x1 = x1 + r.getWidth();
        int _y1 = y1 - r.getHeight();

        return (p.getX() >= x1 && p.getX() <= _x1 && p.getY() <= y1 && p.getY() >= _y1);
    }

    public static boolean intersects(Line l, Rectangle r)
    {
        Line up = new Line(new Point(r.getX(), r.getY()), new Point(r.getX() + r.getWidth(), r.getY()));
        if (Line.intersects(up, l))
        {
            return true;
        }

        Line left = new Line(new Point(r.getX(), r.getY()), new Point(r.getX(), r.getY() - r.getHeight()));
        if (Line.intersects(left, l))
        {
            return true;
        }

        Line bottom = new Line(new Point(r.getX(), r.getY() - r.getHeight()), new Point(r.getX() + r.getWidth(), r.getY() - r.getHeight()));
        if (Line.intersects(bottom, l))
        {
            return true;
        }

        Line right = new Line(new Point(r.getX() + r.getWidth(),r.getY()), new Point(r.getX() + r.getWidth(), r.getY() - r.getHeight()));
        if (Line.intersects(right, l))
        {
            return true;
        }

        return false;
    }

    @Override
    public String toString()
    {
        return "Rectangle(x = " + this.x + ", y = " + this.y + ", width = " + this.width + ", height = " + this.height + ")";
    }
}
