package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("PausePage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("ScorePage.fxml"));
        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(new Scene(root, 550, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}