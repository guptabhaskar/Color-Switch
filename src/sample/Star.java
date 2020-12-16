package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

import java.io.IOException;

public class Star extends Effects {
    SVGPath G;
    public Star() throws IOException {
        AnchorPane Obstacles = FXMLLoader.load(getClass().getResource("Try.fxml"));
        for(Node node:Obstacles.getChildren()) {
            if(node.getId().equals("Star")) {
                G=(SVGPath) node;
                break;
            }
        }
    }

    @Override
    public boolean hit(Ball MainBall) {
        if(!Shape.intersect(G, MainBall.C).getBoundsInLocal().isEmpty()){
            return true;
        }
        return false;
    }
}
