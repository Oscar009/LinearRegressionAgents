package classes;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MultiplyArrays {

  // poner arraylist sin tipado
  public ArrayList<BigDecimal> getResult(ArrayList<BigDecimal> first, ArrayList<BigDecimal> second) {
    ArrayList<BigDecimal> result = new ArrayList<>();
    for (int i = 0; i < first.size(); i++) {
      result.add(first.get(i).multiply(second.get(i)));
    }
    return result;
  }

}
