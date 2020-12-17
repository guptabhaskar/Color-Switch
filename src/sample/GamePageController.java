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
        MainBall.getC().setLayoutY(650);
        GameScreen.getChildren().add(MainBall.getC());
        try {
            addRandomObstacle(300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            addRandomObstacle(-50);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            addRandomObstacle(-400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnimationTi.start();
    }
    private void addStar(double y) throws IOException {
        Star s = new Star();
        s.getG().setLayoutY(y);
        GameScreen.getChildren().addAll(s.getG());
        StarsOnScreen.add(s);
    }
    private void addColorSwitcher(double y) throws IOException {
        ColorSwitcher cs = new ColorSwitcher();
        cs.getG().setLayoutY(y);
        GameScreen.getChildren().addAll(cs.getG());
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
            ((Rectangle) o).getG().setLayoutY(y);
            GameScreen.getChildren().addAll(((Rectangle) o).getG());
            addStar(y);
            addColorSwitcher(y-150);
        } else if(o instanceof Eight){
            ((Eight) o).getG1().setLayoutY(y-300);
            ((Eight) o).getG2().setLayoutY(y-300);
            GameScreen.getChildren().addAll(((Eight) o).getG1(), ((Eight) o).getG2());
            addStar(y-100);
            addColorSwitcher(y-200);
        } else if(o instanceof Plus){
            ((Plus) o).getG1().setLayoutY(y);
            ((Plus) o).getG2().setLayoutY(y);
            GameScreen.getChildren().addAll(((Plus) o).getG1(), ((Plus) o).getG2());
            addStar(y-70);
            addColorSwitcher(y-140);
        } else if(o instanceof Concentric){
            ((Concentric) o).getG1().setLayoutY(y-300);
            ((Concentric) o).getG2().setLayoutY(y-300);
            GameScreen.getChildren().addAll(((Concentric) o).getG1(), ((Concentric) o).getG2());
            addStar(y);
            addColorSwitcher(y-200);
        }
        ObstaclesOnScreen.add(o);
    }

    private class Timer extends AnimationTimer  {
        @Override
        public void handle(long time){
            gravity();
            if(MainBall.getC().getBoundsInParent().getMinY()<450){
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
            MainBall.getC().toFront();
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
                GameScreen.getChildren().remove(s.getG());
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
                GameScreen.getChildren().remove(cs.getG());
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
        System.out.println("Dead");
    }

    public void getRandomColorOnBall() {
        ArrayList<String> allcolors=new ArrayList<>();
        allcolors.add("#fae100");
        allcolors.add("#900dff");
        allcolors.add("#ff0181");
        allcolors.add("#32dbf0");
        Random rand = new Random();
        int c = rand.nextInt(4);
        MainBall.getC().setFill(Paint.valueOf(allcolors.get(c)));
    }

    public void moveScreenDown() throws IOException {
        double g=1;
        boolean toAdd=false;
        for(Obstacle o: ObstaclesOnScreen){
            if(o instanceof Rectangle){
                ((Rectangle) o).getG().setTranslateY(((Rectangle) o).getG().getTranslateY()+g);
                if(((Rectangle) o).getG().getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((Rectangle) o).getG());
                }
            }
            if(o instanceof Eight){
                ((Eight) o).getG1().setTranslateY(((Eight) o).getG1().getTranslateY()+g);
                ((Eight) o).getG2().setTranslateY(((Eight) o).getG2().getTranslateY()+g);
                if(((Eight) o).getG1().getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((Eight) o).getG1());
                    GameScreen.getChildren().removeAll(((Eight) o).getG2());
                }
            }
            if(o instanceof Plus){
                ((Plus) o).getG1().setTranslateY(((Plus) o).getG1().getTranslateY()+g);
                ((Plus) o).getG2().setTranslateY(((Plus) o).getG2().getTranslateY()+g);
                if(((Plus) o).getG1().getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((Plus) o).getG1());
                    GameScreen.getChildren().removeAll(((Plus) o).getG2());
                }
            }
            if(o instanceof Concentric){
                ((Concentric) o).getG1().setTranslateY(((Concentric) o).getG1().getTranslateY()+g);
                ((Concentric) o).getG2().setTranslateY(((Concentric) o).getG2().getTranslateY()+g);
                if(((Concentric) o).getG1().getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((Concentric) o).getG1());
                    GameScreen.getChildren().removeAll(((Concentric) o).getG2());
                }
            }
        }
        for(Star s1: StarsOnScreen) {
            s1.getG().setTranslateY(s1.getG().getTranslateY()+g);
        }
        for(ColorSwitcher cs1: ColorSwitcherOnScreen) {
            cs1.getG().setTranslateY(cs1.getG().getTranslateY()+g);
        }
        if(toAdd){
            addRandomObstacle(-400);
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

    private double differ=0.015;
    private double consta=1;
    private boolean f=true;
    TranslateTransition BallAnim =new TranslateTransition();
    public void gravity(){
        if(!f) {
            MainBall.getC().setTranslateY(MainBall.getC().getTranslateY() + consta);
            consta += differ;
        }
    }

    public void BallJumpClickAction(MouseEvent m) {
        URL path = getClass().getResource("/assets/jump.wav");
        AudioClip ac = new AudioClip(path.toString());
        ac.play();
        consta=1;
        f=false;
        BallAnim.setDuration(Duration.millis(100));
        BallAnim.setNode(MainBall.getC());
        if(MainBall.getC().getBoundsInParent().getMinY()>100){
            BallAnim.setToY(MainBall.getC().getTranslateY() - 50);
            BallAnim.play();
        } else {
            BallAnim.pause();
        }
    }
}
