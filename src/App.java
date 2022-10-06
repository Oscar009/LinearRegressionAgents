
import classes.SimpleLinearRegression;

public class App {
    public static void main(String[] args) throws Exception {
        String filePath = "./src/dataSLR.json";

        SimpleLinearRegression slr = new SimpleLinearRegression(filePath);
        System.out.println(slr.getRegressionEquation());
    }
}
