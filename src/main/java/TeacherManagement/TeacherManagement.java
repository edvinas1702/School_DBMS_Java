package TeacherManagement;

import Management.DBManagement;
import Management.Manage;
import StudentManagement.Mokinys;
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
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class TeacherManagement implements Initializable {

    public TextField searchTeacherLastNameField;

    public TableView teacherList;

    public TableColumn mokytojoVardasColumn;

    public TableColumn mokytojoPavardeColumn;

    public TableColumn mokytojoTelNrColumn;

    public TableColumn mokytojoElPastasColumn;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public static Mokytojas currentMokytojas;


    ObservableList<Mokytojas> mokytojas = FXCollections.observableArrayList();
    ObservableList<Mokytojas> mokytojasInfo = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();

        try {
            preparedStatement = connection.prepareStatement("select * from mokytojas");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                mokytojas.addAll(new Mokytojas(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));

            }
        }catch (SQLException e){e.printStackTrace();}


        mokytojoVardasColumn.setCellValueFactory(new PropertyValueFactory<Mokytojas, String>("mokytojoVardas"));
        mokytojoPavardeColumn.setCellValueFactory(new PropertyValueFactory<Mokytojas, String>("mokytojoPavarde"));
        mokytojoTelNrColumn.setCellValueFactory(new PropertyValueFactory<Mokytojas, String>("mokytojoTelNr"));
        mokytojoElPastasColumn.setCellValueFactory(new PropertyValueFactory<Mokytojas, String>("mokytojoElPastas"));


        teacherList.setItems(mokytojas);


    }

    public void searchTeacherByLastName(ActionEvent actionEvent) {
        if(searchTeacherLastNameField.getText().isEmpty()) {
            refreshData();
            return;
        }
        else{
            mokytojasInfo.clear();
            for(Mokytojas mokytojas : this.mokytojas){
                if(mokytojas.getMokytojoPavarde().toLowerCase(Locale.ROOT).contains(searchTeacherLastNameField.getText().toLowerCase(Locale.ROOT))){
                    mokytojasInfo.addAll(mokytojas);
                }
            }
            teacherList.setItems(mokytojasInfo);
        }
    }


    private void refreshData() {
        mokytojas.clear();
        connection = DBManagement.ConToDB();

        try {
            preparedStatement = connection.prepareStatement("select * from mokytojas");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                mokytojas.addAll(new Mokytojas(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));

            }
        }catch (SQLException e){e.printStackTrace();}


        mokytojoVardasColumn.setCellValueFactory(new PropertyValueFactory<Mokytojas, String>("mokytojoVardas"));
        mokytojoPavardeColumn.setCellValueFactory(new PropertyValueFactory<Mokytojas, String>("mokytojoPavarde"));
        mokytojoTelNrColumn.setCellValueFactory(new PropertyValueFactory<Mokytojas, String>("mokytojoTelNr"));
        mokytojoElPastasColumn.setCellValueFactory(new PropertyValueFactory<Mokytojas, String>("mokytojoElPastas"));


        teacherList.setItems(mokytojas);

    }


    public void clearSearch(ActionEvent actionEvent) {
        searchTeacherLastNameField.clear();
        refreshData();
    }

    public void deleteTeacher(ActionEvent actionEvent) throws IOException {
        currentMokytojas = (Mokytojas) teacherList.getSelectionModel().getSelectedItem();
        if (teacherList.getSelectionModel().isEmpty()) {
            Manage.alertMessage("Jūs nepasirinkote jokio mokytojo.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherManagement/DeleteTeacher.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchTeacherLastNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void quitWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/mainWindow.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) teacherList.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addTeacher(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherManagement/NewTeacher.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) searchTeacherLastNameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void editTeacher(ActionEvent actionEvent) throws IOException {

        if(teacherList.getSelectionModel().isEmpty()){
            Manage.alertMessage("Jūs nepasirinkote jokio mokytojo!");
        }else {
            currentMokytojas = (Mokytojas) teacherList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherManagement/EditTeacher.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchTeacherLastNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }


}
