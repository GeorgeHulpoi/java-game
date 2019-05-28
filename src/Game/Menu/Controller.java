package Game.Menu;

import Engine.Core.Component;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import Engine.EngineController;
import Engine.Image;
import Game.Menu.Button;

public class Controller
{
    private ArrayList<Button> buttons = new ArrayList<>();
    private Image background;

    private boolean SHOW_NEW_GAME = false;
    private boolean SHOW_RESUME = false;

    private Button EXIT;
    private Button NEW_GAME;
    private Button RESUME;

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

        NEW_GAME = new Button("New Game", BTN_WIDTH, BTN_HEIGHT);
        NEW_GAME.eventListener = () -> { NewGameCallback(); };

        RESUME = new Button("Resume", BTN_WIDTH, BTN_HEIGHT);
        RESUME.eventListener = () -> { ResumeCallback(); };

        SHOW_NEW_GAME = true;

        show();
    }

    public void hide()
    {
        EXIT.setVisible(false);
        NEW_GAME.setVisible(false);
        RESUME.setVisible(false);

        buttons.clear();

        EngineController.getInstance().getWindow().getCanvas().setCursor(null);
    }

    public void show()
    {
        if (SHOW_NEW_GAME)
        {
            NEW_GAME.setVisible(true);
            buttons.add(NEW_GAME);
        }
        if (SHOW_RESUME)
        {
            RESUME.setVisible(true);
            buttons.add(RESUME);
        }

        EXIT.setVisible(true);
        buttons.add(EXIT);

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
        EngineController.getInstance().getGame().loaded = true;
        SHOW_NEW_GAME = false;
        SHOW_RESUME = true;
        this.hide();
    }

    private void ResumeCallback()
    {
        EngineController.getInstance().getGame().paused = false;
        hide();
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