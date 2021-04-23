package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class plantacoesController {
    public Pane plantacoesPane;
    public TableView tablePlantacoes;
    public Button btnAddPlantacao;
    public Button btnEditPlantacao;
    public Button btnConcluirPlantacao;
    public TableView tableCastas;
    public TableColumn tabelCastas;
    public Button btnAddCasta;
    public Button btnRemoveCasta;

    public void iniciar() {
        System.out.println("Está na area de listar plantações!");


    }

    public void btnAddPlantClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/addPlantacaoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Plantacao");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addPlantacaoController add = loader.getController();
        add.iniciar();
    }

    public void btnConcluirPlantClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/concluirPlantacaoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Concluir Funcionario");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        concluirPlantacaoController c = loader.getController();
        c.iniciar();
    }

    public void btnEditPlantClic(ActionEvent actionEvent) {


    }

    public void btnAddCastaClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/addCastaPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Funcionario");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addCastaController add = loader.getController();
        add.iniciar();
    }

    public void btnRemoveCastaClic(ActionEvent actionEvent) {
    }
}
