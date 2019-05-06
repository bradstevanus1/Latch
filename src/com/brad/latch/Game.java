package com.brad.latch;

import com.brad.latch.entity.mob.player.ClientPlayer;
import com.brad.latch.events.Event;
import com.brad.latch.events.EventListener;
import com.brad.latch.events.types.MouseMovedEvent;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.layers.Layer;
import com.brad.latch.graphics.ui.UIManager;
import com.brad.latch.input.Keyboard;
import com.brad.latch.input.Mouse;
import com.brad.latch.level.Level;
import com.brad.latch.level.SpawnLevel;
import com.brad.latch.level.tile.TileCoordinate;
import com.brad.latch.net.player.NetPlayer;
import com.brad.latch.util.MathUtils;
import com.brad.latch.util.Vector2i;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

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
 */
public class Game extends Canvas implements Runnable, EventListener {

    private static final long serialVersionUID = 1L;
    private static final String title = "Latch";
    private static final int width = 300 - 80;
    private static final int height = 168;
    private static final int scale = 3;
    private static final double UPDATES_PER_SECOND = 60;
    private static final boolean VSYNC = false;
    private static final double FRAME_RATE = 120;
    private static final int PARTICLE_LIFE = 8 * 60;

    private Thread thread;
    private final JFrame frame;
    private final Keyboard key;
    private Level level;
    private ClientPlayer clientPlayer;
    private boolean running = false;

    private static UIManager uiManager;

    private final Screen screen;
    public static boolean lockedScreen = true;
    private boolean keyReleased = true;
    public static double x, y; // For unlocked camera

    // Levels
    private static final Level SPAWN_LEVEL = new SpawnLevel("/levels/spawn.png");

    // BufferedImage extends java.awt.Image and describes this parent class with an
    // accessible buffer of image data.
    private final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Gets the raster of the image object. Then, gets the data buffer associated with that raster.
    // Then, casts the data buffer to a DataBufferInt object. Then, gets the integer array
    // of this data buffer. Note: this is only one way to implement graphics.
    private final int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    private List<Layer> layerStack = new ArrayList<>();

    public Game() {
        Dimension size = new Dimension(width*scale + 80 * 3, height*scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        Mouse mouse = new Mouse(this);
        level = SPAWN_LEVEL;
        uiManager = new UIManager(level);
        addLayer(level);
        addLayer(uiManager);    // Add event layers from first (top) to last (bottom)
        TileCoordinate playerSpawn = SPAWN_LEVEL.spawnPoint;
        clientPlayer = new ClientPlayer("Centrix", playerSpawn, key);
        level.add(clientPlayer);
        level.add(new NetPlayer(new TileCoordinate(19, 45)));
        x = clientPlayer.getX() - screen.width / 2.0;
        y = clientPlayer.getY() - screen.height / 2.0;

        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    // Changing the level requires you to re-init:
    // all entities
    // uiManager


    @Override
    public void onEvent(Event event) {
        for (int i = layerStack.size() - 1; i >= 0; i--) {
            layerStack.get(i).onEvent(event);
        }
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
    @SuppressWarnings("Duplicates")
    public void run() {
        /*
         * Variables to capture updates/frames per second, and
         * to create a time delta that is held between each run of update().
         * Basically, it runs update() 60 times per second, while render is run
         * as often as possible.
         */
        long timer = System.currentTimeMillis(); // For counting FPS and UPS

        long last_time = System.nanoTime();
        final double update_ns = 1000000000.0 / UPDATES_PER_SECOND;
        double delta = 0;

        final double render_ns = 1000000000.0 / FRAME_RATE;
        double render_delta = 0;

        int frames = 0;     // For counting frames/s
        int updates = 0;    // For counting updates/s
        requestFocus();     // Put focus on the game

        if (VSYNC) {
            while (running) {
                long now = System.nanoTime();
                delta += (now - last_time) / update_ns;
                render_delta += (now - last_time) / render_ns;
                last_time = now;
                while (delta >= 1) {
                    update();
                    updates++;
                    delta--;
                }
                while (render_delta >= 1) {
                    render();
                    frames++;
                    render_delta--;
                }
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                    updates = 0;
                    frames = 0;
                }
            }
        } else {
            while (running) {
                long now = System.nanoTime();
                delta += (now - last_time) / update_ns;
                last_time = now;
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
        }
        stop();
    }

    public void update() {
        if (key.screenLockToggle && keyReleased) {
            lockedScreen = !lockedScreen;
            x = clientPlayer.getX() - screen.width / 2.0;
            y = clientPlayer.getY() - screen.height / 2.0;
            keyReleased = false;
        }
        if (!(key.screenLockToggle || keyReleased)) keyReleased = true;
        if (key.centerCameraOnPlayer) {
            x = clientPlayer.getX() - screen.width / 2.0;
            y = clientPlayer.getY() - screen.height / 2.0;
        }
        key.update();

        // Update layers here
        for (int i = 0; i < layerStack.size(); i++) {
            Layer layer = layerStack.get(i);
            if (!(layer instanceof UIManager)) layer.update();
        }
    }

    public void render() {

        // Sets equal to the buffer strategy of the super, which is canvasZ
        BufferStrategy bs = getBufferStrategy();

        // If there is not a buffer strategy already, create one
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        int xScroll = (lockedScreen) ? (int) (clientPlayer.getX() - screen.width / 2) : (int) x;
        int yScroll = (lockedScreen) ? (int) (clientPlayer.getY() - screen.height / 2) : (int) y;

        /* Elements to draw begin here */


        // Render all the elements, like how update updates the level, player, and keyboard
        level.setScroll(xScroll, yScroll);

        // Render layers here
        Graphics g = bs.getDrawGraphics();

        for (int i = 0; i < layerStack.size(); i++) {
            Layer layer = layerStack.get(i);
            if (layer instanceof UIManager) {
                ((UIManager) layer).render(g);
            } else {
                layer.render(screen);
            }
        }

        /* Elements to draw end here */

        // When all rendering is finished in the screen object, transfer
        // the pixels to this array, which is related to the image object

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

        // Returns a graphics context for the drawing buffer and draws everything
        g.drawImage(image, 0, 0, width * scale, height * scale, null);
        //uiManager.render(g);
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

    public static int getParticleLife() {
        return PARTICLE_LIFE;
    }

    public void addLayer(Layer layer) {
        layerStack.add(layer);
    }

    /**
     * Latch execution starting point.
     * @param args  Command line arguments
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
