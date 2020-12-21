package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("Try.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        primaryStage.setTitle("Color Switch");
        Scene s = new Scene(root,450,700);
        stage = primaryStage;
        scene = s;
        roots.add(scene.getRoot());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage stage;
    private static Scene scene;
    private static ArrayList<Parent> roots = new ArrayList<>();

    public static void load(Parent root) {
        roots.add(root);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void back() {
        if(roots.size()>1){
            scene.setRoot(roots.get(roots.size()-2));
            roots.remove(roots.size()-1);
        }
    }

    public static void removeFront(){
        while(roots.size()>1){
            roots.remove(0);
        }
    }

    public static void close() {
        stage.close();
    }
}