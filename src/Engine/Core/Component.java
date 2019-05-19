package Engine.Core;

import Engine.EngineController;
import java.awt.Graphics;

public class Component
{
    protected int width;
    protected int height;
    private boolean visible = false;

    public void setVisible(boolean value)
    {
        this.visible = value;
    }

    public boolean isVisible()
    {
        return this.visible;
    }

    public Component()
    {
        EngineController.getInstance().AddComponent(this);
    }

    public void destroy()
    {
        EngineController.getInstance().RemoveComponent(this);
    }

    public boolean isWorldObject()
    {
        return false;
    }
    public boolean isWorldLine()
    {
        return false;
    }

    public void render(Graphics g)
    {

    }

    public void tick()
    {

    }
}