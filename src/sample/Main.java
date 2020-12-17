package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Navigate Player;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        primaryStage.setTitle("Color Switch");
        Scene s = new Scene(root,450,700);
        Player = new Navigate(primaryStage, s);
    }

    public static void main(String[] args) {
        launch(args);
    }
}