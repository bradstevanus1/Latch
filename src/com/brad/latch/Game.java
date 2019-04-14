package com.brad.latch;

import com.brad.latch.entity.mob.Player;
import com.brad.latch.graphics.Font;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.ui.UIManager;
import com.brad.latch.input.Keyboard;
import com.brad.latch.input.Mouse;
import com.brad.latch.level.Level;
import com.brad.latch.level.tile.TileCoordinate;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

// Canvas is part of JFrame.
// Implements runnable to instantiate a thread.

// TODO: Make an interface called "renderable" that is implemented
//       by all classes that have a render method

/**
 * "Latch" - a 2D RPG bullet-hell game.
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
    public static String title = "Latch";

    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Mouse mouse;
    private Level level;
    private Player player;
    private boolean running = false;

    private static UIManager uiManager;

    private Screen screen;
    private Font font;
    public static boolean lockedScreen = true;
    private boolean keyReleased = true;
    public static double x, y; // For unlocked camera

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
        uiManager = new UIManager();
        frame = new JFrame();
        key = new Keyboard();
        mouse = new Mouse();
        font = new Font();
        level = Level.spawn;
        TileCoordinate playerSpawn = new TileCoordinate(19, 62);
        player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
        level.add(player);
        x = player.getX() - screen.width / 2.0;
        y = player.getY() - screen.height / 2.0;

        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
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
            x = player.getX() - screen.width / 2.0;
            y = player.getY() - screen.height / 2.0;
            keyReleased = false;
        }
        if (!(key.screenLockToggle || keyReleased)) keyReleased = true;
        if (key.centerCameraOnPlayer) {
            x = player.getX() - screen.width / 2.0;
            y = player.getY() - screen.height / 2.0;
        }
        key.update();
        level.update();
        uiManager.update();
    }

    public void render() {

        // Sets equal to the buffer strategy of the super, which is canvas
        BufferStrategy bs = getBufferStrategy();

        // If there is not a buffer strategy already, create one
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        double xScroll = (lockedScreen) ? player.getX() - screen.width / 2.0 : x;
        double yScroll = (lockedScreen) ? player.getY() - screen.height / 2.0 : y;

        /** Elements to draw begin here */


        // Render all the elements, like how update updates the level, player, and keyboard
        level.render((int) xScroll, (int) yScroll, screen);


        /** Elements to draw end here */

        // When all rendering is finished in the screen object, transfer
        // the pixels to this array, which is related to the image object
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        // Returns a graphics context for the drawing buffer and draws everything
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        uiManager.render(g);
        g.dispose();
        bs.show();

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

    public static UIManager getUIManager() {
        return uiManager;
    }

    /**
     * Latch execution starting point.
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
