class EulerSolution extends Solution{

    public EulerSolution(double init, double step, int numSteps){
        super(init,step,numSteps);
        solSeries.setName("Euler");
    }

    @Override
    public double function(double x, int i) {
        double f;
        if (x!=in){
            f=ys[i-1];
            return (f+st*(1/xs[i-1]+2*f/(xs[i-1]*Math.log(xs[i-1]))));
        }
        else return FXMLController.initial_y;
    }
}