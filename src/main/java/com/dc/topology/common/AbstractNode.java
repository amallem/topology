package com.dc.topology.common;

import java.util.Comparator;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public class AbstractNode<T extends Ranking<T>> {

    public T node;

    public int numNeighbors;

    public List<AbstractNode<T>> neighbors;

    public Comparator<AbstractNode<T>> rankingFunction;

    public AbstractNode(T node, int numNeighbors) {
        this.node = node;
        this.numNeighbors = numNeighbors;
        this.rankingFunction = createRankingFunction();
    }

    private Comparator<AbstractNode<T>> createRankingFunction() {
        return (AbstractNode<T> o1, AbstractNode<T> o2) -> {
            double dist1 = this.node.getDistance(o1.node);
            double dist2 = this.node.getDistance(o2.node);
            if (dist1 < dist2)
                return -1;
            else if (dist1 > dist2)
                return 1;
            else
                return 0;
        };
    }

    public List<AbstractNode<T>> mergeLists(List<AbstractNode<T>> peerList) {
        neighbors.addAll(peerList);
        neighbors.sort(rankingFunction);
        return neighbors.subList(0, Math.min(numNeighbors, neighbors.size()));
    }

    public double sumOfDistances() {
        double totDistance = 0.0;
        for (AbstractNode<T> neighbor : neighbors) {
            totDistance += node.getDistance(neighbor.node);
        }
        return totDistance;
    }

}
