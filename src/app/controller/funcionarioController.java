package app.controller;

import app.error.msg;
import app.guardaDados.userID;
import app.util.Util;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class funcionarioController {
    public Button btnAddFuncionario;
    public Button btnEditFuncionario;
    public Button btnDeleteFuncionario;
    public TableView tableFuncionarios;
    public Pane funcionariosPane;


    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar funcionários!");

        int funcEmpresa = userID.getId();

        Connection conn = Util.criarConexao();

        PreparedStatement pst = conn.prepareStatement("SELECT * FROM FUNCIONARIO WHERE ID_EMPRESA = ?");
        pst.setInt(1, funcEmpresa);

        ResultSet rs = pst.executeQuery();

        if(rs.next()){

            int num = rs.getInt("ID_FUNCIONARIO");
            String nome = rs.getString("NOME");
            String tipo = rs.getString("TIPO_FUNCIONARIO");
            String email = rs.getString("EMAIL");
            String tlm = rs.getString("TLM");
            int estado = rs.getInt("ESTADO");



        }
        else{
            System.out.println("A empresa não tem nenhum funcionário!");
        }

    }

    // BOTAO DE ADICIONAR FUNCIONARIO

    @FXML
    public void btnAddFuncClic(ActionEvent actionEvent) throws IOException, SQLException {

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
