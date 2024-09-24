package TeacherManagement;

import Management.DBManagement;
import Management.Manage;
import StudentManagement.StudentManagement;
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

public class DeleteTeacher implements Initializable {
    public Label currentTeacherName;
    public Label currentTeacherLastName;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private String sql;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentTeacherName.setText(TeacherManagement.currentMokytojas.getMokytojoVardas());
        currentTeacherLastName.setText(TeacherManagement.currentMokytojas.getMokytojoPavarde());
    }

    public void delete(ActionEvent actionEvent) throws SQLException, IOException {

        currentTeacherName.setText(TeacherManagement.currentMokytojas.getMokytojoVardas());
        currentTeacherLastName.setText(TeacherManagement.currentMokytojas.getMokytojoPavarde());

        if (!(TeacherManagement.currentMokytojas == null)) {

            sql = "DELETE FROM mokytojas WHERE mokytojo_id = ? AND mokytojo_vardas = ? AND mokytojo_pavarde = ? AND mokytojo_tel_nr = ? AND mokytojo_el_pastas = ?";

            connection = DBManagement.ConToDB();
            preparedStatement = connection.prepareStatement(sql);
            if (TeacherManagement.currentMokytojas != null) {

                preparedStatement.setString(1, Integer.toString(TeacherManagement.currentMokytojas.getMokytojoID()));
                preparedStatement.setString(2, TeacherManagement.currentMokytojas.getMokytojoVardas());
                preparedStatement.setString(3, TeacherManagement.currentMokytojas.getMokytojoPavarde());
                preparedStatement.setString(4, TeacherManagement.currentMokytojas.getMokytojoTelNr());
                preparedStatement.setString(5, TeacherManagement.currentMokytojas.getMokytojoElPastas());

                preparedStatement.executeUpdate();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/TeacherManagement/TeacherManagement.fxml")));
                Stage stage = new Stage();
                stage.setTitle("Mokytojas");
                stage.setScene(new Scene(root));
                stage.show();
                Manage.infoMessage("Mokytojas sėkmingai pašalintas iš sistemos.");

            }

            Stage stage = (Stage) currentTeacherName.getScene().getWindow();
            stage.close();
        }

    }


    public void exit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherManagement/TeacherManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) currentTeacherName.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
