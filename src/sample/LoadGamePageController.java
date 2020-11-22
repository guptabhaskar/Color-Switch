package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadGamePageController {
    public void startNewGameAction(ActionEvent actionEvent) {
    }

    public void ShowSavedGameAction(ActionEvent actionEvent) {
    }
    @FXML
    private Button ExitB;
    public void ExitGameAction(ActionEvent actionEvent) throws IOException {
        Stage s=(Stage)ExitB.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        s.setScene(new Scene(root, 450, 700));
        s.show();
    }

    public void mouseEnterExit(MouseEvent mouseEvent) {
    }

    public void mouseEnterSave(MouseEvent mouseEvent) {
    }

    public void mouseEnterPlay(MouseEvent mouseEvent) {
    }

    public void mouseExitPlay(MouseEvent mouseEvent) {
    }

    public void mouseExitSave(MouseEvent mouseEvent) {
    }

    public void mouseExitExit(MouseEvent mouseEvent) {
    }


}
