package com.dc.topology.cm;

import com.dc.topology.common.Ranking;

import java.util.Comparator;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public class CrescentMoonNode implements Ranking<CrescentMoonNode> {
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
