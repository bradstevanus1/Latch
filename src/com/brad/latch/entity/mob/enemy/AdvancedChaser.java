package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.mob.player.Player;
import com.brad.latch.graphics.Sprites;
import com.brad.latch.level.Node;
import com.brad.latch.level.tile.TileCoordinate;
import com.brad.latch.util.Vector2i;

import java.util.List;

public class AdvancedChaser extends Enemy {

    private List<Node> path = null;

    public AdvancedChaser(final TileCoordinate tileCoordinate) {
        super(tileCoordinate);
    }

    @Override
    public void update() {
        super.update();

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
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }

    public static AdvancedChaser Pokey(TileCoordinate tileCoordinate) {
        AdvancedChaser mob = new AdvancedChaser(tileCoordinate);

        mob.health = 8;
        mob.maxHealth = mob.health;
        mob.melee = true;
        mob.meleeDamage = 8;
        mob.aggroRadius = 500;
        mob.projectileRate = 0;
        mob.moveSpeed = 0.5;
        mob.meleeRate = 4.0;

        mob.name = "Pokey";
        mob.sprite = Sprites.pokey_down.getSprite();
        size = 32;
        mob.animatedSprite = Sprites.pokey_down;
        mob.animatedSpriteDown = Sprites.pokey_down;
        mob.animatedSpriteUp = Sprites.pokey_up;
        mob.animatedSpriteLeft = Sprites.pokey_left;
        mob.animatedSpriteRight = Sprites.pokey_right;

        return mob;
    }

}
