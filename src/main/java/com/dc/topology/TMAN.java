package com.dc.topology;

import com.dc.topology.bt.BinaryTreeTopology;
import com.dc.topology.cm.CrescentMoonTopology;
import com.dc.topology.common.AbstractTopology;
import com.dc.topology.common.Constants;
import com.dc.topology.dr.DynamicRingTopology;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mallem on 1/16/17.
 */
public class TMAN {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int K = input.nextInt();
        String topologyId = input.next();
        AbstractTopology topology = null;

        switch (topologyId) {
            case "D":
                int n = input.nextInt();
                String[] radii = input.next().split(",");
                if (radii.length != n) {
                    System.out.println("Expected Radius values: " + n + " Provided: " + radii.length);
                    System.exit(0);
                } else {
                    List<String> radiusList = Arrays.asList(radii);
                    topology = new DynamicRingTopology(N, K, radiusList);
                }
                break;
            case "B":
                topology = new BinaryTreeTopology(N, K);
                break;
            case "C":
                topology = new CrescentMoonTopology(N, K);
                break;
            default:
                System.out.println("Invalid Topology ID Received: " + topologyId + " please use one among D, B, C");
                System.exit(0);
                break;
        }

        topology.initialize();
        topology.execute(Constants.Iterations);

    }
}
