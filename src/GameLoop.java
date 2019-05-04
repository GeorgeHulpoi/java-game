import java.awt.*; // sper ca compilatorul selecteaza doar ce se foloseste de aici
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GameLoop extends Thread
{
    private ArrayList<GameComponent> listeners = new ArrayList<GameComponent>();
    private static GameLoop _instance = new GameLoop();
    private boolean running = false;
    public static int MAX_FPS = 100;

    public static GameLoop getInstance()
    {
        return GameLoop._instance;
    }

    public boolean AddListener(GameComponent component)
    {
        return this.listeners.add(component);
    }

    public void run()
    {
        // 1 secunda e 10^(-9) ns
        // Deci FPS_TIME e timpul unui frame in nanosecunde
        int FPS_TIME = 1000000000 / GameLoop.MAX_FPS;
        long LAST_TIME = System.nanoTime();

        this.running = true;

        while(this.running)
        {
            // Diferenta de timp de la ultima rendare
            long DIFF_TIME = System.nanoTime() - LAST_TIME;
            this.tick();

            // Daca diferenta e mai mare decat timpul unui frame rendam din nou
            if (DIFF_TIME >= FPS_TIME)
            {
                // Actualizam LAST_TIME
                LAST_TIME = System.nanoTime();

                this.render();
            }

            try
            {
                this.sleep(1);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void finish()
    {
        this.running = false;
    }

    // Folosit doar pentru grafica
    private void render()
    {
        try
        {
            Graphics g = GameWindow.getInstance().getGraphics();
            BufferStrategy bf = GameWindow.getInstance().getBufferStrategy();

            // Aparent trebuie mereu renderat background-ul
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GameWindow.getInstance().getScreenSize().width, GameWindow.getInstance().getScreenSize().height);

            for (GameComponent component : this.listeners)
            {
                if (component.isVisible())
                {
                    component.render(g);
                }
            }

            g.dispose();
            bf.show();
        }
        catch(NullPointerException e)
        {
            // Se ajunge aici pentru ca nu s-a initializat inca GameWindow-ul complet, TODO fix it
            e.printStackTrace();
        }
    }

    // Folosit pentru calcule, logica din spate s.a.m.d
    private void tick()
    {
        for (GameComponent component : this.listeners)
        {
            component.tick();
        }
    }
}
