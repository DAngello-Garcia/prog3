package co.edu.uniquindio.proyectofinal.utils;

import javafx.scene.control.TextFormatter.Change;

/**
 *
 * @author Johan Noe Londoño
 */
public class TextFormatterUtil {
    
    public static Change upperCaseFormat(Change change){
        change.setText(change.getText().toUpperCase());
        return change;
    }
    public static Change integerFormat(Change change){
        return (change.getText().matches("[0-9]*")) ? change:null;
    }
    
}
