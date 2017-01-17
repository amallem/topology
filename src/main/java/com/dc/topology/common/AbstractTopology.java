package com.dc.topology.common;

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

}
