package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Triangle extends Obstacle {
    Group G;
    public Triangle(){
        AnchorPane Obstacles = null;
        try {
            Obstacles = FXMLLoader.load(getClass().getResource("Try.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Node node:Obstacles.getChildren()) {
            if(node.getId().equals("Triangle")) {
                G=(Group) node;
                break;
            }
        }
    }
}
