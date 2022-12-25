/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.wartermark;

import oop.wartermark.util.*;
import java.text.DecimalFormat;
/**
 *
 * @author Hien PC
 */

public class Matrixx {
    private double[][] matrix;
    private int m, n;
    
    // Constructor
    public Matrixx(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrixx(Matrixx matrix) {
        this.matrix = matrix.getMatrix();
    }

    public Matrixx() {

    }
//    public Matrixx (int m, int n) {
//      this.m = m;
//      this.n = n;
//      matrix = new double[m][n];
//   }
//    public Matrixx (double[][] matrix) {
//      m = matrix.length;
//      n = matrix[0].length;
//      for (int i = 0; i < m; i++) {
//         if (matrix[i].length != n) {
//            throw new IllegalArgumentException("All rows must have the same length.");
//         }
//      }
//      this.matrix = matrix;
//   }

    // util.Matrix Row and Column Counter
    public int getRowCount() {
        return this.matrix.length;
    }

    public int getColumnCount() {
        return this.matrix[0].length;
    }

    public Matrixx transposeMatrix() {
        return new Matrixx(transpose());
    }

    public double[][] transpose() {
        int rows = getRowCount();
        int columns = getColumnCount();

        double[][] transposedMatrix = new double[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }

        return transposedMatrix;
    }

    private double[][] multiplicationMatrix(Matrixx multiplicand) {
        int multiplierRows = getRowCount();
        int multiplierColumns = getColumnCount();

        int multiplicandRows = multiplicand.getRowCount();
        int multiplicandColumns = multiplicand.getColumnCount();

        if (multiplierColumns != multiplicandRows) {
            throw new Error(String.format("Matrices inner dimensions need to be equal, found %s x [%s] and [%s] x %s",
                  multiplierRows, multiplierColumns, multiplicandRows, multiplicandColumns));
        }

        double[][] product = new double[multiplierRows][multiplicandColumns];
        double[][] r = multiplicand.getMatrix();

        for (int i = 0; i < multiplierRows; i++) {
            for (int j = 0; j < multiplicandColumns; j++) {
                for (int k = 0; k < multiplierColumns; k++) {
                    product[i][j] += matrix[i][k] * r[k][j];
                }
            }
        }
        return product;
    }

    public Matrixx multiplyMatrix(Matrixx multiplicand) {
        return new Matrixx(multiplicationMatrix(multiplicand));
    }

    private double[][] subMatrix(Matrixx b) {
        int rows = getRowCount();
        int columns = getColumnCount();
        double[][] sub = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sub[i][j] = this.matrix[i][j] - b.getMatrix()[i][j];
            }
        }
        return sub;
    }


    public Matrixx subtractionMatrix(Matrixx b) {
        return new Matrixx(subMatrix(b));
    }

    // Getter and Setter
    public double[][] getMatrix() {
        return this.matrix;
    }

    //Print matrix
    private void print(boolean format) {
        int columns = getColumnCount();
        for (double[] aMatrix : this.matrix) {
            for (int j = 0; j < columns; j++) {
                String val;
                if (format) {
                    val = new DecimalFormat("0.##").format(aMatrix[j]);
                } else {
                    val = aMatrix[j] + "";
                }
                while (val.length() < 6) {
                    val += " ";
                }

                System.out.print(val + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printMatrix() {
        print(false);
    }

    public void printMatrixTrim() {
        print(true);
    }

    public void createZeroIdentityFromVal(double vector, int size) {
        eh(vector, size, 0);
    }

    private void eh(double vector, int size, double val) {
        double[][] identity = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    identity[i][j] = vector;
                } else {
                    identity[i][j] = val;
                }
            }
        }

        this.matrix = identity;
    }

    void createZeroIdentityFromVector(double[] vector, int rows, int columns) {
        int size = vector.length;
        double[][] identity = new double[rows][columns];

        int index = size - 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == j) {
                    identity[j][j] = vector[index];
                    index--;
                } else {
                    identity[i][j] = 0;
                }
            }
        }
        this.matrix = identity;
    }

    void createUnitMatrix(Matrixx US) {
        int rows = US.getRowCount();
        int columns = US.getColumnCount();
        double[][] U = new double[rows][rows];
        double[][] temp = US.getMatrix();

        for (int i = 0; i < columns; i++) {
            double x = 0;

            for (int j = 0; j < rows; j++) {
                double y = Math.abs(temp[j][i]);
                x += y * y;
            }

            x = Math.sqrt(x);

            for (int j = 0; j < rows; j++) {
                U[j][i] = temp[j][i] / x;

            }

            for (int j = columns; j < rows; j++) {
                for (int k = columns; k < rows; k++) {
                    if (j != k & U[j][k] == 0) {
                        U[j][k] = 1;
                    }
                }
            }
        }

        this.matrix = U;
    }

    void convertToInt() {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                this.matrix[i][j] = (int) Math.round(matrix[i][j]);
            }
        }
    }

    public static double[][] rankedMatrixS(Matrixx matrix, int rank) {
        double[][] rankedMatrix = new double[rank][rank];
        for (int i = 0; i < rank; i++) {
            double[] rowMatrix = matrix.getMatrix()[i];
            System.arraycopy(rowMatrix, 0, rankedMatrix[i], 0, rank);
        }
        return rankedMatrix;
    }

    public static double[][] rankedMatrixUV(Matrixx matrix, int rank) {
        int rows = matrix.getRowCount();
        double[][] rankedMatrix = new double[rows][rank];
        for (int i = 0; i < rows; i++) {
            double[] rowMatrix = matrix.getMatrix()[i];
            System.arraycopy(rowMatrix, 0, rankedMatrix[i], 0, rank);
        }
        return rankedMatrix;
    }

    
}
