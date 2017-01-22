package com.dc.topology.bt;

import com.dc.topology.common.Constants;
import com.dc.topology.common.Distance;

/**
 * Created by mallem on 1/16/17.
 */
public class BinaryTreeNode implements Distance<BinaryTreeNode> {

    int nodeValue;

    int xVal;

    int yVal;

    public BinaryTreeNode(int nodeValue, int xVal, int yVal) {
        this.nodeValue = nodeValue;
        this.xVal = Constants.SCALE_FACTOR * xVal;
        this.yVal = Constants.SCALE_FACTOR * yVal;
    }

    /**
     * Implementation of PseudoCode given in question.
     */
    public double getDistance(BinaryTreeNode node) {
        int bits = 32;
        int aLevel = bits;
        int bLevel = bits;
        int commonPrefix = 0;
        int a = nodeValue;
        int b = node.nodeValue;
        int mask = 1 << bits - 1;
        while ((mask & a) == 0) {
            a <<= 1;
            aLevel--;
        }
        while ((mask & b) == 0) {
            b <<= 1;
            bLevel--;
        }
        int length = Math.min(aLevel, bLevel);
        while ((mask & ~(a ^ b)) != 0 && length > 0) {
            b <<= 1;
            a <<= 1;
            commonPrefix++;
            length--;
        }
        return aLevel - commonPrefix + bLevel - commonPrefix;
    }

}
