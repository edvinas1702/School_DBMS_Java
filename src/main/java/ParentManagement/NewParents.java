package ParentManagement;

import Management.DBManagement;
import Management.Manage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewParents {


    public TextField newMotherName;

    public TextField newMotherLastName;

    public TextField newMotherPhoneNr;

    public TextField newParentAddress;

    public TextField newFatherName;

    public TextField newFatherLastName;

    public TextField newFatherPhoneNr;

    public Button closeWindow;
    private Connection connection;
    private PreparedStatement preparedStatement;




    public void createParents(ActionEvent actionEvent) throws SQLException, IOException {

        if(newMotherName.getText().isEmpty() || newMotherLastName.getText().isEmpty() ||  newMotherPhoneNr.getText().isEmpty() || newParentAddress.getText().isEmpty() || newFatherName.getText().isEmpty() || newFatherLastName.getText().isEmpty() || newFatherPhoneNr.getText().isEmpty()){
            Manage.alertMessage("Neužpildyti visi privalomi laukai!");return;}

        if(newMotherName.getCharacters().length() > 30) {Manage.alertMessage("Vardo maksimalus ilgis - 30 simbolių!");return;}
        if(newMotherLastName.getCharacters().length() > 30) {Manage.alertMessage("Pavardės maksimalus ilgis - 30 simbolių!");return;}
        if(newMotherPhoneNr.getCharacters().length() > 12) {Manage.alertMessage("Telefono nr. maksimalus ilgis - 12 simbolių!");return;}
        if(newParentAddress.getCharacters().length() > 90) {Manage.alertMessage("Adreso maksimalus ilgis - 90 simbolių!");return;}
        if(newFatherName.getCharacters().length() > 30) {Manage.alertMessage("Vardo maksimalus ilgis - 30 simbolių!");return;}
        if(newFatherLastName.getCharacters().length() > 30) {Manage.alertMessage("Pavardės maksimalus ilgis - 30 simbolių!");return;}
        if(newFatherPhoneNr.getCharacters().length() > 12) {Manage.alertMessage("Telefono nr. maksimalus ilgis - 12 simbolių!");return;}



        String sql = "select motinos_vardas from tevai where motinos_vardas = ? and motinos_pavarde = ? and motinos_tel_nr = ? and adresas = ? and tevo_vardas = ? and tevo_pavarde = ? and tevo_tel_nr = ?";
        String sql2 = "insert into tevai (motinos_vardas, motinos_pavarde, motinos_tel_nr, adresas, tevo_vardas, tevo_pavarde, tevo_tel_nr) values (?,?,?,?,?,?,?)";

        connection = DBManagement.ConToDB();
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, newMotherName.getText());
        preparedStatement.setString(2, newMotherLastName.getText());
        preparedStatement.setString(3, newMotherPhoneNr.getText());
        preparedStatement.setString(4, newParentAddress.getText());
        preparedStatement.setString(5, newFatherName.getText());
        preparedStatement.setString(6, newFatherLastName.getText());
        preparedStatement.setString(7, newFatherPhoneNr.getText());


        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            Manage.alertMessage("Tokie tėvai jau yra sistemoje!");
        } else {
            PreparedStatement data = connection.prepareStatement(sql2);

            data.setString(1, newMotherName.getText());
            data.setString(2, newMotherLastName.getText());
            data.setString(3, newMotherPhoneNr.getText());
            data.setString(4, newParentAddress.getText());
            data.setString(5, newFatherName.getText());
            data.setString(6, newFatherLastName.getText());
            data.setString(7, newFatherPhoneNr.getText());

            data.executeUpdate();
            Manage.infoMessage("Tėvai pridėti!");
        }
        DBManagement.disconFromDB(connection, preparedStatement);
        exit(actionEvent);

    }


    public void exit(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ParentManagement/ParentManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) newMotherName.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }


}
