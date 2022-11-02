

import classes.MultipleLinearRegressionVectorized;
public class App {
    public static void main(String[] args) throws Exception {
        // String filePath = "./src/dataSLR.json";

        /* SimpleLinearRegression slr = new SimpleLinearRegression(filePath);
        System.out.println(slr.getRegressionEquation()); */

        MultipleLinearRegressionVectorized mlrv = new MultipleLinearRegressionVectorized("./src/dataMLR.json");
        // System.out.println(mlrv.getRegressionEquation());

        /* DescendingGradient dg = new DescendingGradient(filePath);
        dg.optimizeParameters(); */
    }
}
