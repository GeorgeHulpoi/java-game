package Engine;
import Engine.Forms.Rectangle;
import Exceptions.InvalidSizeException;

/*
 * Se face o mapa de marime (width, height) cu un punct (0,0) centrat.
 */
public class World
{
    private int height;
    private int width;

    public World(int width, int height) throws InvalidSizeException
    {
        if (width % 2 != 0 || height % 2 != 0)
        {
            throw new InvalidSizeException();
        }

        this.height = height;
        this.width = width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getWidth()
    {
        return this.width;
    }

    public Rectangle toRectangle()
    {
        return new Rectangle(-this.width/2, this.height/2, this.width, this.height);
    }

    /*
     * Pentru a ma asigura ca elementele nu sunt in exterior
     */
    public boolean isRectInside(int x, int y, int w, int h)
    {
        // Astea sunt 2 sunt coordonatele punctului din stanga-sus a harti
        int x1 = -this.width/2;
        int y1 = this.height/2;

        // AStea 2 sunt coordonatele punctului din dreapta-jos a harti
        int _x1 = x1 + width;
        int _y1 = y1 - height;

        // Cordonatele din stanga-sus a dreptunghiului
        int x2 = x;
        int y2 = y;

        // Cordonatele din dreapta-jos a dreptunghiului
        int _x2 = x2 + w;
        int _y2 = y2 - h;

        return (x2 >= x1 && y2 <= y1 && _x2 <= _x1 && _y2 >= _y1);
    }
}