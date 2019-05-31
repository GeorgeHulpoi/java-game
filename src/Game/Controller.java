package Game;

import Engine.EngineController;
import Game.Levels.Level;
import Game.Levels.Level1;
import Game.Levels.Level2;
import Game.Levels.Level3;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Controller
{
    private boolean loaded = false;
    // TODO: de schimbat in private
    public boolean paused = false;

    private Level lvl = null;

    public Controller()
    {
        // Luata de pe net.. exista o problema la focusare si asta e cam singura solutie pentru Key Listener
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            (e) ->
            {
                OnKeyEvent(e);
                return false;
            }
        );
    }

    private void OnKeyEvent(KeyEvent e)
    {
        if (loaded)
        {
            if (e.getID() == KeyEvent.KEY_TYPED) {
                if ((int) e.getKeyChar() == 27) {
                    if (paused) {
                        paused = false;
                        EngineController.getInstance().getMenu().hide();
                    } else {
                        paused = true;
                        EngineController.getInstance().getMenu().show();
                    }
                }
            }
        }
    }

    public boolean isLoaded()
    {
        return loaded;
    }

    public void render(Graphics g)
    {
        lvl.render(g);
    }

    public void tick()
    {
        lvl.tick();
    }

    public void load(int level)
    {
        if (level == 1)
        {
            lvl = new Level1();
        }
        else if (level == 2)
        {
            lvl = new Level2();
        }
        else if (level == 3)
        {
            lvl = new Level3();
        }

        EngineController.getInstance().setWorld(lvl.getWorld());
        EngineController.getInstance().setCamera(lvl.getCamera());
        loaded = true;
    }
}