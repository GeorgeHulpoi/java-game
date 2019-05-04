import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class GameWindow extends JFrame
{
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics g;

    private static GameWindow _instance = new GameWindow();

    public static GameWindow getInstance()
    {
        return GameWindow._instance;
    }

    public GameWindow()
    {
        // Initializam JFrame-ul
        Dimension screen = this.getScreenSize();

        this.setMinimumSize(screen);
        this.setMaximumSize(screen);
        this.setPreferredSize(screen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        /* The pack method sizes the frame so that all its contents are at or above their preferred sizes.
         * An alternative to pack is to establish a frame size explicitly by calling setSize or setBounds
         * (which also sets the frame location). In general, using pack is preferable to calling setSize,
         * since pack leaves the frame layout manager in charge of the frame size, and layout managers
         * are good at adjusting to platform dependencies and other factors that affect component size.
         */
        this.pack();
        this.CreateCanvas();
        // Visible trebuie sa fie ultimul
        this.setVisible(true);
    }

    private void CreateCanvas()
    {
        // Cream Canvas-ul
        Dimension screen = this.getScreenSize();
        this.canvas = new Canvas();
        this.canvas.setMinimumSize(screen);
        this.canvas.setMaximumSize(screen);
        this.canvas.setPreferredSize(screen);

        this.add(this.canvas, BorderLayout.CENTER);
        this.canvas.createBufferStrategy(3);

        GameLoop.getInstance().start();
    }

    public Dimension getScreenSize()
    {
        //return Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(640, 640);
    }

    public BufferStrategy getBufferStrategy()
    {
        return this.canvas.getBufferStrategy();
    }

    public Graphics getGraphics() throws NullPointerException
    {
        return this.getBufferStrategy().getDrawGraphics();
    }
}
