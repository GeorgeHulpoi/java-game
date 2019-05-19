package Engine.Core;

import Engine.Forms.Rectangle;
import Exceptions.InvalidSizeException;

import java.awt.*;

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

    public Dimension getDimension()
    {
        return new Dimension(this.width, this.height);
    }

    public Rectangle toRectangle()
    {
        return new Rectangle(-this.width/2, this.height/2, this.width, this.height);
    }
}