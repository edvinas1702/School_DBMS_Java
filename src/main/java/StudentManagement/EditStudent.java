package StudentManagement;

import Management.DBManagement;
import Management.Manage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditStudent implements Initializable{

    public Button closeWindow;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ComboBox parentsList;
    public ComboBox classList;

    public TextField studentNameField;
    public TextField studentLastNameField;
    public TextField studentBirthDateField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();

        String studentName = StudentManagement.currentMokinys.getMokinioVardas();

        String studentLastName = StudentManagement.currentMokinys.getMokinioPavarde();

        LocalDate studentBirthDate = StudentManagement.currentMokinys.getGimimoData();

        Integer parentID = StudentManagement.currentMokinys.getTevuID();

        Integer classID = StudentManagement.currentMokinys.getKlasesID();


        studentNameField.setText(studentName);

        studentLastNameField.setText(studentLastName);

        studentBirthDateField.setText(String.valueOf(studentBirthDate));


        // Įdedami tėvų vardai ir pavardės į ComboBox'ą.
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT motinos_vardas, motinos_pavarde, tevo_vardas, tevo_pavarde FROM tevai");

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String mothersName = resultSet.getString("motinos_vardas");
                String motherslastName = resultSet.getString("motinos_pavarde");

                String fathersName = resultSet.getString("tevo_vardas");
                String fathersLastName = resultSet.getString("tevo_pavarde");

                String entry = mothersName + " " + motherslastName + " " + fathersName + " " + fathersLastName;

                parentsList.getItems().add(entry);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Įdedami tėvų vardai ir pavardės į ComboBox'ą.




        // Padaroma taip, kad pirmasis įrašas ComboBox'e būtų mokinio tėvai.
        try {
            PreparedStatement statement2 = connection.prepareStatement("SELECT motinos_vardas, motinos_pavarde, tevo_vardas, tevo_pavarde FROM tevai WHERE tevu_id = ?");

            statement2.setInt(1, parentID);

            ResultSet resultSet = statement2.executeQuery();


            while (resultSet.next()) {
                String mothersName = resultSet.getString("motinos_vardas");
                String motherslastName = resultSet.getString("motinos_pavarde");

                String fathersName = resultSet.getString("tevo_vardas");
                String fathersLastName = resultSet.getString("tevo_pavarde");

                String entry = mothersName + " " + motherslastName + " " + fathersName + " " + fathersLastName;

                parentsList.setValue(entry);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Padaroma taip, kad pirmasis įrašas ComboBox'e būtų mokinio tėvai.




        // Įdedamos klasės į ComboBox'ą.
        try {
            PreparedStatement statement3 = connection.prepareStatement("SELECT klase FROM klase");

            ResultSet resultSet = statement3.executeQuery();


            while (resultSet.next()) {
                String className = resultSet.getString("klase");

                classList.getItems().add(className);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Įdedamos klasės į ComboBox'ą.



        // Padaroma taip, kad pirmasis įrašas ComboBox'e būtų mokinio klasė.
        try {
            PreparedStatement statement4 = connection.prepareStatement("SELECT klase FROM klase WHERE klases_id = ?");

            statement4.setInt(1, classID);

            ResultSet resultSet = statement4.executeQuery();

            while (resultSet.next()) {
                String className = resultSet.getString("klase");

                classList.setValue(className);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Padaroma taip, kad pirmasis įrašas ComboBox'e būtų mokinio klasė.


    }

    public void updateInfo(ActionEvent actionEvent) throws SQLException, IOException {

        // GAUNAMAS TĖVŲ ID
        String selectedParentInfo = String.valueOf(parentsList.getValue());

        String array[] = selectedParentInfo.split(" ", 4);

        String mothersName = array[0];

        String mothersLastName = array[1];

        String fathersName = array[2];

        String fathersLastName = array[3];

        Integer tevuID = null;

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT tevu_id FROM tevai WHERE motinos_vardas = ? AND motinos_pavarde = ? AND tevo_vardas = ? AND tevo_pavarde = ?");
            statement.setString(1, mothersName);
            statement.setString(2, mothersLastName);
            statement.setString(3, fathersName);
            statement.setString(4, fathersLastName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tevuID = resultSet.getInt("tevu_id");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // GAUNAMAS TĖVŲ ID



        // GAUNAMAS KLASĖS ID
        String selectedClass = String.valueOf(classList.getValue());

        Integer classID = null;

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT klases_id FROM klase WHERE klase = ?");

            statement.setString(1, selectedClass);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                classID = resultSet.getInt("klases_id");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // GAUNAMAS KLASĖS ID


        String studentName = studentNameField.getText();

        String studentLastName = studentLastNameField.getText();

        LocalDate studentBirthDate = LocalDate.parse(studentBirthDateField.getText());


        // Atnaujinamas mokinys
        try{
            String sql = "UPDATE mokinys SET tevu_id = ?, klases_id = ?, mokinio_vardas = ?, mokinio_pavarde = ?, gimimo_data = ? WHERE mokinio_id = ?";

            PreparedStatement statement2 = connection.prepareStatement(sql);

            statement2.setInt(1, tevuID);
            statement2.setInt(2, classID);
            statement2.setString(3, studentName);
            statement2.setString(4, studentLastName);
            statement2.setString(5, String.valueOf(studentBirthDate));
            statement2.setInt(6, StudentManagement.currentMokinys.getMokinioID());

            statement2.executeUpdate();

            Manage.infoMessage("Mokinys atnaujintas!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Atnaujinamas mokinys

        DBManagement.disconFromDB(connection, preparedStatement);
        exitEdit(actionEvent);

    }
    @FXML
    private void exitEdit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/StudentManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) studentNameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
