package Game;

import Engine.Core.Camera;
import Engine.Core.Force;
import Engine.Core.Point;
import Engine.EngineController;
import Engine.Forms.WorldObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class Player extends WorldObject  implements MouseMotionListener
{
    private Image img = null;
    private boolean clicked = false;
    private Force gravity;
    private Force impulse;
    private Force direction;
    private int lastMouseX;
    private int lastMouseY;
    final static int mass = 4;
    private long LAST_TIME_MOVED = 0;

    public Player()
    {
        try
        {
            img = new Image("./assets/images/player.png");
        }
        catch(IOException e)
        {
            System.exit(-1);
        }

        width = 80;
        height = 80;

        gravity = new Force(new Point<Double>(0.0, -1.0) , 9.8 * Player.mass);
        impulse = new Force(new Point<Double>(-1.0, 1.0), 100);
        direction = new Force(new Point<Double>(0.0, 1.0), 0);

        EngineController.getInstance().getWindow().getCanvas().addMouseMotionListener(this);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            (e) ->
            {
                OnKeyEvent(e);
                return false;
            }
        );
    }

    @Override
    public void render(Graphics g)
    {
        if (isVisible())
        {
            Graphics2D g2d = (Graphics2D) g;
            Camera cam = EngineController.getInstance().getCamera();
            int screen_x = x - cam.getX();
            int screen_y = cam.getY() - y;

            drawForce(g2d, gravity, Color.BLACK);
            drawForce(g2d, direction, Color.RED);

            Force resultant = Force.resultant(impulse, gravity);
            drawForce(g2d, resultant, Color.BLACK);

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(this.img.getBufferedImage(), screen_x, screen_y, width, height, null);
        }
    }

    @Override
    public void tick()
    {
        updateDirection(lastMouseX, lastMouseY);
        if (clicked)
        {
            long diff = System.currentTimeMillis() - LAST_TIME_MOVED;

            if (diff >= 10)
            {
                LAST_TIME_MOVED = System.currentTimeMillis();
                Force resultant = Force.resultant(impulse, gravity);
                x = x + (int) (4 * Math.cos(resultant.getRadAngle()));
                y = y + (int) (4 * Math.sin(resultant.getRadAngle()));

                double m = impulse.getMagtitude() - 2;

                if (m < 0)
                {
                    m = 0;
                }

                impulse.setMagtitude(m);
                updateDirection(lastMouseX, lastMouseY);
            }


        }
    }

    private void drawForce(Graphics2D g, Force f, Color c)
    {
        Camera cam = EngineController.getInstance().getCamera();

        // Punctul de mijloc
        int x1 = x + width/2;
        int y1 = y - height/2;
        int sx1 = x1 - cam.getX();
        int sy1 = cam.getY() - y1;

        int x2 = x1 + (int) (width * Math.cos(f.getRadAngle()));
        int y2 = y1 + (int) (width * Math.sin(f.getRadAngle()));

        int sx2 = x2 - cam.getX();
        int sy2 = cam.getY() - y2;

        g.setColor(c);
        g.drawLine(sx1, sy1, sx2, sy2);

        // Adaugam sagetile la capat

        double angle = f.getDegreeAngle() + 8.0;
        angle = Math.toRadians(angle);

        x2 = x1 + (int) ((width - 10) * Math.cos(angle));
        y2 = y1 + (int) ((width - 10) * Math.sin(angle));

        sx1 = x2 - cam.getX();
        sy1 = cam.getY() - y2;

        g.setColor(c);
        g.drawLine(sx1, sy1, sx2, sy2);

        angle = f.getDegreeAngle() - 8.0;
        angle = Math.toRadians(angle);

        x2 = x1 + (int) ((width - 10) * Math.cos(angle));
        y2 = y1 + (int) ((width - 10) * Math.sin(angle));

        sx1 = x2 - cam.getX();
        sy1 = cam.getY() - y2;

        g.setColor(c);
        g.drawLine(sx1, sy1, sx2, sy2);
    }

    public void mouseMoved(MouseEvent e)
    {
        if (isVisible())
        {
            lastMouseX = e.getX();
            lastMouseY = e.getY();
        }
    }

    private void updateDirection(int mouse_x, int mouse_y)
    {
        Camera cam = EngineController.getInstance().getCamera();
        // centrul pe ecran
        int center_x = x - cam.getX() + width/2;
        int center_y = cam.getY() - y + height/2;

        // considerand center ca (0,0)
        int _x = mouse_x - center_x;
        int _y = center_y - mouse_y;

        // unghiul
        double rad = Math.atan2(_y, _x);
        double deg = rad * 180 / Math.PI;

        if (deg >= 0 && deg <= 180)
        {
            // directia normalizata
            double __x = Math.cos(rad);
            double __y = Math.sin(rad);

            direction.setDirection(new Point<Double>(__x, __y));
        }
    }

    private void OnKeyEvent(KeyEvent e)
    {
        clicked = true;
        if (e.getID() == KeyEvent.KEY_TYPED && e.getID() != KeyEvent.KEY_PRESSED)
        {
            if ((int) e.getKeyChar() == 32)
            {
                impulse.setDirection(direction.getDirection());
                impulse.setMagtitude(120);
            }
        }
    }

    // useless
    public void mouseDragged(MouseEvent e) { }
}
