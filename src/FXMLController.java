import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    @FXML
    private XYChart<Double,Double> Chart;
    @FXML
    private CheckBox CB1;
    @FXML
    private CheckBox CB2;
    @FXML
    private CheckBox CB3;
    @FXML
    private CheckBox CB4;
    @FXML
    private CheckBox CB5;
    @FXML
    public TextField initialX;
    @FXML
    private TextField initialY;
    @FXML
    private TextField finalX;
    @FXML
    private TextField stepsNum;
    @FXML
    private javafx.scene.control.Button button=new javafx.scene.control.Button();
    @FXML
    private TextField MinStep;
    @FXML
    private TextField MaxStep;
    @FXML
    private TextField StepStep;
    @FXML
    private CheckBox GlobalErrors;

    public static double minStep;
    public static double maxStep;
    public static double stepStep;
    public static double initial_x;
    public static double step_size;
    public static double initial_y;
    public static int n_steps;
    public static double final_x;
    ExactSolution exactSolution;
    EulerSolution eulerSolution;
    IEulerSolution iEulerSolution;
    Runge_Kutta runge_kutta;
    Errors errorsEuler;
    Errors errorsIEuler;
    Errors errorsRunge;
    GlobalError globalEuler;
    GlobalError globalIEuler;
    GlobalError globalRunge;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handle(ActionEvent mouseEvent){
        setInitialValues();
        parseNewValues();
        step_size=(final_x-initial_x)/n_steps;
        initializeSolutions();
        Chart.getData().clear();
        if(!CB5.isSelected()&&!GlobalErrors.isSelected()) {
            printSolutions();
        }
        else if(CB5.isSelected()&&!GlobalErrors.isSelected()){
            initializeLocalErrors();
            printLocalErrors();
        }
        else if(!CB5.isSelected()&&GlobalErrors.isSelected()){
            initializeGlobalErrors();
            printGlobalErrors();
        }
    }
    void setInitialValues(){
        Chart.getYAxis().setLabel("Y");
        Chart.getXAxis().setLabel("X");
        initial_x=2;
        initial_y=0;
        final_x=12;
        minStep=0.1;
        maxStep=0.1;
        stepStep=0.1;
        n_steps=10;
    }
    void parseNewValues(){
        if(!initialX.getText().equals("")&&Double.parseDouble(initialX.getText())>=2)
            initial_x=Double.parseDouble(initialX.getText());
        if(!initialY.getText().equals(""))
            initial_y=Double.parseDouble(initialY.getText());
        if(!finalX.getText().equals("")&&Double.parseDouble(finalX.getText())>=2)
            final_x=Double.parseDouble(finalX.getText());
        if(!MinStep.getText().equals(""))
            minStep=Double.parseDouble(MinStep.getText());
        if(!MaxStep.getText().equals(""))
            maxStep=Double.parseDouble(MaxStep.getText());
        if(!StepStep.getText().equals(""))
            stepStep=Double.parseDouble(StepStep.getText());
        if(!stepsNum.getText().equals(""))
            n_steps=Integer.parseInt(stepsNum.getText());
    }
    void initializeSolutions(){
        exactSolution=new ExactSolution(initial_x,step_size,n_steps);
        eulerSolution=new EulerSolution(initial_x,step_size,n_steps);
        iEulerSolution=new IEulerSolution(initial_x,step_size,n_steps);
        runge_kutta=new Runge_Kutta(initial_x,step_size,n_steps);
    }
    void printSolutions(){
        if (CB1.isSelected())
            Chart.getData().add(exactSolution.solSeries);
        if (CB2.isSelected())
            Chart.getData().add(eulerSolution.solSeries);
        if (CB3.isSelected())
            Chart.getData().add(iEulerSolution.solSeries);
        if (CB4.isSelected())
            Chart.getData().add(runge_kutta.solSeries);
    }
    void initializeLocalErrors(){
        errorsEuler=new Errors(initial_x,step_size,n_steps,exactSolution.ys,eulerSolution.ys);
        errorsEuler.series.setName("Euler error");
        errorsIEuler=new Errors(initial_x,step_size,n_steps,exactSolution.ys,iEulerSolution.ys);
        errorsIEuler.series.setName("Improved Euler error");
        errorsRunge=new Errors(initial_x,step_size,n_steps,exactSolution.ys,runge_kutta.ys);
        errorsRunge.series.setName("Runge Kutta error");
    }
    void printLocalErrors(){
        if (CB2.isSelected())
            Chart.getData().add(errorsEuler.series);
        if (CB3.isSelected())
            Chart.getData().add(errorsIEuler.series);
        if (CB4.isSelected())
            Chart.getData().add(errorsRunge.series);
    }
    void initializeGlobalErrors(){
        Chart.getXAxis().setLabel("Step size");
        Chart.getYAxis().setLabel("Global error");
        globalEuler=new GlobalError("Euler");
        globalEuler.series.setName("Global Euler");
        globalIEuler=new GlobalError("IEuler");
        globalIEuler.series.setName("Global IEuler");
        globalRunge=new GlobalError("RK");
        globalRunge.series.setName("Global Runge");
    }
    void printGlobalErrors(){
        if (CB2.isSelected())
            Chart.getData().add(globalEuler.series);
        if (CB3.isSelected())
            Chart.getData().add(globalIEuler.series);
        if (CB4.isSelected())
            Chart.getData().add(globalRunge.series);
    }
}










