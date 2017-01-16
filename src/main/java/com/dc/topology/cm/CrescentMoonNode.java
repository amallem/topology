package com.dc.topology.cm;

import com.dc.topology.common.AbstractNode;

import java.util.Comparator;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public class CrescentMoonNode extends AbstractNode<CrescentMoonNode> {
    public List<CrescentMoonNode> merge(List<CrescentMoonNode> peerNeighbors) {
        return null;
    }

    public Comparator<CrescentMoonNode> createRankingFunction() {
        return null;
    }

    public double getDistance(CrescentMoonNode node) {
        return 0;
    }
}
