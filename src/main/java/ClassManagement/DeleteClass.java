package ClassManagement;

import Management.DBManagement;
import Management.Manage;
import TeacherManagement.TeacherManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class DeleteClass implements Initializable {

    public Label currentClassName;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private String sql;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentClassName.setText(ClassManagement.currentKlase.getKlase());

    }

    public void delete(ActionEvent actionEvent) throws SQLException, IOException {

        currentClassName.setText(ClassManagement.currentKlase.getKlase());

        if (!(ClassManagement.currentKlase == null)) {

            sql = "DELETE FROM klase WHERE mokytojo_id = ? AND klase = ?";

            connection = DBManagement.ConToDB();
            preparedStatement = connection.prepareStatement(sql);
            if (ClassManagement.currentKlase != null) {

                preparedStatement.setString(1, Integer.toString(ClassManagement.currentKlase.getMokytojoID()));
                preparedStatement.setString(2, ClassManagement.currentKlase.getKlase());

                preparedStatement.executeUpdate();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ClassManagement/ClassManagement.fxml")));
                Stage stage = new Stage();
                stage.setTitle("Klasė");
                stage.setScene(new Scene(root));
                stage.show();
                Manage.infoMessage("Klasė sėkmingai pašalinta iš sistemos.");

            }

            Stage stage = (Stage) currentClassName.getScene().getWindow();
            stage.close();
        }


    }

    public void exit(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassManagement/ClassManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) currentClassName.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
