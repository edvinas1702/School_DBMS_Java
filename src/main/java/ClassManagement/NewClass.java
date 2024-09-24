package ClassManagement;

import Management.DBManagement;
import Management.Manage;
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

public class NewClass implements Initializable {

    public TextField newKlasesPavadinimas;

    public Button closeWindow;

    public ComboBox teacherList;

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void createClass(ActionEvent actionEvent) throws SQLException, IOException {

        if(teacherList.getValue() == null ||  newKlasesPavadinimas.getText().isEmpty()){
            Manage.alertMessage("Neužpildyti visi privalomi laukai!");return;
        }

        if(newKlasesPavadinimas.getCharacters().length() > 5) {Manage.alertMessage("Klasės pavadinimo maksimalus ilgis - 5 simboliai!");return;}


        // MOKYTOJO TĖVŲ ID
        String selectedTeacherInfo = String.valueOf(teacherList.getValue());

        String array[] = selectedTeacherInfo.split(" ", 2);

        String firstName = array[0];

        String lastName = array[1];


        Integer mokytojoID = null;

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT mokytojo_id FROM mokytojas WHERE mokytojo_vardas = ? AND mokytojo_pavarde = ?");
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                mokytojoID = resultSet.getInt("mokytojo_id");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // GAUNAMAS MOKYTOJO ID



        // Tikrinama tokia klasė jau egzistuoja ir sukūriama nauja klasė

        String sql = "SELECT klase FROM klase WHERE mokytojo_id = ? AND klase = ?";

        String sql2 = "INSERT INTO klase (mokytojo_id, klase) values (?,?)";

        connection = DBManagement.ConToDB();

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, Integer.valueOf(mokytojoID));
        preparedStatement.setString(2, String.valueOf(newKlasesPavadinimas.getText()));


        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            Manage.alertMessage("Tokia klasė jau yra sistemoje!");
        } else {
            PreparedStatement data = connection.prepareStatement(sql2);

            data.setString(1, String.valueOf(mokytojoID));
            data.setString(2, String.valueOf(newKlasesPavadinimas.getText()));

            data.executeUpdate();
            Manage.infoMessage("Klasė: " + newKlasesPavadinimas.getText() + " pridėta!");
        }
        DBManagement.disconFromDB(connection, preparedStatement);
        exit(actionEvent);

        // Tikrinama tokia klasė jau egzistuoja ir sukūriama nauja klasė

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();

        // Įdedami mokytojų vardai ir pavardės į ComboBox'ą.
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT mokytojo_vardas, mokytojo_pavarde FROM mokytojas");

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String teacherName = resultSet.getString("mokytojo_vardas");
                String teacherlastName = resultSet.getString("mokytojo_pavarde");


                String entry = teacherName + " " + teacherlastName;

                teacherList.getItems().add(entry);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Įdedami mokytojų vardai ir pavardės į ComboBox'ą.

    }

    public void exit(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassManagement/ClassManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) newKlasesPavadinimas.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
