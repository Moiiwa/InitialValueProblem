class ExactSolution extends Solution{
    public ExactSolution(double init, double step, int numSteps) {
        super(init, step, numSteps);
        solSeries.setName("Exact");
    }
    @Override
    public double function(double x, int i)
    {
        return (-Math.log(x)+c*Math.log(x)*Math.log(x));
    }

}