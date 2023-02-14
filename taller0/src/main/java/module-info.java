module co.edu.uniquindio.ingesis.p3.taller0 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens co.edu.uniquindio.ingesis.p3.taller0 to javafx.fxml;
    exports co.edu.uniquindio.ingesis.p3.taller0;
    exports co.edu.uniquindio.ingesis.p3.taller0.controller;
    opens co.edu.uniquindio.ingesis.p3.taller0.controller to javafx.fxml;
}