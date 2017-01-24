package com.dc.topology.bt;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;

import java.util.ArrayList;

/**
 * Created by mallem on 1/16/17.
 */
public class BinaryTreeTopology extends AbstractTopology<BinaryTreeNode> {

    public BinaryTreeTopology(int numNodes, int numNeighbors) {
        this.numNodes = numNodes;
        this.numNeighbors = numNeighbors;
        this.nodes = new ArrayList<>(numNodes);
        this.TOPOLOGY_ID = "B";
    }

    //TODO : Not yet finalized.
    public void initialize() {
        int curLevel = (int) (Math.log(numNodes + 1) / Math.log(2));
        curLevel++;
        int numNodesAtCurLevel = 1;
        int nodeNumber = 1;

        nodes.add(new AbstractNode<>(nodeNumber, new BinaryTreeNode(nodeNumber, 0, curLevel), numNeighbors));

        nodeNumber++;
        numNodesAtCurLevel *= 2;
        curLevel--;

        while (curLevel > 0) {
            int leftSideNodeCount = numNodesAtCurLevel / 2;

            for (int i = leftSideNodeCount; i > 0 && nodeNumber <= numNodes; i--) {
                nodes.add(new AbstractNode<>(nodeNumber, new BinaryTreeNode(nodeNumber, -i, curLevel), numNeighbors));
                nodeNumber++;
            }

            for (int i = 1; i <= leftSideNodeCount && nodeNumber <= numNodes; i++) {
                nodes.add(new AbstractNode<>(nodeNumber, new BinaryTreeNode(nodeNumber, i, curLevel), numNeighbors));
                nodeNumber++;
            }

            numNodesAtCurLevel *= 2;
            curLevel--;
        }

        for (int i = 0; i < numNodes; i++) {
            nodes.get(i).neighbors = generateNeighbors(numNeighbors);
        }

        sumOfDistances(0);
        printNeighboursToFile(1);
        printGraphicToFile(1);
    }

    @Override
    protected int getY(AbstractNode<BinaryTreeNode> aNode) {
        return 1000 * aNode.node.yVal;
    }

    @Override
    protected int getX(AbstractNode<BinaryTreeNode> aNode) {
        return 20 * aNode.node.xVal;
    }


}
