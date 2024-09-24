package StudentManagement;

import Management.DBManagement;
import Management.Manage;
import ParentManagement.ParentManagement;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewStudent implements Initializable {

    public TextField newMokinioVardas;

    public TextField newMokinioPavarde;

    public TextField newMokinioGimimoData;

    public Button closeWindow;

    public ComboBox parentsList;

    public ComboBox classList;

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void createStudent(ActionEvent actionEvent) throws SQLException, IOException {

        if(parentsList.getValue() == null || classList.getValue() == null ||  newMokinioVardas.getText().isEmpty() || newMokinioPavarde.getText().isEmpty() || newMokinioGimimoData.getText().isEmpty()){ //Gali buti ne tiek
            Manage.alertMessage("Neužpildyti visi privalomi laukai!");return;
        }

        if(newMokinioVardas.getCharacters().length() > 30) {Manage.alertMessage("Vardo maksimalus ilgis - 30 simboliai!");return;}
        if(newMokinioPavarde.getCharacters().length() > 30) {Manage.alertMessage("Pavardės maksimalus ilgis - 30 simboliai!");return;}


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



        // Tikrinama ar toksai mokinys jau egzistuoja ir sukūriamas naujas mokinys

        String sql = "select mokinio_vardas from mokinys where tevu_id = ? and klases_id = ? and mokinio_vardas = ? and mokinio_pavarde = ? and gimimo_data = ?";

        String sql2 = "insert into mokinys (tevu_id, klases_id, mokinio_vardas, mokinio_pavarde, gimimo_data) values (?,?,?,?,?)";

        connection = DBManagement.ConToDB();

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, String.valueOf(tevuID));
        preparedStatement.setString(2, String.valueOf(classID));
        preparedStatement.setString(3, newMokinioVardas.getText());
        preparedStatement.setString(4, newMokinioPavarde.getText());
        preparedStatement.setString(5, newMokinioGimimoData.getText());


        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            Manage.alertMessage("Toks mokinys jau yra sistemoje!");
        } else {
            PreparedStatement data = connection.prepareStatement(sql2);
            data.setString(1, String.valueOf(tevuID));
            data.setString(2, String.valueOf(classID));
            data.setString(3, newMokinioVardas.getText());
            data.setString(4, newMokinioPavarde.getText());
            data.setString(5, newMokinioGimimoData.getText());

            data.executeUpdate();
            Manage.infoMessage("Mokinys: " + newMokinioVardas.getText() + " pridėtas!");
        }
       DBManagement.disconFromDB(connection, preparedStatement);
        exit(actionEvent);

        // Tikrinama ar toksai mokinys jau egzistuoja ir sukūriamas naujas mokinys
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/StudentManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) parentsList.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();


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



        // Padaroma taip, kad pirmasis įrašas ComboBox'e būtų pasirinkti tėvai.
        if(ParentManagement.currentTevaiStudentSelection != null) {
            try {
                PreparedStatement statement2 = connection.prepareStatement("SELECT motinos_vardas, motinos_pavarde, tevo_vardas, tevo_pavarde FROM tevai WHERE tevu_id = ?");

                statement2.setInt(1, ParentManagement.currentTevaiStudentSelection.getTevuID());

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
        }
        // Padaroma taip, kad pirmasis įrašas ComboBox'e būtų pasirinkti tėvai.



        // Įdedamos klasės į ComboBox'ą.
        try {
            PreparedStatement statement2 = connection.prepareStatement("SELECT klase FROM klase");

            ResultSet resultSet2 = statement2.executeQuery();


            while (resultSet2.next()) {
                String className = resultSet2.getString("klase");

                classList.getItems().add(className);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Įdedamos klasės į ComboBox'ą.

    }

    public void parentSelectionPrompt(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ParentManagement/ParentManagement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) newMokinioVardas.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();



    }
}

