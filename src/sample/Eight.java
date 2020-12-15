package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

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
}
