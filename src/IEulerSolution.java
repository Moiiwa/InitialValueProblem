class IEulerSolution extends Solution{

    public IEulerSolution(double init, double step, int numSteps){
        super(init,step,numSteps);
        solSeries.setName("Improved Euler");
    }

    @Override
    public double function(double x, int i) {
        double f;
        double g;
        double new_x;
        if (i>0){
            f=ys[i-1];
            new_x=(xs[i-1]+st/2);
            g=(1/xs[i-1]+2*f/(xs[i-1]*Math.log(xs[i-1])));
            return f+st*(1/(new_x)+2*(f+st/2*(g))/(new_x*Math.log(new_x)));
        }
        else{
            return FXMLController.initial_y;
        }
    }
}