package com.dc.topology.dr;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public class DynamicRingTopology extends AbstractTopology {

    public List<Integer> radii;

    public List<AbstractNode<DynamicRingNode>> nodes;

    public DynamicRingTopology(int numNodes, int numNeighbors, List<Integer> radii) {
        this.numNeighbors = numNeighbors;
        this.numNodes = numNodes;
        this.radii = radii;
        this.nodes = new ArrayList<>(numNodes);
    }

    /**
     * Create nodes in the network.
     * Initialize each node with its neighbors list.
     * Print sumOfDistances.
     */
    public void initialize() {
        double degree;
        for (int i = 0; i < numNodes; i++) {
            degree = 360 * (i / numNodes);
            DynamicRingNode currNode = new DynamicRingNode(degree, radii.get(0));
            nodes.add(new AbstractNode<>(currNode, numNeighbors));
        }
        for (int i = 0; i < numNodes; i++) {
            nodes.get(i).neighbors = generateNeighbors(numNeighbors);
        }
        sumOfDistances();
    }

    /**
     * Initialization Phase.
     * Neighbors maybe duplicate in the list.
     * The same node may also end up in its neighbor list.
     */
    private List<AbstractNode<DynamicRingNode>> generateNeighbors(int numNeighbors) {

        List<AbstractNode<DynamicRingNode>> newNodes = new ArrayList<>();
        for (int i = 0; i < numNeighbors; i++) {
            newNodes.add(nodes.get(rand.nextInt(numNodes)));
        }

        return newNodes;
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
            sumOfDistances();
        }
    }


    public void sumOfDistances() {
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + " : " + nodes.get(i).sumOfDistances());
        }
    }
}
