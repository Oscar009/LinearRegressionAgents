package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import org.json.simple.DeserializationException;

public class DescendingGradient {
  private BigDecimal beta_0;
  private BigDecimal beta_1;
  private BigDecimal dBeta_0;
  private BigDecimal dBeta_1;
  private BigDecimal alpha;
  private String filePath;
  private BigDecimal tolerance;

  public DescendingGradient(String path) {
    // Step 1
    beta_0 = new BigDecimal(0);
    beta_1 = new BigDecimal(0);
    dBeta_0 = new BigDecimal(0);
    dBeta_1 = new BigDecimal(0);
    alpha = new BigDecimal(0.01);
    tolerance = new BigDecimal(1);
    filePath = path;
  }

  public ArrayList<BigDecimal> getData(String key) throws FileNotFoundException, IOException, DeserializationException {
    ReadData data = new ReadData(filePath, key);
    return data.getData();
  }

  public void optimizeParameters() throws FileNotFoundException, IOException, DeserializationException {

    // Step 2

    partialB1();
    partialB1();

    lossFunction();
  }

  public void partialB1() throws FileNotFoundException, IOException, DeserializationException {
    ArrayList<BigDecimal> xi = getData("x");
    ArrayList<BigDecimal> yi = getData("y");

    BigDecimal beta_1_aux = new BigDecimal(0);
    // Calculo de beta_1 parcial
    for (int i = 0; i < yi.size(); i++) {
      beta_1_aux = beta_1_aux.add(yi.get(i).subtract(beta_0.add(beta_1.multiply(xi.get(i)))));
    }

    dBeta_1 = (new BigDecimal(-2).divide(new BigDecimal(xi.size()), MathContext.DECIMAL32)).multiply(beta_1_aux);
  }

  public void partialB0() throws FileNotFoundException, IOException, DeserializationException {
    ArrayList<BigDecimal> xi = getData("x");
    ArrayList<BigDecimal> yi = getData("y");
    BigDecimal beta_0_aux = new BigDecimal(0);
    // Calculo de beta_0 parcial
    for (int i = 0; i < yi.size(); i++) {
      beta_0_aux = beta_0_aux.add(xi.get(0).multiply(yi.get(i).subtract(beta_0.add(beta_1.multiply(xi.get(i))))));
    }
    dBeta_0 = (new BigDecimal(-2).divide(new BigDecimal(xi.size()), MathContext.DECIMAL32)).multiply(beta_0_aux);
  }

  public void lossFunction() throws FileNotFoundException, IOException, DeserializationException {

    BigDecimal auxError = calculateError();
    int counter = 0;

    while (auxError.compareTo(tolerance) == 1) {
      beta_0 = beta_0.subtract(alpha.multiply(dBeta_0));
      beta_1 = beta_1.subtract(alpha.multiply(dBeta_1));
      System.out.println("\nVuelta: " + counter);
      System.out.println("Error: " + auxError);
      System.out.println("b0: " + beta_0);
      System.out.println("b1: " + beta_1);
      auxError = calculateError();
      partialB1();
      partialB0();
      counter++;
    }

  }

  public BigDecimal calculateError() throws FileNotFoundException, IOException, DeserializationException {
    ArrayList<BigDecimal> yi = getData("y");
    ArrayList<BigDecimal> xi = getData("x");

    BigDecimal error = new BigDecimal(0);

    for (int i = 0; i < getData("y").size(); i++) {
      error = error.add((yi.get(i).subtract(beta_0.add(beta_1.multiply(xi.get(i))))).pow(2));
    }

    error = (new BigDecimal(1).divide(new BigDecimal(getData("y").size()), MathContext.DECIMAL64)).multiply(error);
    return error;
  }

}
