package sample;

import java.io.Serializable;
import java.util.ArrayList;

// State Design Pattern
public class GameStateArray implements Serializable {
    private int totalScore;
    private ArrayList<GameState> GameStates = new ArrayList<>();

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public ArrayList<GameState> getGameStates() {
        return GameStates;
    }

    public void setGameStates(ArrayList<GameState> gameStates) {
        GameStates = gameStates;
    }
}
