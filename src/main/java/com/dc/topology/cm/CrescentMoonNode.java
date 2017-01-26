package com.dc.topology.cm;

import com.dc.topology.common.Constants;
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
        double aX = 0;
        double aY = Constants.CM_INNER_RADIUS;

        double bX = 0;
        double bY = -1 * Constants.CM_INNER_RADIUS;

        double distanceBetweenPoints = getDistanceUtil(this.xVal, this.yVal, node.xVal, node.yVal);

        double distanceFromA = getDistanceUtil(aX, aY, node.xVal, node.yVal);

        double distanceFromB = getDistanceUtil(bX, bY, node.xVal, node.yVal);


        return distanceBetweenPoints*2 + distanceFromA + distanceFromB;
//        double xDiff = this.xVal - node.xVal;
//        double yDiff = this.yVal - node.yVal;
//        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    private double getDistanceUtil(double ax, double ay, double bx, double by){
        return Math.sqrt((Math.pow(ax-bx, 2)) + (Math.pow(ay-by, 2)));
    }
}
