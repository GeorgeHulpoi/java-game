package Game.Menu;

import Engine.Animation.ColorAnimation;
import Engine.Animation.Types.EaseInOut;
import Engine.Core.Component;
import Engine.Core.GraphicFont;
import Engine.EngineController;
import Engine.Forms.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;

public class Button extends Component implements MouseMotionListener, MouseListener
{
    private int x;
    private int y;
    private String value;
    private boolean cursorInside = false;
    private ColorAnimation animation = null;
    private Color def = Color.WHITE;
    private Color hover = Color.GREEN;
    private int delay = 300; // in milisecunde

    public ButtonClickEvent eventListener = null;

    public Button(String value, int width, int height)
    {
        // Initializam datele
        this.width = width;
        this.height = height;
        this.value = value;

        // Adaugam event-urile pentru Mouse (miscare pentru hover pe elemente + click)
        // Insa event-urile astea nu sunt pe elementul grafic, ci pe intreg ecranul
        // Deci o sa verificam coordonatele si de asemenea la click daca cursorul este pe element
        EngineController.getInstance().getWindow().getCanvas().addMouseMotionListener(this);
        EngineController.getInstance().getWindow().getCanvas().addMouseListener(this);
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
    public void render(Graphics g)
    {
        if (isVisible()) {
            // Incarcam font-ul custom
            Font font = GraphicFont.get();
            // Setam marimea
            font = font.deriveFont(Font.PLAIN, 20);

            // Copiat de pe net, pentru centrarea in rectangle
            FontMetrics metrics = g.getFontMetrics(font);
            int _x = x + (width - metrics.stringWidth(value)) / 2;
            int _y = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
            g.setFont(font);


            // Verificam daca exista o animatie activa
            if (animation == null) {
                // Daca nu, verificam doar daca cursor-ul este pe element sau nu si setam culoarea
                if (cursorInside) {
                    g.setColor(hover);
                } else {
                    g.setColor(def);
                }
            } else {
                // Daca animatia exista verificam daca s-a oprit cumva (it can happens)
                if (animation.hasStoped()) {
                    // Stergem animatia si de asemenea punem si culoarea
                    // Daca nu punem culoarea acum o sa fie un frame cu textul fara culoare
                    animation = null;

                    if (cursorInside) {
                        g.setColor(hover);
                    } else {
                        g.setColor(def);
                    }
                } else {
                    // Animatie in desfasurare, luam culoarea din animatie
                    g.setColor(animation.getColor());
                }
            }

            // Chestie luata de pe internet
            // Aparent in mod normal, fontul este foarte pixelat si de calitate slaba.
            // Cu chestia asta calitatea e mult mai buna, nu stiu de ce si nici nu-mi pasa pentru un homework
            Map<?, ?> desktopHints = (Map<?, ?>) Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
            Graphics2D g2d = (Graphics2D) g;
            if (desktopHints != null) {
                g2d.setRenderingHints(desktopHints);
            }
            g2d.drawString(value, _x, _y);
        }
    }

    private void OnMouseEnter()
    {
        // Setam cursor de hover
        EngineController.getInstance().getWindow().getCanvas().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Daca nu exista o animatie, o cream
        if (animation == null)
        {
            animation = new ColorAnimation(def, hover, delay, new EaseInOut());
        }
    }

    private void OnMouseExit()
    {
        // Setam cursorul default
        EngineController.getInstance().getWindow().getCanvas().setCursor(null);

        // Daca animatia nu exista, o cream
        if (animation == null)
        {
            animation = new ColorAnimation(hover, def, delay, new EaseInOut());
        }
        else
        {
            // In situatia in care exista, vedem cat timp a functionat animatia si luam culoarea actuala
            // Oprim animatia curenta si cream alta plecand de la culoarea actuala si cu timpul ramas (un fel de reverse animation)
            long diff = animation.DifferenceTime();
            Color act = animation.getColor();

            animation = new ColorAnimation(act, def, (int) diff, new EaseInOut());
        }
    }

    private void OnClick()
    {
        if (eventListener != null)
        {
            eventListener.OnClick();
        }
    }

    public void mouseMoved(MouseEvent e)
    {
        if (isVisible()) {
            // Verificam daca mouse-ul este pe element
            if (Rectangle.screenInside(toRectangle(), new Point(e.getX(), e.getY()))) {
                // cursorInside este o variabila de control, ca sa nu se apeleze la fiecare miscare OnMouseEnter/OnMouseExit
                if (cursorInside == false) {
                    cursorInside = true;
                    this.OnMouseEnter();
                }
            } else {
                if (cursorInside == true) {
                    cursorInside = false;
                    this.OnMouseExit();
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        if (cursorInside && isVisible())
        {
            this.OnClick();
        }
    }

    // useless
    public void mouseDragged(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }


    private Rectangle toRectangle()
    {
        return new Rectangle(x, y, width, height);
    }
}
