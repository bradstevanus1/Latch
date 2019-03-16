package com.brad.rain;

import com.brad.rain.entity.mob.Player;
import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.graphics.SpriteSheet;
import com.brad.rain.graphics.SpriteSheetCollection;
import com.brad.rain.input.Keyboard;
import com.brad.rain.input.Mouse;
import com.brad.rain.level.Level;
import com.brad.rain.level.tile.TileCoordinate;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

// Canvas is part of JFrame.
// Implements runnable to instantiate a thread.

/**
 * "Rain" - a 2D RPG bullet-hell game.
 *
 * @author Bradley Stevanus
 * @version 1.0
 * @since 2019
 * @// TODO: 3/10/2019 Finish game.
 */
public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private static int width = 300;
    private static int height = 168;
    private static int scale = 3;
    public static String title = "Rain";

    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;

    private Screen screen;
    public static boolean lockedScreen = true;
    private boolean keyReleased = true;
    public static int x, y; // For unlocked camera

    // BufferedImage extends java.awt.Image and describes this parent class with an
    // accessible buffer of image data.
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Gets the raster of the image object. Then, gets the data buffer associated with that raster.
    // Then, casts the data buffer to a DataBufferInt object. Then, gets the integer array
    // of this data buffer. Note: this is only one way to implement graphics.
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = Level.spawn;
        TileCoordinate playerSpawn = new TileCoordinate(19, 62);
        player = new Player(playerSpawn.getX(), playerSpawn.getY(), key, 4);
        x = player.x - screen.width / 2;
        y = player.y - screen.height / 2;
        player.init(level);

        Mouse mouse = new Mouse();
        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public static int getWindowWidth() {
        return width * scale;
    }

    public static int getWindowHeight() {
        return height * scale;
    }

    public static int getScale() {
        return scale;
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        // Starts the new thread, which puts control into the run method
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // This runs when the new thread is started
    public void run() {
        /*
         * Variables to capture updates/frames per second, and
         * to create a time delta that is held between each run of update().
         * Basically, it runs update() 60 times per second, while render is run
         * as often as possible.
         */
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;     // For counting frames/s
        int updates = 0;    // For counting updates/s
        requestFocus();     // Put focus on the game
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }


    public void update() {
        if (key.screenLockToggle && keyReleased) {
            lockedScreen = !lockedScreen;
            x = player.x - screen.width / 2;
            y = player.y - screen.height / 2;
            keyReleased = false;
        }
        if (!(key.screenLockToggle || keyReleased)) keyReleased = true;
        if (key.centerCameraOnPlayer) {
            x = player.x - screen.width / 2;
            y = player.y - screen.height / 2;
        }
        key.update();
        player.update();
        level.update();
    }

    public void render() {

        // Sets equal to the buffer strategy of the super, which is canvas
        BufferStrategy bs = getBufferStrategy();

        // If there is not buffer stretegy already, create one
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        // Clear the screen of the last frame
        screen.clear();

        // Set the scroll factor based on player's position (locked) or static pos (unlocked).
        // These variables are set to the top left corner of the screen, so all render methods
        // start their drawing in some way from these variables. In the case of level,
        // the offsets are exactly equal to these scrolls.
        int xScroll = (lockedScreen) ? player.x - screen.width / 2 : x;
        int yScroll = (lockedScreen) ? player.y - screen.height / 2 : y;

        // Render all the elements, like how update updates the level, player, and keyboard
        level.render(xScroll, yScroll, screen);
        player.render(screen);
        //TODO temporary debugging: remove
        screen.renderSheet(40, 40, SpriteSheetCollection.player_down, false);

        // When all rendering is finished in the screen object, transfer
        // the pixels to this array, which is related to the image object

        Sprite sprite = new Sprite(40, 40, 0xff00ff);
        // If fixed == false, the sprite will stick to the screen in GUI fashion.
        // If not, it will appear at the absolute value on the level.
        screen.renderSprite(0, 0, sprite, true);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        // Returns a graphics context for the drawing buffer and draws everything
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();

    }

    /**
     * Rain execution starting point.
     * @param args
     */
    public static void main(String[] args) {
        // Instantiate the game class and set frame parameters.
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

}
