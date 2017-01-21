package com.dc.topology.dr;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;
import com.dc.topology.common.Constants;

import java.util.*;

/**
 * Created by mallem on 1/16/17.
 */
public class DynamicRingTopology extends AbstractTopology {

    private int MAX_RADIUS;

    public Iterator<String> radiusIter;

    public List<AbstractNode<DynamicRingNode>> nodes;

    public DynamicRingTopology(int numNodes, int numNeighbors, List<String> radii) {
        this.numNeighbors = numNeighbors;
        this.numNodes = numNodes;
        radiusIter = radii.iterator();
        this.MAX_RADIUS = Integer.parseInt(radiusIter.next());
        this.nodes = new ArrayList<>(numNodes);
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
            degree = 360 * (new Double(i) / new Double(numNodes));
            DynamicRingNode currNode = new DynamicRingNode(degree);
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
     * List of neighbors are unique for better convergence.
     */
    private List<AbstractNode<DynamicRingNode>> generateNeighbors(int numNeighbors) {

        Set<Integer> chosenSet = new HashSet<>(numNeighbors);
        List<AbstractNode<DynamicRingNode>> newNodes = new ArrayList<>();
        for (int i = 0; i < numNeighbors; i++) {
            int currNum = rand.nextInt(numNodes);
            while (chosenSet.contains(currNum)) {
                currNum = rand.nextInt(numNodes);
            }
            chosenSet.add(currNum);
            newNodes.add(nodes.get(currNum));
        }

        return newNodes;
    }

    /**
     * Execution Phase.
     * Print sumOfDistances every 5 iterations.
     * Increase r value every 3 iterations.
     * Re-Read r value every 5 iterations.
     */
    public void execute(int numIterations) {
        for (int i = 1; i <= numIterations; i++) {
            if (i % Constants.RADIUS_INC == 0 && Constants.CURRENT_RADIUS < MAX_RADIUS) {
                Constants.CURRENT_RADIUS++;
            }
            for (int j = 0; j < numNodes; j++) {
                performExchange(nodes.get(j));
            }
            if (i % Constants.RADIUS_READ == 0) {
                sumOfDistances();
                MAX_RADIUS = (radiusIter.hasNext()) ? Integer.parseInt(radiusIter.next()) : MAX_RADIUS;
            }
        }
    }

    /**
     * Choose destNode as a random neighbor of currNode.
     * Add currNode to peerList being sent.
     * Send peerList to destNode.
     * Get peerList from destNode.
     * Merge current and received peerList at currNode.
     * Merge current and received peerList at destNode.
     */
    private void performExchange(AbstractNode<DynamicRingNode> currNode) {
        AbstractNode<DynamicRingNode> destNode = currNode.neighbors.get(rand.nextInt(numNeighbors));
        List<AbstractNode<DynamicRingNode>> peerListFromDest = destNode.neighbors;
        List<AbstractNode<DynamicRingNode>> peerListToDest = currNode.neighbors;
        peerListToDest.add(currNode);
        currNode.neighbors = currNode.mergeLists(peerListFromDest);
        destNode.neighbors = destNode.mergeLists(peerListToDest);
    }

    /**
     * TODO: Needs to change.
     */
    public void sumOfDistances() {
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + " : " + nodes.get(i).sumOfDistances());
        }
    }
}
