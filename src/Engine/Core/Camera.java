package Engine.Core;

import Engine.EngineController;
import Engine.Forms.Rectangle;
import Engine.Core.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera extends Rectangle implements KeyListener
{
    private boolean UP_PRESSED = false;
    private boolean LEFT_PRESSED = false;
    private boolean DOWN_PRESSED = false;
    private boolean RIGHT_PRESSED = false;

    public Camera(int x, int y, int width, int height)
    {
        super(x, y, width, height);

        EngineController.getInstance().getWindow().addKeyListener(this);
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        // arrow up
        if (keyCode == 38)
        {
            UP_PRESSED = true;
        }
        // arrow left
        else if (keyCode == 37)
        {
            LEFT_PRESSED = true;
        }
        // arrow down
        else if (keyCode == 40)
        {
            DOWN_PRESSED = true;
        }
        // arrow right
        else if (keyCode == 39)
        {
            RIGHT_PRESSED = true;
        }

        update();
    }

    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        // arrow up
        if (keyCode == 38)
        {
            UP_PRESSED = false;
        }
        // arrow left
        else if (keyCode == 37)
        {
            LEFT_PRESSED = false;
        }
        // arrow down
        else if (keyCode == 40)
        {
            DOWN_PRESSED = false;
        }
        // arrow right
        else if (keyCode == 39)
        {
            RIGHT_PRESSED = false;
        }
    }

    public void update()
    {
        int tx = x;
        int ty = y;

        if (UP_PRESSED)
        {
            ty += 20;
        }

        if (LEFT_PRESSED)
        {
            tx -= 20;
        }

        if (RIGHT_PRESSED)
        {
            tx += 20;
        }

        if (DOWN_PRESSED)
        {
            ty -= 20;
        }

        if (Rectangle.inside(EngineController.getInstance().getWorld().toRectangle(), new Rectangle(tx, ty, this.width, this.height)))
        {
            x = tx;
            y = ty;
        }
    }
}