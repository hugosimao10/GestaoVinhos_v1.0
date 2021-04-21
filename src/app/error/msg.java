package app.error;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class msg {

    public static void alertaErro(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        alert.showAndWait();
    }

    public static void alertaAviso(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        alert.showAndWait();
    }
}
