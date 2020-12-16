package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.io.IOException;

public class Eight extends Obstacle {
    Group G1;
    Group G2;
    public Eight(){
        AnchorPane Obstacles = null;
        try {
            Obstacles = FXMLLoader.load(getClass().getResource("Try.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Node node:Obstacles.getChildren()) {
            if(node.getId().equals("Eight1")) {
                G1=(Group) node;
            }
            if(node.getId().equals("Eight2")) {
                G2=(Group) node;
            }
            if(G1!=null && G2!=null){
                break;
            }
        }
    }

    @Override
    public boolean hit(Ball MainBall) {
        for(Node n: G1.getChildren()){
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
