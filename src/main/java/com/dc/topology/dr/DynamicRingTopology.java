package com.dc.topology.dr;

import com.dc.topology.common.AbstractTopology;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mallem on 1/16/17.
 */
public class DynamicRingTopology extends AbstractTopology {

    public List<Integer> radii;

    public List<DynamicRingNode> nodes;

    public DynamicRingTopology(int numNodes, int numNeighbors, List<Integer> radii) {
        this.numNeighbors = numNeighbors;
        this.numNodes = numNodes;
        this.radii = radii;
        this.nodes = new ArrayList<DynamicRingNode>(numNodes);
    }

    /**
     * Create nodes in the network.
     * Initialize each node with its neighbors list.
     */
    public void initialize() {
        double degree;
        for (int i = 0; i < numNodes; i++) {
            degree = 360 * (i / numNodes);
            nodes.add(new DynamicRingNode(degree, radii.get(0), numNeighbors));
        }

        for (int i = 0; i < numNodes; i++) {
            nodes.get(i).neighbors = generateNeighbors(numNeighbors);
        }
    }

    /**
     * Initialization Phase.
     * Neighbors maybe duplicate in the list.
     * The same node may also end up in its neighbor list.
     */
    private List<DynamicRingNode> generateNeighbors(int numNeighbors) {

        Random rand = new Random();
        List<DynamicRingNode> newNodes = new ArrayList<DynamicRingNode>();
        for (int i = 0; i < numNeighbors; i++) {
            newNodes.add(nodes.get(rand.nextInt(numNodes)));
        }

        return newNodes;
    }

    /**
     * Execution Phase.
     *
     * @param numIterations
     */
    public void execute(int numIterations) {

    }


    public void sumOfDistances() {

    }
}
