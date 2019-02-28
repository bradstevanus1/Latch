package com.brad.rain.entity.mob;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.input.Keyboard;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private byte animate = 0;
    private final byte animationSpeed = 30; // Lower is faster
    private final byte firstAnimationFrame = animationSpeed / 3;
    private final byte secondAnimationFrame = (animationSpeed * 2) / 3;
    private boolean walking = false;

    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_forward;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        int xa = 0, ya = 0;
        if (animate < animationSpeed) animate++;
        else animate = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
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
                    Sprite.player_forward,
                    Sprite.player_forward_1,
                    Sprite.player_forward_2
            );
        }
        if (dir == 1) {
            sprite = getAnimatedSpriteDirection(
                    Sprite.player_side,
                    Sprite.player_side_1,
                    Sprite.player_side_2
            );
        }
        if (dir == 2) {
            sprite = getAnimatedSpriteDirection(
                    Sprite.player_back,
                    Sprite.player_back_1,
                    Sprite.player_back_2
            );
        }
        int flip = 0;
        if (dir == 3) {
            sprite = getAnimatedSpriteDirection(
                    Sprite.player_side,
                    Sprite.player_side_1,
                    Sprite.player_side_2
            );
            flip = 1;
        }
        screen.renderPlayer(x - 16, y - 16, sprite, flip);
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

}
