package com.dc.topology.common;

import java.util.*;

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

    /**
     * The returned list is unique.
     * If numNeighbors > size of listA + size of listB then returned list has nodes < numNeighbors.
     */
    public List<AbstractNode<T>> mergeLists(List<AbstractNode<T>> peerList) {
        neighbors.addAll(peerList);
        neighbors.sort(rankingFunction);

        Set<AbstractNode<T>> chosenNodes = new HashSet<>();
        List<AbstractNode<T>> finalList = new ArrayList<>();
        Iterator<AbstractNode<T>> iter = neighbors.iterator();
        while (finalList.size() < numNeighbors && iter.hasNext()) {
            AbstractNode<T> currNode = iter.next();
            if (!chosenNodes.contains(currNode)) {
                finalList.add(currNode);
                chosenNodes.add(currNode);
            }
        }
        return finalList;
    }

    public double sumOfDistances() {
        double totDistance = 0.0;
        for (AbstractNode<T> neighbor : neighbors) {
            totDistance += node.getDistance(neighbor.node);
        }
        return totDistance;
    }

}
