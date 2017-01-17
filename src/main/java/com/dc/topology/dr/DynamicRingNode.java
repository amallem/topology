package com.dc.topology.dr;

import com.dc.topology.common.Ranking;

/**
 * Created by mallem on 1/16/17.
 */
public class DynamicRingNode implements Ranking<DynamicRingNode> {

    public double angle;

    public int radius;

    public DynamicRingNode(double degree, int radius) {
        this.angle = Math.toRadians(degree);
        this.radius = radius;
    }

    /**
     * Returns the result of Square(x1-x2) - Square(y1-y2).
     * TODO: Radius is not yet used.
     */
    public double getDistance(DynamicRingNode node) {
        double xDiff = Math.cos(this.angle) - Math.cos(node.angle);
        double yDiff = Math.sin(this.angle) - Math.sin(node.angle);
        return ((xDiff * xDiff) - (yDiff * yDiff));
    }
}
