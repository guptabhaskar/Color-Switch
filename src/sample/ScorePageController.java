package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
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
import javafx.util.Duration;

import java.io.IOException;

public class ScorePageController {
    // Controller
    public ScorePageController(){
        RotateT.start();
    }

    // For Rotating O's, Star Effect and New Game Button Effect
    @FXML private Group O11;
    @FXML private Group O22;
    @FXML private SVGPath S11;
    @FXML private SVGPath S22;
    @FXML private SVGPath S33;
    @FXML private Button NewGameBu;
    private double diff=0.025;
    private double diffB=0.004;
    AnimationTimer RotateT = new Timer();
    private class Timer extends AnimationTimer{
        @Override
        public void handle(long time){
            Rotate RO1 = new Rotate();
            RO1.setAngle(1);
            RO1.setPivotX(0);
            RO1.setPivotY(0);
            O11.getTransforms().add(RO1);
            Rotate RO2 = new Rotate();
            RO2.setAngle(-1);
            RO2.setPivotX(0);
            RO2.setPivotY(0);
            O22.getTransforms().add(RO2);

            NewGameBu.setScaleX(NewGameBu.getScaleX()+diffB);
            NewGameBu.setScaleY(NewGameBu.getScaleY()+diffB);
            S11.setScaleX(S11.getScaleX()+diff);
            S11.setScaleY(S11.getScaleY()+diff);
            S22.setScaleX(S22.getScaleX()+diff);
            S22.setScaleY(S22.getScaleY()+diff);
            S33.setScaleX(S33.getScaleX()+diff);
            S33.setScaleY(S33.getScaleY()+diff);
            if(S11.getScaleX()>=3.5) {
                diffB*=-1;
                diff *= -1;
            } else if(S11.getScaleX()<=3.0) {
                diff *= -1;
                diffB*=-1;
            }


        }
    }

    // For Home Button
    @FXML private Button HomeBu;
    public void homeButtonAction(ActionEvent m) throws IOException {
        // Done IG
        Stage s=(Stage)HomeBu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        s.setScene(new Scene(root, 450, 700));
        s.show();
    }

    // For New Game Button
    public void newGameButtonAction(ActionEvent a) throws IOException {
        // Start New Game
        Stage s=(Stage)NewGameBu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
        s.setScene(new Scene(root, 450, 700));
        s.show();
    }
}
