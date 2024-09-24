package Management;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Manage {

    public static void alertMessage(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Informacija");
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
    public static void infoMessage(String infoMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patvirtinimas");
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    public static void LoadMainWindow(TextField button) throws IOException {
        FXMLLoader loader = new FXMLLoader(Manage.class.getResource("/Management/MainWindow.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
