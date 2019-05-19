package Engine.Forms;

import java.awt.*;

public class Line
{
    public Point p1;
    public Point p2;

    public Line(Point p1, Point p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }

    public static boolean intersects(Line l1, Line l2)
    {
        double denominator = ((l1.p2.getX() - l1.p1.getX()) * (l2.p2.getY() - l2.p1.getY())) - ((l1.p2.getY() - l1.p1.getY()) * (l2.p2.getX() - l2.p1.getX()));
        double numerator1 = ((l1.p1.getY() - l2.p1.getY()) * (l2.p2.getX() - l2.p1.getX())) - ((l1.p1.getX() - l2.p1.getX()) * (l2.p2.getY() - l2.p1.getY()));
        double numerator2 = ((l1.p1.getY() - l2.p1.getY()) * (l1.p2.getX() - l1.p1.getX())) - ((l1.p1.getX() - l2.p1.getX()) * (l1.p2.getY() - l1.p1.getY()));

        if (denominator == 0) return numerator1 == 0 && numerator2 == 0;

        double r = numerator1 / denominator;
        double s = numerator2 / denominator;

        return (r >= 0 && r <= 1) && (s >= 0 && s <= 1);
    }

    public String toString()
    {
        return "Line (" + p1 + ", " + p2 + ")";
    }
}
