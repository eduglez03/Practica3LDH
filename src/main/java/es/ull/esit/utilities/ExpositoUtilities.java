/**
 * @file ExpositoUtilities.java
 * @brief Clase que proporciona utilidades diversas para manejo de matrices,
 * cadenas, y archivos.
 */

package es.ull.esit.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpositoUtilities {

    /** @brief Ancho de columna por defecto */
    public static final int DEFAULT_COLUMN_WIDTH = 10;

    /** @brief Alineación a la izquierda */
    public static final int ALIGNMENT_LEFT = 1;

    /** @brief Alineación a la derecha */
    public static final int ALIGNMENT_RIGHT = 2;

    /**
     * @brief Obtiene la primera aparición de un elemento en un vector.
     * @param vector Vector de enteros en el que buscar.
     * @param element Elemento a buscar.
     * @return Índice de la primera aparición del elemento o -1 si no se encuentra.
     */

    private static int getFirstAppearance(int[] vector, int element) {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @brief Imprime el contenido de un archivo en consola.
     * @param file Ruta del archivo a leer.
     */
    public static void printFile(String file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            Logger.getLogger(ExpositoUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) { // Comprobar si reader no es null antes de cerrar
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExpositoUtilities.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * @brief Simplifica una cadena eliminando espacios extras y tabulaciones.
     * @param string Cadena a simplificar.
     * @return Cadena simplificada.
     */

    public static String simplifyString(String string) {
        string = string.replaceAll("\t", " ");
        for (int i = 0; i < 50; i++) {
            string = string.replaceAll("  ", " ");
        }
        string = string.trim();
        return string;
    }


    /**
     * @brief Multiplica dos matrices.
     * @param a Matriz de entrada a.
     * @param b Matriz de entrada b.
     * @return Matriz resultado de la multiplicación o null si no son compatibles.
     */
    public static double[][] multiplyMatrices(double a[][], double b[][]) {
        if (a.length == 0) {
            return new double[0][0];
        }
        if (a[0].length != b.length) {
            return null;
        }
        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;
        double ans[][] = new double[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }

    /**
     * @brief Escribe texto en un archivo.
     * @param file Ruta del archivo.
     * @param text Texto a escribir.
     * @throws IOException Si ocurre un error de escritura.
     */

    public static void writeTextToFile(String file, String text) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
            writer.flush();
        }
    }

    /**
     * @brief Formatea una cadena como entero o doble si es posible.
     * @param string Cadena a formatear.
     * @return Cadena formateada.
     */

    public static String getFormat(String string) {
        if (!ExpositoUtilities.isInteger(string)) {
            if (ExpositoUtilities.isDouble(string)) {
                double value = Double.parseDouble(string);
                string = ExpositoUtilities.getFormat(value);
            }
        }
        return string;
    }


    /**
     * @brief Formatea un número con tres decimales.
     * @param value Valor numérico.
     * @return Cadena formateada.
     */
    public static String getFormat(double value) {
        DecimalFormat decimalFormatter = new DecimalFormat("0.000");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        decimalFormatter.setDecimalFormatSymbols(symbols);
        return decimalFormatter.format(value);
    }


    /**
     * @brief Formatea un número con decimales específicos.
     * @param value Valor numérico.
     * @param zeros Número de decimales.
     * @return Cadena formateada.
     */
    public static String getFormat(double value, int zeros) {
        String format = "0.";
        for (int i = 0; i < zeros; i++) {
            format += "0";
        }
        DecimalFormat decimalFormatter = new DecimalFormat(format);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        decimalFormatter.setDecimalFormatSymbols(symbols);
        return decimalFormatter.format(value);
    }


    /**
     * @brief Formatea una cadena con un ancho específico.
     * @param string Cadena a formatear.
     * @param width Ancho de la cadena.
     * @return Cadena formateada.
     */
    public static String getFormat(String string, int width) {
        return ExpositoUtilities.getFormat(string, width, ExpositoUtilities.ALIGNMENT_RIGHT);
    }

    /**
     * @brief Formatea una cadena con un ancho y alineación específicos.
     * @param string Cadena a formatear.
     * @param width Ancho de la cadena.
     * @param alignment Alineación de la cadena.
     * @return Cadena formateada.
     */
    public static String getFormat(String string, int width, int alignment) {
        String format = "";
        if (alignment == ExpositoUtilities.ALIGNMENT_LEFT) {
            format = "%-" + width + "s";
        } else {
            format = "%" + 1 + "$" + width + "s";
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        String[] data = new String[]{string};
        return String.format(format, (Object[]) data);
    }

    /**
     * @brief Formatea un vector de cadenas con un ancho específico.
     * @param strings Vector de cadenas a formatear.
     * @param width Ancho de las cadenas.
     * @return Cadena formateada.
     */
    public static String getFormat(ArrayList<String> strings, int width) {
        String format = "";
        for (int i = 0; i < strings.size(); i++) {
            format += "%" + (i + 1) + "$" + width + "s";
        }
        String[] data = new String[strings.size()];
        for (int t = 0; t < strings.size(); t++) {
            data[t] = "" + ExpositoUtilities.getFormat(strings.get(t));
        }
        return String.format(format, (Object[]) data);
    }

    /**
     * @brief Formatea un vector de enteros con un ancho específico.
     * @param strings Vector de enteros a formatear.
     * @return Cadena formateada.
     */
    public static String getFormat(ArrayList<Integer> strings) {
        String format = "";
        for (int i = 0; i < strings.size(); i++) {
            format += "%" + (i + 1) + "$" + DEFAULT_COLUMN_WIDTH + "s";
        }
        Integer[] data = new Integer[strings.size()];
        for (int t = 0; t < strings.size(); t++) {
            data[t] = strings.get(t);
        }
        return String.format(format, (Object[]) data);
    }

    /**
     * @brief Formatea un vector de cadenas con un ancho específico.
     * @param strings Vector de cadenas a formatear.
     * @param width Ancho de las cadenas.
     * @return Cadena formateada.
     */
    public static String getFormat(String[] strings, int width) {
        int[] alignment = new int[strings.length];
        Arrays.fill(alignment, ExpositoUtilities.ALIGNMENT_RIGHT);
        int[] widths = new int[strings.length];
        Arrays.fill(widths, width);
        return ExpositoUtilities.getFormat(strings, widths, alignment);
    }


    /**
     * @brief Formatea un vector de cadenas con un ancho y alineación específicos.
     * @param matrixStrings Vector de cadenas a formatear.
     * @param width Ancho de las cadenas.
     * @return Cadena formateada.
     */
    public static String getFormat(String[][] matrixStrings, int width) {
        String result = "";
        for (int i = 0; i < matrixStrings.length; i++) {
            String[] strings = matrixStrings[i];
            int[] alignment = new int[strings.length];
            Arrays.fill(alignment, ExpositoUtilities.ALIGNMENT_RIGHT);
            int[] widths = new int[strings.length];
            Arrays.fill(widths, width);
            result += ExpositoUtilities.getFormat(strings, widths, alignment);
            if (i < (matrixStrings.length - 1)) {
                result += "\n";
            }
        }
        return result;
    }

    /**
     * @brief Formatea un vector de cadenas con un ancho específico.
     * @param strings Vector de cadenas a formatear.
     * @return Cadena formateada.
     */
    public static String getFormat(String[] strings) {
        int[] alignment = new int[strings.length];
        Arrays.fill(alignment, ExpositoUtilities.ALIGNMENT_RIGHT);
        int[] widths = new int[strings.length];
        Arrays.fill(widths, ExpositoUtilities.DEFAULT_COLUMN_WIDTH);
        return ExpositoUtilities.getFormat(strings, widths, alignment);
    }

    /**
     * @brief Formatea un vector de cadenas con un ancho especificos.
     * @param strings Vector de cadenas a formatear.
     * @param width Ancho de las cadenas.
     * @return Cadena formateada.
     */
    public static String getFormat(String[] strings, int[] width) {
        int[] alignment = new int[strings.length];
        Arrays.fill(alignment, ExpositoUtilities.ALIGNMENT_RIGHT);
        return ExpositoUtilities.getFormat(strings, width, alignment);
    }

    /**
     * @brief Formatea un vector de cadenas con un ancho y alineación específicos.
     * @param strings Vector de cadenas a formatear.
     * @param width Ancho de las cadenas.
     * @param alignment Alineación de las cadenas.
     * @return Cadena formateada.
     */
    public static String getFormat(String[] strings, int[] width, int[] alignment) {
        String format = "";
        for (int i = 0; i < strings.length; i++) {
            if (alignment[i] == ExpositoUtilities.ALIGNMENT_LEFT) {
                format += "%" + (i + 1) + "$-" + width[i] + "s";
            } else {
                format += "%" + (i + 1) + "$" + width[i] + "s";
            }
        }
        String[] data = new String[strings.length];
        for (int t = 0; t < strings.length; t++) {
            data[t] = "" + ExpositoUtilities.getFormat(strings[t]);
        }
        return String.format(format, (Object[]) data);
    }

    /**
     * @brief Comprueba si una cadena es un número entero.
     * @param str Cadena a comprobar.
     * @return Verdadero si es un número entero, falso en caso contrario.
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * @brief Comprueba si una cadena es un número doble.
     * @param str Cadena a comprobar.
     * @return Verdadero si es un número doble, falso en caso contrario.
     */
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * @brief Comprueba si una matriz es acrílica
     * @param distanceMatrix Cadena a comprobar.
     * @return Verdadero si es una matriz acrilica, falso en caso contrario.
     */
    public static boolean isAcyclic(int[][] distanceMatrix) {
        int numRealTasks = distanceMatrix.length - 2;
        int node = 1;
        boolean acyclic = true;
        while (acyclic && node <= numRealTasks) {
            if (ExpositoUtilities.thereIsPath(distanceMatrix, node)) {
                return false;
            }
            node++;
        }
        return true;
    }

    /**
     * @brief Comprueba si hay un camino en una matriz de distancias
     * @param distanceMatrix Matriz de distancias.
     * @param node Nodo a comprobar.
     * @return Verdadero si hay un camino, falso en caso contrario.
     */
    public static boolean thereIsPath(int[][] distanceMatrix, int node) {
        HashSet<Integer> visits = new HashSet<>();
        HashSet<Integer> noVisits = new HashSet<>();
        for (int i = 0; i < distanceMatrix.length; i++) {
            if (i != node) {
                noVisits.add(i);
            }
        }
        visits.add(node);
        while (!visits.isEmpty()) {
            Iterator<Integer> it = visits.iterator();
            int toCheck = it.next();
            visits.remove(toCheck);
            for (int i = 0; i < distanceMatrix.length; i++) {
                if (toCheck != i && distanceMatrix[toCheck][i] != Integer.MAX_VALUE) {
                    if (i == node) {
                        return true;
                    }
                    if (noVisits.contains(i)) {
                        noVisits.remove(i);
                        visits.add(i);
                    }
                }
            }
        }
        return false;
    }
}