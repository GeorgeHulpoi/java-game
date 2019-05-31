package Engine.Core;

public class Point<T>
{
    private T x;
    private T y;

    public Point(T x, T y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(T value)
    {
        x = value;
    }

    public void setY(T value)
    {
        y = value;
    }

    public T getX()
    {
        return x;
    }

    public T getY()
    {
        return y;
    }
}
