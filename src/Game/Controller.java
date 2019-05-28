package Game;

import Engine.Core.Camera;
import Engine.Core.World;
import Engine.EngineController;
import Engine.Core.Component;
import Engine.Forms.WorldLine;
import Engine.Forms.WorldObject;
import Engine.Forms.Rectangle;
import Exceptions.InvalidSizeException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Controller
{
    // TODO: de schimbat in private
    public boolean loaded = false;
    public boolean paused = false;

    private ArrayList<Component> components = new ArrayList<>();

    public Controller()
    {
        // Luata de pe net.. exista o problema la focusare si asta e cam singura solutie pentru Key Listener
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            (e) ->
            {
                OnKeyEvent(e);
                return false;
            }
        );

        try
        {
            World world = new World(640 * 3, 640 * 3);
            Camera cam = new Camera(-640/2, 640/2, 640, 640);

            EngineController.getInstance().setWorld(world);
            EngineController.getInstance().setCamera(cam);
        }
        catch (InvalidSizeException e)
        {
            System.out.println("width si height divizibile cu 2");
        }

        XAxis x = new XAxis();
        AddComponent(x);
        x.setVisible(true);

        YAxis y = new YAxis();
        AddComponent(y);
        y.setVisible(true);

        BiAxis bi = new BiAxis();
        AddComponent(bi);
        bi.setVisible(true);

        NBiAxis nbi = new NBiAxis();
        AddComponent(nbi);
        nbi.setVisible(true);

        ImgObj img = new ImgObj();
        AddComponent(img);
        img.setVisible(true);

        TLObj tl = new TLObj();
        AddComponent(tl);
        tl.setVisible(true);

        TRObj tr = new TRObj();
        AddComponent(tr);
        tr.setVisible(true);

        BLObj bl = new BLObj();
        AddComponent(bl);
        bl.setVisible(true);

        BRObj br = new BRObj();
        AddComponent(br);
        br.setVisible(true);

        Poly p = new Poly();
        AddComponent(p);
        p.setVisible(true);
    }

    private void OnKeyEvent(KeyEvent e)
    {
        if (loaded)
        {
            if (e.getID() == KeyEvent.KEY_TYPED) {
                if ((int) e.getKeyChar() == 27) {
                    if (paused) {
                        paused = false;
                        EngineController.getInstance().getMenu().hide();
                    } else {
                        paused = true;
                        EngineController.getInstance().getMenu().show();
                    }
                }
            }
        }
    }

    public boolean AddComponent(Component component)
    {
        return this.components.add(component);
    }

    public boolean RemoveComponent(Component component)
    {
        return this.components.remove(component);
    }

    public boolean isLoaded()
    {
        return loaded;
    }

    public void render(Graphics g)
    {
        for (int i = 0; i < this.components.size(); ++i)
        {
            Component component = this.components.get(i);

            if (component.isVisible())
            {
                if (component instanceof WorldObject)
                {
                    // Plec de la ideea ca orice obiect in joc care are o coordonata X,Y trebuie sa fie un dreptunghi
                    // Asta face mai usoara rendarea doar cand e nevoie, oricat de complicat e obiectul respectiv, el poate sa fie
                    // reprezentat de un dreptunghi pana la urma
                    if (Rectangle.collision(EngineController.getInstance().getCamera(), ((WorldObject) component).toRectangle()))
                    {
                        component.render(g);
                    }
                }
                else if (component instanceof WorldLine)
                {
                    if (Rectangle.intersects( ((WorldLine) component).toLine(), EngineController.getInstance().getCamera()))
                    {
                        component.render(g);
                    }
                }
                else if (component instanceof Component)
                {
                    component.render(g);
                }
            }
        }
    }

    public void tick()
    {
        for (int i = 0; i < this.components.size(); ++i)
        {
            Component component = this.components.get(i);
            component.tick();
        }
    }
}