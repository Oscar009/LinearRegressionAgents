package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.simple.DeserializationException;

public class MultipleLinearRegressionVectorized {
  private String filePath;
  private LinearAlgebra linearAlgebra = new LinearAlgebra();

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
    matrizT.add(getData("y"));

    ArrayList<ArrayList<BigDecimal>> matriz = linearAlgebra.matrixTranspose(matrizT);

    ArrayList<ArrayList<BigDecimal>> matrizTXmatriz = linearAlgebra.matrixMultiplication(matrizT, matriz);

  }

  public ArrayList<BigDecimal> getData(String key) throws FileNotFoundException, IOException, DeserializationException {
    ReadData data = new ReadData(filePath, key);
    return data.getData();
  }

  public String getRegressionEquation() {
    return "Equation";
  }

}
