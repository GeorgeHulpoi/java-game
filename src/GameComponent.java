import java.awt.Graphics;

public class GameComponent
{
    private boolean visible = false;

    public GameComponent()
    {
        GameLoop.getInstance().AddListener(this);
    }

    public void render(Graphics g)
    {

    }

    public void tick()
    {

    }

    public void setVisible(boolean value)
    {
        this.visible = value;
    }

    public boolean isVisible()
    {
        return this.visible;
    }
}