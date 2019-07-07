/*
 * GraphTester.java
 *
 * Richard Kaune T00641439
 * COMP 2231_SW2 Assignment 5 Question 2
 * This is the GraphTester client code to test the implmentation of the Graph 
 * class in the use of an adjacency matrix
 */

package jsjf;

/**
 *
 * @author richardkaune
 */
public class GraphTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Graph G = new Graph();
        G.addVertex(1);
        G.addVertex(2);
        G.addVertex(3);
        G.addVertex(4);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(0, 3);
        System.out.println("Graph is connected: "+G.isConnected());
        System.out.println("" + G.toString());
    }
    
    
}
