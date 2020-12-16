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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {
    Ball MainBall=new Ball();
    @FXML private AnchorPane GameScreen;

    boolean BallColor=false; // To check if ball has random color or not in start
    AnimationTimer AnimationTi = new Timer();
    ArrayList<Obstacle> ObstaclesOnScreen = new ArrayList<>();
    ArrayList<Star> StarsOnScreen = new ArrayList<>();
    ArrayList<ColorSwitcher> ColorSwitcherOnScreen = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL path = getClass().getResource("/assets/start.wav");
        AudioClip ac = new AudioClip(path.toString());
        ac.play();
        MainBall.C.setLayoutY(650);
        GameScreen.getChildren().add(MainBall.C);
        try {
            addRandomObstacle(300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            addRandomObstacle(-200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            addRandomObstacle(-700);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnimationTi.start();
    }
    private void addStar(double y) throws IOException {
        Star s = new Star();
        s.G.setLayoutY(y);
        GameScreen.getChildren().addAll(s.G);
        StarsOnScreen.add(s);
    }
    private void addColorSwitcher(double y) throws IOException {
        ColorSwitcher cs = new ColorSwitcher();
        cs.G.setLayoutY(y);
        GameScreen.getChildren().addAll(cs.G);
        ColorSwitcherOnScreen.add(cs);
    }

    private void addRandomObstacle(double y) throws IOException {
        ArrayList<Obstacle> chooseOne = new ArrayList<>();
        chooseOne.add(new Rectangle());
        chooseOne.add(new Concentric());
        chooseOne.add(new Eight());
        chooseOne.add(new Plus());
        Random r = new Random();
        int c = r.nextInt(4);
        Obstacle o = chooseOne.get(c);
        if(o instanceof Rectangle){
            ((Rectangle) o).G.setLayoutY(y);
            GameScreen.getChildren().addAll(((Rectangle) o).G);
            addStar(y);
            addColorSwitcher(y-150);
        } else if(o instanceof Eight){
            ((Eight) o).G1.setLayoutY(y-300);
            ((Eight) o).G2.setLayoutY(y-300);
            GameScreen.getChildren().addAll(((Eight) o).G1, ((Eight) o).G2);
            addStar(y-100);
            addColorSwitcher(y-200);
        } else if(o instanceof Plus){
            ((Plus) o).G1.setLayoutY(y);
            ((Plus) o).G2.setLayoutY(y);
            GameScreen.getChildren().addAll(((Plus) o).G1, ((Plus) o).G2);
            addStar(y-70);
            addColorSwitcher(y-140);
        } else if(o instanceof Concentric){
            ((Concentric) o).G1.setLayoutY(y-300);
            ((Concentric) o).G2.setLayoutY(y-300);
            GameScreen.getChildren().addAll(((Concentric) o).G1, ((Concentric) o).G2);
            addStar(y);
            addColorSwitcher(y-200);
        }
        ObstaclesOnScreen.add(o);
    }

    private class Timer extends AnimationTimer{
        @Override
        public void handle(long time){
            gravity();
            if(MainBall.C.getBoundsInParent().getMinY()<300){
                try {
                    moveScreenDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // Give Random Color to Ball;
            if(!BallColor){
                getRandomColorOnBall();
                BallColor=true;
            }
            MainBall.C.toFront();
            PauseB.toFront();
            ScoreL.toFront();
            try {
                checkCollision();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML Label ScoreL;
    private void checkCollision() throws IOException {
        for(Star s: StarsOnScreen) {
            if(s.hit(MainBall)){
                URL path = getClass().getResource("/assets/star.wav");
                AudioClip ac = new AudioClip(path.toString());
                ac.play();
                ScoreL.setText(Integer.toString ( Integer.parseInt(ScoreL.getText()) + 1));
                GameScreen.getChildren().remove(s.G);
                StarsOnScreen.remove(0);
                break;
            }
        }
        for(ColorSwitcher  cs: ColorSwitcherOnScreen) {
            if(cs.hit(MainBall)) {
                URL path = getClass().getResource("/assets/colorswitch.wav");
                AudioClip ac = new AudioClip(path.toString());
                ac.play();
                getRandomColorOnBall();
                GameScreen.getChildren().remove(cs.G);
                ColorSwitcherOnScreen.remove(0);
                break;
            }
        }
        for(Obstacle o: ObstaclesOnScreen) {
            if(o.hit(MainBall)) {
                goToScorePage();
                break;
            }
        }
    }

    public void goToScorePage() throws IOException {
        URL path = getClass().getResource("/assets/dead.wav");
        AudioClip ac = new AudioClip(path.toString());
        ac.play();

        // Just for now
        Stage s=(Stage)PauseB.getScene().getWindow();
        s.close();
    }

    public void getRandomColorOnBall() {
        ArrayList<String> allcolors=new ArrayList<>();
        allcolors.add("#fae100");
        allcolors.add("#900dff");
        allcolors.add("#ff0181");
        allcolors.add("#32dbf0");
        Random rand = new Random();
        int c = rand.nextInt(4);
        MainBall.C.setFill(Paint.valueOf(allcolors.get(c)));
    }

    public void moveScreenDown() throws IOException {
        MainBall.C.setTranslateY(MainBall.C.getTranslateY()+20);
        boolean toAdd=false;
        for(Obstacle o: ObstaclesOnScreen){
            if(o instanceof Rectangle){
                ((Rectangle) o).G.setTranslateY(((Rectangle) o).G.getTranslateY()+5);
                if(((Rectangle) o).G.getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((Rectangle) o).G);
                }
            }
            if(o instanceof Eight){
                ((Eight) o).G1.setTranslateY(((Eight) o).G1.getTranslateY()+5);
                ((Eight) o).G2.setTranslateY(((Eight) o).G2.getTranslateY()+5);
                if(((Eight) o).G1.getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((Eight) o).G1);
                    GameScreen.getChildren().removeAll(((Eight) o).G2);
                }
            }
            if(o instanceof Plus){
                ((Plus) o).G1.setTranslateY(((Plus) o).G1.getTranslateY()+5);
                ((Plus) o).G2.setTranslateY(((Plus) o).G2.getTranslateY()+5);
                if(((Plus) o).G1.getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((Plus) o).G1);
                    GameScreen.getChildren().removeAll(((Plus) o).G2);
                }
            }
            if(o instanceof Concentric){
                ((Concentric) o).G1.setTranslateY(((Concentric) o).G1.getTranslateY()+5);
                ((Concentric) o).G2.setTranslateY(((Concentric) o).G2.getTranslateY()+5);
                if(((Concentric) o).G1.getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((Concentric) o).G1);
                    GameScreen.getChildren().removeAll(((Concentric) o).G2);
                }
            }
        }
        for(Star s1: StarsOnScreen) {
            s1.G.setTranslateY(s1.G.getTranslateY()+5);
        }
        for(ColorSwitcher cs1: ColorSwitcherOnScreen) {
            cs1.G.setTranslateY(cs1.G.getTranslateY()+5);
        }
        if(toAdd){
            addRandomObstacle(-700);
            ObstaclesOnScreen.remove(0);
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
        URL path = getClass().getResource("/assets/jump.wav");
        AudioClip ac = new AudioClip(path.toString());
        ac.play();
        consta=1;
        f=false;
        tr.setDuration(Duration.millis(300));
        tr.setToY(MainBall.C.getTranslateY() - 50);
        tr.setNode(MainBall.C);
        tr.play();
    }
}
