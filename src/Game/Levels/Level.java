package Game.Levels;

import Engine.Core.Camera;
import Engine.Core.World;
import java.awt.*;

public abstract class Level
{
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract World getWorld();
    public abstract Camera getCamera();
}
