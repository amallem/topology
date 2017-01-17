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
