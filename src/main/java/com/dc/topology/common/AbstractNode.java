package com.dc.topology.common;

import java.util.Comparator;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public abstract class AbstractNode<T> {

    public int numNeighbors;

    public List<T> neighbors;

    public Comparator<T> rankingFunction;

    public abstract List<T> merge(List<T> peerNeighbors);

    public abstract Comparator<T> createRankingFunction();

    public abstract double getDistance(T node);

}
