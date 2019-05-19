package Engine;

import Engine.Core.Camera;
import Engine.Core.Window;
import Engine.Core.World;
import Engine.Core.Component;
import Engine.Forms.Rectangle;
import Engine.Forms.WorldLine;
import Engine.Forms.WorldObject;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class EngineController implements EngineThreadCallback
{
    private static EngineController instance = null;
    private EngineThread thread;
    private Window window;
    private World world;
    private Camera camera;
    private ArrayList<Component> components = new ArrayList<Component>();

    public static EngineController getInstance()
    {
        if (instance == null)
        {
            instance = new EngineController();
            instance.thread = new EngineThread(instance);
            instance.window = new Window();
        }
        return instance;
    }

    public void start()
    {
        this.thread.start();
    }

    public World getWorld()
    {
        return this.world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public Camera getCamera()
    {
        return this.camera;
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }

    public Window getWindow()
    {
        return this.window;
    }

    public boolean AddComponent(Component component)
    {
        return this.components.add(component);
    }

    public boolean RemoveComponent(Component component)
    {
        return this.components.remove(component);
    }

    public void tick()
    {
        for (Component component : this.components)
        {
            component.tick();
        }
    }

    public void render()
    {
        try
        {
            Graphics g = this.window.getGraphics();
            BufferStrategy bf = this.window.getBufferStrategy();

            // Aparent trebuie mereu renderat background-ul
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.window.getScreenSize().width, this.window.getScreenSize().height);

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
                        if (Rectangle.collision(this.camera, ((WorldObject) component).toRectangle()))
                        {
                            component.render(g);
                        }
                    }
                    else if (component instanceof WorldLine)
                    {
                        if (Rectangle.intersects( ((WorldLine) component).toLine(), this.camera))
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

            g.dispose();
            bf.show();
        }
        catch (NullPointerException e)
        {
            // Inca nu s-a initializat fereastra, just wait...
        }
    }
}
