package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScorePageController implements Initializable {
    // For Rotating O's, Star Effect and New Game Button Effect
    @FXML private Group O11;
    @FXML private Group O22;
    @FXML private SVGPath S11;
    @FXML private SVGPath S22;
    @FXML private SVGPath S33;
    @FXML private Button NewGameBu;
    @FXML private Label CurrSc;
    @FXML private Label TotalStars;
    @FXML private Button NewLife;
    @FXML private AnchorPane Screen;

    public void update(int n) {
        CurrSc.setText(String.valueOf(n));
        if(Integer.parseInt(TotalStars.getText())<30) {
            Screen.getChildren().remove(NewLife);
        }
    }

    private double diff=0.025;
    private double diffB=0.004;
    AnimationTimer RotateT = new Timer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RotateT.start();
    }

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
    public void homeButtonAction(MouseEvent m) throws IOException {
        // Done IG
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Main.load(root);
        Main.removeFront();
    }

    // For New Game Button
    public void newGameButtonAction(MouseEvent a) throws IOException {
        // Start New Game
        Parent root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
        Main.load(root);
        Main.removeFront();
    }

    public void NewLifeButtonAction(MouseEvent a) throws IOException {
        TotalStars.setText(String.valueOf(Integer.parseInt(TotalStars.getText())-30));
        Main.back();
    }
}
