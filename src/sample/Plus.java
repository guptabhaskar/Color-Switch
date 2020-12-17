package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.io.IOException;

public class Plus extends Obstacle {
    private Group G1;
    private Group G2;

    public Plus(){
        AnchorPane Obstacles = null;
        try {
            Obstacles = FXMLLoader.load(getClass().getResource("Try.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Node node:Obstacles.getChildren()) {
            if(node.getId().equals("Plus1")) {
                G1=(Group) node;
            }
            if(node.getId().equals("Plus2")) {
                G2=(Group) node;
            }
            if(G1!=null && G2!=null){
                break;
            }
        }
    }

    public Group getG1() {
        return G1;
    }

    public void setG1(Group g1) {
        G1 = g1;
    }

    public Group getG2() {
        return G2;
    }

    public void setG2(Group g2) {
        G2 = g2;
    }

    @Override
    public boolean hit(Ball MainBall) {
        for(Node n: G1.getChildren()){
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
