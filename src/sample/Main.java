package sample;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        primaryStage.setTitle("Color Switch");
        Button newG = new Button();
        Button loadG = new Button();
        Button exitG = new Button();
        primaryStage.setScene(new Scene(root, 450, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
