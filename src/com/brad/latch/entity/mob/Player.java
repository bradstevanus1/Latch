package com.brad.latch.entity.mob;

import com.brad.latch.Game;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.graphics.AnimatedSprite;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.SpriteCollection;
import com.brad.latch.input.Keyboard;
import com.brad.latch.input.Mouse;
import com.brad.latch.level.tile.Tile;

public class Player extends Mob {

    private Keyboard input;
    private static final int PLAYER_SIZE = 32;
    private int rateOfFire = 0;
    private AnimatedSprite animatedSprite = SpriteCollection.player_down;

    public Player(Keyboard input, int moveSpeed) {
        super(moveSpeed);
        this.input = input;
        sprite = SpriteCollection.player_up;
    }

    public Player(int x, int y, Keyboard input, int moveSpeed) {
        super(x, y, SpriteCollection.player_up, moveSpeed);
        this.input = input;
        rateOfFire = SpearProjectile.rateOfFire;
    }

    public void update() {
        if (moving) animatedSprite.update();
        else animatedSprite.setFrame(0);
        if (rateOfFire > 0) rateOfFire--;
        int xa = 0, ya = 0;
        if (input.up) {
            animatedSprite = SpriteCollection.player_up;
            ya = ya - moveSpeed;
        } else if (input.down) {
            animatedSprite = SpriteCollection.player_down;
            ya = ya + moveSpeed;
        }
        if (input.left) {
            animatedSprite = SpriteCollection.player_side;
            xa = xa - moveSpeed;
        } else if (input.right) {
            animatedSprite = SpriteCollection.player_side;
            xa = xa + moveSpeed;
        }
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            moving = true;
        } else {
            moving = false;
        }
        clear();
        updatePosRelativeToScreen();
        updateShooting();
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
            xRelativeToScreen = Game.getWindowWidth() / 2;
            yRelativeToScreen = Game.getWindowHeight() / 2;
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

    /*
     * flip = 0 -> No flip
     * flip = 1 -> Horizontal flip
     * flip = 2 -> Vertical flip
     * flip = 3 -> Horizontal and vertical flip
     */
    // FIXME Rendering of player direction doesn't look quite right
    // TODO Create the rest of the player sprites
    public void render(Screen screen) {
        int flip = (dir == Direction.LEFT) ? 1 : 0;
        sprite = animatedSprite.getSprite();
        screen.renderMob(x - Tile.getTileSize(), y - Tile.getTileSize(), sprite, flip);
    }

    public static int getPlayerSize() {
        return PLAYER_SIZE;
    }
}
