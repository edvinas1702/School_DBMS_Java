package ParentManagement;

import Management.DBManagement;
import Management.Manage;
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

public class ParentManagement implements Initializable  {


    public TextField searchMothersLastNameField;

    public TableView parentList;
    public TableColumn motinosVardasColumn;
    public TableColumn motinosPavardeColumn;
    public TableColumn motinosTelNrColumn;
    public TableColumn tevuAdresas;
    public TableColumn tevoVardasColumn;
    public TableColumn tevoPavardeColumn;
    public TableColumn tevoTelNrColumn;


    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public static Tevai currentTevai;

    public static Tevai currentTevaiStudentSelection;



    ObservableList<Tevai> tevai = FXCollections.observableArrayList();
    ObservableList<Tevai> tevuInfo = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();

        try {
            preparedStatement = connection.prepareStatement("select * from tevai");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                tevai.addAll(new Tevai(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));

            }
        }catch (SQLException e){e.printStackTrace();}


        motinosVardasColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("motinosVardas"));
        motinosPavardeColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("motinosPavarde"));
        motinosTelNrColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("motinosTelNr"));
        tevuAdresas.setCellValueFactory(new PropertyValueFactory<Tevai, String>("adresas"));
        tevoVardasColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("tevoVardas"));
        tevoPavardeColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("tevoPavarde"));
        tevoTelNrColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("tevoTelNr"));


        parentList.setItems(tevai);

    }

    public void searchParentsByLastName(ActionEvent actionEvent) {

        if(searchMothersLastNameField.getText().isEmpty()) {
            refreshData();
            return;
        }
        else{
            tevuInfo.clear();
            for(Tevai tevai : this.tevai){
                if(tevai.getMotinosPavarde().toLowerCase(Locale.ROOT).contains(searchMothersLastNameField.getText().toLowerCase(Locale.ROOT))){
                    tevuInfo.addAll(tevai);
                }
            }
            parentList.setItems(tevuInfo);
        }

    }

    public void clearSearch(ActionEvent actionEvent) {

        searchMothersLastNameField.clear();
        refreshData();

    }

    public void addParents(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ParentManagement/NewParents.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) searchMothersLastNameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void deleteParents(ActionEvent actionEvent) throws IOException {

        currentTevai = (Tevai) parentList.getSelectionModel().getSelectedItem();
        if (parentList.getSelectionModel().isEmpty()) {
            Manage.alertMessage("Jūs nepasirinkote jokių tėvų.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ParentManagement/DeleteParents.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchMothersLastNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }

    public void quitWindow(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/StudentManagement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) searchMothersLastNameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void editParents(ActionEvent actionEvent) throws IOException {

        if(parentList.getSelectionModel().isEmpty()){
            Manage.alertMessage("Jūs nepasirinkote jokių tėvų!");
        }else {
            currentTevai = (Tevai) parentList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ParentManagement/EditParents.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchMothersLastNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }


    private void refreshData() {
        tevai.clear();
        connection = DBManagement.ConToDB();

        try {
            preparedStatement = connection.prepareStatement("select * from tevai");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                tevai.addAll(new Tevai(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));

            }
        }catch (SQLException e){e.printStackTrace();}


        motinosVardasColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("motinosVardas"));
        motinosPavardeColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("motinosPavarde"));
        motinosTelNrColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("motinosTelNr"));
        tevuAdresas.setCellValueFactory(new PropertyValueFactory<Tevai, String>("adresas"));
        tevoVardasColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("tevoVardas"));
        tevoPavardeColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("tevoPavarde"));
        tevoTelNrColumn.setCellValueFactory(new PropertyValueFactory<Tevai, String>("tevoTelNr"));


        parentList.setItems(tevai);

    }

    public void parentSelection(ActionEvent actionEvent) {

        if (parentList.getSelectionModel().isEmpty()) {
            Manage.alertMessage("Jūs nepasirinkote jokių tėvų!");
        } else {
            currentTevaiStudentSelection = (Tevai) parentList.getSelectionModel().getSelectedItem();
        }
    }
}
