package com.brad.latch.level;

import com.brad.latch.util.Vector2i;

public class Node {

    public Vector2i tileVector;
    public Node parent;
    public double fCost;  // Combination cost
    public double gCost;  // Node-to-node cost
    public double hCost;  // Heuristic cost

    public Node(Vector2i tile, Node parent, double gCost, double hCost) {
        this.tileVector = tile;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = this.gCost + this.hCost;
    }

}
