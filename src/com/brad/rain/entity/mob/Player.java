package com.brad.rain.entity.mob;

import com.brad.rain.Game;
import com.brad.rain.entity.projectile.Projectile;
import com.brad.rain.entity.projectile.SpearProjectile;
import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.graphics.SpriteCollection;
import com.brad.rain.input.Keyboard;
import com.brad.rain.input.Mouse;
import com.brad.rain.level.tile.Tile;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private byte animate = 0;
    private final byte animationSpeed = 30; // Lower is faster
    private final byte firstAnimationFrame = animationSpeed / 3;
    private final byte secondAnimationFrame = (animationSpeed * 2) / 3;
    private boolean walking = false;
    private static final int PLAYER_SIZE = 32;

    Projectile p;
    private int RATE_OF_FIRE = 0;


    public Player(Keyboard input, int moveSpeed) {
        this.input = input;
        this.moveSpeed = moveSpeed;
        sprite = SpriteCollection.player_forward;

    }

    public Player(int x, int y, Keyboard input, int moveSpeed) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = SpriteCollection.player_forward;
        this.moveSpeed = moveSpeed;
        RATE_OF_FIRE = SpearProjectile.RATE_OF_FIRE;
    }

    public void update() {
        if (RATE_OF_FIRE > 0) RATE_OF_FIRE--;
        int xa = 0, ya = 0;
        if (animate < animationSpeed) animate++;
        else animate = 0;
        if (input.up) ya = ya - moveSpeed;
        if (input.down) ya = ya + moveSpeed;
        if (input.left) xa = xa - moveSpeed;
        if (input.right) xa = xa + moveSpeed;
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
        clear();
        updatePosRelativeToScreen();
        updateShooting();
    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) level.getProjectiles().remove(i);
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

    private void updateShooting() {
        if (Mouse.getButton() == 1 && RATE_OF_FIRE <= 0) {
            // TODO Change this, it currently fires from the centre only

            double dx = Mouse.getX() - xRelativeToScreen;
            double dy = Mouse.getY() - yRelativeToScreen;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            RATE_OF_FIRE = SpearProjectile.RATE_OF_FIRE;
        }
    }

    /*
     * flip = 0 -> No flip
     * flip = 1 -> Horizontal flip
     * flip = 2 -> Vertical flip
     * flip = 3 -> Horizontal and vertical flip
     */
    public void render(Screen screen) {
        if (dir == 0) {
            sprite = getAnimatedSpriteDirection(
                    SpriteCollection.player_forward,
                    SpriteCollection.player_forward_1,
                    SpriteCollection.player_forward_2
            );
        }
        if (dir == 1) {
            sprite = getAnimatedSpriteDirection(
                    SpriteCollection.player_side,
                    SpriteCollection.player_side_1,
                    SpriteCollection.player_side_2
            );
        }
        if (dir == 2) {
            sprite = getAnimatedSpriteDirection(
                    SpriteCollection.player_back,
                    SpriteCollection.player_back_1,
                    SpriteCollection.player_back_2
            );
        }
        int flip = 0;
        if (dir == 3) {
            sprite = getAnimatedSpriteDirection(
                    SpriteCollection.player_side,
                    SpriteCollection.player_side_1,
                    SpriteCollection.player_side_2
            );
            flip = 1;
        }
        screen.renderPlayer(x - Tile.getTileSize(), y - Tile.getTileSize(), sprite, flip);
    }

    private Sprite getAnimatedSpriteDirection(Sprite still, Sprite mov1, Sprite mov2) {
        if (walking) {
            if (animate >= 0 && animate < firstAnimationFrame) {
                sprite = still;
            } else if (animate >= firstAnimationFrame && animate < secondAnimationFrame) {
                sprite = mov1;
            } else {
                sprite = mov2;
            }
        } else {
            sprite = still;
        }
        return sprite;
    }

    public static int getPlayerSize() {
        return PLAYER_SIZE;
    }
}
