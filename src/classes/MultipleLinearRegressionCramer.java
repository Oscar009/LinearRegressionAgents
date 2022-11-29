package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import org.json.simple.DeserializationException;

public class MultipleLinearRegressionCramer {
  private String filePath;
  private LinearAlgebra linearAlgebra = new LinearAlgebra();

  private BigDecimal beta_0;
  private BigDecimal beta_1;
  private BigDecimal beta_2;

  public MultipleLinearRegressionCramer(String path)
      throws FileNotFoundException, IOException, DeserializationException {
    filePath = path;

    // leer datos
    ArrayList<BigDecimal> x1i = getData("x1");
    ArrayList<BigDecimal> x2i = getData("x2");
    ArrayList<BigDecimal> yi = getData("y");

    // hacer sumatorias
    BigDecimal n = new BigDecimal(getData("y").size());
    BigDecimal Sx1i = linearAlgebra.summationArray(x1i);
    BigDecimal Sx2i = linearAlgebra.summationArray(x2i);
    BigDecimal Syi = linearAlgebra.summationArray(yi);
    BigDecimal Sx1i2 = linearAlgebra.summationArray(linearAlgebra.multiplyArrays(x1i, x1i));
    BigDecimal Sx2i2 = linearAlgebra.summationArray(linearAlgebra.multiplyArrays(x2i, x2i));
    BigDecimal Sx1iporx2i = linearAlgebra.summationArray(linearAlgebra.multiplyArrays(x1i, x2i));
    BigDecimal Syiporx1i = linearAlgebra.summationArray(linearAlgebra.multiplyArrays(yi, x1i));
    BigDecimal Syiporx2i = linearAlgebra.summationArray(linearAlgebra.multiplyArrays(yi, x2i));

    System.out.println("PANTALLA: " + linearAlgebra.summationArray(linearAlgebra.multiplyArrays(x1i, x1i)));

    // obtener determinantes
    // Determinante del sistema
    BigDecimal Ds = n.multiply(Sx1i2).multiply(Sx2i2).add(Sx1i.multiply(Sx1iporx2i).multiply(Sx2i))
        .add(Sx2i.multiply(Sx1i).multiply(Sx1iporx2i)).subtract(Sx2i.multiply(Sx1i2).multiply(Sx2i))
        .subtract(n.multiply(Sx1iporx2i).multiply(Sx1iporx2i)).subtract(Sx2i2.multiply(Sx1i).multiply(Sx1i));

    // Determinante beta_0
    BigDecimal Dbeta_0 = Syi.multiply(Sx1i2).multiply(Sx2i2).add(Sx1i.multiply(Sx1iporx2i).multiply(Syiporx2i))
        .add(Sx2i.multiply(Syiporx1i).multiply(Sx1iporx2i)).subtract(Syiporx2i.multiply(Sx1i2).multiply(Sx2i))
        .subtract(Sx1iporx2i.multiply(Sx1iporx2i).multiply(Syi)).subtract(Sx2i2.multiply(Syiporx1i).multiply(Sx1i));

    // Determinante beta_1
    BigDecimal Dbeta_1 = Syi.multiply(Sx1i).multiply(Sx2i2).add(n.multiply(Sx1iporx2i).multiply(Syiporx2i))
        .add(Sx2i.multiply(Syiporx1i).multiply(Sx2i)).subtract(Syiporx2i.multiply(Sx1i).multiply(Sx2i))
        .subtract(Sx2i.multiply(Sx1iporx2i).multiply(Syi)).subtract(Sx2i2.multiply(Syiporx1i).multiply(n))
        .multiply(new BigDecimal(-1));

    // Determinante beta_2
    BigDecimal Dbeta_2 = Syi.multiply(Sx1i).multiply(Sx1iporx2i).add(n.multiply(Sx1i2).multiply(Syiporx2i))
        .add(Sx1i.multiply(Syiporx1i).multiply(Sx2i)).subtract(Syiporx2i.multiply(Sx1i).multiply(Sx1i))
        .subtract(Sx2i.multiply(Sx1i2).multiply(Syi)).subtract(Sx1iporx2i.multiply(Syiporx1i).multiply(n));

    // obtener betas
    beta_0 = Dbeta_0.divide(Ds, MathContext.DECIMAL64);
    beta_1 = Dbeta_1.divide(Ds, MathContext.DECIMAL64);
    beta_2 = Dbeta_2.divide(Ds, MathContext.DECIMAL64);
  }

  public ArrayList<BigDecimal> getData(String key) throws FileNotFoundException, IOException, DeserializationException {
    ReadData data = new ReadData(filePath, key);
    return data.getData();
  }

  public String getRegressionEquation() {
    return "y = " + beta_0 + " + " + beta_1 + "x1 + " + beta_2 + "x2 " + "+ epsilon";
  }

}
