package app.controller.avaliacao;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class addAvaliacaoController {
    public Pane addAvaliacaoPane;
    public TextField numControlo;
    public TextField qtdProduzida;
    public Button btnConfirmAddAvaliacao;
    public Button btnCancelAddAvaliacao;
    public TextField qualidadeVinho;

    public void iniciar() {
        System.out.println("Está na area de adicionar avaliação!");


    }

    public void btnAddAvaliacaoAddClic(ActionEvent actionEvent) {
    }

    public void btnAddAvaliacaoCancelarClic(ActionEvent actionEvent) {

        numControlo.setText("");
        qtdProduzida.setText("");
        qualidadeVinho.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
