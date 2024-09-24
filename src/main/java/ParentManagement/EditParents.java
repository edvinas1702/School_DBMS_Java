package ParentManagement;

import Management.DBManagement;
import Management.Manage;
import TeacherManagement.TeacherManagement;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditParents implements Initializable {

    public Button closeWindow;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public TextField motherNameField;
    public TextField motherLastNameField;
    public TextField motherPhoneNrField;
    public TextField parentAddressField;
    public TextField fatherNameField;
    public TextField fatherLastNameField;
    public TextField fatherPhoneNrField;


    String motherName = ParentManagement.currentTevai.getMotinosVardas();
    String motherLastName = ParentManagement.currentTevai.getMotinosPavarde();
    String motherPhoneNr = ParentManagement.currentTevai.getMotinosTelNr();
    String parentAddress = ParentManagement.currentTevai.getAdresas();
    String fatherName = ParentManagement.currentTevai.getTevoVardas();
    String fatherLastName = ParentManagement.currentTevai.getTevoPavarde();
    String fatherPhoneNr = ParentManagement.currentTevai.getTevoTelNr();


    public void updateInfo(ActionEvent actionEvent) throws IOException {

        if(motherNameField.getText().isEmpty() || motherLastNameField.getText().isEmpty() ||  motherPhoneNrField.getText().isEmpty() || parentAddressField.getText().isEmpty() || fatherNameField.getText().isEmpty() || fatherLastNameField.getText().isEmpty() || fatherPhoneNrField.getText().isEmpty()){
            Manage.alertMessage("Neužpildyti visi privalomi laukai!");return;}


        if(motherNameField.getCharacters().length() > 30) {Manage.alertMessage("Vardo maksimalus ilgis - 30 simbolių!");return;}
        if(motherLastNameField.getCharacters().length() > 30) {Manage.alertMessage("Pavardės maksimalus ilgis - 30 simbolių!");return;}
        if(motherPhoneNrField.getCharacters().length() > 12) {Manage.alertMessage("Telefono nr. maksimalus ilgis - 12 simbolių!");return;}
        if(parentAddressField.getCharacters().length() > 90) {Manage.alertMessage("Adreso maksimalus ilgis - 90 simbolių!");return;}
        if(fatherNameField.getCharacters().length() > 30) {Manage.alertMessage("Vardo maksimalus ilgis - 30 simbolių!");return;}
        if(fatherLastNameField.getCharacters().length() > 30) {Manage.alertMessage("Pavardės maksimalus ilgis - 30 simbolių!");return;}
        if(fatherPhoneNrField.getCharacters().length() > 12) {Manage.alertMessage("Telefono nr. maksimalus ilgis - 12 simbolių!");return;}


        // Atnaujinami tėvai
        try{
            String sql = "UPDATE tevai SET motinos_vardas = ?, motinos_pavarde = ?, motinos_tel_nr = ?, adresas = ?, tevo_vardas = ?, tevo_pavarde = ?, tevo_tel_nr = ? WHERE tevu_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, motherNameField.getText());
            statement.setString(2, motherLastNameField.getText());
            statement.setString(3, motherPhoneNrField.getText());
            statement.setString(4, parentAddressField.getText());
            statement.setString(5, fatherNameField.getText());
            statement.setString(6, fatherLastNameField.getText());
            statement.setString(7, fatherPhoneNrField.getText());
            statement.setInt(8, ParentManagement.currentTevai.getTevuID());

            statement.executeUpdate();

            Manage.infoMessage("Tėvų informacija atnaujinta!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Atnaujinami tėvai

        DBManagement.disconFromDB(connection, preparedStatement);
        exitEdit(actionEvent);


    }

    public void exitEdit(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ParentManagement/ParentManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) motherNameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();


        motherNameField.setText(motherName);

        motherLastNameField.setText(motherLastName);

        motherPhoneNrField.setText(motherPhoneNr);

        parentAddressField.setText(parentAddress);

        fatherNameField.setText(fatherName);

        fatherLastNameField.setText(fatherLastName);

        fatherPhoneNrField.setText(fatherPhoneNr);


    }
}
