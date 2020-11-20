package sample;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class MainPageController {

    // For Exit Button
    @FXML private Button ExitB;
    @FXML
    public void closeButtonAction(ActionEvent a) {
        Stage s=(Stage)ExitB.getScene().getWindow();
        s.close();
    }

    // For Rotating Groups
    @FXML private Group D1;
    @FXML private Group D2;
    @FXML private Group D3;
    @FXML private Group O1;
    @FXML private Group O2;
    AnimationTimer t = new Timer();
    public MainPageController(){
        t.start();
    }

    private class Timer extends AnimationTimer{
        @Override
        public void handle(long time){
            Rotate RO1 = new Rotate();
            RO1.setAngle(1);
            RO1.setPivotX(0);
            RO1.setPivotY(0);
            O1.getTransforms().add(RO1);
            Rotate RO2 = new Rotate();
            RO2.setAngle(-1);
            RO2.setPivotX(0);
            RO2.setPivotY(0);
            O2.getTransforms().add(RO2);
            Rotate RD1 = new Rotate();
            RD1.setAngle(1);
            RD1.setPivotX(0);
            RD1.setPivotY(0);
            D1.getTransforms().add(RD1);
            Rotate RD2 = new Rotate();
            RD2.setAngle(-1);
            RD2.setPivotX(0);
            RD2.setPivotY(0);
            D2.getTransforms().add(RD2);
            Rotate RD3 = new Rotate();
            RD3.setAngle(1);
            RD3.setPivotX(0);
            RD3.setPivotY(0);
            D3.getTransforms().add(RD3);
        }
    }
}
