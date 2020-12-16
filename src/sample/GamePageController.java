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
import javafx.scene.paint.Color;
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
    AnimationTimer AnimationTi = new Timer();
    ArrayList<Obstacle> onScreen = new ArrayList<>();
    ArrayList<Star> StarsOnScreen = new ArrayList<>();
    ArrayList<ColorSwitcher> ColorSwitcherOnScreen = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainBall.C.setLayoutY(650);
        GameScreen.getChildren().add(MainBall.C);
        addRandomObstacle(300);
        addRandomObstacle(-50);
        addRandomObstacle(-400);
        try {
            addStar(300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            addColorSwitcher(500);
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

    private void addRandomObstacle(double y){
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
            ((Triangle) o).G.setLayoutY(y);
            GameScreen.getChildren().addAll(((Triangle) o).G);
        } else if(o instanceof Rectangle){
            ((Rectangle) o).G.setLayoutY(y);
            GameScreen.getChildren().addAll(((Rectangle) o).G);
        } else if(o instanceof Eight){
            ((Eight) o).G1.setLayoutY(y);
            ((Eight) o).G2.setLayoutY(y);
            GameScreen.getChildren().addAll(((Eight) o).G1, ((Eight) o).G2);
        } else if(o instanceof Plus){
            ((Plus) o).G1.setLayoutY(y);
            ((Plus) o).G2.setLayoutY(y);
            GameScreen.getChildren().addAll(((Plus) o).G1, ((Plus) o).G2);
        } else if(o instanceof Concentric){
            ((Concentric) o).G1.setLayoutY(y);
            ((Concentric) o).G2.setLayoutY(y);
            GameScreen.getChildren().addAll(((Concentric) o).G1, ((Concentric) o).G2);
        }
        onScreen.add(o);
    }

    private class Timer extends AnimationTimer{
        @Override
        public void handle(long time){
            gravity();
            if(MainBall.C.getTranslateY()<-300){
                moveScreenDown();
            }
            // Give Random Color to Ball;
            if(!BallColor){
                getRandomColorOnBall();
                BallColor=true;
            }
            MainBall.C.toFront();
        }
    }

    public void getRandomColorOnBall() {
        ArrayList<String> allcolors=new ArrayList<>();
        allcolors.add("#FAE100");
        allcolors.add("#900DFF");
        allcolors.add("#FF0181");
        allcolors.add("#32DBF0");
        Random rand = new Random();
        int c = rand.nextInt(4);
        MainBall.C.setFill(Paint.valueOf(allcolors.get(c)));
    }

    public void moveScreenDown() {
        MainBall.C.setTranslateY(MainBall.C.getTranslateY()+20);
        boolean toAdd=false;
        for(Obstacle o: onScreen){
            if(o instanceof Triangle){
                ((Triangle) o).G.setTranslateY(((Triangle) o).G.getTranslateY()+5);
                if(((Triangle) o).G.getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((Triangle) o).G);
                }
            }
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
            addRandomObstacle(-350);
            onScreen.remove(0);
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
