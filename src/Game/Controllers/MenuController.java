package Game.Controllers;

import Engine.Core.Component;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import Engine.Image;
import Game.Controllers.Menu.Button;

public class MenuController
{
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private Image background;
    private int selected = 0;
    final static int BTN_HEIGHT = 35;
    final static int BTN_WIDTH = 150;
    final static int BTN_MARGIN = 15;

    public MenuController()
    {
        try
        {
            background = new Image("./assets/images/menu_background.png");
        }
        catch (IOException e)
        {

        }

        this.buttons.add(new Button("New game",  BTN_WIDTH, BTN_HEIGHT));
        this.buttons.add(new Button("Load game",  BTN_WIDTH, BTN_HEIGHT));
        this.buttons.add(new Button("",  BTN_WIDTH, BTN_HEIGHT));
    }

    public void render(Graphics g)
    {
        g.drawImage(this.background.getBufferedImage(), 0, 0, 640, 640, null);

        for (int i = 0; i < this.buttons.size(); ++i)
        {
            Component component = this.buttons.get(i);
            component.render(g);
        }
    }

    public void tick()
    {
        for (int i = 0; i < this.buttons.size(); ++i)
        {
            Component component = this.buttons.get(i);
            component.tick();
        }
    }
}
