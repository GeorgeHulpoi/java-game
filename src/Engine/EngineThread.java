package Engine;

public class EngineThread extends Thread
{
    private EngineThreadCallback callback;
    private boolean running = false;
    public static int MAX_FPS = 108;

    public EngineThread(EngineThreadCallback callback)
    {
        super();
        // Practic n-are rost sa adaug listeners, oricum nu o sa apelez funcitile in alta parte inafara de controller-ul Engine-ului
        this.callback = callback;
    }

    public void run()
    {
        // 1 secunda e 10^(-9) ns
        // Deci FPS_TIME e timpul unui frame in nanosecunde
        int FPS_TIME = 1000000000 / EngineThread.MAX_FPS;
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
        this.callback.render();
    }

    // Folosit pentru calcule, logica din spate s.a.m.d
    private void tick()
    {
        this.callback.tick();
    }
}