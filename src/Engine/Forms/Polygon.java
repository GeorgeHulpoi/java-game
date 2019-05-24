package Engine.Forms;

import java.util.ArrayList;

public class Polygon
{
    int n; // numarul de puncte
    int[] x; // coordonatele x
    int[] y; // coordonatele y

    public Polygon(int[] x, int[] y, int n)
    {
        this.set(x, y, n);
    }

    public void set(int[] x, int[] y, int n)
    {
        this.x = x;
        this.y = y;
        this.n = n;
    }

    // Iti returneaza un Rectangle incadrat perfect in polygon
    public Rectangle bounds()
    {
        int min_x = x[0];
        int max_x = x[0];

        int min_y = y[0];
        int max_y = y[0];

        final int len = x.length;

        for (int i = 1; i < len; ++i)
        {
            if (x[i] < min_x)
            {
                min_x = x[i];
            }
            else if (x[i] > max_x)
            {
                max_x = x[i];
            }

            if (y[i] < min_y)
            {
                min_y = y[i];
            }
            else if (y[i] > max_y)
            {
                max_y = y[i];
            }
        }

        // Acum min_x este pivotul x
        // Iar max_y este pivotul y

        int width = max_x - min_x;
        int height = max_y - min_y;

        return new Rectangle(min_x, max_y, width, height);
    }
}
