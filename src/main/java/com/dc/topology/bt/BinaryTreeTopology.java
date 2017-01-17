package com.dc.topology.bt;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;

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

    }


    public void execute(int numIterations) {

    }

    public void sumOfDistances() {
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + " : " + nodes.get(i).sumOfDistances());
        }
    }
}
