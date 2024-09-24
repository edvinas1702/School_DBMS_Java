package TeacherManagement;

import Management.DBManagement;
import Management.Manage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewTeacher {

    public TextField newTeacherName;

    public TextField newTeacherLastName;

    public TextField newTeacherPhoneNumber;

    public TextField newTeacherEmail;

    public Button closeWindow;
    private Connection connection;
    private PreparedStatement preparedStatement;



    public void createTeacher(ActionEvent actionEvent) throws SQLException, IOException {

        if(newTeacherName.getText().isEmpty() || newTeacherLastName.getText().isEmpty() ||  newTeacherPhoneNumber.getText().isEmpty() || newTeacherEmail.getText().isEmpty()){
            Manage.alertMessage("Neužpildyti visi privalomi laukai!");return;}

        if(newTeacherName.getCharacters().length() > 30) {Manage.alertMessage("Vardo maksimalus ilgis - 30 simbolių!");return;}
        if(newTeacherLastName.getCharacters().length() > 30) {Manage.alertMessage("Pavardės maksimalus ilgis - 30 simbolių!");return;}
        if(newTeacherPhoneNumber.getCharacters().length() > 12) {Manage.alertMessage("Telefono nr. maksimalus ilgis - 12 simbolių!");return;}
        if(newTeacherEmail.getCharacters().length() > 254) {Manage.alertMessage("El. pašto maksimalus ilgis - 254 simboliai!");return;}


        String sql = "select mokytojo_vardas from mokytojas where mokytojo_vardas = ? and mokytojo_pavarde = ? and mokytojo_tel_nr = ? and mokytojo_el_pastas = ?";
        String sql2 = "insert into mokytojas (mokytojo_vardas, mokytojo_pavarde, mokytojo_tel_nr, mokytojo_el_pastas) values (?,?,?,?)";

        connection = DBManagement.ConToDB();
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, newTeacherName.getText());
        preparedStatement.setString(2, newTeacherLastName.getText());
        preparedStatement.setString(3, newTeacherPhoneNumber.getText());
        preparedStatement.setString(4, newTeacherEmail.getText());


        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            Manage.alertMessage("Toks mokytojas jau yra sistemoje!");
        } else {
            PreparedStatement data = connection.prepareStatement(sql2);
            data.setString(1, newTeacherName.getText()); // Neteisingi duomenu tipai
            data.setString(2, newTeacherLastName.getText());
            data.setString(3, newTeacherPhoneNumber.getText());
            data.setString(4, newTeacherEmail.getText());

            data.executeUpdate();
            Manage.infoMessage("Mokytojas: " + newTeacherName.getText() + " pridėtas!");
        }
        DBManagement.disconFromDB(connection, preparedStatement);
        exit(actionEvent);

    }


    public void exit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherManagement/TeacherManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) newTeacherName.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
