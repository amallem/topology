package com.dc.topology.cm;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;
import com.dc.topology.common.Constants;

import java.util.ArrayList;

/**
 * Created by mallem on 1/16/17.
 */
public class CrescentMoonTopology extends AbstractTopology<CrescentMoonNode> {

    private final int innerRadius = 150;

    private final double ratio = 3.0 / 13.0;

    public CrescentMoonTopology(int numNodes, int numNeighbors) {
        this.numNodes = numNodes;
        this.numNeighbors = numNeighbors;
        this.nodes = new ArrayList<>(numNodes);
        this.origin_x = 0;
        this.origin_y = 0;
        this.image_dim = Constants.C_IMAGE_DIMENSION;
    }

    //TODO: Similar to Dynamic Ring?
    public void initialize() {
        int smallCircleNodes = (int) (numNodes * ratio);
        double degree;
        for (int i = 0; i < smallCircleNodes; i++) {
            degree = 90 + (180 * ((double) i / (double) smallCircleNodes));
            CrescentMoonNode currNode = new CrescentMoonNode(innerRadius * Math.cos(Math.toRadians(degree)),
                    innerRadius * Math.sin(Math.toRadians(degree)));
            nodes.add(new AbstractNode<>(i, currNode, numNeighbors));
        }

        for (int i = 0; i < (numNodes - smallCircleNodes); i++) {
            degree = 30 + (300 * ((double) i / (double) (numNodes - smallCircleNodes)));
            CrescentMoonNode currNode = new CrescentMoonNode((Math.sqrt(3) * innerRadius * -1) + 2 * innerRadius * Math.cos(Math.toRadians(degree)),
                    2 * innerRadius * Math.sin(Math.toRadians(degree)));
            nodes.add(new AbstractNode<>(i, currNode, numNeighbors));
        }

        for (int i = 0; i < numNodes; i++) {
            nodes.get(i).neighbors = generateNeighbors(numNeighbors);
        }
        sumOfDistances(0);
        printNeighboursToFile(1);
        printGraphicToFile(1);
    }

    @Override
    protected int getY(AbstractNode<CrescentMoonNode> aNode) {
        return (int) aNode.node.yVal;
    }

    @Override
    protected int getX(AbstractNode<CrescentMoonNode> aNode) {
        return (int) aNode.node.xVal;
    }
}
