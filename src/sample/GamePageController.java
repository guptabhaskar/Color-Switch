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
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {
    static GameStateArray G = new GameStateArray();
    static GameState GS = new GameState();

    public static void Serialize() throws Exception{
        FileOutputStream fileOutputStream=new FileOutputStream("Save.txt");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(G);
    }

    public static void Deserialize() throws Exception{
        FileInputStream fileInputStream=new FileInputStream("Save.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        G=(GameStateArray) objectInputStream.readObject();
    }

    Ball MainBall=new Ball();
    @FXML private AnchorPane GameScreen;

    boolean BallColor=false; // To check if ball has random color or not in start
    AnimationTimer AnimationTi = new Timer();
    ArrayList<Common> ObstaclesOnScreen = new ArrayList<>();
    ArrayList<Common> StarsOnScreen = new ArrayList<>();
    ArrayList<Common> BoltsOnScreen = new ArrayList<>();
    ArrayList<Common> ColorSwitcherOnScreen = new ArrayList<>();

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
    private void addBolt(double y) throws IOException {
        Bolt s = new Bolt();
        s.getG().setLayoutY(y);
        GameScreen.getChildren().addAll(s.getG());
        BoltsOnScreen.add(s);
    }

    private void addColorSwitcher(double y) throws IOException {
        ColorSwitcher cs = new ColorSwitcher();
        cs.getG().setLayoutY(y);
        GameScreen.getChildren().addAll(cs.getG());
        ColorSwitcherOnScreen.add(cs);
    }

    private void addRandomObstacle(double y) throws IOException {
        // Factory Design Pattern
        GetObstacle GO = new GetObstacle();
        Random r = new Random();
        int c = r.nextInt(5);
        Common o = GO.getObstacle(c);
        if(o instanceof Rectangle) {
            ((Rectangle) o).getG().setLayoutY(y);
            GameScreen.getChildren().addAll(((Rectangle) o).getG());
            addStar(y);
            addColorSwitcher(y-150);
        } else if(o instanceof Eight) {
            ((Eight) o).getG1().setLayoutY(y-300);
            ((Eight) o).getG2().setLayoutY(y-300);
            GameScreen.getChildren().addAll(((Eight) o).getG1(), ((Eight) o).getG2());
            addStar(y-100);
            addColorSwitcher(y-200);
        } else if(o instanceof Plus) {
            ((Plus) o).getG1().setLayoutY(y);
            ((Plus) o).getG2().setLayoutY(y);
            GameScreen.getChildren().addAll(((Plus) o).getG1(), ((Plus) o).getG2());
            addStar(y-70);
            addColorSwitcher(y-140);
        } else if(o instanceof Concentric) {
            ((Concentric) o).getG1().setLayoutY(y-300);
            ((Concentric) o).getG2().setLayoutY(y-300);
            GameScreen.getChildren().addAll(((Concentric) o).getG1(), ((Concentric) o).getG2());
            addStar(y);
            addColorSwitcher(y-200);
        } else if(o instanceof NormalCircle) {
            ((NormalCircle) o).getG().setLayoutY(y - 300);
            GameScreen.getChildren().addAll(((NormalCircle) o).getG());
            addBolt(y - 200);
            addColorSwitcher(y - 200);
        }
        ObstaclesOnScreen.add(o);
    }

    private boolean power = false;
    private int ctr=0;
    private class Timer extends AnimationTimer  {
        @Override
        public void handle(long time){
            if(pause()) {
                gravity();
                if (MainBall.getC().getBoundsInParent().getMinY() < 450) {
                    try {
                        moveScreenDown(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // Give Random Color to Ball;
                if (!BallColor) {
                    getRandomColorOnBall();
                    BallColor = true;
                }
                MainBall.getC().toFront();
                PauseB.toFront();
                ScoreL.toFront();
                try {
                    checkCollision();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if((MainBall.getC().getBoundsInParent().getMinY()>700)) {
                    try {
                        goToScorePage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @FXML Label ScoreL;
    private void checkCollision() throws Exception {
        // Iterator Design Pattern
        for(Common s: StarsOnScreen) {
            if(s.hit(MainBall)){
                if(power){
                    ctr+=1;
                }
                URL path = getClass().getResource("/assets/star.wav");
                AudioClip ac = new AudioClip(path.toString());
                ac.play();
                ScoreL.setText(Integer.toString ( Integer.parseInt(ScoreL.getText()) + 1));
                GameScreen.getChildren().remove(((Star)s).getG());
                StarsOnScreen.remove(0);
                break;
            }
        }
        for(Common  cs: ColorSwitcherOnScreen) {
            if(cs.hit(MainBall)) {
                URL path = getClass().getResource("/assets/colorswitch.wav");
                AudioClip ac = new AudioClip(path.toString());
                ac.play();
                getRandomColorOnBall();
                GameScreen.getChildren().remove(((ColorSwitcher) cs).getG());
                ColorSwitcherOnScreen.remove(0);
                break;
            }
        }
        for(Common  cs: BoltsOnScreen) {
            if(cs.hit(MainBall)) {
                URL path = getClass().getResource("/assets/colorswitch.wav");
                AudioClip ac = new AudioClip(path.toString());
                ac.play();
                power = true;
                GameScreen.getChildren().remove(((Bolt) cs).getG());
                ScoreL.setText(Integer.toString ( Integer.parseInt(ScoreL.getText()) + 3));
                BoltsOnScreen.remove(0);
                break;
            }
        }
        for(Common o: ObstaclesOnScreen) {
            if(o.hit(MainBall) && !power) {
                goToScorePage();
                break;
            }
        }
    }

    public void goToScorePage() throws Exception {
        URL path = getClass().getResource("/assets/dead.wav");
        AudioClip ac = new AudioClip(path.toString());
        ac.play();

        // Just for now
        System.out.println("Dead");
        MainBall.getC().setLayoutY(MainBall.getC().getLayoutY()-50);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new ScorePageController());
            loader.setLocation(getClass().getResource("/sample/ScorePage.fxml"));
            Parent root = loader.load();
            Main.load(root);
            ((ScorePageController)loader.getController()).update(Integer.parseInt(ScoreL.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void moveScreenDown(double speed) throws IOException {
        double g=speed;
        boolean toAdd=false;
        for(Common o: ObstaclesOnScreen){
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
            if(o instanceof NormalCircle){
                ((NormalCircle) o).getG().setTranslateY(((NormalCircle) o).getG().getTranslateY()+g);
                if(((NormalCircle) o).getG().getBoundsInParent().getMinY()>700) {
                    toAdd=true;
                    GameScreen.getChildren().removeAll(((NormalCircle) o).getG());
                }
            }
        }
        for(Common s1: StarsOnScreen) {
            ((Star)s1).getG().setTranslateY(((Star)s1).getG().getTranslateY()+g);
        }
        for(Common cs1: ColorSwitcherOnScreen) {
            ((ColorSwitcher)cs1).getG().setTranslateY(((ColorSwitcher)cs1).getG().getTranslateY()+g);
        }
        for(Common cs1: BoltsOnScreen) {
            ((Bolt)cs1).getG().setTranslateY(((Bolt)cs1).getG().getTranslateY()+g);
        }
        if(toAdd){
            addRandomObstacle(-400);
            ObstaclesOnScreen.remove(0);
        }
        if(ctr==5){
            ctr=0;
            power=false;
        }
    }

    // For Pause Button
    @FXML private Button PauseB;
    public void PauseButtonAction(ActionEvent a) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PausePage.fxml"));
        Main.load(root);
    }

    public boolean pause(){
        return GameScreen.getScene()!=null;
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
