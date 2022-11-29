import java.math.BigDecimal;

import classes.DescendingGradient;
import classes.MultipleLinearRegressionCramer;
import classes.MultipleLinearRegressionVectorized;
import classes.SimpleLinearRegression;
import classes.logisticRegression;

public class App {
    public static void main(String[] args) throws Exception {
        String filePath = "./src/dataSLR.json";

        /*
         * System.out.println("SLR: ");
         * SimpleLinearRegression slr = new SimpleLinearRegression(filePath);
         * System.out.println(slr.getRegressionEquation());
         * 
         * System.out.println("MLR vectorized: ");
         * MultipleLinearRegressionVectorized mlrv = new
         * MultipleLinearRegressionVectorized("./src/dataMLR.json");
         * System.out.println(mlrv.getRegressionEquation());
         * System.out.println("Pronostic: " + mlrv.pronostic(new BigDecimal(41.9), new
         * BigDecimal(29.1)));
         */

        /*
         * System.out.println("MLR CRAMER: ");
         * MultipleLinearRegressionCramer mlrc = new
         * MultipleLinearRegressionCramer("./src/dataMLR.json");
         * System.out.println(mlrc.getRegressionEquation());
         */

        DescendingGradient dg = new DescendingGradient(filePath);
        dg.optimizeParameters();

        // logisticRegression lg = new logisticRegression();

    }
}
