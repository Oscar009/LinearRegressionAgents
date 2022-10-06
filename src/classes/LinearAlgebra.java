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
    BigDecimal aux= new BigDecimal(0);

    for (int index = 0; index < data.size(); index++) {
      aux = aux.add(data.get(index));
    }

    return aux;
  }


}
