package Engine.Forms;
import Engine.Core.Component;
import Engine.EngineController;

import java.awt.*;
import java.awt.geom.Line2D;

public class WorldLine extends Component
{
    protected Point p1;
    protected Point p2;

    public boolean isWorldLine()
    {
        return true;
    }

    public Line toLine()
    {
        return new Line(p1, p2);
    }
}

