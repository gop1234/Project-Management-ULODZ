module project_management.management {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens project_management.management to javafx.fxml;

    exports project_management.management;
}

