package com.brad.rain;

import com.brad.rain.entity.mob.Player;
import com.brad.rain.graphics.Screen;
import com.brad.rain.input.Keyboard;
import com.brad.rain.input.Mouse;
import com.brad.rain.level.Level;
import com.brad.rain.level.SpawnLevel;
import com.brad.rain.level.TileCoordinate;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

// Canvas is part of JFrame. f
// Implements runnable to instantiate a thread.
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

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = Level.spawn;
        TileCoordinate playerSpawn = new TileCoordinate(19, 62);
        player = new Player(playerSpawn.getX(), playerSpawn.getY(), key, 1);
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

    // This runs when the new thread is created
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
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        int xScroll = (lockedScreen) ? player.x - screen.width / 2 : x;
        int yScroll = (lockedScreen) ? player.y - screen.height / 2 : y;
        level.render(xScroll, yScroll, screen);
        player.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", 0, 50));
        //g.fillRect(Mouse.getX(), Mouse.getY(), 64, 64);
        //g.drawString("Button: " + Mouse.getButton(), 80, 80);
        g.dispose();
        bs.show();

    }

    /**
     * Main starting point for Rain.
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
