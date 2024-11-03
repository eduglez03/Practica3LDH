package top;

import java.util.Arrays;

import es.ull.esit.utilities.ExpositoUtilities;

public class TOPTWSolution {
    public static final int NO_INITIALIZED = -1;
    private top.TOPTW problem;
    private int[] predecessors;
    private int[] successors;
    private double[] waitingTime;
    private int[] positionInRoute;

    private int[] routes;
    private int availableVehicles;
    private double objectiveFunctionValue;

    /**
     * Constructor de la clase TOPTWSolution.
     * @param problem Problema TOPTW.
     */
    public TOPTWSolution(top.TOPTW problem) {
        this.problem = problem;
        this.availableVehicles = this.problem.getVehicles();
        this.predecessors = new int[this.problem.getPOIs()+this.problem.getVehicles()];
        this.successors = new int[this.problem.getPOIs()+this.problem.getVehicles()];
        this.waitingTime = new double[this.problem.getPOIs()];
        this.positionInRoute = new int[this.problem.getPOIs()];
        Arrays.fill(this.predecessors, TOPTWSolution.NO_INITIALIZED);
        Arrays.fill(this.successors, TOPTWSolution.NO_INITIALIZED);
        Arrays.fill(this.waitingTime, TOPTWSolution.NO_INITIALIZED);
        Arrays.fill(this.positionInRoute, TOPTWSolution.NO_INITIALIZED);
        this.routes = new int[this.problem.getVehicles()];
        this.objectiveFunctionValue = TOPTWEvaluator.NO_EVALUATED;
    }

    /**
     * Método que inicializa la solución.
     */
    public void initSolution() {
        this.predecessors = new int[this.problem.getPOIs()+this.problem.getVehicles()];
        this.successors = new int[this.problem.getPOIs()+this.problem.getVehicles()];
        Arrays.fill(this.predecessors, TOPTWSolution.NO_INITIALIZED);
        Arrays.fill(this.successors, TOPTWSolution.NO_INITIALIZED);
        this.routes = new int[this.problem.getVehicles()];
        Arrays.fill(this.routes, TOPTWSolution.NO_INITIALIZED);
        this.routes[0] = 0;
        this.predecessors[0] = 0;
        this.successors[0] = 0;
        this.availableVehicles = this.problem.getVehicles() - 1;
    }

    /**
     * Metodo que comprueba si un nodo es un deposito.
     * @param c
     * @return verdadero si es un deposito, falso en caso contrario.
     */
    public boolean isDepot(int c) {
        for(int i = 0; i < this.routes.length; i++) {
            if(c==this.routes[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que comprueba si dos soluciones son iguales.
     * @param otherSolution Otra solución.
     * @return true si son iguales, false en caso contrario.
     */
    public boolean iguales(TOPTWSolution otherSolution) {
        for (int i = 0; i < this.predecessors.length; i++) {
            if (this.predecessors[i] != otherSolution.predecessors[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que devuelve el número de vehículos disponibles.
     * @return Número de vehículos disponibles.
     */
    public int getAvailableVehicles() {
        return this.availableVehicles;
    }

    /**
     * Método que devuelve el número de rutas creadas.
     * @return Número de rutas creadas.
     */
    public int getCreatedRoutes() {
        return this.problem.getVehicles() - this.availableVehicles;
    }

    /**
     * Método que devuelve la distancia entre dos nodos.
     * @param x Nodo x.
     * @param y Nodo y.
     * @return Distancia entre los nodos.
     */
    public double getDistance(int x, int y) {
        return this.problem.getDistance(x, y);
    }

    /**
     * Metodo que establece el número de vehículos disponibles.
     * @param availableVehicles
     */
    public void setAvailableVehicles(int availableVehicles) {
        this.availableVehicles = availableVehicles;
    }

    /**
     * Método que devuelve el predecesor de un nodo.
     * @param customer Nodo.
     * @return Predecesor del nodo.
     */
    public int getPredecessor(int customer) {
        return this.predecessors[customer];
    }

    /**
     * Método que devuelve los predecesores de los nodos.
     * @return Predecesores de los nodos.
     */
    public int[] getPredecessors() {
        return this.predecessors;
    }

    /**
     * Método que devuelve el problema TOPTW.
     * @return Problema TOPTW.
     */
    public top.TOPTW getProblem() {
        return this.problem;
    }

    /**
     * Método que devuelve el valor de la función objetivo.
     * @return Valor de la función objetivo.
     */
    public double getObjectiveFunctionValue() {
        return this.objectiveFunctionValue;
    }

    /**
     * Método que devuelve la posición de un nodo en la ruta.
     * @param customer Nodo.
     * @return Posición del nodo en la ruta.
     */
    public int getPositionInRoute(int customer) {
        return this.positionInRoute[customer];
    }

    /**
     * Método que devuelve los sucesores
     * @return sucesores de la ruta.
     */
    public int getSuccessor(int customer) {
        return this.successors[customer];
    }

    /**
     * Método que devuelve los sucesores de los nodos.
     * @return Sucesores de los nodos.
     */
    public int[] getSuccessors() {
        return this.successors;
    }

    /**
     * Método que devuelve el indice de la ruta.
     * @param index Índice de la ruta.
     * @return indice de la ruta.
     */
    public int getIndexRoute(int index) {
        return this.routes[index];
    }

    /**
     * Metodo que devuelve el tiempo de espera de un cliente.
     * @param customer
     * @return tiempo de espera del cliente.
     */
    public double getWaitingTime(int customer) {
        return this.waitingTime[customer];
    }

    /**
     * Metodo que establece la función objetivo.
     * @param objectiveFunctionValue
     */
    public void setObjectiveFunctionValue(double objectiveFunctionValue) {
        this.objectiveFunctionValue = objectiveFunctionValue;
    }

    /**
     * Metodo que establece una posición en la ruta.
     * @param customer
     * @param position
     */
    public void setPositionInRoute(int customer, int position) {
        this.positionInRoute[customer] = position;
    }

    /**
     * Metodo que establece el predecesor de un cliente.
     * @param customer
     * @param predecessor
     */
    public void setPredecessor(int customer, int predecessor) {
        this.predecessors[customer] = predecessor;
    }

    /**
     * Metodo que establece el sucesor de un cliente.
     * @param customer
     * @param succesor
     */
    public void setSuccessor(int customer, int succesor) {
        this.successors[customer] = succesor;
    }

    /**
     * Metodo que establece el tiempo de espera de un cliente.
     * @param customer
     * @param waitingTime
     */
    public void setWaitingTime(int customer, int waitingTime) {
        this.waitingTime[customer] = waitingTime;
    }

    /**
     * Metodo que devuelve la información de la solución.
     * @return información de la solución.
     */
    public String getInfoSolution() {
        final int COLUMN_WIDTH = 15;
        String text = "\n"+"NODES: " + this.problem.getPOIs() + "\n" + "MAX TIME PER ROUTE: " + this.problem.getMaxTimePerRoute() + "\n" + "MAX NUMBER OF ROUTES: " + this.problem.getMaxRoutes() + "\n";
        String textSolution = "\n"+"SOLUTION: "+"\n";
        double costTimeSolution = 0.0, fitnessScore = 0.0;
        boolean validSolution = true;
        for(int k = 0; k < this.getCreatedRoutes(); k++) { // rutas creadas
            String[] strings = new String[]{"\n" + "ROUTE " + k };
            int[] width = new int[strings.length];
            Arrays.fill(width, COLUMN_WIDTH);
            text += ExpositoUtilities.getFormat(strings, width) + "\n";
            strings = new String[]{"CUST NO.", "X COORD.", "Y. COORD.", "READY TIME", "DUE DATE", "ARRIVE TIME", " LEAVE TIME", "SERVICE TIME"};
            width = new int[strings.length];
            Arrays.fill(width, COLUMN_WIDTH);
            text += ExpositoUtilities.getFormat(strings, width) + "\n";
            strings = new String[strings.length];
            int depot = this.getIndexRoute(k);
            int pre=-1, suc=-1;
            double costTimeRoute = 0.0, fitnessScoreRoute = 0.0;
            pre = depot;
            int index = 0;
            strings[index++] = "" + pre;
            strings[index++] = "" + this.getProblem().getX(pre);
            strings[index++] = "" + this.getProblem().getY(pre);
            strings[index++] = "" + this.getProblem().getReadyTime(pre);
            strings[index++] = "" + this.getProblem().getDueTime(pre);
            strings[index++] = "" + 0;
            strings[index++] = "" + 0;
            strings[index++] = "" + this.getProblem().getServiceTime(pre);
            text += ExpositoUtilities.getFormat(strings, width);
            text += "\n";
            do {                // recorremos la ruta
                index = 0;
                suc = this.getSuccessor(pre);
                textSolution += pre+" - ";
                strings[index++] = "" + suc;
                strings[index++] = "" + this.getProblem().getX(suc);
                strings[index++] = "" + this.getProblem().getY(suc);
                strings[index++] = "" + this.getProblem().getReadyTime(suc);
                strings[index++] = "" + this.getProblem().getDueTime(suc);
                costTimeRoute += this.getDistance(pre, suc);
                if(costTimeRoute < (this.getProblem().getDueTime(suc))) {
                    if(costTimeRoute < this.getProblem().getReadyTime(suc)) {
                        costTimeRoute = this.getProblem().getReadyTime(suc);
                    }
                    strings[index++] = "" + costTimeRoute;
                    costTimeRoute +=  this.getProblem().getServiceTime(suc);
                    strings[index++] = "" + costTimeRoute;
                    strings[index++] = "" + this.getProblem().getServiceTime(pre);
                    if(costTimeRoute > this.getProblem().getMaxTimePerRoute()) { validSolution = false; }
                    fitnessScoreRoute += this.problem.getScore(suc);
                } else { validSolution = false; }
                pre = suc;
                text += ExpositoUtilities.getFormat(strings, width);
                text += "\n";
            } while(suc != depot);
            textSolution += suc+"\n";
            costTimeSolution += costTimeRoute;
            fitnessScore += fitnessScoreRoute;
        }
        textSolution += "FEASIBLE SOLUTION: "+validSolution+"\n"+"SCORE: "+fitnessScore+"\n"+"TIME COST: "+costTimeSolution+"\n";
        return textSolution+text;
    }

    /**
     * Metodo que evalua la solución.
     * @return valor de la función objetivo.
     */
    public double evaluateFitness() {
        double objectiveFunction = 0.0;
        double objectiveFunctionPerRoute = 0.0;
        for(int k = 0; k < this.getCreatedRoutes(); k++) {
            int depot = this.getIndexRoute(k);
            int pre=depot, suc = -1;
            do {
                suc = this.getSuccessor(pre);
                objectiveFunctionPerRoute = objectiveFunctionPerRoute + this.problem.getScore(suc);
                pre = suc;
            } while((suc != depot));
            objectiveFunction = objectiveFunction + objectiveFunctionPerRoute;
            objectiveFunctionPerRoute = 0.0;
        }
        return objectiveFunction;
    }

    /**
     * Metodo que añade una ruta.
     * @return deposito.
     */
    public int addRoute() {
        int depot = this.problem.getPOIs();
        depot++;
        int routePos = 1;
        for(int i = 0; i < this.routes.length; i++) {
            if(this.routes[i] != -1 && this.routes[i] != 0) {
                depot = this.routes[i];
                depot++;
                routePos = i+1;
            }
        }
        this.routes[routePos] = depot;
        this.availableVehicles--;
        this.predecessors[depot] = depot;
        this.successors[depot] = depot;
        this.problem.addNodeDepot();
        return depot;
    }

    /**
     * Metodo que imprime la solución.
     * @return valor de la función objetivo.
     */
    public double printSolution() {
        for(int k = 0; k < this.getCreatedRoutes(); k++) {
            int depot = this.getIndexRoute(k);
            int pre=depot, suc = -1;
            do {
                suc = this.getSuccessor(pre);
                System.out.print(pre+" - ");
                pre = suc;
            } while((suc != depot));
            System.out.println(suc+"  ");
        }
        double fitness = this.evaluateFitness();
        System.out.println("SC="+fitness);
        return fitness;
    }

}
