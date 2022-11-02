package classes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class LinearAlgebra {

  public ArrayList<BigDecimal> multiplyArrays(ArrayList<BigDecimal> first, ArrayList<BigDecimal> second) {
    ArrayList<BigDecimal> result = new ArrayList<>();
    for (int i = 0; i < first.size(); i++) {
      result.add(first.get(i).multiply(second.get(i)));
    }
    return result;
  }

  public BigDecimal summationArray(ArrayList<BigDecimal> data) {
    BigDecimal aux = new BigDecimal(0);

    for (int index = 0; index < data.size(); index++) {
      aux = aux.add(data.get(index));
    }

    return aux;
  }

  public void printMatriz(ArrayList<ArrayList<BigDecimal>> aList) {
    for (int i = 0; i < aList.size(); i++) {
      for (int j = 0; j < aList.get(i).size(); j++) {
        System.out.print(aList.get(i).get(j) + " ");
      }
      System.out.println();
    }
  }

  public ArrayList<ArrayList<BigDecimal>> matrixTranspose(ArrayList<ArrayList<BigDecimal>> matriz) {
    ArrayList<ArrayList<BigDecimal>> aux = new ArrayList<ArrayList<BigDecimal>>(matriz.get(0).size());

    for (int i = 0; i < matriz.get(0).size(); i++) {
      ArrayList<BigDecimal> temp = new ArrayList<BigDecimal>();
      for (int j = 0; j < matriz.size(); j++) {
        temp.add(matriz.get(j).get(i));
      }
      aux.add(temp);
    }

    return aux;
  }

  public ArrayList<ArrayList<BigDecimal>> inverseMatrix(ArrayList<ArrayList<BigDecimal>> matriz) {
    ArrayList<ArrayList<BigDecimal>> aux = new ArrayList<ArrayList<BigDecimal>>(matriz.get(0).size());

    // Calculo de determinante
    // Primero las 6 diagonales
    BigDecimal aux1 = matriz.get(0).get(0).multiply(matriz.get(1).get(1).multiply(matriz.get(2).get(2)));

    BigDecimal aux2 = matriz.get(0).get(1).multiply(matriz.get(2).get(1).multiply(matriz.get(0).get(2)));

    BigDecimal aux3 = matriz.get(2).get(0).multiply(matriz.get(0).get(1).multiply(matriz.get(1).get(2)));
    // menos
    BigDecimal aux4 = matriz.get(0).get(2).multiply(matriz.get(1).get(1).multiply(matriz.get(2).get(0)));

    BigDecimal aux5 = matriz.get(1).get(2).multiply(matriz.get(2).get(1).multiply(matriz.get(0).get(0)));

    BigDecimal aux6 = matriz.get(2).get(2).multiply(matriz.get(0).get(1).multiply(matriz.get(1).get(0)));
    // determinante
    BigDecimal determinant = aux1.add(aux2).add(aux3).subtract(aux4.add(aux5).add(aux6));
    // Calculamos la adjunta
    ArrayList<ArrayList<BigDecimal>> adjunta = new ArrayList<ArrayList<BigDecimal>>(matriz.get(0).size());
    // primera fila de matriz
    ArrayList<BigDecimal> f1 = new ArrayList<BigDecimal>(matriz.get(0).size());
    // 0-0 | 0-1 | 0-2
    f1.add(determinant2x2(matriz, 1, 1, 2, 2, 1, 2, 2, 1));
    f1.add(determinant2x2(matriz, 1, 0, 2, 2, 1, 2, 2, 0).multiply(new BigDecimal(-1)));
    f1.add(determinant2x2(matriz, 1, 0, 2, 1, 1, 1, 2, 0));
    // segunda fila de matriz
    ArrayList<BigDecimal> f2 = new ArrayList<BigDecimal>(matriz.get(0).size());
    f2.add(determinant2x2(matriz, 0, 1, 2, 2, 0, 2, 2, 1).multiply(new BigDecimal(-1)));
    f2.add(determinant2x2(matriz, 0, 0, 2, 2, 0, 2, 2, 0));
    f2.add(determinant2x2(matriz, 0, 0, 2, 1, 0, 1, 2, 0).multiply(new BigDecimal(-1)));
    // tercera fila de matriz
    ArrayList<BigDecimal> f3 = new ArrayList<BigDecimal>(matriz.get(0).size());
    f3.add(determinant2x2(matriz, 0, 1, 1, 2, 0, 2, 1, 1));
    f3.add(determinant2x2(matriz, 0, 0, 1, 2, 0, 2, 1, 0).multiply(new BigDecimal(-1)));
    f3.add(determinant2x2(matriz, 0, 0, 1, 1, 0, 1, 1, 0));

    adjunta.add(f1);
    adjunta.add(f2);
    adjunta.add(f3);
    // System.out.println(adjunta);

    for (int i = 0; i < matriz.size(); i++) {
      ArrayList<BigDecimal> row = new ArrayList<BigDecimal>(matriz.size());
      for (int j = 0; j < adjunta.get(0).size(); j++) {
        row.add(adjunta.get(i).get(j).divide(determinant, MathContext.DECIMAL64));
      }
      aux.add(row);
    }

    // System.out.println(aux);

    return aux;
  }

  public ArrayList<ArrayList<BigDecimal>> matrixMultiplication(ArrayList<ArrayList<BigDecimal>> matriz1,
      ArrayList<ArrayList<BigDecimal>> matriz2) {
    ArrayList<ArrayList<BigDecimal>> aux = new ArrayList<ArrayList<BigDecimal>>(matriz1.get(0).size());

    for (int i = 0; i < matriz1.size(); i++) {
      ArrayList<BigDecimal> row = new ArrayList<BigDecimal>(matriz1.size());
      for (int j = 0; j < matriz2.get(0).size(); j++) {
        BigDecimal sum = new BigDecimal(0);
        // System.out.print("[" + i + "][" + j + "]: ");
        for (int k = 0; k < matriz2.size(); k++) {
          sum = sum.add(matriz1.get(i).get(k).multiply(matriz2.get(k).get(j)));
        }
        // System.out.println(sum);
        row.add(sum);
      }
      aux.add(row);
    }

    return aux;
  }

  public BigDecimal determinant2x2(ArrayList<ArrayList<BigDecimal>> matriz, int i1, int j1, int i2, int j2, int i3,
      int j3, int i4, int j4) {
    return matriz.get(i1).get(j1).multiply(matriz.get(i2).get(j2))
        .subtract(matriz.get(i3).get(j3).multiply(matriz.get(i4).get(j4)));
  }

}
