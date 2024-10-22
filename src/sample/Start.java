package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("PausePage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("ScorePage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("Try.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        play(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void play(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(new Scene(root, 450, 700));
        primaryStage.show();
    }
}
