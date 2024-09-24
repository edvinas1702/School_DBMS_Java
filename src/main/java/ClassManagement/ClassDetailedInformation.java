package ClassManagement;

import Management.DBManagement;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassDetailedInformation implements Initializable {

    public Label currentTeacherName;

    public Label currentTeacherLastName;


    private Connection connection;
    private PreparedStatement preparedStatement;
    private String sql;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();

        //Mokytojo vardas
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT mokytojo_vardas FROM mokytojas WHERE mokytojo_id = ?");
            statement.setInt(1, ClassManagement.currentKlase.getMokytojoID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String mokytojo_vardas = resultSet.getString("mokytojo_vardas");
                currentTeacherName.setText(mokytojo_vardas);
            }



        } catch (SQLException e) {
            System.out.println("Nepavyko");
            throw new RuntimeException(e);
        }
        //Mokytojo vardas


        //Mokytojo pavardė
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT mokytojo_pavarde FROM mokytojas WHERE mokytojo_id = ?");
            statement.setInt(1, ClassManagement.currentKlase.getMokytojoID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String mokytojo_pavarde = resultSet.getString("mokytojo_pavarde");
                currentTeacherLastName.setText(mokytojo_pavarde);
            }



        } catch (SQLException e) {
            System.out.println("Nepavyko");
            throw new RuntimeException(e);
        }
        //Mokytojo pavardė


    }


    public void quitWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassManagement/ClassManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) currentTeacherName.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
