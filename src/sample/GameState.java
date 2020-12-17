package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private int highestScore;
    private ArrayList<ObstacleState> obstacles = new ArrayList<>();
    private int score;
    private double BallPos;
    private String BallColor;

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public ArrayList<ObstacleState> getObstacle() {
        return obstacles;
    }

    public void setObstacle(ArrayList<ObstacleState> obstacle) {
        this.obstacles = obstacles;
    }

    public void addObstacle(ObstacleState o) {
        this.obstacles.add(o);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getBallPos() {
        return BallPos;
    }

    public void setBallPos(double ballPos) {
        BallPos = ballPos;
    }

    public String getBallColor() {
        return BallColor;
    }

    public void setBallColor(String ballColor) {
        BallColor = ballColor;
    }
}
