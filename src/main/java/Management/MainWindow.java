package Management;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainWindow implements Initializable {

    public Button usersID;
    public Button studentManagementBtn;


    public void studentManagement(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/StudentManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) studentManagementBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void overallUsers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/Users.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) studentManagementBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void reservations(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/ReservationsManagement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) studentManagementBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void teacherManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherManagement/TeacherManagement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) studentManagementBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void parentManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ParentManagement/ParentManagement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) studentManagementBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void classManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassManagement/ClassManagement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) studentManagementBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

