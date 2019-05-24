import Engine.EngineController;
import Engine.Core.World;
import Engine.Core.Camera;
import Engine.Forms.Line;
import Engine.Forms.WorldLine;
import Exceptions.InvalidSizeException;

import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        EngineController.getInstance().start();

        /*try
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
        x.setVisible(true);

        YAxis y = new YAxis();
        y.setVisible(true);

        BiAxis bi = new BiAxis();
        bi.setVisible(true);

        NBiAxis nbi = new NBiAxis();
        nbi.setVisible(true);

        ImgObj img = new ImgObj();
        img.setVisible(true);

        TLObj tl = new TLObj();
        tl.setVisible(true);

        TRObj tr = new TRObj();
        tr.setVisible(true);

        BLObj bl = new BLObj();
        bl.setVisible(true);

        BRObj br = new BRObj();
        br.setVisible(true);

        Poly p = new Poly();
        p.setVisible(true);

        FPS fps = new FPS();
        fps.setVisible(true);*/
    }
}
