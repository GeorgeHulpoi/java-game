package Engine.Game;

import Engine.Forms.Rectangle;

public class Object extends Component
{
    protected int x;
    protected int y;

    @Override
    public boolean isObject()
    {
        return true;
    }

    public Rectangle toRectangle()
    {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
}
