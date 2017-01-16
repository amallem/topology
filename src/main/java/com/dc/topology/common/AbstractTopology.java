package com.dc.topology.common;

/**
 * Created by mallem on 1/16/17.
 */
public abstract class AbstractTopology {

    public int numNodes;

    public int numNeighbors;

    public abstract void initialize();

    public abstract void execute(int numIterations);

    public abstract void sumOfDistances();

}
