package Engine;

import Engine.Forms.Rectangle;
import Engine.Game.Component;
import Engine.Game.LoopThread;
import Engine.Game.LoopThreadCallbacks;
import Engine.Game.Object;
import Engine.Game.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Controller implements LoopThreadCallbacks
{
    private boolean paused = false;
    private LoopThread thread;
    private ArrayList<Component> components = new ArrayList<Component>();
    private Camera camera;
    private World world;

    private static Controller instance = null;

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new Controller();
        }
        return instance;
    }

    private Controller()
    {

    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public World getWorld()
    {
        return this.world;
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }

    public Camera getCamera()
    {
        return this.camera;
    }

    public void init()
    {
        // Fortam fereastra sa se initializeze
        Window.getInstance();
        // Pornim thread-ul
        this.thread = new LoopThread(this);
        this.thread.start();
    }

    public boolean AddComponent(Component component)
    {
        return this.components.add(component);
    }

    public boolean RemoveComponent(Component component)
    {
        return this.components.remove(component);
    }

    public void render()
    {
        try
        {
            Graphics g = Window.getInstance().getGraphics();
            BufferStrategy bf = Window.getInstance().getBufferStrategy();

            // Aparent trebuie mereu renderat background-ul
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Window.getInstance().getScreenSize().width, Window.getInstance().getScreenSize().height);

            // TO FIX ConcurrentModificationException

            for (int i = 0; i < this.components.size(); ++i)
            {
                Component component = this.components.get(i);

                if (component.isVisible())
                {
                    if (component.isObject())
                    {
                        if (Rectangle.collision(this.camera, ((Object) component).toRectangle())) {
                            component.render(g);
                        }
                    }
                    else {
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

    public void tick()
    {
        for (Component component : this.components)
        {
            component.tick();
        }
    }

    public void pause()
    {
        this.paused = true;
    }

    public void resume()
    {
        this.paused = false;
    }

    public boolean isPaused()
    {
        return this.paused;
    }
}
