package com.dc.topology.cm;

import com.dc.topology.common.AbstractTopology;

import java.util.ArrayList;

/**
 * Created by mallem on 1/16/17.
 */
public class CrescentMoonTopology extends AbstractTopology<CrescentMoonNode> {

    public CrescentMoonTopology(int numNodes, int numNeighbors) {
        this.numNodes = numNodes;
        this.numNeighbors = numNeighbors;
        this.nodes = new ArrayList<>(numNodes);
    }

    //TODO: Similar to Dynamic Ring?
    public void initialize() {

    }

    /**
     * Execution Phase.
     * TODO
     */
    public void execute(int numIterations) {
        for (int i = 0; i < numIterations; i++) {
            for (int j = 0; j < numNodes; j++) {
                performExchange(nodes.get(j));
            }
            sumOfDistances();
        }
    }

    @Override
    public void plotGraph() {

    }
}
