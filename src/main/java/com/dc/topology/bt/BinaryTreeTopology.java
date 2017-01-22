package com.dc.topology.bt;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mallem on 1/16/17.
 */
public class BinaryTreeTopology extends AbstractTopology<BinaryTreeNode> {

    public BinaryTreeTopology(int numNodes, int numNeighbors) {
        this.numNodes = numNodes;
        this.numNeighbors = numNeighbors;
        this.nodes = new ArrayList<>(numNodes);
        setSize(1500, 1500);
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

        sumOfDistances();
        setVisible(true);
    }

    /**
     * Execution Phase.
     * TODO : Print sumOfDistances every 5 iterations.
     * TODO : Increase r value every 3 iterations.
     * TODO : Re-Read r value every 5 iterations.
     */
    public void execute(int numIterations) {
        for (int i = 0; i < numIterations; i++) {
            for (int j = 0; j < numNodes; j++) {
                performExchange(nodes.get(j));
            }
            if (i % 5 == 0)
                sumOfDistances();
        }
        System.out.println("Execute finished");
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        Font font = new Font("Serif", Font.PLAIN, 24);
        g2d.setFont(font);
        g2d.translate(500, 500);
        g2d.scale(1.0, -1.0);
        for (AbstractNode<BinaryTreeNode> node : nodes) {
            for (AbstractNode<BinaryTreeNode> neighbor : node.neighbors) {
                g.drawLine(node.node.xVal, node.node.yVal, neighbor.node.xVal, neighbor.node.yVal);
            }
        }
    }
}
