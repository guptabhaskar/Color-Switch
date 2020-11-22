package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("PausePage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("ScorePage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("Try.fxml"));

        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(new Scene(root, 450, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}