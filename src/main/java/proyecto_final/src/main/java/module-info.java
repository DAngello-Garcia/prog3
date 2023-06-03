module co.edu.uniquindio.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens co.edu.uniquindio.proyectofinal to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal;
    exports co.edu.uniquindio.proyectofinal.controllers;
    opens co.edu.uniquindio.proyectofinal.controllers to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.exceptions;
    opens co.edu.uniquindio.proyectofinal.exceptions to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.model;
    opens co.edu.uniquindio.proyectofinal.model to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.persistencia;
    opens co.edu.uniquindio.proyectofinal.persistencia to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.utils;
    opens co.edu.uniquindio.proyectofinal.utils to javafx.fxml;
}