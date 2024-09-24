package StudentManagement;

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

public class DeleteStudent implements Initializable {
    public Label currentStudentName;
    public Label currentStudentLastName;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private String sql;



    public void delete(ActionEvent actionEvent) throws SQLException, IOException {
        currentStudentName.setText(StudentManagement.currentMokinys.getMokinioVardas());
        currentStudentLastName.setText(StudentManagement.currentMokinys.getMokinioPavarde());

        if (!(StudentManagement.currentMokinys == null)) {

            sql = "DELETE FROM mokinys WHERE mokinio_id = ? AND tevu_id = ? AND klases_id = ? AND mokinio_vardas = ? AND mokinio_pavarde = ? AND gimimo_data = ?";

            connection = DBManagement.ConToDB();
            preparedStatement = connection.prepareStatement(sql);
            if (StudentManagement.currentMokinys != null) {

                preparedStatement.setString(1, Integer.toString(StudentManagement.currentMokinys.getMokinioID()));
                preparedStatement.setString(2, Integer.toString(StudentManagement.currentMokinys.getTevuID()));
                preparedStatement.setString(3, Integer.toString(StudentManagement.currentMokinys.getKlasesID()));
                preparedStatement.setString(4, StudentManagement.currentMokinys.getMokinioVardas());
                preparedStatement.setString(5, StudentManagement.currentMokinys.getMokinioPavarde());
                preparedStatement.setString(6, String.valueOf(StudentManagement.currentMokinys.getGimimoData())); // Galbut blogai, kad yra String.valueOf()

                preparedStatement.executeUpdate();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/StudentManagement/StudentManagement.fxml")));
                Stage stage = new Stage();
                stage.setTitle("Mokinys");
                stage.setScene(new Scene(root));
                stage.show();
                Manage.infoMessage("Mokinys sėkmingai pašalintas iš sistemos.");

            }

            Stage stage = (Stage) currentStudentName.getScene().getWindow();
            stage.close();
        }
    }
    public void exit(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/StudentManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) currentStudentName.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentStudentName.setText(StudentManagement.currentMokinys.getMokinioVardas());
        currentStudentLastName.setText(StudentManagement.currentMokinys.getMokinioPavarde());

    }
}
