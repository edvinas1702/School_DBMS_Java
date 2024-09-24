package ClassManagement;

import Management.DBManagement;
import Management.Manage;
import StudentManagement.StudentManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class EditClass implements Initializable {

    public ComboBox teacherList;
    public TextField classField;

    private Connection connection;

    private PreparedStatement preparedStatement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBManagement.ConToDB();


        classField.setText(ClassManagement.currentKlase.getKlase());


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


        // Padaroma taip, kad pirmasis įrašas ComboBox'e būtų klasės auklėtoja.
        try {
            PreparedStatement statement2 = connection.prepareStatement("SELECT mokytojo_vardas, mokytojo_pavarde FROM mokytojas WHERE mokytojo_id = ?");

            statement2.setInt(1, ClassManagement.currentKlase.getMokytojoID());

            ResultSet resultSet = statement2.executeQuery();


            while (resultSet.next()) {
                String teacherName = resultSet.getString("mokytojo_vardas");
                String teacherlastName = resultSet.getString("mokytojo_pavarde");


                String entry = teacherName + " " + teacherlastName;

                teacherList.setValue(entry);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Padaroma taip, kad pirmasis įrašas ComboBox'e būtų klasės auklėtoja.


    }

    public void updateInfo(ActionEvent actionEvent) throws IOException {

        // GAUNAMAS MOKYTOJO ID
        String selectedTeacherInfo = String.valueOf(teacherList.getValue());

        String array[] = selectedTeacherInfo.split(" ", 2);

        String teacherName = array[0];

        String teacherLastName = array[1];

        Integer mokytojoID = null;

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT mokytojo_id FROM mokytojas WHERE mokytojo_vardas = ? AND mokytojo_pavarde = ?");
            statement.setString(1, teacherName);
            statement.setString(2, teacherLastName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                mokytojoID = resultSet.getInt("mokytojo_id");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // GAUNAMAS MOKYTOJO ID



        // Atnaujinama klasė
        try{
            String sql = "UPDATE klase SET mokytojo_id = ?, klase = ?";

            PreparedStatement statement2 = connection.prepareStatement(sql);

            statement2.setInt(1, mokytojoID);
            statement2.setString(2, classField.getText());

            statement2.executeUpdate();

            Manage.infoMessage("Klasė atnaujinta!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Atnaujinama klasė

        DBManagement.disconFromDB(connection, preparedStatement);
        exitEdit(actionEvent);

    }

    public void exitEdit(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassManagement/ClassManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) classField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
