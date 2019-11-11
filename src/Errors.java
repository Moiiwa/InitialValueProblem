import javafx.scene.chart.XYChart;

public class Errors{
    double ys[];
    double xs[];
    double yl[];
    XYChart.Series series;
    public Errors(double init, double step, int numSteps, double y1[],double y2[]){
        ys=new double[numSteps];
        xs=new double[numSteps];
        yl=new double[numSteps];
        xs[0]=init;
        ys[0]=0;
        yl[0]=0;
        series=new XYChart.Series();
        series.getData().add(new XYChart.Data(Double.toString(xs[0]),ys[0]));
        for (int i=1;i<numSteps;i++){
            xs[i]=xs[i-1]+step;
            ys[i]=Math.abs(y1[i]-y2[i]);
            yl[i]=Math.abs(ys[i]-ys[i-1]);
            series.getData().add(new XYChart.Data(Double.toString(xs[i]),yl[i]));
        }
    }
}
