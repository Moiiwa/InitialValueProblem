import javafx.scene.chart.XYChart;

abstract class Solution{
    public  XYChart.Series solSeries;
    double in;
    double st;
    double xs[];
    double ys[];
    double c;
    public Solution(double init, double step, int numSteps){
        c=(FXMLController.initial_y+Math.log(FXMLController.initial_x))/(Math.log(FXMLController.initial_x)*Math.log(FXMLController.initial_x));
        solSeries=new XYChart.Series();
        in=init;
        st=step;
        ys=new double[numSteps+1];
        xs=new double[numSteps+1];
        xs[0]=init;
        ys[0]=function(xs[0], 0);
        solSeries.getData().add(new XYChart.Data(Double.toString(xs[0]),ys[0]));
        for(int i=1;i<numSteps;i++){
            xs[i]=xs[i-1]+step;
            ys[i]=function(xs[i], i);
            solSeries.getData().add(new XYChart.Data(Double.toString(xs[i]),ys[i]));
        }
    }
    public abstract double function(double x, int i);
}