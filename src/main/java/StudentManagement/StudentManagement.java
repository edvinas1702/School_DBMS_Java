package StudentManagement;

import Management.DBManagement;
import Management.Manage;
import ParentManagement.ParentManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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

public class StudentManagement implements Initializable {
    public TextField searchStudentByLastName;
    public TableView studentList;


    public TableColumn mokinioVardasColumn;

    public TableColumn mokinioPavardeColumn;

    public TableColumn mokinioGimimoDataColumn;

    public TableColumn mokinioKlasesPavadinimasColumn;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public static Mokinys2 currentMokinys;

    ObservableList<Mokinys> mokinys = FXCollections.observableArrayList();

    ObservableList<Mokinys2> mokinys2 = FXCollections.observableArrayList();
    ObservableList<Mokinys2> mokinysInfo = FXCollections.observableArrayList();

    public ComboBox klasesSarasas;

    public void searchStudByLastName(ActionEvent actionEvent) { //Mygtukas "Ieskoti"

        // NETEISINGAI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(searchStudentByLastName.getText().isEmpty()) {
            refreshData();
            return;
        }
        else{
            mokinysInfo.clear();
            for(Mokinys2 mokinys2 : this.mokinys2){
                if(mokinys2.getMokinioPavarde().toLowerCase(Locale.ROOT).contains(searchStudentByLastName.getText().toLowerCase(Locale.ROOT))){
                    mokinysInfo.addAll(mokinys2);
                }
            }
            studentList.setItems(mokinysInfo);
        }
    }

    private void refreshData() {

        mokinys2.clear();
        connection = DBManagement.ConToDB();

        try {
//            preparedStatement = connection.prepareStatement("select * from mokinys");
            preparedStatement = connection.prepareStatement("SELECT mokinys.*, klase.klase FROM mokinys INNER JOIN klase ON mokinys.klases_id = klase.klases_id");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LocalDate localDate = resultSet.getDate(6).toLocalDate();
                mokinys2.addAll(new Mokinys2(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), localDate, resultSet.getString(7)));

            }
        }catch (SQLException e){e.printStackTrace();}


        mokinioVardasColumn.setCellValueFactory(new PropertyValueFactory<Mokinys2, String>("mokinioVardas"));
        mokinioPavardeColumn.setCellValueFactory(new PropertyValueFactory<Mokinys2, String>("mokinioPavarde"));
        mokinioGimimoDataColumn.setCellValueFactory(new PropertyValueFactory<Mokinys2, LocalDate>("gimimoData"));
        mokinioKlasesPavadinimasColumn.setCellValueFactory(new PropertyValueFactory<Mokinys2, String>("klasesPavadinimas"));


        studentList.setItems(mokinys2);

    }

    public void clearSearch(ActionEvent actionEvent) {
        searchStudentByLastName.clear();
        refreshData();
    }

    public void quitWindow(ActionEvent actionEvent) throws IOException { //Išeiti iš "Restaurants" lango
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/mainWindow.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) studentList.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addStudent(ActionEvent actionEvent) throws IOException { // Nueinama į "NewRestaurant" langą
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/NewStudent.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) searchStudentByLastName.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void detailedInformation(ActionEvent actionEvent) throws IOException { //
        currentMokinys = (Mokinys2) studentList.getSelectionModel().getSelectedItem();
        if (studentList.getSelectionModel().isEmpty()) {
            Manage.alertMessage("Jūs nepasirinkote jokio mokinio.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/StudentDetailedInformation.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchStudentByLastName.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }

    public void editStudent(ActionEvent actionEvent) throws IOException {

        if(studentList.getSelectionModel().isEmpty()){
            Manage.alertMessage("Jūs nesate pasirinkę jokio mokinio!");
        }else {
            currentMokinys = (Mokinys2) studentList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/EditStudent.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchStudentByLastName.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }


    public void deleteStudent(ActionEvent actionEvent) throws IOException {
        currentMokinys = (Mokinys2) studentList.getSelectionModel().getSelectedItem();
        if (studentList.getSelectionModel().isEmpty()) {
            Manage.alertMessage("Jūs nepasirinkote jokio mokinio.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentManagement/DeleteStudent.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) searchStudentByLastName.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBManagement.ConToDB();


        try {
//            preparedStatement = connection.prepareStatement("select * from mokinys");
            preparedStatement = connection.prepareStatement("SELECT mokinys.*, klase.klase FROM mokinys INNER JOIN klase ON mokinys.klases_id = klase.klases_id");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LocalDate localDate = resultSet.getDate(6).toLocalDate();
                mokinys2.addAll(new Mokinys2(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), localDate, resultSet.getString(7)));

            }
        }catch (SQLException e){e.printStackTrace();}


        mokinioVardasColumn.setCellValueFactory(new PropertyValueFactory<Mokinys2, String>("mokinioVardas"));
        mokinioPavardeColumn.setCellValueFactory(new PropertyValueFactory<Mokinys2, String>("mokinioPavarde"));
        mokinioGimimoDataColumn.setCellValueFactory(new PropertyValueFactory<Mokinys2, LocalDate>("gimimoData")); // Not sure if this will work !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        mokinioKlasesPavadinimasColumn.setCellValueFactory(new PropertyValueFactory<Mokinys2, String>("klasesPavadinimas"));


        studentList.setItems(mokinys2);



        // Klasių pridėjimas
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM klase");
            ObservableList data = FXCollections.observableArrayList();

            while(rs.next()){

                data.add(new String(rs.getString(3)));
            }

            klasesSarasas.setItems(data);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void filterByClass(ActionEvent actionEvent) {
        String studentClass = String.valueOf(klasesSarasas.getValue());

        mokinys2.clear();
        
        Integer classID = null;

        try {
            //Gaunamas klases ID
            PreparedStatement statement = connection.prepareStatement("SELECT klases_id FROM klase WHERE klase = ?");
            statement.setString(1, studentClass);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                classID = resultSet.getInt("klases_id");

            }

            // Atspausdinami mokiniai i lentelę, kurių atitinka ID
            PreparedStatement statement2 = connection.prepareStatement("SELECT mokinys.*, klase.klase FROM mokinys INNER JOIN klase ON mokinys.klases_id = klase.klases_id WHERE mokinys.klases_id = ?");
            statement2.setInt(1, classID);

            resultSet = statement2.executeQuery();
            while(resultSet.next()){
                LocalDate localDate = resultSet.getDate(6).toLocalDate();
                mokinys2.addAll(new Mokinys2(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), localDate, resultSet.getString(7)));

            }


        } catch (SQLException e) {
            System.out.println("Nepavyko");
            throw new RuntimeException(e);
        }

        mokinioVardasColumn.setCellValueFactory(new PropertyValueFactory<Mokinys, String>("mokinioVardas"));
        mokinioPavardeColumn.setCellValueFactory(new PropertyValueFactory<Mokinys, String>("mokinioPavarde"));
        mokinioGimimoDataColumn.setCellValueFactory(new PropertyValueFactory<Mokinys, LocalDate>("gimimoData"));
        mokinioKlasesPavadinimasColumn.setCellValueFactory(new PropertyValueFactory<Mokinys2, String>("klasesPavadinimas"));


        studentList.setItems(mokinys2);



    }


}
