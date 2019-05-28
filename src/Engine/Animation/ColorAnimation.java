package Engine.Animation;

import java.awt.*;

public class ColorAnimation extends Animation
{
    private Color start;
    private Color finish;
    private Color act;
    private long start_time;

    private boolean started = false;
    private boolean stoped = false;

    public ColorAnimation(Color start, Color finish, int delay, Type type)
    {
        super(delay, type);
        this.start = start;
        this.finish = finish;
        start_time = System.currentTimeMillis();

        this.start();
    }

    void start()
    {
        started = true;
    }

    public long DifferenceTime()
    {
        return System.currentTimeMillis() - start_time;
    }

    public Color getColor()
    {
        // Vedem diferenta de timp dintre inceput si prezent
        long diff = DifferenceTime();
        double diff_delta = (double) diff / delay;
        // daca delta este mai mare sau egala cu 1, atunci animatia s-a terminat
        // Daca delta este egala cu 0, atunci de-abia acum incepe
        // Daca este intre 0 si 1, atunci este in desfasurare

        if (diff_delta >= 1)
        {
            stoped = true;
            return finish;
        }
        else if (diff_delta == 0)
        {
            return start;
        }
        else if (diff_delta > 0 && diff_delta < 1)
        {
            int R = finish.getRed() - start.getRed();
            int G = finish.getGreen() - start.getGreen();
            int B = finish.getBlue() - start.getBlue();
            int A = finish.getAlpha() - start.getAlpha();

            double t = type.formula(diff_delta);
            R = start.getRed() + (int) (t * R);
            G = start.getGreen() + (int) (t * G);
            B = start.getBlue() + (int) (t * B);
            A = start.getAlpha() + (int) (t * A);

            if (R < 0)
            {
                R = 0;
            }
            else if (R > 255)
            {
                R = 255;
            }

            if (G < 0)
            {
                G = 0;
            }
            else if (G > 255)
            {
                G = 255;
            }

            if (B < 0)
            {
                B = 0;
            }
            else if (B > 255)
            {
                B = 255;
            }

            if (A < 0)
            {
                A = 0;
            }
            else if (A > 255)
            {
                A = 255;
            }

            return new Color(R, G, B, A);
        }
        else
        {
            return null;
        }
    }

    public boolean hasStarted()
    {
        return started;
    }

    public boolean hasStoped()
    {
        return stoped;
    }
}
