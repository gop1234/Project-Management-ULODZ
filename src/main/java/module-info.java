module project_management.management {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens project_management.management to javafx.fxml;
<<<<<<< HEAD
    exports project_management.management;
}
=======
    opens Data to javafx.base;

    exports project_management.management;
}
>>>>>>> 887d6f1 (Payment's system)
