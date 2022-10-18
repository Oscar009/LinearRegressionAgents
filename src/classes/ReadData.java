package classes;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

public class ReadData {

  ArrayList<BigDecimal> data = new ArrayList<>();

  public ReadData(String path, String key) throws FileNotFoundException, IOException, DeserializationException {
    try (FileReader fileReader = new FileReader((path))) {

      JsonObject deserialize = (JsonObject) Jsoner.deserialize(fileReader);

      JsonArray jArray = (JsonArray) deserialize.get(key);

      JsonObject des = (JsonObject) jArray.get(0);

      JsonArray DAT = (JsonArray) des.get("data");

      for (int i = 0; i < DAT.size(); i++) {
        data.add((BigDecimal) DAT.get(i));
      }

    }
  }

  ArrayList<BigDecimal> getData() {
    return data;
  }

}
