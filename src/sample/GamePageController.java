package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GamePageController {
    // Constructor
    TranslateTransition tr=new TranslateTransition();
    @FXML private Circle MainBall;
    public GamePageController(){
        RotateTi.start();
//        RotateT.start();
//        BallT.start();
    }

    // Eight Obstacle and ConcentricCircle Obstacle
    @FXML private Group Eight1;
    @FXML private Group Eight2;
    @FXML private Group Concentric1;
    @FXML private Group Concentric2;
    @FXML private Group Plus1;
    @FXML private Group Plus2;
    @FXML private SVGPath Star1;
    boolean BallColor=false; // To check if ball has random color or not in start
    private double diff=0.01;
    AnimationTimer RotateTi = new Timer();
    private boolean clicked=false;
    private class Timer extends AnimationTimer{
        @Override
        public void handle(long time){
            Rotate REight1 = new Rotate();
            REight1.setAngle(1);
            REight1.setPivotX(0);
            REight1.setPivotY(0);
            Eight1.getTransforms().add(REight1);
            Rotate REight2 = new Rotate();
            REight2.setAngle(-1);
            REight2.setPivotX(0);
            REight2.setPivotY(0);
            Eight2.getTransforms().add(REight2);
            Rotate RConcentric1 = new Rotate();
            RConcentric1.setAngle(1);
            RConcentric1.setPivotX(0);
            RConcentric1.setPivotY(0);
            Concentric1.getTransforms().add(RConcentric1);
            Rotate RConcentric2 = new Rotate();
            RConcentric2.setAngle(-1);
            RConcentric2.setPivotX(0);
            RConcentric2.setPivotY(0);
            Concentric2.getTransforms().add(RConcentric2);
            Rotate RPlus1 = new Rotate();
            RPlus1.setAngle(1);
            RPlus1.setPivotX(64);
            RPlus1.setPivotY(0);
            Plus1.getTransforms().add(RPlus1);
            Rotate RPlus2 = new Rotate();
            RPlus2.setAngle(-1);
            RPlus2.setPivotX(64);
            RPlus2.setPivotY(0);
            Plus2.getTransforms().add(RPlus1);

            Star1.setScaleX(Star1.getScaleX()+diff);
            Star1.setScaleY(Star1.getScaleY()+diff);
            if(Star1.getScaleX()>=2.5) {
                diff *= -1;
            } else if(Star1.getScaleX()<=2.0) {
                diff *= -1;
            }

            gravity();

            // Give Random Color to Ball;
            if(!BallColor){
                BallColor=true;
                ArrayList<String> allcolors=new ArrayList<>();
                allcolors.add("#FAE100");
                allcolors.add("#900DFF");
                allcolors.add("#FF0181");
                allcolors.add("#32DBF0");
                Random rand = new Random();
                int c = rand.nextInt(4);
                System.out.println(c);
                MainBall.setFill(Paint.valueOf(allcolors.get(c)));
            }
        }
    }

    // For Pause Button
    @FXML private Button PauseB;
    public void PauseButtonAction(ActionEvent a) throws IOException {
        Stage s=(Stage)PauseB.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("PausePage.fxml"));
        s.setScene(new Scene(root, 450, 700));
        s.show();
    }

    private double differ=0.01;
    private double consta=1;
    private boolean f=true;

    public void gravity(){
        if(!f) {

            MainBall.setTranslateY(MainBall.getTranslateY() + consta);
            consta += differ;
//            tr.setDuration(Duration.INDEFINITE);
//            tr.setToY(MainBall.getTranslateY() + 10);
//            tr.setNode(MainBall);
//            tr.play();

        }
//        else if(f){
//            MainBall.setTranslateY(MainBall.getTranslateY() - consta);
//            consta += differ;
//        }
    }
    public void BallJumpClickAction(MouseEvent m) {
        consta=1;
        f=false;

//        TranslateTransition tr=new TranslateTransition();
        tr.setDuration(Duration.millis(150));
        tr.setToY(MainBall.getTranslateY() - 50);
        tr.setNode(MainBall);
//        tr.setOnFinished(e->gravity());
        tr.play();


    }
}
