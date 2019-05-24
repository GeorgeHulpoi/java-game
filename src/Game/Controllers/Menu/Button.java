package Game.Controllers.Menu;

import Engine.Core.Component;

import java.awt.*;

public class Button extends Component
{
    private int x;
    private int y;
    private String value;

    public Button(String value, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.value = value;
    }

    public void setX(int value)
    {
        this.x = value;
    }

    public void setY(int value)
    {
        this.y = value;
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(new Color(255,255, 255, 255));
        g.fillRect(x, y, width, height);
    }
}
