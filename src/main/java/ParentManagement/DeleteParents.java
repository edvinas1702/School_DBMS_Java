package ParentManagement;

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

public class DeleteParents implements Initializable {

    public Label currentMothersName;
    public Label currentMothersLastName;

    public Label currentFathersName;
    public Label currentFathersLastName;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private String sql;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentMothersName.setText(ParentManagement.currentTevai.getMotinosVardas());
        currentMothersLastName.setText(ParentManagement.currentTevai.getMotinosPavarde());
        currentFathersName.setText(ParentManagement.currentTevai.getTevoVardas());
        currentFathersLastName.setText(ParentManagement.currentTevai.getTevoPavarde());


    }

    public void delete(ActionEvent actionEvent) throws SQLException, IOException {

        currentMothersName.setText(ParentManagement.currentTevai.getMotinosVardas());
        currentMothersLastName.setText(ParentManagement.currentTevai.getMotinosPavarde());
        currentFathersName.setText(ParentManagement.currentTevai.getTevoVardas());
        currentFathersLastName.setText(ParentManagement.currentTevai.getTevoPavarde());


        if (!(ParentManagement.currentTevai == null)) {

            sql = "DELETE FROM tevai WHERE tevu_id = ? AND motinos_vardas = ? AND motinos_pavarde = ? AND motinos_tel_nr = ? AND adresas = ? AND tevo_vardas = ? AND tevo_pavarde = ? AND tevo_tel_nr = ?";

            connection = DBManagement.ConToDB();
            preparedStatement = connection.prepareStatement(sql);
            if (ParentManagement.currentTevai != null) {

                preparedStatement.setInt(1, ParentManagement.currentTevai.getTevuID());
                preparedStatement.setString(2, ParentManagement.currentTevai.getMotinosVardas());
                preparedStatement.setString(3, ParentManagement.currentTevai.getMotinosPavarde());
                preparedStatement.setString(4, ParentManagement.currentTevai.getMotinosTelNr());
                preparedStatement.setString(5, ParentManagement.currentTevai.getAdresas());
                preparedStatement.setString(6, ParentManagement.currentTevai.getTevoVardas());
                preparedStatement.setString(7, ParentManagement.currentTevai.getTevoPavarde());
                preparedStatement.setString(8, ParentManagement.currentTevai.getTevoTelNr());


                preparedStatement.executeUpdate();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ParentManagement/ParentManagement.fxml")));
                Stage stage = new Stage();
                stage.setTitle("Tėvai");
                stage.setScene(new Scene(root));
                stage.show();
                Manage.infoMessage("Tėvai sėkmingai pašalinti iš sistemos.");

            }

            Stage stage = (Stage) currentMothersName.getScene().getWindow();
            stage.close();
        }

    }

    public void exit(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ParentManagement/ParentManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) currentMothersName.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
