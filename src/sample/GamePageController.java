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
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {
    Ball MainBall = new Ball();
    @FXML private AnchorPane GameScreen;

    boolean BallColor=false; // To check if ball has random color or not in start
    private double diff=0.01;
    AnimationTimer AnimationTi = new Timer();
    ArrayList<Obstacle> onScreen = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameScreen.getChildren().addAll(MainBall.C);
        ArrayList<Obstacle> chooseOne = new ArrayList<>();
        chooseOne.add(new Rectangle());
        chooseOne.add(new Triangle());
        chooseOne.add(new Concentric());
        chooseOne.add(new Eight());
        chooseOne.add(new Plus());
        Random r = new Random();
        int c = r.nextInt(5);
        Obstacle o = chooseOne.get(c);
        if(o instanceof Triangle){
            ((Triangle) o).G.setLayoutY(350);
            GameScreen.getChildren().addAll(((Triangle) o).G);
        } else if(o instanceof Rectangle){
            ((Rectangle) o).G.setLayoutY(350);
            GameScreen.getChildren().addAll(((Rectangle) o).G);
        } else if(o instanceof Eight){
            ((Eight) o).G1.setLayoutY(350);
            ((Eight) o).G2.setLayoutY(350);
            GameScreen.getChildren().addAll(((Eight) o).G1, ((Eight) o).G2);
        } else if(o instanceof Plus){
            ((Plus) o).G1.setLayoutY(350);
            ((Plus) o).G2.setLayoutY(350);
            GameScreen.getChildren().addAll(((Plus) o).G1, ((Plus) o).G2);
        } else if(o instanceof Concentric){
            ((Concentric) o).G1.setLayoutY(350);
            ((Concentric) o).G2.setLayoutY(350);
            GameScreen.getChildren().addAll(((Concentric) o).G1, ((Concentric) o).G2);
        }
        onScreen.add(o);
        AnimationTi.start();
    }

    private class Timer extends AnimationTimer{
        @Override
        public void handle(long time){
            gravity();
//            ArrayList<Obstacle> chooseOne = new ArrayList<>();
//            chooseOne.add(new Rectangle());
//            chooseOne.add(new Triangle());
//            chooseOne.add(new Concentric());
//            chooseOne.add(new Eight());
//            chooseOne.add(new Plus());
            System.out.println(MainBall.C.getTranslateY());
            if(MainBall.C.getTranslateY()<-300){
                for(Obstacle o: onScreen){
                    if(o instanceof Triangle){
                        ((Triangle) o).G.setTranslateY(((Triangle) o).G.getTranslateY()+5);
                    }
                     if(o instanceof Rectangle){
                        ((Rectangle) o).G.setTranslateY(((Rectangle) o).G.getTranslateY()+5);
                    }
                    if(o instanceof Eight){
                        ((Eight) o).G1.setTranslateY(((Eight) o).G1.getTranslateY()+5);
                        ((Eight) o).G2.setTranslateY(((Eight) o).G2.getTranslateY()+5);
                    }
                    if(o instanceof Plus){
                        ((Plus) o).G1.setTranslateY(((Plus) o).G1.getTranslateY()+5);
                        ((Plus) o).G2.setTranslateY(((Plus) o).G2.getTranslateY()+5);
                    }
                    if(o instanceof Concentric){
                        ((Concentric) o).G1.setTranslateY(((Concentric) o).G1.getTranslateY()+5);
                        ((Concentric) o).G2.setTranslateY(((Concentric) o).G2.getTranslateY()+5);
                    }
                }
                MainBall.C.setTranslateY(-260);
            }
            // Give Random Color to Ball;
            if(!BallColor){
                ArrayList<String> allcolors=new ArrayList<>();
                allcolors.add("#FAE100");
                allcolors.add("#900DFF");
                allcolors.add("#FF0181");
                allcolors.add("#32DBF0");
                Random rand = new Random();
                int c = rand.nextInt(4);
                MainBall.C.setFill(Paint.valueOf(allcolors.get(c)));
                BallColor=true;
            }
            MainBall.C.toFront();
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
            MainBall.C.setTranslateY(MainBall.C.getTranslateY() + consta);
            consta += differ;
        }
    }

    public void BallJumpClickAction(MouseEvent m) {
        consta=1;
        f=false;
        tr.setDuration(Duration.millis(300));
        tr.setToY(MainBall.C.getTranslateY() - 50);
        tr.setNode(MainBall.C);
        tr.play();
    }
}
