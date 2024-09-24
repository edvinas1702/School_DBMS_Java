package StudentManagement;

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
import java.sql.*;
import java.util.ResourceBundle;

public class StudentDetailedInformation implements Initializable {

    public Label currentStudentName; // tevuID
    public Label currentStudentLastName; // klasesID

    private Connection connection;
    private PreparedStatement preparedStatement;
    private String sql;

    public Label motinosVardas;
    public Label motinosPavarde;
    public Label tevoVardas;
    public Label tevoPavarde;
    public Label mokinioKlase;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentStudentName.setText(StudentManagement.currentMokinys.getMokinioVardas());
        currentStudentLastName.setText(StudentManagement.currentMokinys.getMokinioPavarde());

        int tevuID = StudentManagement.currentMokinys.getTevuID();
        int klasesID = StudentManagement.currentMokinys.getKlasesID();

        connection = DBManagement.ConToDB();

        //Motinos vardas
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT motinos_vardas FROM tevai WHERE tevu_id = ?");
            statement.setInt(1, tevuID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String motinos_vardas = resultSet.getString("motinos_vardas");
                motinosVardas.setText(motinos_vardas);
            }



        } catch (SQLException e) {
            System.out.println("Nepavyko");
            throw new RuntimeException(e);
        }


        //Motinos pavarde
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT motinos_pavarde FROM tevai WHERE tevu_id = ?");
            statement.setInt(1, tevuID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String motinos_pavarde = resultSet.getString("motinos_pavarde");
                motinosPavarde.setText(motinos_pavarde);
            }



        } catch (SQLException e) {
            System.out.println("Nepavyko");
            throw new RuntimeException(e);
        }


        //Tevo vardas
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT tevo_vardas FROM tevai WHERE tevu_id = ?");
            statement.setInt(1, tevuID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tevo_vardas = resultSet.getString("tevo_vardas");
                tevoVardas.setText(tevo_vardas);
            }



        } catch (SQLException e) {
            System.out.println("Nepavyko");
            throw new RuntimeException(e);
        }


        //Tevo pavarde
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT tevo_pavarde FROM tevai WHERE tevu_id = ?");
            statement.setInt(1, tevuID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tevo_pavarde = resultSet.getString("tevo_pavarde");
                tevoPavarde.setText(tevo_pavarde);
            }



        } catch (SQLException e) {
            System.out.println("Nepavyko");
            throw new RuntimeException(e);
        }


        // Klasė
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT klase FROM klase WHERE klases_id = ?");
            statement.setInt(1, klasesID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String klases_pavadinimas = resultSet.getString("klase");
                mokinioKlase.setText(klases_pavadinimas);
            }



        } catch (SQLException e) {
            System.out.println("Nepavyko");
            throw new RuntimeException(e);
        }



    }

    public void quitWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/StudentManagement.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) motinosVardas.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
