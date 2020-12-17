package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Navigate {
    private final Stage stage;
    private final Scene scene;
    private ArrayList<Parent> roots = new ArrayList<>();

    public Navigate(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
        roots.add(scene.getRoot());
        stage.setScene(scene);
        stage.show();
    }

    public void load(Parent root) {
        roots.add(root);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    public void back() {
        if(roots.size()>1){
            scene.setRoot(roots.get(roots.size()-2));
            roots.remove(roots.size()-1);
        }
    }

    public void removeFront(){
        while(roots.size()>1){
            roots.remove(0);
        }
    }

    public void close() {
        stage.close();
    }
}
