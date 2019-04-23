package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.Node;
import com.brad.latch.util.Vector2i;

import java.util.List;

public abstract class AdvancedChaser extends Mob {

    private List<Node> path = null;

    public AdvancedChaser(final int x, final int y) {
        super(x, y);
    }

    @Override
    public void update() {
        time++;

        List<Player> playersInRange = level.getPlayersInRange(this, aggroRadius);
        if (playersInRange.size() > 0) {
            Player player = playersInRange.get(0);  // First player is client
            int xPlayer = (int) player.getX();
            int yPlayer = (int) player.getY();
            Vector2i start = new Vector2i((int) getX() >> 4, (int) getY() >> 4);
            Vector2i end = new Vector2i(xPlayer >> 4, yPlayer >> 4);
            if (time % 15 == 0) path = level.findPath(start, end);  // A* search algorithm
            if (path != null) {
                if (path.size() > 0) {
                    Vector2i vector = path.get(path.size() - 1).tile;
                    int xVectorPixel = vector.getX() << 4;
                    int yVectorPixel = vector.getY() << 4;
                    //  Fetch a newer node if the closest one has been reached
                    if (((int) x) == xVectorPixel && ((int) y) == yVectorPixel && path.size() != 1) {
                        path.remove(path.size() - 1);
                    }
                    if ((int) x < xVectorPixel) xDelta = moveSpeed;
                    else if ((int) x > xVectorPixel) xDelta = -moveSpeed;
                    else xDelta = 0;
                    if ((int) y < yVectorPixel) yDelta = moveSpeed;
                    else if ((int) y > yVectorPixel) yDelta = -moveSpeed;
                    else yDelta = 0;
                }
            } else {  // Random movement if the player cannot be reached
                randomDirection();
            }
        } else {  // Random movement if the player is out of range
            randomDirection();
        }
        moveToDirection();
        updateHealth();
        updateDamagedTargets();
    }

}
