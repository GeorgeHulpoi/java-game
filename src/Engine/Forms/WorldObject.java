package Engine.Forms;

import Engine.Core.Component;

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
}
