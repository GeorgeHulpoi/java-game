package Engine.Core;
import Engine.Forms.Rectangle;

public class Camera extends Rectangle
{
    private boolean UP_PRESSED = false;
    private boolean LEFT_PRESSED = false;
    private boolean DOWN_PRESSED = false;
    private boolean RIGHT_PRESSED = false;

    public Camera(int x, int y, int width, int height)
    {
        super(x, y, width, height);

    }
}