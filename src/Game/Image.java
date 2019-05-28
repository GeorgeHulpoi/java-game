package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image
{
    private File file;
    private BufferedImage image;
    private Double scale = 1.0;

    public Image(String path) throws IOException
    {
        image = ImageIO.read(new File(path));
        System.out.println("w = " + image.getWidth() + ", h = " + image.getHeight());
    }

    public BufferedImage getBufferedImage()
    {
        return this.image;
    }

    public int getWidth()
    {
        return this.image.getWidth();
    }

    public int getHeight()
    {
        return this.image.getHeight();
    }
}
