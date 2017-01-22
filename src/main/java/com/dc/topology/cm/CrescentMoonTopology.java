package com.dc.topology.cm;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mallem on 1/16/17.
 */
public class CrescentMoonTopology extends AbstractTopology<CrescentMoonNode> {

    final int innerRadius = 150;

    final double ratio = 3.0 / 13.0;

    public CrescentMoonTopology(int numNodes, int numNeighbors) {
        this.numNodes = numNodes;
        this.numNeighbors = numNeighbors;
        this.nodes = new ArrayList<>(numNodes);
        setSize(5000, 5000);
    }

    //TODO: Similar to Dynamic Ring?
    public void initialize() {
        int smallCircleNodes = (int) (numNodes * ratio);
        double degree;
        for (int i = 0; i < smallCircleNodes; i++) {
            degree = 90 + (180 * ((double) i / (double) smallCircleNodes));
            CrescentMoonNode currNode = new CrescentMoonNode(innerRadius * Math.cos(Math.toRadians(degree)),
                    innerRadius * Math.sin(Math.toRadians(degree)));
            nodes.add(new AbstractNode<>(i, currNode, numNeighbors));
        }

        for (int i = 0; i < (numNodes - smallCircleNodes); i++) {
            degree = 30 + (300 * ((double) i / (double) (numNodes - smallCircleNodes)));
            CrescentMoonNode currNode = new CrescentMoonNode((Math.sqrt(3) * innerRadius * -1) + 2 * innerRadius * Math.cos(Math.toRadians(degree)),
                    2 * innerRadius * Math.sin(Math.toRadians(degree)));
            nodes.add(new AbstractNode<>(i, currNode, numNeighbors));
        }

        for (int i = 0; i < numNodes; i++) {
            nodes.get(i).neighbors = generateNeighbors(numNeighbors);
        }
        sumOfDistances();
        setVisible(true);
    }

    /**
     * Execution Phase.
     * TODO
     */
    public void execute(int numIterations) {
        for (int i = 0; i < numIterations; i++) {
            for (int j = 0; j < numNodes; j++) {
                performExchange(nodes.get(j));
            }
            sumOfDistances();
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        Font font = new Font("Serif", Font.PLAIN, 24);
        g2d.setFont(font);
        g2d.translate(400, 350);
        g2d.scale(1.0, -1.0);
        for (AbstractNode<CrescentMoonNode> node : nodes) {
            for (AbstractNode<CrescentMoonNode> neighbor : node.neighbors) {
                g.drawLine((int) node.node.xVal, (int) node.node.yVal, (int) neighbor.node.xVal, (int) neighbor.node.yVal);
            }
        }
    }
}
