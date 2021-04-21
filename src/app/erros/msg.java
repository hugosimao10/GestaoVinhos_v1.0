package app.erros;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class msg {

    public static void alerta(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        alert.showAndWait();
    }
}
