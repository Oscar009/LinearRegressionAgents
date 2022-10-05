
import classes.SimpleLinearRegression;

public class App {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Oskr0\\Desktop\\semestres\\Noveno semestre\\IA\\SLR\\src\\dataSLR.json";

        SimpleLinearRegression slr = new SimpleLinearRegression(filePath);

        System.out.println("Beta 0: " + slr.getBeta_0());
        System.out.println("Beta 1: " + slr.getBeta_1());
    }
}
