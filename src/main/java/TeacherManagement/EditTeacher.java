package TeacherManagement;

import Management.DBManagement;
import Management.Manage;
import StudentManagement.StudentManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditTeacher implements Initializable {

    public Button closeWindow;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public TextField teacherNameField;
    public TextField teacherLastNameField;
    public TextField teacherPhoneNrField;
    public TextField teacherEmailField;

    String teacherName = TeacherManagement.currentMokytojas.getMokytojoVardas();

    String teacherLastName = TeacherManagement.currentMokytojas.getMokytojoPavarde();

    String teacherPhoneNumber = TeacherManagement.currentMokytojas.getMokytojoTelNr();
    String teacherEmail = TeacherManagement.currentMokytojas.getMokytojoElPastas();

    Integer teacherID = TeacherManagement.currentMokytojas.getMokytojoID();


    public void updateInfo(ActionEvent actionEvent) throws IOException {

        if(teacherNameField.getText().isEmpty() || teacherLastNameField.getText().isEmpty() ||  teacherPhoneNrField.getText().isEmpty() || teacherEmailField.getText().isEmpty()){
            Manage.alertMessage("Neužpildyti visi privalomi laukai!");return;}

        if(teacherNameField.getCharacters().length() > 30) {Manage.alertMessage("Vardo maksimalus ilgis - 30 simbolių!");return;}
        if(teacherLastNameField.getCharacters().length() > 30) {Manage.alertMessage("Pavardės maksimalus ilgis - 30 simbolių!");return;}
        if(teacherPhoneNrField.getCharacters().length() > 12) {Manage.alertMessage("Telefono nr. maksimalus ilgis - 12 simbolių!");return;}
        if(teacherEmailField.getCharacters().length() > 254) {Manage.alertMessage("El. pašto maksimalus ilgis - 254 simboliai!");return;}

        // Atnaujinamas mokytojas
        try{
            String sql = "UPDATE mokytojas SET mokytojo_vardas = ?, mokytojo_pavarde = ?, mokytojo_tel_nr = ?, mokytojo_el_pastas = ? WHERE mokytojo_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, teacherNameField.getText());
            statement.setString(2, teacherLastNameField.getText());
            statement.setString(3, teacherPhoneNrField.getText());
            statement.setString(4, teacherEmailField.getText());
            statement.setInt(5, teacherID);

            statement.executeUpdate();

            Manage.infoMessage("Mokytojas atnaujintas!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Atnaujinamas mokytojas

        DBManagement.disconFromDB(connection, preparedStatement);
        exitEdit(actionEvent);

    }

    public void exitEdit(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherManagement/TeacherManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) teacherNameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();


        teacherNameField.setText(teacherName);

        teacherLastNameField.setText(teacherLastName);

        teacherPhoneNrField.setText(teacherPhoneNumber);

        teacherEmailField.setText(teacherEmail);


    }




}
