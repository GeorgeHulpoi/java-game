package Game.Menu;

import Engine.Core.Component;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import Engine.Image;
import Game.Menu.Button;

public class Controller
{
    private ArrayList<Button> buttons = new ArrayList<>();
    private Image background;
    private int selected = 0;

    private Button EXIT;
    private Button NEW_GAME;

    final static int BTN_HEIGHT = 28;
    final static int BTN_WIDTH = 190;
    final static int BTN_MARGIN = 10;

    public Controller()
    {
        try
        {
            background = new Image("./assets/images/menu_background.png");
        }
        catch (IOException e)
        {
            System.exit(-1);
        }


        EXIT = new Button("Exit",  BTN_WIDTH, BTN_HEIGHT);
        EXIT.eventListener = () -> { ExitCallback(); };
        EXIT.setVisible(true);

        NEW_GAME = new Button("New Game", BTN_WIDTH, BTN_HEIGHT);
        NEW_GAME.eventListener = () -> { NewGameCallback(); };
        NEW_GAME.setVisible(true);

        this.buttons.add(NEW_GAME);
        this.buttons.add(EXIT);

        centerButtons();
    }

    private void centerButtons()
    {
        int len = buttons.size();
        int pivotY = (640 / 2) - (len * BTN_HEIGHT + (len - 1) * BTN_MARGIN)/2;
        int pivotX = (640 / 2) - BTN_WIDTH/2;

        for (int i = 0; i < this.buttons.size(); ++i)
        {
            Button btn = this.buttons.get(i);
            btn.setX(pivotX);
            btn.setY(pivotY + i * (BTN_MARGIN + BTN_HEIGHT));
        }
    }

    private void NewGameCallback()
    {
        System.out.println("New Game clicked");
    }

    private void ExitCallback()
    {
        // TODO: salvare automata
        System.exit(0);
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