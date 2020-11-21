package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class PauseGameController {

    // For Save Button
    public void saveButtonAction(ActionEvent actionEvent) {
        System.out.println("Save");
    }

    // For Exit Button
    @FXML
    private Button ExitB;
    @FXML
    public void closeButtonAction(ActionEvent actionEvent) {
        Stage s=(Stage)ExitB.getScene().getWindow();
        s.close();
    }

    // For Resume Button
    public void resumeButtonAction(MouseEvent mouseEvent) {
        System.out.println("Resume");
    }

    // For Home Button
    public void homeButtonAction(MouseEvent mouseEvent) {
        System.out.println("Home");
    }

    // For Rotating Resume Button
    @FXML private Group D1;
    @FXML private Group D2;
    @FXML private Group D3;
    AnimationTimer t = new Timer();
    public PauseGameController(){
        t.start();
    }
    private class Timer extends AnimationTimer{
        @Override
        public void handle(long time){
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
