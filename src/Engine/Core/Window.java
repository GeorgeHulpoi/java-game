package Engine.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame
{
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics g;

    public Window()
    {
        // Initializam JFrame-ul
        Dimension screen = this.getScreenSize();

        // Unde e cu + cred ca is elementele de la Windows GUI
        // Daca e asa, cred ca trebuie facute 2 variante, pentru Ubuntu si Windows 10 avand in vedere ca pe astea o sa prezint
        this.setMinimumSize(new Dimension(640 + 4, 640 + 28));
        this.setMaximumSize(new Dimension(640 + 4, 640 + 28));
        this.setPreferredSize(new Dimension(640 + 4, 640 + 28));
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

    public Canvas getCanvas()
    {
        return this.canvas;
    }
}
