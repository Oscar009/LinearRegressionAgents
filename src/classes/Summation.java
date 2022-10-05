package classes;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Summation {

  public BigDecimal getValue(ArrayList<BigDecimal> data) {
    BigDecimal valu= new BigDecimal(0);

    for (int index = 0; index < data.size(); index++) {
      valu = valu.add(data.get(index));
    }

    return valu;
  }

}
