import javafx.scene.chart.XYChart;

class GlobalError{
    double ys[];
    double xs[];
    double curStep;
    String name;
    XYChart.Series series;
    public GlobalError(String name){
        int numOfSteps;
        this.name=name;
        series=new XYChart.Series();
        numOfSteps= (int)((FXMLController.maxStep-FXMLController.minStep)/FXMLController.stepStep);
        ys=new double[numOfSteps];
        xs=new double[numOfSteps];
        xs[0]=FXMLController.minStep;
        curStep=xs[0];
        ys[0]=maxLocalError();
        series.getData().add(new XYChart.Data(Double.toString(xs[0]),ys[0]));
        for(int i=1;i<numOfSteps;i++){
            xs[i]=xs[i-1]+FXMLController.stepStep;
            curStep=xs[i];
            ys[i]=maxLocalError();
            series.getData().add(new XYChart.Data(Double.toString(xs[i]),ys[i]));
        }
    }
    double maxLocalError(){
        double maxErr=0;
        int numOfSteps= (int) ((FXMLController.final_x-FXMLController.initial_x)/curStep);
        ExactSolution exactSolution=new ExactSolution(FXMLController.initial_x,curStep,numOfSteps);
        Solution solution=new Solution(FXMLController.initial_x,curStep,numOfSteps) {
            @Override
            public double function(double x, int i) {
                return 0;
            }
        };
        switch (name){
            case "Euler":{
                solution=new EulerSolution(FXMLController.initial_x,curStep,numOfSteps);
                break;
            }
            case "IEuler":{
                solution=new IEulerSolution(FXMLController.initial_x,curStep,numOfSteps);
                break;
            }
            case "RK":{
                solution=new Runge_Kutta(FXMLController.initial_x,curStep,numOfSteps);
            }
        }
        Errors errors = new Errors(FXMLController.initial_x,curStep,exactSolution.ys.length,exactSolution.ys,solution.ys);
        for(int i=0;i<errors.xs.length;i++){
            if(errors.ys[i]>maxErr) maxErr=errors.ys[i];
        }
        return maxErr;
    }
}
