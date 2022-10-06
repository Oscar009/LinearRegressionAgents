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

  public SimpleLinearRegression(String path) throws FileNotFoundException, IOException, DeserializationException {
    filePath = path;
    if (getData("x").size() == getData("y").size())
      this.isEqualLengthDataSet = true;
  }

  public ArrayList<BigDecimal> getData(String key) throws FileNotFoundException, IOException, DeserializationException {
    ReadData data = new ReadData(filePath, key);
    return data.getData();
  }

  public BigDecimal getBeta_0() throws FileNotFoundException, IOException, DeserializationException {
    if (!isEqualLengthDataSet)
      return new BigDecimal(-1);

    BigDecimal sumy = new BigDecimal(0);
    sumy = linearAlgebra.summationArray(getData("y"));
    BigDecimal sumx = new BigDecimal(0);
    sumx = linearAlgebra.summationArray(getData("x"));
    BigDecimal n = new BigDecimal(getData("x").size());

    return ((sumy.subtract(getBeta_1().multiply(sumx))).divide(n, MathContext.DECIMAL64));
  }

  public BigDecimal getBeta_1() throws FileNotFoundException, IOException, DeserializationException {
    if (!isEqualLengthDataSet)
      return new BigDecimal(-1);

    BigDecimal n = new BigDecimal(getData("x").size());
    BigDecimal sumxy = new BigDecimal(0);
    sumxy = linearAlgebra.summationArray(linearAlgebra.multiplyArrays(getData("x"), getData("y")));
    BigDecimal sumx = new BigDecimal(0);
    sumx = linearAlgebra.summationArray(getData("x"));
    BigDecimal sumy = new BigDecimal(0);
    sumy = linearAlgebra.summationArray(getData("y"));
    BigDecimal sumx2 = new BigDecimal(0);
    sumx2 = linearAlgebra.summationArray(linearAlgebra.multiplyArrays(getData("x"), getData("x")));

    return ((n.multiply(sumxy)).subtract(sumx.multiply(sumy))
        .divide((n.multiply(sumx2)).subtract(sumx.multiply(sumx)), MathContext.DECIMAL64));
  }

}