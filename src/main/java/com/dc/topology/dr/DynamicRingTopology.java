package com.dc.topology.dr;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;
import com.dc.topology.common.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public class DynamicRingTopology extends AbstractTopology<DynamicRingNode> {

    private int MAX_RADIUS;

    private Iterator<String> radiusIter;

    public DynamicRingTopology(int numNodes, int numNeighbors, List<String> radii) {
        this.numNeighbors = numNeighbors;
        this.numNodes = numNodes;
        radiusIter = radii.iterator();
        this.MAX_RADIUS = Integer.parseInt(radiusIter.next());
        this.nodes = new ArrayList<>(numNodes);
        this.TOPOLOGY_ID = "D";

    }

    /**
     * Create nodes in the network.
     * Initialize each node with its neighbors list.
     * Print sumOfDistances.
     */
    public void initialize() {
        double degree;
        Constants.CURRENT_RADIUS = MAX_RADIUS;
        for (int i = 0; i < numNodes; i++) {
            degree = 360 * ((double) i / (double) numNodes);
            DynamicRingNode currNode = new DynamicRingNode(degree);
            nodes.add(new AbstractNode<>(i, currNode, numNeighbors));
        }
        for (int i = 0; i < numNodes; i++) {
            nodes.get(i).neighbors = generateNeighbors(numNeighbors);
        }
        sumOfDistances(0);
        printNeighboursToFile(1);
        printGraphicToFile(1);
    }

    /**
     * Execution Phase.
     * Print sumOfDistances every 5 iterations.
     * Increase r value every 3 iterations.
     * Re-Read r value every 5 iterations.
     */
    @Override
    public void execute(int numIterations) {
        for (int i = 1; i <= numIterations; i++) {
            if (i % Constants.RADIUS_INC == 0 && Constants.CURRENT_RADIUS < MAX_RADIUS) {
                Constants.CURRENT_RADIUS++;
            }
            for (int j = 0; j < numNodes; j++) {
                performExchange(nodes.get(j));
            }
            if (i % Constants.RADIUS_READ == 0) {
                sumOfDistances(i);
                MAX_RADIUS = (radiusIter.hasNext()) ? Integer.parseInt(radiusIter.next()) : MAX_RADIUS;
                printNeighboursToFile(i);
                printGraphicToFile(i);
            }

        }

    }

    protected int getX(AbstractNode<DynamicRingNode> aNode) {

        return (int) (Constants.SCALE_FACTOR * Constants.CURRENT_RADIUS * Math.cos(aNode.node.angle));
    }

    protected int getY(AbstractNode<DynamicRingNode> aNode) {

        return (int) (Constants.SCALE_FACTOR * Constants.CURRENT_RADIUS * Math.sin(aNode.node.angle));
    }


}
