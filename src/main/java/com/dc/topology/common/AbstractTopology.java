package com.dc.topology.common;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by mallem on 1/16/17.
 */
public abstract class AbstractTopology<T extends Distance<T>> {

    protected List<AbstractNode<T>> nodes;

    protected int numNodes;

    protected int numNeighbors;

    private Random rand = new Random();

    protected String TOPOLOGY_ID;

    protected int origin_x;

    protected int origin_y;

    protected int image_dim;

    public abstract void initialize();

    private DefaultCategoryDataset lineChartDataset = new DefaultCategoryDataset();

    public void execute(int numIterations) {
        for (int i = 1; i <= numIterations; i++) {
            for (int j = 0; j < numNodes; j++) {
                performExchange(nodes.get(j));
            }
            if (i % 5 == 0) {
                sumOfDistances(i);
                printNeighboursToFile(i);
                printGraphicToFile(i);
            }
        }
        createDistanceChart();
    }

    protected List<AbstractNode<T>> generateNeighbors(int numNeighbors) {

        Set<Integer> chosenSet = new HashSet<>(numNeighbors);
        List<AbstractNode<T>> newNodes = new ArrayList<>();
        for (int i = 0; i < numNeighbors; i++) {
            int currNum = rand.nextInt(numNodes);
            while (chosenSet.contains(currNum)) {
                currNum = rand.nextInt(numNodes);
            }
            chosenSet.add(currNum);
            newNodes.add(nodes.get(currNum));
        }

        return newNodes;
    }

    /**
     * Choose destNode as a random neighbor of currNode.
     * Send peerList to destNode.
     * Get peerList from destNode.
     * Merge current and recvd peerList at currNode.
     * Merge current and recvd peerlist at destNode.
     */
    protected void performExchange(AbstractNode<T> currNode) {
        AbstractNode<T> destNode = currNode.neighbors.get(rand.nextInt(numNeighbors));
        List<AbstractNode<T>> peerListFromDest = destNode.neighbors;
        List<AbstractNode<T>> peerListToDest = currNode.neighbors;
        currNode.neighbors = currNode.mergeLists(peerListFromDest);
        destNode.neighbors = destNode.mergeLists(peerListToDest);
    }

    protected void sumOfDistances(int cycleNum) {
        double totDistance = 0.0;
        for (int i = 0; i < numNodes; i++) {
            totDistance += nodes.get(i).sumOfDistances();
        }

        System.out.println(cycleNum + " : " + totDistance);
        lineChartDataset.addValue(totDistance, "iterations", "" + cycleNum);

        String fileName = TOPOLOGY_ID + "_N" + numNodes + "_K" + numNeighbors + ".txt";
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(cycleNum + " " + totDistance);
        } catch (IOException e) {
            System.out.println("Unable to create File : " + fileName);
        }

    }

    protected void printNeighboursToFile(int iterCount) {
        System.out.println("Printing Node Neighbors to File for iteration: " + iterCount);
        String fileName = TOPOLOGY_ID + "_N" + numNodes + "_K" + numNeighbors + "_" + iterCount + ".txt";
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (AbstractNode<T> node : nodes) {
                out.print(node.id + " -> ");
                for (AbstractNode<T> neighbor : node.neighbors) {
                    out.print(neighbor.id + ", ");
                }
                out.println();
            }
        } catch (IOException e) {
            System.out.println("Unable to create File : " + fileName);
        }
        System.out.println("Finished printing neighbors to file.");
    }

    protected void printGraphicToFile(int iterCount) {
        System.out.println("Creating topology plot for iteration: " + iterCount);
        String fileName = TOPOLOGY_ID + "_N" + numNodes + "_K" + numNeighbors + "_" + iterCount + ".png";
        BufferedImage img = new BufferedImage(image_dim, image_dim, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.BLUE);
        g2d.scale(1.0, -1.0);
        Font font = new Font("Serif", Font.PLAIN, 400);

        g2d.translate(origin_x, origin_y);
        AffineTransform transform = new AffineTransform();
        transform.scale(1.0, -1.0);
        g2d.setFont(font.deriveFont(transform));
        g2d.drawString("Iteration: " + iterCount, -1 * (origin_x - 500), -1 * (origin_y + 500));

        for (AbstractNode<T> node : nodes) {
            for (AbstractNode<T> neighbor : node.neighbors) {
                g2d.drawLine(getX(node), getY(node), getX(neighbor), getY(neighbor));
            }
        }

        try {
            ImageIO.write(img, "PNG", new File(fileName));
        } catch (IOException e) {
            System.out.println("Exception during image creation occurred");
        }
        System.out.println("Finished storing topology plot as png file.");
    }

    protected void createDistanceChart() {
        System.out.println("Creating Distance Chart for Topology");
        String fileName = TOPOLOGY_ID + "_N" + numNodes + "_K" + numNeighbors;
        JFreeChart lineChart = ChartFactory.createLineChart("Sum_of_Distances vs Iterations",
                "Iterations", "Sum of Distances", lineChartDataset,
                PlotOrientation.VERTICAL, true, true, false);
        File file = new File(fileName + ".png");
        try {
            ChartUtilities.saveChartAsPNG(file, lineChart, 640, 480);
        } catch (IOException e) {
            System.out.println("Unable to plot distance line chart. IOException Occurred!!!");
        }
        System.out.println("Finished printing Distance Chart.");
    }

    protected abstract int getY(AbstractNode<T> node);

    protected abstract int getX(AbstractNode<T> node);
}
