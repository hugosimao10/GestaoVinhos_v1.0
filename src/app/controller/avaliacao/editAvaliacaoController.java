package app.controller.avaliacao;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class editAvaliacaoController {
    public Pane editAvaliacaoPane;
    public TextField numControlo;
    public TextField qtdProduzida;
    public Button btnConfirmEditAvaliacao;
    public Button btnCancelEditAvaliacao;
    public TextField qualidadeVinho;
    public Text guardaIdEditAvaliacao;

    public void iniciar() {
        System.out.println("Está na area de editar avaliação!");


    }

    public void btnEditAvaliacaoAddClic(ActionEvent actionEvent) {
    }

    public void btnEditAvaliacaoCancelarClic(ActionEvent actionEvent) {

        numControlo.setText("");
        qtdProduzida.setText("");
        qualidadeVinho.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
