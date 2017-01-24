package com.dc.topology.common;


import javax.imageio.ImageIO;
import java.awt.*;
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

    public abstract void initialize();

    public void execute(int numIterations) {
        for (int i = 0; i < numIterations; i++) {
            for (int j = 0; j < numNodes; j++) {
                performExchange(nodes.get(j));
            }
            if (i % 5 == 0) {
                sumOfDistances(i);
                printNeighboursToFile(i);
                printGraphicToFile(i);
            }
        }
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
    }

    protected void printGraphicToFile(int iterCount) {
        String fileName = TOPOLOGY_ID + "_N" + numNodes + "_K" + numNeighbors + "_" + iterCount + ".png";
        BufferedImage img = new BufferedImage(20000, 15000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.BLUE);
        g2d.scale(1.0, -1.0);
        Font font = new Font("Serif", Font.PLAIN, 24);
        g2d.setFont(font);
        g2d.translate(10000, -14500);

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
    }

    protected abstract int getY(AbstractNode<T> node);

    protected abstract int getX(AbstractNode<T> node);
}
