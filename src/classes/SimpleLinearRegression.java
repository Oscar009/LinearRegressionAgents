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

    Summation summation = new Summation();

    BigDecimal sumy = new BigDecimal(0);
    sumy = summation.getValue(getData("y"));
    BigDecimal sumx = new BigDecimal(0);
    sumx = summation.getValue(getData("x"));
    BigDecimal n = new BigDecimal(getData("x").size());

    // System.out.println((sumy.subtract(getBeta_1().multiply(sumx))).divide(n,
    // MathContext.DECIMAL128));

    return ((sumy.subtract(getBeta_1().multiply(sumx))).divide(n, MathContext.DECIMAL128));
  }

  public BigDecimal getBeta_1() throws FileNotFoundException, IOException, DeserializationException {
    if (!isEqualLengthDataSet)
      return new BigDecimal(-1);

    MultiplyArrays multiplyArrays = new MultiplyArrays();
    Summation summation = new Summation();

    BigDecimal n = new BigDecimal(getData("x").size());
    BigDecimal sumxy = new BigDecimal(0);
    sumxy = summation.getValue(multiplyArrays.getResult(getData("x"), getData("y")));
    BigDecimal sumx = new BigDecimal(0);
    sumx = summation.getValue(getData("x"));
    BigDecimal sumy = new BigDecimal(0);
    sumy = summation.getValue(getData("y"));
    BigDecimal sumx2 = new BigDecimal(0);
    sumx2 = summation.getValue(multiplyArrays.getResult(getData("x"), getData("x")));

    // System.out.println((n.multiply(sumxy)).subtract(sumx.multiply(sumy)));
    // System.out.println((n.multiply(sumx2)).subtract(sumx.multiply(sumx)));

    // System.out.println((n.multiply(sumxy)).subtract(sumx.multiply(sumy)).divide((n.multiply(sumx2)).subtract(sumx.multiply(sumx)),
    // MathContext.DECIMAL128));

    return ((n.multiply(sumxy)).subtract(sumx.multiply(sumy))
        .divide((n.multiply(sumx2)).subtract(sumx.multiply(sumx)), MathContext.DECIMAL128));
  }

}