package com.dc.topology.dr;

import com.dc.topology.common.AbstractNode;
import com.dc.topology.common.AbstractTopology;
import com.dc.topology.common.Constants;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.AdjacencyListGraph;
import org.graphstream.stream.file.FileSinkImages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public class DynamicRingTopology extends AbstractTopology<DynamicRingNode> {

    private int MAX_RADIUS;

    private Iterator<String> radiusIter;

    public DynamicRingTopology(int numNodes, int numNeighbors, List<String> radii) {
        this.numNeighbors = numNeighbors;
        this.numNodes = numNodes;
        radiusIter = radii.iterator();
        this.MAX_RADIUS = Integer.parseInt(radiusIter.next());
        this.nodes = new ArrayList<>(numNodes);
    }

    /**
     * Create nodes in the network.
     * Initialize each node with its neighbors list.
     * Print sumOfDistances.
     */
    public void initialize() {
        double degree;
        Constants.CURRENT_RADIUS = MAX_RADIUS;
        for (int i = 0; i < numNodes; i++) {
            degree = 360 * ((double) i / (double) numNodes);
            DynamicRingNode currNode = new DynamicRingNode(degree);
            nodes.add(new AbstractNode<>(i, currNode, numNeighbors));
        }
        for (int i = 0; i < numNodes; i++) {
            nodes.get(i).neighbors = generateNeighbors(numNeighbors);
        }
        sumOfDistances();
    }

    /**
     * Execution Phase.
     * Print sumOfDistances every 5 iterations.
     * Increase r value every 3 iterations.
     * Re-Read r value every 5 iterations.
     */
    public void execute(int numIterations) {
        for (int i = 1; i <= numIterations; i++) {
            if (i % Constants.RADIUS_INC == 0 && Constants.CURRENT_RADIUS < MAX_RADIUS) {
                Constants.CURRENT_RADIUS++;
            }
            for (int j = 0; j < numNodes; j++) {
                performExchange(nodes.get(j));
            }
            if (i % Constants.RADIUS_READ == 0) {
                sumOfDistances();
                MAX_RADIUS = (radiusIter.hasNext()) ? Integer.parseInt(radiusIter.next()) : MAX_RADIUS;
            }
        }
    }

    public void plotGraph() {
        AdjacencyListGraph graph = new AdjacencyListGraph("Dynamic Ring Graph", false, true);

        FileSinkImages pic = new FileSinkImages(FileSinkImages.OutputType.png, FileSinkImages.Resolutions.HD720);
        pic.setLayoutPolicy(FileSinkImages.LayoutPolicy.COMPUTED_FULLY_AT_NEW_IMAGE);

        for (AbstractNode<DynamicRingNode> node : nodes) {
            Node currNode = graph.getNode(node.id);
            if (currNode != null) {
                graph.addNode(node.id);
            }
            for (AbstractNode<DynamicRingNode> neighbor : node.neighbors) {
                graph.addEdge(node.id + neighbor.id, node.id, neighbor.id);
            }
        }

        try {
            pic.writeAll(graph, "sample.png");
        } catch (IOException e) {
            System.out.println("Could not create image");
            e.printStackTrace();
        }
    }
}
