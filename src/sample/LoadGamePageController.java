package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadGamePageController implements Initializable {

    @FXML
    Label Save1;
    Label Save2;
    Label Save3;
    Label Save4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DeserealizeLoadGames();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void BackButtonAction(ActionEvent a) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Main.load(root);
        Main.removeFront();
    }

    private void DeserealizeLoadGames() throws IOException, ClassNotFoundException {
        GameStateArray G1;
        ArrayList<Label> arr = new ArrayList<>();
        arr.add(Save1);
        arr.add(Save2);
        arr.add(Save3);
        arr.add(Save4);
        FileInputStream fileInputStream=new FileInputStream("Save.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        G1=(GameStateArray) objectInputStream.readObject();
        int i=1;
        for(GameState G2: G1.getGameStates()) {
                arr.get(i-1).setText("Save "+ i);
                arr.get(i-1).setOnMouseClicked((MouseEvent e)->{
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    Main.load(root);
                    Main.removeFront();
                });
            i++;
        }
    }
}
