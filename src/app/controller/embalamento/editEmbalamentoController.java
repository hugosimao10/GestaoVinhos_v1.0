package app.controller.embalamento;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class editEmbalamentoController {
    public Pane editEmbalamentoPane;
    public TextField idAvaliacao;
    public TextField qtdCaixas;
    public Button btnConfirmEditEmbalamento;
    public Button btnCancelEditEmbalamento;
    public TextField tipoVinho;
    public Text guardaIdEditEmbalamento;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de editar embalamentos!");


    }

    public void btnEditEmbalamentoCancelarClic(ActionEvent actionEvent) {

        idAvaliacao.setText("");
        qtdCaixas.setText("");
        tipoVinho.setText("");
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void btnEditEmbalamentoAddClic(ActionEvent actionEvent) {
    }
}
