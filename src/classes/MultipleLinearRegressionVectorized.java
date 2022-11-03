package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.simple.DeserializationException;

public class MultipleLinearRegressionVectorized {
  private String filePath;
  private LinearAlgebra linearAlgebra = new LinearAlgebra();

  private BigDecimal beta_0;
  private BigDecimal beta_1;
  private BigDecimal beta_2;

  public MultipleLinearRegressionVectorized(String path)
      throws FileNotFoundException, IOException, DeserializationException {
    filePath = path;

    ArrayList<ArrayList<BigDecimal>> matrizT = new ArrayList<ArrayList<BigDecimal>>(getData("y").size());

    ArrayList<BigDecimal> ones = new ArrayList<BigDecimal>();

    for (int i = 0; i < getData("y").size(); i++) {
      ones.add(i, new BigDecimal(1));
    }
    matrizT.add(ones);
    matrizT.add(getData("x1"));
    matrizT.add(getData("x2"));

    ArrayList<ArrayList<BigDecimal>> matriz = linearAlgebra.matrixTranspose(matrizT);

    ArrayList<ArrayList<BigDecimal>> matrizTXmatriz = linearAlgebra.matrixMultiplication(matrizT, matriz);

    ArrayList<ArrayList<BigDecimal>> Minverse = linearAlgebra.inverseMatrix(matrizTXmatriz);

    ArrayList<ArrayList<BigDecimal>> y = new ArrayList<ArrayList<BigDecimal>>(1);
    y.add(getData("y"));

    ArrayList<ArrayList<BigDecimal>> results = linearAlgebra.matrixMultiplication(Minverse,
        linearAlgebra.matrixMultiplication(matrizT, linearAlgebra.matrixTranspose(y)));

    beta_0 = results.get(0).get(0);
    beta_1 = results.get(1).get(0);
    beta_2 = results.get(2).get(0);
  }

  public ArrayList<BigDecimal> getData(String key) throws FileNotFoundException, IOException, DeserializationException {
    ReadData data = new ReadData(filePath, key);
    return data.getData();
  }

  public String getRegressionEquation() {
    return "y = " + beta_0 + " + " + beta_1 + "x1 " + beta_2 + "x2 " + "+ epsilon";
  }

  public BigDecimal pronostic(BigDecimal a, BigDecimal b) {
    BigDecimal aux = beta_0.add(beta_1.multiply(a)).add(beta_2.multiply(b));

    return aux;
  }

}
