package com.dc.topology.dr;

import com.dc.topology.common.Constants;
import com.dc.topology.common.Distance;

/**
 * Created by mallem on 1/16/17.
 */
public class DynamicRingNode implements Distance<DynamicRingNode> {

    public double angle;

    public DynamicRingNode(double degree) {
        this.angle = Math.toRadians(degree);
    }

    /**
     * Returns the result of Math.sqrt(Square(x1-x2) - Square(y1-y2)).
     */
    public double getDistance(DynamicRingNode node) {
        double xDiff = Constants.CURRENT_RADIUS * (Math.cos(this.angle) - Math.cos(node.angle));
        double yDiff = Constants.CURRENT_RADIUS * (Math.sin(this.angle) - Math.sin(node.angle));
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }
}
