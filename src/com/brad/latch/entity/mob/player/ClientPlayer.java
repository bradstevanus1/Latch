package com.brad.latch.entity.mob.player;

import com.brad.latch.Game;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.events.Event;
import com.brad.latch.events.EventDispatcher;
import com.brad.latch.events.EventListener;
import com.brad.latch.events.types.MousePressedEvent;
import com.brad.latch.events.types.MouseReleasedEvent;
import com.brad.latch.graphics.ui.UIButton;
import com.brad.latch.graphics.ui.UIButtonHoverImpl;
import com.brad.latch.graphics.ui.UIButtonListener;
import com.brad.latch.graphics.ui.UILabel;
import com.brad.latch.graphics.ui.UIManager;
import com.brad.latch.graphics.ui.UIPanel;
import com.brad.latch.graphics.ui.UIProgressBar;
import com.brad.latch.input.Keyboard;
import com.brad.latch.input.Mouse;
import com.brad.latch.util.Vector2i;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.brad.latch.util.MathUtils.percentageOf;

/* TODO:
       * Make a UI button for returning to the level spawn
       * Make a UI button for closing the right-hand drawer
 */

public class ClientPlayer extends Player {

    private Keyboard input;
    private boolean shooting = false;

    private UIManager ui;
    private UIPanel panel;
    private UIProgressBar uiHealthBar;
    private UILabel uiHealthLabel;

    private BufferedImage playerIconImage, homeImage;

    public ClientPlayer(String name, int x, int y, Keyboard input) {
        super(x, y);
        this.input = input;
        this.name = name;

        // Player default attributes
        maxHealth = 100;
        moveSpeed = 1;
        melee = false;
        meleeDamage = 0;
        meleeRate = 60;
        projectileRate = SpearProjectile.projectileRate; // later
        aggroRadius = 0;

        health = maxHealth;
        size = 32;
        sprite = player_down;
        animatedSprite = player_down;
        animatedSpriteDown = player_down;
        animatedSpriteUp = player_up;
        animatedSpriteLeft = player_left;
        animatedSpriteRight = player_right;

        initUI();
    }

    /**
     * Does all UI creation after the player has been
     * created.
     */
    private void initUI() {
        int foregroundText = new Color(0xEBEBEB).getRGB();

        // There is only one UIManager in the game, which is updated and
        // rendered by the Game class. All other uses must be static
        // gets of this instance.
        ui = Game.getUIManager();
        panel = (UIPanel) new UIPanel(
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

        uiHealthLabel = new UILabel(new Vector2i(uiHealthBar.position).add(2, 16), String.format("HP - %d", health));
        uiHealthLabel.setColor(foregroundText);
        uiHealthLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.addComponent(uiHealthLabel);

        UIButton testButton = new UIButton(new Vector2i(50, 245), new Vector2i(100, 30),
                new UIButtonListener() {},
                () -> System.out.println("ACTION OCCURS!"));
        testButton.setText("Hello");
        panel.addComponent(testButton);

        // Loads the images for an icon-style button.
        try {
            playerIconImage = ImageIO.read(ClientPlayer.class.getResource("/ui/buttons/player_icon.png"));
            homeImage = ImageIO.read(ClientPlayer.class.getResource("/ui/buttons/home.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Creates the button and overrides some methods to use the regular icon
        // or the new brightened icon when appropriate.
        UIButton playerButton = new UIButton(new Vector2i(10, 175), playerIconImage,
                new UIButtonHoverImpl(playerIconImage), this::playerIconPressed);
        panel.addComponent(playerButton);

        UIButton homeButton = new UIButton(new Vector2i(10, 245), homeImage,
                new UIButtonHoverImpl(homeImage), this::recall);
        panel.addComponent(homeButton);
    }

    public void update() {
        super.update();
        uiHealthBar.setProgress(percentageOf(health, maxHealth));
        uiHealthLabel.setText(String.format("HP - %d", health));
        updatePosRelativeToScreen();
        if (health == 0) {
            death();
        }
        updateShooting();

        if (moving) animatedSprite.update();
        else animatedSprite.setFrame(0);
        if (projectileTimer > 0) projectileTimer--;
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
    }

    private void updateShooting() {
        if (!shooting || projectileTimer > 0) return;
        double dx = Mouse.getX() - xRelativeToScreen;
        double dy = Mouse.getY() - yRelativeToScreen;
        double dir = Math.atan2(dy, dx);
        shoot(x, y, dir);
        projectileTimer = (int) (60 / projectileRate);
    }


    private void death() {
        recall();
        health = maxHealth;
    }

    private void recall() {
        x = level.spawnPoint.getX();
        y = level.spawnPoint.getY();
    }

    private void playerIconPressed() {
        System.out.println("Player icon pressed!");
    }

    protected void shoot(double x, double y, double dir) {
        Projectile p = new SpearProjectile(x, y, dir, this);
        level.add(p);
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

    public static int getSize() {
        return size;
    }

    public UIPanel getPanel() {
        return panel;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
