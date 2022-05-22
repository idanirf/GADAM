package com.dam.gestionalmacendam.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Utils {

    public static boolean  isNumberInt(String num){
        String regex = "^\\d+$";
        return regex.matches(num);
    }


    public static Alert getAlertErrorDetails(String title, String header, String content, String details) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Label label = new Label("Los errores son:");

        TextArea textArea = new TextArea(details);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        return alert;
    }

    public int parseNumber(String campo){
        return Integer.parseInt(campo);
    }
    public static boolean isNumerDouble(String num) {
        String regex = "^[0-9]+([,][0-9]+)?$";
        return regex.matches(num);
    }
}
