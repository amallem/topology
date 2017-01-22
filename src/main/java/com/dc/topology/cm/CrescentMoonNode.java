package com.dc.topology.cm;

import com.dc.topology.common.Distance;

/**
 * Created by mallem on 1/16/17.
 */
public class CrescentMoonNode implements Distance<CrescentMoonNode> {

    double xVal;

    double yVal;

    public CrescentMoonNode(double xVal, double yVal) {
        this.xVal = xVal;
        this.yVal = yVal;
    }

    /**
     *
     */
    public double getDistance(CrescentMoonNode node) {
        double xDiff = this.xVal - node.xVal;
        double yDiff = this.yVal - node.yVal;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }
}
