package com.dc.topology.common;

import java.util.*;

/**
 * Created by mallem on 1/16/17.
 */
public abstract class AbstractTopology<T extends Distance<T>> {

    protected List<AbstractNode<T>> nodes;

    protected int numNodes;

    protected int numNeighbors;

    private Random rand = new Random();

    public abstract void initialize();

    public abstract void execute(int numIterations);

    public abstract void plotGraph();

    protected List<AbstractNode<T>> generateNeighbors(int numNeighbors) {

        Set<Integer> chosenSet = new HashSet<>(numNeighbors);
        List<AbstractNode<T>> newNodes = new ArrayList<>();
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
     * Choose destNode as a random neighbor of currNode.
     * Send peerList to destNode.
     * Get peerList from destNode.
     * Merge current and recvd peerList at currNode.
     * Merge current and recvd peerlist at destNode.
     */
    public void performExchange(AbstractNode<T> currNode) {
        AbstractNode<T> destNode = currNode.neighbors.get(rand.nextInt(numNeighbors));
        List<AbstractNode<T>> peerListFromDest = destNode.neighbors;
        List<AbstractNode<T>> peerListToDest = currNode.neighbors;
        currNode.neighbors = currNode.mergeLists(peerListFromDest);
        destNode.neighbors = destNode.mergeLists(peerListToDest);
    }

    public void sumOfDistances() {
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + " : " + nodes.get(i).sumOfDistances());
        }
    }

}
