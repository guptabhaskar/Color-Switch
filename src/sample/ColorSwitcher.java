package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.io.IOException;

public class ColorSwitcher extends Effects {
    private Group G;

    public ColorSwitcher() throws IOException {
        AnchorPane Obstacles = FXMLLoader.load(getClass().getResource("Try.fxml"));
        for(Node node:Obstacles.getChildren()) {
            if(node.getId().equals("ColorSwitcher")) {
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
        for(Node n : G.getChildren()){
            if(!Shape.intersect((Shape) n, MainBall.getC()).getBoundsInLocal().isEmpty()){
                return true;
            }
        }
        return false;
    }
}
