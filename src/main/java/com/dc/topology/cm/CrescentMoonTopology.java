package com.dc.topology.cm;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;

import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public class CrescentMoonTopology extends AbstractTopology {

    public List<AbstractNode<CrescentMoonNode>> nodes;

    public CrescentMoonTopology(int numNodes, int numNeighbors) {
        this.numNodes = numNodes;
        this.numNeighbors = numNeighbors;
    }

    //TODO: Similar to Dynamic Ring?
    public void initialize() {

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

    /**
     * Choose destNode as a random neighbor of currNode.
     * Send peerList to destNode.
     * Get peerList from destNode.
     * Merge current and recvd peerList at currNode.
     * Merge current and recvd peerlist at destNode.
     */
    private void performExchange(AbstractNode<CrescentMoonNode> currNode) {
        AbstractNode<CrescentMoonNode> destNode = currNode.neighbors.get(rand.nextInt(numNeighbors));
        List<AbstractNode<CrescentMoonNode>> peerListFromDest = destNode.neighbors;
        List<AbstractNode<CrescentMoonNode>> peerListToDest = currNode.neighbors;
        currNode.neighbors = currNode.mergeLists(peerListFromDest);
        destNode.neighbors = destNode.mergeLists(peerListToDest);
    }

    public void sumOfDistances() {
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + " : " + nodes.get(i).sumOfDistances());
        }
    }

}
