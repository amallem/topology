package com.dc.topology.dr;

import com.dc.topology.common.AbstractNode;

import java.util.Comparator;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public class DynamicRingNode extends AbstractNode<DynamicRingNode> {

    public double angle;

    public int radius;

    public DynamicRingNode(double degree, int radius, int numNeighbors) {
        this.angle = Math.toRadians(degree);
        this.radius = radius;
        this.numNeighbors = numNeighbors;
        rankingFunction = createRankingFunction();
    }

    public List<DynamicRingNode> merge(List<DynamicRingNode> peerNeighbors) {
        return null;
    }

    public Comparator<DynamicRingNode> createRankingFunction() {
        final DynamicRingNode curr = this;
        return new Comparator<DynamicRingNode>() {
            public int compare(DynamicRingNode o1, DynamicRingNode o2) {
                double dist1 = curr.getDistance(o1);
                double dist2 = curr.getDistance(o2);
                if (dist1 < dist2)
                    return -1;
                else if (dist1 > dist2)
                    return 1;
                else
                    return 0;
            }
        };
    }

    /**
     * Returns the result of Square(x1-x2) - Square(y1-y2).
     */
    public double getDistance(DynamicRingNode node) {
        double xDiff = Math.cos(this.angle) - Math.cos(node.angle);
        double yDiff = Math.sin(this.angle) - Math.sin(node.angle);
        return ((xDiff * xDiff) - (yDiff * yDiff));
    }
}
