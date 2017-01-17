package com.dc.topology.bt;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public class BinaryTreeTopology extends AbstractTopology {

    public List<AbstractNode<BinaryTreeNode>> nodes;

    public BinaryTreeTopology(int numNodes, int numNeighbors) {
        this.numNodes = numNodes;
        this.numNeighbors = numNeighbors;
    }

    //TODO : Not yet finalized.
    public void initialize() {
        for (int i = 1; i <= numNodes; i++) {
            nodes.add(new AbstractNode<>(new BinaryTreeNode(i), numNeighbors));
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
    private List<AbstractNode<BinaryTreeNode>> generateNeighbors(int numNeighbors) {

        List<AbstractNode<BinaryTreeNode>> newNodes = new ArrayList<>();
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

    /**
     * Choose destNode as a random neighbor of currNode.
     * Send peerList to destNode.
     * Get peerList from destNode.
     * Merge current and recvd peerList at currNode.
     * Merge current and recvd peerlist at destNode.
     */
    private void performExchange(AbstractNode<BinaryTreeNode> currNode) {
        AbstractNode<BinaryTreeNode> destNode = currNode.neighbors.get(rand.nextInt(numNeighbors));
        List<AbstractNode<BinaryTreeNode>> peerListFromDest = destNode.neighbors;
        List<AbstractNode<BinaryTreeNode>> peerListToDest = currNode.neighbors;
        currNode.neighbors = currNode.mergeLists(peerListFromDest);
        destNode.neighbors = destNode.mergeLists(peerListToDest);
    }


    public void sumOfDistances() {
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + " : " + nodes.get(i).sumOfDistances());
        }
    }
}
