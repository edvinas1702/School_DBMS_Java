module restaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    exports StudentManagement;
    opens StudentManagement to javafx.fxml;

    exports Management;
    opens Management to javafx.fxml;

    exports TeacherManagement;
    opens TeacherManagement to javafx.fxml;

    exports ParentManagement;
    opens ParentManagement to javafx.fxml;

    exports ClassManagement;
    opens ClassManagement to javafx.fxml;
}