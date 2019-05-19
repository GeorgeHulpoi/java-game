import Engine.Core.Camera;
import Engine.EngineController;
import Engine.Forms.WorldLine;

import java.awt.*;

public class XAxis extends WorldLine
{
    public XAxis()
    {
        this.p1 = new Point(-640*3/2, 0);
        this.p2 = new Point(640*3/2, 0);
    }

    @Override
    public void render(Graphics g)
    {
        Camera cam = EngineController.getInstance().getCamera();

        int x1 = (int) p1.getX() - cam.getX();
        int y1 =  cam.getY() - (int) p1.getY();
        int x2 = (int) p2.getX() - cam.getX();
        int y2 =  cam.getY() - (int) p2.getY();

        g.setColor(Color.WHITE);
        g.drawLine(x1, y1, x2, y2);
    }
}
