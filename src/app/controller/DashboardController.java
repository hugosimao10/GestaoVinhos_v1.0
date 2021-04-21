package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DashboardController {
    public Button btnQuintas;
    public Button btnFuncionarios;
    public Button btnPlantacoes;
    public Button btnVindimas;
    public Button btnControlos;
    public Button btnAvaliacoes;
    public Button btnEmbalamentos;
    public Button btnPaginaInicial;
    public BorderPane paneToChange;

    public void iniciar() throws IOException {

        btnPaginaInicial.fire();
    }

    @FXML
    private void openHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/paginaInicialPane.fxml"));
        Parent root = loader.load();
        paginaInicialController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();


    }


}
