package app.controller.funcionario;


import app.entities.Funcionario;
import app.entities.userID;
import app.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class funcionarioController implements Initializable {
    public Button btnAddFuncionario;
    public Button btnEditFuncionario;
    public Button btnDeleteFuncionario;
    public Pane funcionariosPane;
    @FXML
    public TableColumn<Funcionario, Integer> colNum;
    @FXML
    public TableColumn<Funcionario, String> colNome;
    @FXML
    public TableColumn<Funcionario, String> colCargo;
    @FXML
    public TableColumn<Funcionario, String> colMail;
    @FXML
    public TableColumn<Funcionario, String> colTLM;
    @FXML
    public TableColumn<Funcionario, Integer> colEstado;
    @FXML
    public TableView<Funcionario> table;

    @FXML
    public ObservableList<Funcionario> oblist = FXCollections.observableArrayList();


    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar funcionários!");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int funcEmpresa = userID.getId();

        Connection conn = Util.criarConexao();

        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT ID_FUNCIONARIO, NOME, TIPO_FUNCIONARIO, EMAIL, USERNAME, ESTADO, ID_EMPRESA " +
                    "FROM FUNCIONARIO WHERE ID_EMPRESA LIKE ?");

            pst.setInt(1, funcEmpresa);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                int nCargo = rs.getInt("TIPO_FUNCIONARIO");

                PreparedStatement p4 = conn.prepareStatement("SELECT * FROM TIPO_FUNCIONARIO WHERE ID = ?");
                p4.setInt(1, nCargo);

                ResultSet set1 = p4.executeQuery();

                if (set1.next()) {

                    oblist.add(new Funcionario(rs.getInt("id_funcionario"), rs.getString("nome"),
                            set1.getString("CARGO"), rs.getString("email"),
                            rs.getString("USERNAME"), rs.getInt("estado")));

                } else {
                    System.out.println("Erro ao atribuir cargo relacionado!");
                }


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        colNum.setCellValueFactory(new PropertyValueFactory<>("id_funcionario"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("tipo_funcionario"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTLM.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        table.setItems(oblist);

    }


    // BOTAO DE ADICIONAR FUNCIONARIO

    @FXML
    public void btnAddFuncClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/funcionario/addFuncionarioPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Funcionario");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image("/img/logo.png"));
        addFuncionarioController add = loader.getController();
        add.iniciar();


    }

    // BOTAO PARA EDITAR FUNCIONARIO

    @FXML
    public void btnEditFuncClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/funcionario/selectFuncionarioToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Funcionario");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image("/img/logo.png"));
        selectFuncionarioToEditController edit = loader.getController();
        edit.iniciar();

    }

    // BOTAO PARA REMOVER FUNCIONARIO

    @FXML
    public void btnRemoveFuncClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/funcionario/removeFuncionarioPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Remover Funcionario");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image("/img/logo.png"));
        removeFuncionarioController rem = loader.getController();
        rem.iniciar();


    }


}
