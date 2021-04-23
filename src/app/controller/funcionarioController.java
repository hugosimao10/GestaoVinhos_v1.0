package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class funcionarioController {
    public Button btnAddFuncionario;
    public Button btnEditFuncionario;
    public Button btnDeleteFuncionario;
    public TableView tableFuncionarios;
    public Pane funcionariosPane;


    public void iniciar() {
        System.out.println("Está na area de listar funcionários!");



    }

    // BOTAO DE ADICIONAR FUNCIONARIO

    @FXML
    public void btnAddFuncClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/addFuncionarioPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Funcionario");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addFuncionarioController add = loader.getController();
        add.iniciar();


    }

    // BOTAO PARA EDITAR FUNCIONARIO

    @FXML
    public void btnEditFuncClic(ActionEvent actionEvent)throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/selectFuncionarioToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Funcionario");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        selectFuncionarioToEditController edit = loader.getController();
        edit.iniciar();

    }

    // BOTAO PARA REMOVER FUNCIONARIO

    @FXML
    public void btnRemoveFuncClic(ActionEvent actionEvent) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/removeFuncionarioPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Remover Funcionario");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        removeFuncionarioController rem = loader.getController();
        rem.iniciar();



    }
}
