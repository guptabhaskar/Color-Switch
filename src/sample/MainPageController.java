package sample;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.io.IOException;

public class MainPageController {
    // Constructor
    public MainPageController(){
        RotateT.start();
        BallT.start();
    }

    // For Play Now! Button
    @FXML private Button PlayB;
    public void startNewGameAction(ActionEvent a) throws IOException {
        Stage s=(Stage)PlayB.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
        s.setScene(new Scene(root, 450, 700));
        s.show();
    }

    // For Saved Game Button
    @FXML private Button SaveB;
    public void ShowSavedGameAction(ActionEvent a) throws IOException {
        System.out.println("Show Saved Games");
        Stage s=(Stage)SaveB.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LoadGamePage.fxml"));
        s.setScene(new Scene(root, 450, 700));
        s.show();
    }

    // For Exit Button
    @FXML private Button ExitB;
    public void ExitGameAction(ActionEvent a) {
        Stage s=(Stage)ExitB.getScene().getWindow();
        s.close();
    }

    // For Rotating Groups
    @FXML private Group D1;
    @FXML private Group D2;
    @FXML private Group D3;
    @FXML private Group O1;
    @FXML private Group O2;
    AnimationTimer RotateT = new Timer();
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

    // For Bouncing Design Ball
    @FXML private Circle BounceBall;
    AnimationTimer BallT = new BounceTimer();
    private class BounceTimer extends AnimationTimer{
        private double dy = 1.5;
        @Override
        public void handle(long time){
            BounceBall.setLayoutY(BounceBall.getLayoutY() + dy);
            if((BounceBall.getLayoutY() >= (490 - BounceBall.getRadius())) || (BounceBall.getLayoutY() <= (410 + BounceBall.getRadius()))){
                dy = -dy;
            }
        }
    }
}
