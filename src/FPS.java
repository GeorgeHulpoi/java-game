import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class FPS extends GameComponent
{
    private long time = System.currentTimeMillis();
    private int frames = 0;
    private int ticks = 0;
    private int last_fps = 0;
    private int last_tps = 0;

    @Override
    public void tick()
    {
        ticks++;
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("FPS: " + last_fps + ", TPS: " + last_tps, 8, 20);

        if (System.currentTimeMillis() - time > 1000)
        {
            last_fps = frames;
            last_tps = ticks;
            time = System.currentTimeMillis();
            frames = 0;
            ticks = 0;

            return;
        }

        frames++;
    }
}
