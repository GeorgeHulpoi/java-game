package Game.Levels;

import Engine.Core.Camera;
import Engine.Core.World;
import Engine.EngineController;
import Engine.Forms.Rectangle;
import Game.Background;
import Game.Brick;
import Game.Player;

import java.awt.*;
import java.util.ArrayList;

public class Level2 extends Level
{
    private boolean gameover = false;
    private boolean winner = false;
    private Game.Image gameover_img;
    private Game.Image winner_img;
    private ArrayList<Brick> bricks = new ArrayList<>();
    private long LAST_TIME = 0;

    World world = null;
    Camera cam = null;
    Player player = null;

    Background bg1 = null;
    Background bg2 = null;
    Background bg3 = null;

    public Level2()
    {
        try {
            world = new World(640, 1920);
            cam = new Camera(-world.getWidth() / 2, -world.getHeight() / 2 + 640, 640, 640);
            player = new Player();

            player.setX(0 - player.getWidth()/2);
            player.setY(-world.getHeight()/2 + player.getHeight() + 20);
            player.setVisible(true);

            bg1 = new Background();
            bg2 = new Background();
            bg3 = new Background();

            bg1.setX(-320);
            bg1.setY(-world.getHeight() / 2 + 640);
            bg1.setVisible(true);

            bg2.setX(-320);
            bg2.setY(-world.getHeight() / 2 + 640 * 2);
            bg2.setVisible(true);

            bg3.setX(-320);
            bg3.setY(-world.getHeight() / 2 + 640 * 3);
            bg3.setVisible(true);

            int _x = -320;
            for (int i = 0; i <= 5; ++i)
            {
                Brick br = new Brick();
                br.setY(-world.getHeight()/2 + player.getHeight() + 220);
                br.setX(_x);
                br.setVisible(true);
                bricks.add(br);
                _x += 80;
            }

            _x = 320 - 80;
            for (int i = 0; i <= 5; ++i)
            {
                Brick br = new Brick();
                br.setY(-world.getHeight()/2 + player.getHeight() + 520);
                br.setX(_x);
                br.setVisible(true);
                bricks.add(br);
                _x -= 80;
            }

            _x = -320;
            for (int i = 0; i <= 5; ++i)
            {
                Brick br = new Brick();
                br.setY(-world.getHeight()/2 + player.getHeight() + 820);
                br.setX(_x);
                br.setVisible(true);
                bricks.add(br);
                _x += 80;
            }

            _x = 320 - 80;
            for (int i = 0; i <= 5; ++i)
            {
                Brick br = new Brick();
                br.setY(-world.getHeight()/2 + player.getHeight() + 1120);
                br.setX(_x);
                br.setVisible(true);
                bricks.add(br);
                _x -= 80;
            }

            _x = -320;
            for (int i = 0; i <= 5; ++i)
            {
                Brick br = new Brick();
                br.setY(-world.getHeight()/2 + player.getHeight() + 1420);
                br.setX(_x);
                br.setVisible(true);
                bricks.add(br);
                _x += 80;
            }

            gameover_img = new Game.Image("./assets/images/game_over.png");
            winner_img = new Game.Image("./assets/images/contest-winner.png");
        }
        catch (Exception e)
        {
            System.exit(-1);
        }
    }

    public World getWorld()
    {
        return world;
    }

    public Camera getCamera()
    {
        return cam;
    }

    public void render(Graphics g)
    {
        if (Engine.Forms.Rectangle.collision(bg1.toRectangle(), cam))
        {
            bg1.render(g);
        }
        if (Engine.Forms.Rectangle.collision(bg2.toRectangle(), cam))
        {
            bg2.render(g);
        }

        if (Engine.Forms.Rectangle.collision(bg3.toRectangle(), cam))
        {
            bg3.render(g);
        }

        for (int i = 0; i < bricks.size(); ++i)
        {
            Brick component = bricks.get(i);

            if (Engine.Forms.Rectangle.collision(component.toRectangle(), cam))
            {
                if (Engine.Forms.Rectangle.collision(component.toRectangle(), player.toRectangle()))
                {
                    if (!gameover)
                    {
                        gameover = true;
                        LAST_TIME = System.currentTimeMillis();
                    }
                }

                component.render(g);
            }
        }

        player.render(g);

        if (gameover)
        {
            Graphics2D g2d = (Graphics2D) g;

            g.setColor(new Color(0, 0, 0, 120));
            g.fillRect(0,0, 640, 640);

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(this.gameover_img.getBufferedImage(), 160, 160, 320, 320, null);

            long diff = System.currentTimeMillis() - LAST_TIME;
            if (diff >= 4000)
            {
                player = new Player();
                player.setX(0 - player.getWidth()/2);
                player.setY(-world.getHeight()/2 + player.getHeight() + 20);
                player.setVisible(true);
                cam.setPosition(-world.getWidth()/2, -world.getHeight()/2 + 640);

                gameover = false;

            }
        }
        else if (winner)
        {
            Graphics2D g2d = (Graphics2D) g;

            g.setColor(new Color(0, 0, 0, 120));
            g.fillRect(0,0, 640, 640);

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(this.winner_img.getBufferedImage(), 120, 173, 400, 294, null);

            long diff = System.currentTimeMillis() - LAST_TIME;
            if (diff >= 4000)
            {
                EngineController.getInstance().getGame().load(3);
            }
        }
    }

    public void tick()
    {
        if (gameover || winner) return;

        if (Engine.Forms.Rectangle.collision(player.toRectangle(), world.toRectangle()))
        {
            if (((world.getHeight()/2) - player.getY()) > 100 )
            {
                player.tick();

                int oldy = cam.getY();
                cam.setPosition(cam.getX(), player.getY() + cam.getHeight() / 2);

                if (!Rectangle.inside(world.toRectangle(), cam)) {
                    cam.setPosition(cam.getX(), oldy);
                }
            }
            else
            {
                winner = true;
                LAST_TIME = System.currentTimeMillis();
            }
        }
        else
        {
            gameover = true;
            LAST_TIME = System.currentTimeMillis();
        }
    }
}
