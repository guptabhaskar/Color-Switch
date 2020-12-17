package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.io.IOException;

public class Rectangle extends Obstacle{
    private Group G;

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

    public Group getG() {
        return G;
    }

    public void setG(Group g) {
        G = g;
    }

    @Override
    public boolean hit(Ball MainBall) {
        for(Node n: G.getChildren()){
            if(!Shape.intersect((Shape) n, MainBall.getC()).getBoundsInLocal().isEmpty()){
                if(!((Shape) n).getStroke().equals(MainBall.getC().getFill())){
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
