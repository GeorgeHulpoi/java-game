package Engine.Forms;

import Exceptions.InvalidPolygon;

public class WorldPolygon extends WorldObject
{
    public int n = 0; // numarul de puncte
    public int[] points_x; // coordonatele x a polygonului. A nu se confunda cu pozitia x
    public int[] points_y; // coordonatele y

    public void set(int[] x, int[] y, int n) throws InvalidPolygon
    {
        if (x.length != y.length && n != x.length && n == 0)
        {
            throw new InvalidPolygon();
        }

        this.points_x = x;
        this.points_y = y;
        this.n = n;

        Rectangle rect = this.bounds();

        this.x = rect.getX();
        this.y = rect.getY();
        this.width = rect.getWidth();
        this.height = rect.getHeight();
    }

    // Iti returneaza un Rectangle incadrat perfect in poligon
    public Rectangle bounds()
    {
        int min_x = points_x[0];
        int max_x = points_x[0];

        int min_y = points_y[0];
        int max_y = points_y[0];

        for (int i = 1; i < n; ++i)
        {
            if (points_x[i] < min_x)
            {
                min_x = points_x[i];
            }
            else if (points_x[i] > max_x)
            {
                max_x = points_x[i];
            }

            if (points_y[i] < min_y)
            {
                min_y = points_y[i];
            }
            else if (points_y[i] > max_y)
            {
                max_y = points_y[i];
            }
        }

        // Acum min_x este pivotul x
        // Iar max_y este pivotul y

        int width = max_x - min_x;
        int height = max_y - min_y;

        return new Rectangle(min_x, max_y, width, height);
    }
}
