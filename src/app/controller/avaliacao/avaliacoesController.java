package app.controller.avaliacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class avaliacoesController implements Initializable {
    public Pane avaliacoesPane;
    public TableView table;
    public TableColumn colQtdProduzida;
    public TableColumn colQldVinho;
    public Button btnAddAvaliacao;
    public Button btnEditAvaliacao;
    public TableColumn colNumAv;
    public TableColumn colControloAva;

    public void iniciar() {
        System.out.println("Está na area de listar avaliações!");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnAddAvaliacaoClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/avaliacao/addAvaliacaoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Avaliações");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addAvaliacaoController add = loader.getController();
        add.iniciar();
    }

    public void btnEditAvaliacaoClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/avaliacao/selectAvaliacaoToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Avaliações");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        selectAvaliacaoToEditController add = loader.getController();
        add.iniciar();
    }


}
