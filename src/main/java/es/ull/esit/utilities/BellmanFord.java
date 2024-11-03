package es.ull.esit.utilities;

import java.util.ArrayList;

/**
 * Class that implements the Bellman-Ford algorithm.
 */
public class BellmanFord {
    /**
     * Value of infinity.
     */
    private static final int INFINITY = 999999;
    /**
     * Distance matrix of the graph.
     */
    private final int[][] distanceMatrix;
    /**
     * Edges of the graph.
     */
    private ArrayList<Integer> edges1 = null;
    /**
     * Edges of the graph.
     */
    private ArrayList<Integer> edges2 = null;
    /**
     * Number of nodes.
     */
    private final int nodes;
    /**
     * Path of the algorithm.
     */
    private final ArrayList<Integer> path;
    /**
     * Distances of the nodes.
     */
    private int[] distances = null;
    /**
     * Value of the path.
     */
    private int value;

    /**
     * Constructor of the class.
     * @param distanceMatrix
     * @param nodes
     * @param path
     */
    public BellmanFord(int[][] distanceMatrix, int nodes, ArrayList<Integer> path) {
        this.distanceMatrix = distanceMatrix;
        this.nodes = nodes;
        this.path = path;
        this.calculateEdges();
        this.value = BellmanFord.INFINITY;
    }

    /**
     * Calculates the edges of the graph.
     */
    private void calculateEdges() {
        this.edges1 = new ArrayList<>();
        this.edges2 = new ArrayList<>();
        for (int i = 0; i < this.nodes; i++) {
            for (int j = 0; j < this.nodes; j++) {
                if (this.distanceMatrix[i][j] != Integer.MAX_VALUE) {
                    this.edges1.add(i);
                    this.edges2.add(j);
                }
            }
        }
    }

    /**
     * get the distances
     * @return
     */
    public int[] getDistances() {
        return this.distances;
    }

    /**
     * get the value of the path
     * @return
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Solves the Bellman-Ford algorithm.
     */
    public void solve() {
        int numEdges = this.edges1.size();
        int[] predecessor = new int[this.nodes];
        this.distances = new int[this.nodes];
        for (int i = 0; i < this.nodes; i++) {
            this.distances[i] = BellmanFord.INFINITY;
            predecessor[i] = -1;
        }
        this.distances[0] = 0;
        for (int i = 0; i < (this.nodes - 1); i++) {
            for (int j = 0; j < numEdges; j++) {
                int u = this.edges1.get(j);
                int v = this.edges2.get(j);
                if (this.distances[v] > this.distances[u] + this.distanceMatrix[u][v]) {
                    this.distances[v] = this.distances[u] + this.distanceMatrix[u][v];
                    predecessor[v] = u;
                }
            }
        }
        this.path.add(this.nodes - 1);
        int pred = predecessor[this.nodes - 1];
        while (pred != -1) {
            this.path.add(pred);
            pred = predecessor[pred];
        }
        this.value = -this.distances[this.nodes - 1];
    }
}
