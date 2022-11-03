import classes.DescendingGradient;
import classes.MultipleLinearRegressionCramer;

public class App {
    public static void main(String[] args) throws Exception {
        // String filePath = "./src/dataSLR.json";

        /* SimpleLinearRegression slr = new SimpleLinearRegression(filePath);
        System.out.println(slr.getRegressionEquation()); */

        /* MultipleLinearRegressionVectorized mlrv = new MultipleLinearRegressionVectorized("./src/dataMLR.json");
        System.out.println(mlrv.getRegressionEquation()); */

        MultipleLinearRegressionCramer mlrc = new MultipleLinearRegressionCramer("./src/dataMLR.json");
        System.out.println(mlrc.getRegressionEquation());

       /* DescendingGradient dg = new DescendingGradient(filePath);
        dg.optimizeParameters(); */
    }
}
