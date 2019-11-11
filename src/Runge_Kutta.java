class Runge_Kutta extends Solution{

    public Runge_Kutta(double init, double step, int numSteps) {
        super(init, step, numSteps);
        solSeries.setName("Runge Kutta");
    }

    @Override
    public double function(double x, int i) {
        double k1,k2,k3,k4;
        double new_x,new_x1;
        if(i>0){
            new_x=(xs[i-1]+st/2);
            new_x1=(xs[i-1]+st);
            k1=1/xs[i-1]+2*ys[i-1]/(xs[i-1]*Math.log(xs[i-1]));
            k2=1/new_x+2*(ys[i-1]+st*k1/2)/(new_x*Math.log(new_x));
            k3=1/new_x+2*(ys[i-1]+st*k2/2)/(new_x*Math.log(new_x));
            k4=1/new_x1+2*(ys[i-1]+st*k3)/(new_x1*Math.log(new_x1));
            return ys[i-1]+st/6*(k1+2*k2+2*k3+k4);
        }
        else{
            return FXMLController.initial_y;
        }
    }
}