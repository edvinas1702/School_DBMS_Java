package ClassManagement;

import Management.DBManagement;
import Management.Manage;
import StudentManagement.Mokinys;
import TeacherManagement.Mokytojas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClassManagement implements Initializable {

    public TextField searchClassField;
    public TableView classList;
    public TableColumn klasesColumn;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public static Klase currentKlase;

    ObservableList<Klase> klase = FXCollections.observableArrayList();
    ObservableList<Klase> klaseInfo = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();

        try {
            preparedStatement = connection.prepareStatement("select * from klase");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                klase.addAll(new Klase(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));

            }
        }catch (SQLException e){e.printStackTrace();}


        klasesColumn.setCellValueFactory(new PropertyValueFactory<Klase, String>("klase"));

        classList.setItems(klase);

    }


    public void searchClass(ActionEvent actionEvent) {

        if(searchClassField.getText().isEmpty()) {
            refreshData();
            return;
        }
        else{
            klaseInfo.clear();
            for(Klase klase : this.klase){
                if(klase.getKlase().toLowerCase(Locale.ROOT).contains(searchClassField.getText().toLowerCase(Locale.ROOT))){
                    klaseInfo.addAll(klase);
                }
            }
            classList.setItems(klaseInfo);
        }

    }

    public void clearSearch(ActionEvent actionEvent) {

        searchClassField.clear();
        refreshData();

    }

    public void addClass(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassManagement/NewClass.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) searchClassField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void deleteClass(ActionEvent actionEvent) throws IOException {

        currentKlase = (Klase) classList.getSelectionModel().getSelectedItem();
        if (classList.getSelectionModel().isEmpty()) {
            Manage.alertMessage("Jūs nepasirinkote jokios klasės.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassManagement/DeleteClass.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchClassField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }

    public void quitWindow(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/mainWindow.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) classList.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void editClass(ActionEvent actionEvent) throws IOException {

        if(classList.getSelectionModel().isEmpty()){
            Manage.alertMessage("Jūs nesate pasirinkę jokios klasės!");
        }else {
            currentKlase = (Klase) classList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassManagement/EditClass.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchClassField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }


    }

    private void refreshData(){

        klase.clear();
        connection = DBManagement.ConToDB();


        try {
            preparedStatement = connection.prepareStatement("select * from klase");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                klase.addAll(new Klase(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));

            }
        }catch (SQLException e){e.printStackTrace();}


        klasesColumn.setCellValueFactory(new PropertyValueFactory<Klase, String>("klase"));

        classList.setItems(klase);


    }


    public void detailedInformation(ActionEvent actionEvent) throws IOException {

        currentKlase = (Klase) classList.getSelectionModel().getSelectedItem();
        if (classList.getSelectionModel().isEmpty()) {
            Manage.alertMessage("Jūs nepasirinkote jokios klasės.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassManagement/ClassDetailedInformation.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchClassField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }
}
