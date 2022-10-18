package classes;

import java.math.BigDecimal;
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

    return aux;
  }

  public ArrayList<ArrayList<BigDecimal>> matrixMultiplication(ArrayList<ArrayList<BigDecimal>> matriz1,
      ArrayList<ArrayList<BigDecimal>> matriz2) {
    ArrayList<ArrayList<BigDecimal>> aux = new ArrayList<ArrayList<BigDecimal>>(matriz1.get(0).size());

    for (int i = 0; i < matriz1.size(); i++) {

      for (int j = 0; j < matriz2.get(0).size(); j++) {
        BigDecimal sum = new BigDecimal(0);
        System.out.print("[" + i + "][" + j + "]: ");
        for (int k = 0; k < matriz2.size(); k++) {
          sum = sum.add(matriz1.get(i).get(k).multiply(matriz2.get(k).get(j)));
        }
        System.out.println(sum);
      }
    }

    return aux;
  }

}
