import java.awt.Graphics;
import java.awt.Color;

public class Patratel extends GameComponent
{
    private double x = 0;

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        x += 0.05;
        if ((int)(Math.sin(x) * 100) == 0)
        {
            x = 0;
        }
        g.fillRect(100 + (int)(Math.sin(x) * 100) , 100, 32, 32);
    }
}
