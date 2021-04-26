package app.controller.avaliacao;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class avaliacoesController {
    public Pane avaliacoesPane;
    public TableView table;
    public TableColumn colNum;
    public TableColumn colQtdProduzida;
    public TableColumn colQldVinho;
    public Button btnAddAvaliacao;
    public Button btnEditAvaliacao;

    public void iniciar() {
        System.out.println("Está na area de listar avaliações!");


    }

    public void btnAddAvaliacaoClic(ActionEvent actionEvent) {
    }

    public void btnEditAvaliacaoClic(ActionEvent actionEvent) {
    }
}
