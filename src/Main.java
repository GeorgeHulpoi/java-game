import Engine.Camera;
import Engine.Controller;
import Engine.World;
import Exceptions.InvalidSizeException;

public class Main
{
    public static void main(String[] args)
    {
        Controller.getInstance().init();

        try
        {
            World world = new World(640 * 3, 640 * 3);
            Camera cam = new Camera(-640/2, 640/2, 640, 640);

            Controller.getInstance().setWorld(world);
            Controller.getInstance().setCamera(cam);
        }
        catch (InvalidSizeException e)
        {
            System.out.println("width si height divizibile cu 2");
        }

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

        FPS fps = new FPS();
        fps.setVisible(true);
    }
}
