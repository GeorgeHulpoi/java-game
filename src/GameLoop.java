public class GameLoop extends Thread
{
    private static GameLoop _instance = new GameLoop();
    static int MAX_FPS = 100;

    public static GameLoop getInstance()
    {
        return GameLoop._instance;
    }

    public void run()
    {
        // 1 secunda e 10^(-9) ns
        // Deci FPS_TIME e timpul unui frame in nanosecunde
        int FPS_TIME = 1000000000 / GameLoop.MAX_FPS;
        long LAST_TIME = System.nanoTime();

        // Pentru debug
        long _time = System.currentTimeMillis();
        int _frames = 0;
        int _ticks = 0;

        while(true)
        {
            // Diferenta de timp de la ultima rendare
            long DIFF_TIME = System.nanoTime() - LAST_TIME;

            // Daca diferenta e mai mare decat timpul unui frame rendam din nou
            if (DIFF_TIME >= FPS_TIME)
            {
                // Actualizam LAST_TIME
                LAST_TIME = System.nanoTime();

                this.render();
                _frames++;
            }

            this.tick();
            _ticks++;


            // Debug

            if (System.currentTimeMillis() - _time > 1000)
            {
                _time = System.currentTimeMillis();
                System.out.println("FPS" + _frames + ", TPS: " + _ticks);
                _frames = 0;
                _ticks = 0;
            }
        }
    }

    // Folosit doar pentru grafica
    private void render()
    {

    }

    // Folosit pentru calcule, logica din spate s.a.m.d
    private void tick()
    {

    }
}
