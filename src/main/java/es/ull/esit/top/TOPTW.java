package top;

import java.util.ArrayList;
import java.util.Arrays;

import es.ull.esit.utilities.ExpositoUtilities;

/**
 * Clase TOPTW. Representa un problema de rutas con ventanas de tiempo.
 */
public class TOPTW {
    private int nodes;
    private double[] x;
    private double[] y;
    private double[] score;
    private double[] readyTime;
    private double[] dueTime;
    private double[] serviceTime;
    private int vehicles;
    private int depots;
    private double maxTimePerRoute;
    private double maxRoutes;
    private double[][] distanceMatrix;

    /**
     * Constructor de la clase TOPTW.
     * @param nodes Número de nodos del problema.
     * @param routes Número de rutas del problema.
     */
    public TOPTW(int nodes, int routes) {
        this.nodes = nodes;
        this.depots = 0;
        this.x = new double[this.nodes + 1];
        this.y = new double[this.nodes + 1];
        this.score = new double[this.nodes + 1];
        this.readyTime = new double[this.nodes + 1];
        this.dueTime = new double[this.nodes + 1];
        this.serviceTime = new double[this.nodes + 1];
        this.distanceMatrix = new double[this.nodes + 1][this.nodes + 1];
        for (int i = 0; i < this.nodes + 1; i++) {
            for (int j = 0; j < this.nodes + 1; j++) {
                this.distanceMatrix[i][j] = 0.0;
            }
        }
        this.maxRoutes = routes;
        this.vehicles = routes;
    }

    /**
     * Metodo isDepot. Comprueba si un nodo es un depósito.
     * @param a
     * @return el nodo
     */
    public boolean isDepot(int a) {
        if(a > this.nodes) {
            return true;
        }
        return false;
    }

    /**
     * Metodo getDistance. Devuelve la distancia entre dos nodos.
     * @param route
     * @return la distancia
     */
    public double getDistance(int[] route) {
        double distance = 0.0;
        for (int i = 0; i < route.length - 1; i++) {
            int node1 = route[i];
            int node2 = route[i + 1];
            distance += this.getDistance(node1, node2);
        }
        return distance;
    }

    /**
     * Metodo getDistance. Devuelve la distancia entre dos nodos.
     * @param route
     * @return la distancia
     */
    public double getDistance(ArrayList<Integer> route) {
        double distance = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            int node1 = route.get(i);
            int node2 = route.get(i + 1);
            distance += this.getDistance(node1, node2);
        }
        return distance;
    }

    /**
     * Metodo getDistance. Devuelve la distancia entre dos nodos.
     * @param routes
     * @return la distancia
     */
    public double getDistance(ArrayList<Integer>[] routes) {
        double distance = 0.0;
        for (ArrayList<Integer> route : routes) {
            distance += this.getDistance(route);
        }
        return distance;
    }

    /**
     * Metodo getDistanceMatrix. Devuelve la matriz de distancias.
     * @return la matriz de distancias
     */
    public void calculateDistanceMatrix() {
        for (int i = 0; i < this.nodes + 1; i++) {
            for (int j = 0; j < this.nodes + 1; j++) {
                if (i != j) {
                    double diffXs = this.x[i] - this.x[j];
                    double diffYs = this.y[i] - this.y[j];
                    this.distanceMatrix[i][j] = Math.sqrt(diffXs * diffXs + diffYs * diffYs);
                    this.distanceMatrix[j][i] = this.distanceMatrix[i][j];
                } else {
                    this.distanceMatrix[i][j] = 0.0;
                }
            }
        }
    }

    /**
     * Método getDistanceMatrix. Devuelve la matriz de distancias.
     * @return el tiempo de la ruta
     */
    public double getMaxTimePerRoute() {
        return maxTimePerRoute;
    }

    /**
     * Método setMaxTimePerRoute. Establece el tiempo máximo por ruta.
     * @param maxTimePerRoute
     */
    public void setMaxTimePerRoute(double maxTimePerRoute) {
        this.maxTimePerRoute = maxTimePerRoute;
    }

    /**
     * Método getMaxRoutes. Devuelve el número máximo de rutas.
     * @return el número máximo de rutas
     */
    public double getMaxRoutes() {
        return maxRoutes;
    }

    /**
     * Método setMaxRoutes. Establece el número máximo de rutas.
     * @param maxRoutes
     */
    public void setMaxRoutes(double maxRoutes) {
        this.maxRoutes = maxRoutes;
    }

    /**
     * Método getPOIs. Devuelve el nodo
     * @return el nodo
     */
    public int getPOIs() {
        return this.nodes;
    }

    /**
     * Método getDistance.
     * @param i
     * @param j
     * @return la distancia entre dos nodos
     */
    public double getDistance(int i, int j) {
        if(this.isDepot(i)) { i=0; }
        if(this.isDepot(j)) { j=0; }
        return this.distanceMatrix[i][j];
    }

    /**
     * Metodo getTime
     * @param i
     * @param j
     * @return el tiempo entre dos nodos
     */

    public double getTime(int i, int j) {
        if(this.isDepot(i)) { i=0; }
        if(this.isDepot(j)) { j=0; }
        return this.distanceMatrix[i][j];
    }

    /**
     * Método getNodes. Devuelve el número de nodos.
     * @return el número de nodos
     */
    public int getNodes() {
        return this.nodes;
    }

    /**
     * Método setNodes. Establece el número de nodos.
     * @param nodes
     */
    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    /**
     * Método getX. Devuelve la coordenada x de un nodo.
     * @param index
     * @return la coordenada x
     */
    public double getX(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.x[index];
    }

    /**
     * Método setX. Establece la coordenada x de un nodo.
     * @param index
     * @param x
     */
    public void setX(int index, double x) {
        this.x[index] = x;
    }

    /**
     * Método getY. Devuelve la coordenada y de un nodo.
     * @param index
     * @return la coordenada y
     */
    public double getY(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.y[index];
    }

    /**
     * Método setY. Establece la coordenada y de un nodo.
     * @param index
     * @param y
     */
    public void setY(int index, double y) {
        this.y[index] = y;
    }

    /**
     * Método getScore. Devuelve la puntuación de un nodo.
     * @param index
     * @return la puntuación
     */
    public double getScore(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.score[index];
    }


    /**
     * Método getScore. Devuelve la puntuación de un nodo.
     * @return
     */
    public double[] getScore() {
        return this.score;
    }

    /**
     * Método setScore. Establece la puntuación de un nodo.
     * @param index
     * @param score
     */
    public void setScore(int index, double score) {
        this.score[index] = score;
    }

    /**
     * Método getReadyTime. Devuelve el tiempo de inicio de un nodo.
     * @param index
     * @return el tiempo de inicio
     */
    public double getReadyTime(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.readyTime[index];
    }

    /**
     * Método setReadyTime. Establece el tiempo de inicio de un nodo.
     * @param index
     * @param readyTime
     */
    public void setReadyTime(int index, double readyTime) {
        this.readyTime[index] = readyTime;
    }

    /**
     * Método getDueTime. Devuelve el tiempo de finalización de un nodo.
     * @param index
     * @return el tiempo de finalización
     */
    public double getDueTime(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.dueTime[index];
    }

    /**
     * Método setDueTime. Establece el tiempo de finalización de un nodo.
     * @param index
     * @param dueTime
     */
    public void setDueTime(int index, double dueTime) {
        this.dueTime[index] = dueTime;
    }

    /**
     * Método getServiceTime. Devuelve el tiempo de servicio de un nodo.
     * @param index
     * @return el tiempo de servicio
     */
    public double getServiceTime(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.serviceTime[index];
    }

    /**
     * Método setServiceTime. Establece el tiempo de servicio de un nodo.
     * @param index
     * @param serviceTime
     */
    public void setServiceTime(int index, double serviceTime) {
        this.serviceTime[index] = serviceTime;
    }

    /**
     * Método getVehicles. Devuelve el número de vehículos.
     * @return el número de vehículos
     */
    public int getVehicles() {
        return this.vehicles;
    }

    /**
     * Metodo toString de la clase TOPTW.
     * @return el texto
     */
    @Override
    public String toString() {
        final int COLUMN_WIDTH = 15;
        String text = "Nodes: " + this.nodes + "\n";
        String[] strings = new String[]{"CUST NO.", "XCOORD.", "YCOORD.", "SCORE", "READY TIME", "DUE DATE", "SERVICE TIME"};
        int[] width = new int[strings.length];
        Arrays.fill(width, COLUMN_WIDTH);
        text += ExpositoUtilities.getFormat(strings, width) + "\n";
        for (int i = 0; i < this.nodes; i++) {
            strings = new String[strings.length];
            int index = 0;
            //strings[index++] = Integer.toString("" + i);
            strings[index++] = Integer.toString(i);
            strings[index++] = "" + this.x[i];
            strings[index++] = "" + this.y[i];
            strings[index++] = "" + this.score[i];
            strings[index++] = "" + this.readyTime[i];
            strings[index++] = "" + this.dueTime[i];
            strings[index++] = "" + this.serviceTime[i];
            text += ExpositoUtilities.getFormat(strings, width);
            text += "\n";
        }
        text += "Vehicles: " + this.vehicles + "\n";
        strings = new String[]{"VEHICLE", "CAPACITY"};
        width = new int[strings.length];
        Arrays.fill(width, COLUMN_WIDTH);
        text += ExpositoUtilities.getFormat(strings, width) + "\n";
        return text;
    }

    /**
     * Método addNode. Añade un nodo.
     * @return el nodo
     */
    public int addNode() {
        this.nodes++;
        return this.nodes;
    }

    /**
     * Método addNodeDepot. Añade un nodo depósito.
     * @return el nodo depósito
     */
    public int addNodeDepot() {
        this.depots++;
        return this.depots;
    }
}