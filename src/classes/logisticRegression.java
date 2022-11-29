package classes;

public class logisticRegression {
  double[] x1 = { 1, 1, 1 };
  double[] x2 = { 1, 4, 2 };
  double[] x3 = { 1, 2, 4 };

  double[] yi = { 0, 1, 1 };

  double learningRate = 0.1;
  double iterations = 1;

  double[] wj = { 0, 0, 0 };

  public logisticRegression(){
    while(iterations > 0){
      iterations--;

      for (int i = 0; i < wj.length; i++) {
        wj[i] = wj[i] - learningRate * ((yi[0] - .5)*(x1[0]) + (yi[1] - .5)*(x1[1]) + (yi[2] - .5)*(x1[2]));
      }
      
    }

    System.out.println(wj[0]);
  }

}
