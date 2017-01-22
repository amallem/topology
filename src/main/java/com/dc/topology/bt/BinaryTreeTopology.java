package com.dc.topology.bt;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;

import java.util.ArrayList;

/**
 * Created by mallem on 1/16/17.
 */
public class BinaryTreeTopology extends AbstractTopology<BinaryTreeNode> {

    public BinaryTreeTopology(int numNodes, int numNeighbors) {
        this.numNodes = numNodes;
        this.numNeighbors = numNeighbors;
        this.nodes = new ArrayList<>(numNodes);
    }

    //TODO : Not yet finalized.
    public void initialize() {
        for (int i = 1; i <= numNodes; i++) {
            nodes.add(new AbstractNode<>(i, new BinaryTreeNode(i), numNeighbors));
        }

        for (int i = 0; i < numNodes; i++) {
            nodes.get(i).neighbors = generateNeighbors(numNeighbors);
        }

        sumOfDistances();
    }

    /**
     * Execution Phase.
     * TODO : Print sumOfDistances every 5 iterations.
     * TODO : Increase r value every 3 iterations.
     * TODO : Re-Read r value every 5 iterations.
     */
    public void execute(int numIterations) {
        for (int i = 0; i < numIterations; i++) {
            for (int j = 0; j < numNodes; j++) {
                performExchange(nodes.get(j));
            }
            if (i % 5 == 0)
                sumOfDistances();
        }
    }

    @Override
    public void plotGraph() {

    }
}
