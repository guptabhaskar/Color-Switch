package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {
    @FXML private Circle MainBall;
    @FXML private AnchorPane GameScreen;

    boolean BallColor=false; // To check if ball has random color or not in start
    private double diff=0.01;
    AnimationTimer RotateTi = new Timer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RotateTi.start();
        Triangle CS= new Triangle();
        CS.G.setLayoutY(600);
        GameScreen.getChildren().addAll(CS.G);

    }

    private class Timer extends AnimationTimer{
        @Override
        public void handle(long time){
            gravity();
            // Give Random Color to Ball;
            if(!BallColor){
                ArrayList<String> allcolors=new ArrayList<>();
                allcolors.add("#FAE100");
                allcolors.add("#900DFF");
                allcolors.add("#FF0181");
                allcolors.add("#32DBF0");
                Random rand = new Random();
                int c = rand.nextInt(4);
                MainBall.setFill(Paint.valueOf(allcolors.get(c)));
                BallColor=true;
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
    TranslateTransition tr=new TranslateTransition();
    public void gravity(){
        if(!f) {

            MainBall.setTranslateY(MainBall.getTranslateY() + consta);
            consta += differ;

        }

    }
    public void BallJumpClickAction(MouseEvent m) {
        consta=1;
        f=false;
        tr.setDuration(Duration.millis(300));
        tr.setToY(MainBall.getTranslateY() - 50);
        tr.setNode(MainBall);
        tr.play();
    }
}
