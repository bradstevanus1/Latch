package com.brad.latch.entity.mob;

import com.brad.latch.Game;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.graphics.ui.UIButton;
import com.brad.latch.graphics.ui.UIButtonListener;
import com.brad.latch.graphics.ui.UILabel;
import com.brad.latch.graphics.ui.UIManager;
import com.brad.latch.graphics.ui.UIPanel;
import com.brad.latch.graphics.ui.UIProgressBar;
import com.brad.latch.input.Keyboard;
import com.brad.latch.input.Mouse;
import com.brad.latch.util.Vector2i;
import com.sun.source.doctree.UnknownBlockTagTree;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

/* TODO:
       * Make a UI button for returning to the level spawn
       * Make a UI button for closing the right-hand drawer
 */

public class Player extends Mob {

    private Keyboard input;

    private UIManager ui;
    private UIProgressBar uiHealthBar;

    private BufferedImage playerIconImage, playerIconImageHover,
                            homeImage, homeImageHover;

    @Deprecated
    public Player(String name, Keyboard input) {
        this(name, 0, 0, input);
        ui = Game.getUIManager();
        this.name = name;
    }

    public Player(String name, int x, int y, Keyboard input) {
        super(x, y, player_down, 1);
        this.input = input;
        this.name = name;

        // Player default attributes
        health = 100;
        fireRate = SpearProjectile.rateOfFire;
        size = 32;
        animatedSprite = player_down;
        animatedSpriteDown = player_down;
        animatedSpriteUp = player_up;
        animatedSpriteLeft = player_left;
        animatedSpriteRight = player_right;

        init();
    }

    /**
     * Does all UI creation after the player has been
     * created.
     */
    private void init() {
        int foregroundText = new Color(0xEBEBEB).getRGB();

        // There is only one UIManager in the game, which is updated and
        // rendered by the Game class. All other uses must be static
        // gets of this instance.
        ui = Game.getUIManager();
        UIPanel panel = (UIPanel) new UIPanel(
                new Vector2i((300 - 80) * 3, 0), new Vector2i(80 * 3, 168 * 3)).setColor(0x4f4f4f);
        ui.addPanel(panel);

        UILabel nameLabel = new UILabel(new Vector2i(50, 200), name);
        nameLabel.setColor(foregroundText);
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
        nameLabel.dropShadow = true;
        panel.addComponent(nameLabel);

        uiHealthBar = new UIProgressBar(new Vector2i(10, 215), new Vector2i(80 * 3 - 20, 20));
        uiHealthBar.setColor(new Color(0x6f6f6f).getRGB());
        uiHealthBar.setForegroundColor(Color.RED.getRGB());
        panel.addComponent(uiHealthBar);

        UILabel hpLabel = new UILabel(new Vector2i(uiHealthBar.position).add(2, 16), "HP");
        hpLabel.setColor(foregroundText);
        hpLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.addComponent(hpLabel);

        UIButton testButton = new UIButton(new Vector2i(50, 245), new Vector2i(100, 30),
                new UIButtonListener() {},
                () -> System.out.println("ACTION OCCURS!"));
        testButton.setText("Hello");
        panel.addComponent(testButton);

        // Loads the images for an icon-style button.
        try {
            playerIconImage = ImageIO.read(new File("res/ui/buttons/player_icon.png"));
            homeImage = ImageIO.read(new File("res/ui/buttons/home.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Creates a new buffered image from the old image.
        playerIconImageHover = new BufferedImage(playerIconImage.getWidth(), playerIconImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int[] newPixelsPlayer = ((DataBufferInt)playerIconImageHover.getRaster().getDataBuffer()).getData();
        homeImageHover = new BufferedImage(homeImage.getWidth(), homeImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int[] newPixelsHome = ((DataBufferInt)homeImageHover.getRaster().getDataBuffer()).getData();

        // Fills the array with the appropriated pixel colors
        for (int y = 0; y < playerIconImage.getHeight(); y++) {
            for (int x = 0; x < playerIconImage.getWidth(); x++) {
                newPixelsPlayer[x + y * playerIconImage.getWidth()] = playerIconImage.getRGB(x, y);
                newPixelsHome[x + y * homeImage.getWidth()] = homeImage.getRGB(x, y);
            }
        }

        // Modifies each pixel in the hover image to have slightly higher brightness.
        for (int y = 0; y < playerIconImage.getHeight(); y++) {
            for (int x = 0; x < playerIconImage.getWidth(); x++) {
                int colorPlayer = newPixelsPlayer[x + y * playerIconImage.getWidth()];
                int colorHome = newPixelsHome[x + y * homeImage.getWidth()];
                int r_p = (colorPlayer & 0xFF0000) >> 16;
                int g_p = (colorPlayer & 0xFF00) >> 8;
                int b_p = colorPlayer & 0xFF;
                int r_h = (colorHome & 0xFF0000) >> 16;
                int g_h = (colorHome & 0xFF00) >> 8;
                int b_h = colorHome & 0xFF;

                r_p += 25; r_h += 25;
                g_p += 25; g_h += 25;
                b_p += 25; b_h += 25;

                colorPlayer &= 0xFF000000;
                colorHome &= 0xFF000000;

                newPixelsPlayer[x + y * playerIconImage.getWidth()] = colorPlayer | r_p << 16 | g_p << 8 | b_p;
                newPixelsHome[x + y * homeImage.getWidth()] = colorHome | r_h << 16 | g_h << 8 | b_h;
            }
        }

        // Creates the button and overrides some methods to use the regular icon
        // or the new brightened icon when appropriate.
        UIButton playerButton = new UIButton(new Vector2i(10, 175), playerIconImage,
                new UIButtonListener() {
                    public void buttonEntered(UIButton button) {
                        button.setImage(playerIconImageHover);
                    }

                    public void buttonExited(UIButton button) {
                        button.setImage(playerIconImage);
                    }
                },
                () -> System.out.println("Player icon pressed!"));
        panel.addComponent(playerButton);

        UIButton homeButton = new UIButton(new Vector2i(10, 245), homeImage,
                new UIButtonListener() {
                    public void buttonEntered(UIButton button) {
                        button.setImage(homeImageHover);
                    }

                    public void buttonExited(UIButton button) {
                        button.setImage(homeImage);
                    }
                },
                () -> {
                    System.out.println("Home button pressed! Exiting");
                    System.exit(0);
                }
                );
        panel.addComponent(homeButton);
    }

    public void update() {
        if (moving) animatedSprite.update();
        else animatedSprite.setFrame(0);
        if (fireRate > 0) fireRate--;
        double xDelta = 0, yDelta = 0;
        if (input.up) {
            animatedSprite = player_up;
            yDelta -= moveSpeed;
        } else if (input.down) {
            animatedSprite = player_down;
            yDelta += moveSpeed;
        }
        if (input.left) {
            animatedSprite = player_left;
            xDelta -= moveSpeed;
        } else if (input.right) {
            animatedSprite = player_right;
            xDelta += moveSpeed;
        }
        if (xDelta != 0 || yDelta != 0) {
            move(xDelta, yDelta);
            moving = true;
        } else {
            moving = false;
        }
        clear();
        updatePosRelativeToScreen();
        updateShooting();
        uiHealthBar.setProgress(health);
    }

    protected void shoot(double x, double y, double dir) {
        Projectile p = new SpearProjectile(x, y, dir);
        level.add(p);
    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) {
                level.getProjectiles().remove(i);
                i--;
            }
        }
    }

    private void updatePosRelativeToScreen() {
        if (Game.lockedScreen) {
            xRelativeToScreen = Game.getWindowWidth() / 2.0;
            yRelativeToScreen = Game.getWindowHeight() / 2.0;
        } else {
            xRelativeToScreen = (x - Game.x)*Game.getScale();
            yRelativeToScreen = (y - Game.y)*Game.getScale();
        }
    }

    // FIXME Player's projectile gets stuck in the wall if shooting while there is a collide-able tile above
    private void updateShooting() {
        if (Mouse.getButton() == 1 && fireRate <= 0) {
            double dx = Mouse.getX() - xRelativeToScreen;
            double dy = Mouse.getY() - yRelativeToScreen;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            fireRate = SpearProjectile.rateOfFire;
        }
    }

    public static int getSize() {
        return size;
    }
}
