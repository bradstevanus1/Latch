package com.brad.latch.entity.mob;

import com.brad.latch.Game;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.SpriteCollection;
import com.brad.latch.graphics.ui.UIComponent;
import com.brad.latch.graphics.ui.UILabel;
import com.brad.latch.graphics.ui.UIManager;
import com.brad.latch.graphics.ui.UIPanel;
import com.brad.latch.input.Keyboard;
import com.brad.latch.input.Mouse;
import com.brad.latch.util.Vector2i;

public class Player extends Mob {

    private Keyboard input;
    private int rateOfFire = 0;

    private UIManager ui;

    public Player(Keyboard input) {
        this(0, 0, input);
        ui = Game.getUIManager();
    }

    public Player(int x, int y, Keyboard input) {
        super(x, y, SpriteCollection.player_down, 1);
        this.input = input;
        name = "Centrix";
        rateOfFire = SpearProjectile.rateOfFire;
        size = 32;
        animatedSprite = SpriteCollection.player_down;
        animatedSpriteDown = SpriteCollection.player_down;
        animatedSpriteUp = SpriteCollection.player_up;
        animatedSpriteLeft = SpriteCollection.player_left;
        animatedSpriteRight = SpriteCollection.player_right;
        ui = Game.getUIManager();
        UIPanel panel = new UIPanel(new Vector2i(300 - 80, 0));
        ui.addPanel(panel);
        panel.addComponent(new UILabel(new Vector2i(-10, 1), "Hello!~"));
    }

    public void update() {
        if (moving) animatedSprite.update();
        else animatedSprite.setFrame(0);
        if (rateOfFire > 0) rateOfFire--;
        double xDelta = 0, yDelta = 0;
        if (input.up) {
            animatedSprite = SpriteCollection.player_up;
            yDelta -= moveSpeed;
        } else if (input.down) {
            animatedSprite = SpriteCollection.player_down;
            yDelta += moveSpeed;
        }
        if (input.left) {
            animatedSprite = SpriteCollection.player_left;
            xDelta -= moveSpeed;
        } else if (input.right) {
            animatedSprite = SpriteCollection.player_right;
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
        if (Mouse.getButton() == 1 && rateOfFire <= 0) {
            double dx = Mouse.getX() - xRelativeToScreen;
            double dy = Mouse.getY() - yRelativeToScreen;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            rateOfFire = SpearProjectile.rateOfFire;
        }
    }

    public static int getSize() {
        return size;
    }
}
