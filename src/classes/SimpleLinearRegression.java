package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import org.json.simple.DeserializationException;

public class SimpleLinearRegression {
  private String filePath;
  private boolean isEqualLengthDataSet = false;
  private LinearAlgebra linearAlgebra = new LinearAlgebra();
  private BigDecimal beta_0 = new BigDecimal(0), beta_1 = new BigDecimal(0);

  public SimpleLinearRegression(String path) throws FileNotFoundException, IOException, DeserializationException {
    filePath = path;
    if (getData("x").size() == getData("y").size()) {
      this.isEqualLengthDataSet = true;
    }
    BigDecimal sumy = new BigDecimal(0);
    sumy = linearAlgebra.summationArray(getData("y"));
    BigDecimal sumx = new BigDecimal(0);
    sumx = linearAlgebra.summationArray(getData("x"));
    BigDecimal n = new BigDecimal(getData("x").size());
    BigDecimal sumxy = new BigDecimal(0);
    sumxy = linearAlgebra.summationArray(linearAlgebra.multiplyArrays(getData("x"), getData("y")));
    BigDecimal sumx2 = new BigDecimal(0);
    sumx2 = linearAlgebra.summationArray(linearAlgebra.multiplyArrays(getData("x"), getData("x")));

    beta_1 = getBeta_1(n, sumx, sumy, sumxy, sumx2);
    beta_0 = getBeta_0(n, sumx, sumy);
  }

  public ArrayList<BigDecimal> getData(String key) throws FileNotFoundException, IOException, DeserializationException {
    ReadData data = new ReadData(filePath, key);
    return data.getData();
  }

  private BigDecimal getBeta_0(BigDecimal n, BigDecimal sumx, BigDecimal sumy) {
    if (!isEqualLengthDataSet)
      return new BigDecimal(-1);
    return ((sumy.subtract(beta_1.multiply(sumx))).divide(n, MathContext.DECIMAL64));
  }

  protected BigDecimal getBeta_1(BigDecimal n, BigDecimal sumx, BigDecimal sumy, BigDecimal sumxy, BigDecimal sumx2) {
    if (!isEqualLengthDataSet)
      return new BigDecimal(-1);
    return ((n.multiply(sumxy)).subtract(sumx.multiply(sumy))
        .divide((n.multiply(sumx2)).subtract(sumx.multiply(sumx)), MathContext.DECIMAL64));
  }

  public String getRegressionEquation() {
    return "y^ = " + beta_0.toString() + " + " + beta_1.toString() + " X";
  }

}