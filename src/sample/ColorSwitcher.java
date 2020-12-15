package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ColorSwitcher extends Effects {
    Group G;
    public ColorSwitcher() throws IOException {
        AnchorPane Obstacles = FXMLLoader.load(getClass().getResource("Try.fxml"));
        for(Node node:Obstacles.getChildren()) {
            if(node.getId().equals("ColorSwitcher")) {
                G=(Group) node;
                break;
            }
        }
    }
}
