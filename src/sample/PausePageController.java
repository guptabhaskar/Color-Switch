package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.io.IOException;

public class PausePageController {

    // Hover Effect for Buttons
    public void mouseEnterSave(MouseEvent m) {
        SaveB.setScaleX(1.1);
        SaveB.setScaleY(1.1);
    }
    public void mouseExitSave(MouseEvent m) {
        SaveB.setScaleX(1);
        SaveB.setScaleY(1);
    }
    public void mouseEnterExit(MouseEvent m) {
        ExitB.setScaleX(1.1);
        ExitB.setScaleY(1.1);
    }
    public void mouseExitExit(MouseEvent m) {
        ExitB.setScaleX(1);
        ExitB.setScaleY(1);
    }

    // For Save Button
    @FXML private Button SaveB;
    public void saveButtonAction(ActionEvent a) throws Exception {
        // Save Game and Go to Main Page
        GamePageController.Serialize();
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Main.load(root);
        Main.removeFront();
    }

    // For Exit Button
    @FXML private Button ExitB;
    public void closeButtonAction(ActionEvent a) {
        // Done IG
        System.exit(0);
    }

    // For Resume Button
    @FXML private SVGPath ResumeB;
    public void resumeButtonAction(MouseEvent m) throws IOException {
        // Resume Game Code Here
        Main.back();
    }

    // For Home Button
    @FXML private Button HomeB;
    public void homeButtonAction(MouseEvent m) throws IOException {
        // Done IG
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Main.load(root);
    }

    // For Rotating Resume Button
    @FXML private Group D1;
    @FXML private Group D2;
    @FXML private Group D3;
    AnimationTimer t = new Timer();
    public PausePageController(){
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
