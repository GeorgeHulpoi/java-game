package Engine.Game;

import Engine.Controller;

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
        Controller.getInstance().AddComponent(this);
    }

    public boolean isObject()
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