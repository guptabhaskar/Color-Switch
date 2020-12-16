package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class Ball {
    Circle C;
    public Ball(){
        AnchorPane Obstacles = null;
        try {
            Obstacles = FXMLLoader.load(getClass().getResource("Try.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Node n:Obstacles.getChildren()) {
            if(n.getId().equals("MainBall")) {
                C = (Circle) n;
                break;
            }
        }
    }
}
