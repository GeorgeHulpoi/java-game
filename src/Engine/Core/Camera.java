package Engine.Core;

import Engine.EngineController;
import Engine.Forms.Rectangle;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Camera extends Rectangle
{
    private boolean UP_PRESSED = false;
    private boolean LEFT_PRESSED = false;
    private boolean DOWN_PRESSED = false;
    private boolean RIGHT_PRESSED = false;

    public Camera(int x, int y, int width, int height)
    {
        super(x, y, width, height);

        // Luata de pe net.. exista o problema la focusare si asta e cam singura solutie pentru Key Listener
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            (e) ->
            {
                OnKeyEvent(e);
                return false;
            }
        );
    }

    private void OnKeyEvent(KeyEvent e)
    {
        if (e.getID() == KeyEvent.KEY_PRESSED)
        {
            if ((int) e.getKeyChar() == 119)
            {
                UP_PRESSED = true;
            }
            if ((int) e.getKeyChar() == 97)
            {
                LEFT_PRESSED = true;
            }
            if ((int) e.getKeyChar() == 115)
            {
                DOWN_PRESSED = true;
            }
            if ((int) e.getKeyChar() == 100)
            {
                RIGHT_PRESSED = true;
            }
        }
        else if (e.getID() == KeyEvent.KEY_RELEASED)
        {
            if ((int) e.getKeyChar() == 119)
            {
                UP_PRESSED = false;
            }
            if ((int) e.getKeyChar() == 97)
            {
                LEFT_PRESSED = false;
            }
            if ((int) e.getKeyChar() == 115)
            {
                DOWN_PRESSED = false;
            }
            if ((int) e.getKeyChar() == 100)
            {
                RIGHT_PRESSED = false;
            }
        }
        update();
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