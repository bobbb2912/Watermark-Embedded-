/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.wartermark;

import oop.wartermark.util.*;
import java.util.List;
/**
 *
 * @author Hien PC
 */
public class SVD {
      private double[][] S;
    private double[][] U;
    private double[][] V;

    public Matrixx compose() {
        Matrixx matrixS = new Matrixx(S);
        Matrixx matrixU = new Matrixx(U);
        Matrixx matrixV = new Matrixx(V);

        Matrixx US = matrixU.multiplyMatrix(matrixS);
        Matrixx composed = US.multiplyMatrix(matrixV);
        composed.convertToInt();

        return composed;
    }

    public void createS(double[] eigenValues, int rows, int columns) {
        for (int i = 0, eigenLength = eigenValues.length; i < eigenLength; i++) {
            eigenValues[i] = Math.sqrt(eigenValues[i]);
        }

        Matrixx matrixS = new Matrixx();
        matrixS.createZeroIdentityFromVector(eigenValues, rows, columns);
        S = matrixS.getMatrix();
    }

    public void createV(List<Matrixx> eigenMatrices) {
        Matrixx V = new Matrixx(
              new Jama.Matrix(eigenMatrices
                    .get(0)
                    .getMatrix())
                    .eig()
                    .getV()
                    .getArray());

        double[][] e = V.getMatrix();
        int size = e.length;
        for (int i = 0; i < e.length; i++) {
            double[] temp = e[i];
            e[i] = e[size - 1];
            e[size - 1] = temp;
        }

        this.V = new Matrixx(e).transposeMatrix().getMatrix();
    }

    public void createU(Matrixx matrix) {
        Matrixx US = new Matrixx(matrix.multiplyMatrix(new Matrixx(this.V)));

        Matrixx U = new Matrixx();
        U.createUnitMatrix(US);

        this.U = U.getMatrix();
    }

    public double[][] getS() {
        return this.S;
    }

    public double[][] getU() {
        return this.U;
    }

    public double[][] getV() {
        return this.V;
    }
}
