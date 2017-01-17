package com.dc.topology.common;

import com.dc.topology.dr.DynamicRingNode;

import java.util.List;
import java.util.Random;

/**
 * Created by mallem on 1/16/17.
 */
public abstract class AbstractTopology {

    public int numNodes;

    public int numNeighbors;

    public Random rand = new Random();

    public abstract void initialize();

    public abstract void execute(int numIterations);

    public abstract void sumOfDistances();

    /**
     * Choose destNode as a random neighbor of currNode.
     * Send peerList to destNode.
     * Get peerList from destNode.
     * Merge current and recvd peerList at currNode.
     * Merge current and recvd peerlist at destNode.
     */
    public void performExchange(AbstractNode<DynamicRingNode> currNode) {
        AbstractNode<DynamicRingNode> destNode = currNode.neighbors.get(rand.nextInt(numNeighbors));
        List<AbstractNode<DynamicRingNode>> peerListFromDest = destNode.neighbors;
        List<AbstractNode<DynamicRingNode>> peerListToDest = currNode.neighbors;
        currNode.neighbors = currNode.mergeLists(peerListFromDest);
        destNode.neighbors = destNode.mergeLists(peerListToDest);
    }

}
