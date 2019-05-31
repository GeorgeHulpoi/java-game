package Engine;

import Engine.Core.Camera;
import Engine.Core.Window;
import Engine.Core.World;

import java.awt.*;
import java.awt.image.BufferStrategy;
public class EngineController implements EngineThreadCallback
{
    private static EngineController instance = null;
    private EngineThread thread;
    private Window window = null;
    private World world;
    private Camera camera;
    private Game.Menu.Controller menu;
    private Game.Controller game;

    public static EngineController getInstance()
    {
        if (instance == null)
        {
            instance = new EngineController();
            instance.thread = new EngineThread(instance);
            instance.window = new Window();
            instance.menu = new Game.Menu.Controller();
            instance.game = new Game.Controller();
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

    public Game.Menu.Controller getMenu()
    {
        return this.menu;
    }

    public Game.Controller getGame() { return this.game; }

    public void tick()
    {
        if (this.game.isLoaded())
        {
            if (this.game.paused)
            {
                this.menu.tick();
            }
            else
            {
                this.game.tick();
            }
        }
        else
        {
            this.menu.tick();
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

            if (this.game.isLoaded())
            {
                if (this.game.paused)
                {
                    this.menu.render(g);
                }
                else
                {
                    this.game.render(g);
                }
            }
            else
            {
                this.menu.render(g);
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
