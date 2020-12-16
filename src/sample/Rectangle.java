package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.io.IOException;

public class Rectangle extends Obstacle{
    Group G;
    public Rectangle(){
        AnchorPane Obstacles = null;
        try {
            Obstacles = FXMLLoader.load(getClass().getResource("Try.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Node node:Obstacles.getChildren()) {
            if(node.getId().equals("Rectangle")) {
                G=(Group) node;
                break;
            }
        }
    }

    @Override
    public boolean hit(Ball MainBall) {
        for(Node n: G.getChildren()){
            if(!Shape.intersect((Shape) n, MainBall.C).getBoundsInLocal().isEmpty()){
                if(!((Shape) n).getStroke().equals(MainBall.C.getFill())){
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
