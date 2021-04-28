package app.controller.embalamento;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class addEmbalamentoController {
    public Pane addEmbalamentoPane;
    public TextField idAvaliacao;
    public TextField qtdCaixas;
    public Button btnConfirmAddEmbalamento;
    public Button btnCancelAddEmbalamento;
    public TextField tipoVinho;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de adicionar embalamentos!");


    }

    public void btnAddEmbalamentoAddClic(ActionEvent actionEvent) {
    }

    public void btnAddEmbalamentoCancelarClic(ActionEvent actionEvent) {
        idAvaliacao.setText("");
        qtdCaixas.setText("");
        tipoVinho.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
