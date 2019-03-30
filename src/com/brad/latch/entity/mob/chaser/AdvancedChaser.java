package com.brad.latch.entity.mob.chaser;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.Node;
import com.brad.latch.util.Vector2i;

import java.util.List;

public abstract class AdvancedChaser extends Mob {

    private List<Node> path = null;

    public AdvancedChaser(int x, int y, Sprite sprite, double moveSpeed) {
        super(x, y, sprite, moveSpeed);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void update() {
        time++;
        xDelta = 0;
        yDelta = 0;

        List<Player> playersInRange = level.getPlayersInRange(this, aggroRadius);
        if (playersInRange.size() > 0) {
            Player player = playersInRange.get(0);  // First player is client
            int xPlayer = (int) player.getX();
            int yPlayer = (int) player.getY();
            Vector2i start = new Vector2i((int) getX() >> 4, (int) getY() >> 4);
            Vector2i end = new Vector2i(xPlayer >> 4, yPlayer >> 4);
            if (time % 3 == 0) path = level.findPath(start, end);  // A* search algorithm
            if (path != null) {
                if (path.size() > 0) {
                    Vector2i vector = path.get(path.size() - 1).tile;
                    if (x < vector.getX() << 4) xDelta += moveSpeed;
                    if (x > vector.getX() << 4) xDelta -= moveSpeed;
                    if (y < vector.getY() << 4) yDelta += moveSpeed;
                    if (y > vector.getY() << 4) yDelta -= moveSpeed;
                }
            } else {
                System.out.println("Got here");
                if (time % (random.nextInt(50) + 30) == 0) {
                    xDelta = (random.nextInt(3) - 1) * moveSpeed;
                    yDelta = (random.nextInt(3) - 1) * moveSpeed;
                    if (random.nextInt(3) == 0) {
                        xDelta = 0;
                        yDelta = 0;
                    }
                    if ((xDelta != 0) && (yDelta != 0)) {
                        if (random.nextBoolean()) xDelta = 0;
                        else yDelta = 0;
                    }
                }
            }
        }

        if (yDelta < 0) {
            dir = Direction.UP;
            animatedSprite = animatedSpriteUp;
        } else if (yDelta > 0) {
            dir = Direction.DOWN;
            animatedSprite = animatedSpriteDown;
        }
        if (xDelta < 0) {
            dir = Direction.LEFT;
            animatedSprite = animatedSpriteLeft;
        } else if (xDelta > 0) {
            dir = Direction.RIGHT;
            animatedSprite = animatedSpriteRight;
        }
        if (xDelta != 0 || yDelta != 0) {
            move(xDelta, yDelta);
            moving = true;
        } else {
            moving = false;
        }
        if (moving) animatedSprite.update();
        else animatedSprite.setFrame(0);
    }

}
