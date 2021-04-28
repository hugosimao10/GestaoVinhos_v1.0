package app.controller.embalamento;

import app.entities.Embalamento;
import app.entities.Quinta;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class embalamentoController implements Initializable {
    public Pane embalamentosPane;
    @FXML
    public TableView table;
    @FXML
    public TableColumn colNum;
    @FXML
    public TableColumn colQtdCaixas;
    @FXML
    public TableColumn colTipoVinho;
    @FXML
    public TableColumn colData;
    public Button btnAddEmbalamento;
    public Button btnEditEmbalamento;
    @FXML
    public TableColumn numAvaEmbalamento;

    @FXML
    public ObservableList<Embalamento> oblist1 = FXCollections.observableArrayList();

    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar embalamentos!");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int nIdEmpresa = userID.getId();

        Connection conn = Util.criarConexao();

        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT e.*, a.*, c.*, f.* " +
                    "FROM PRODUTOFINAL e, AVALIACAO a, CONTROLO c, FUNCIONARIO F WHERE e.ID_PRODUTO_FINAL = a.ID_PRODUTO_FINAL AND " +
                    "a.ID_AVALIACAO = c.ID_AVALIACAO AND c.ID_FUNCIONARIO = f.ID_FUNCIONARIO AND f.ID_EMPRESA = ?");

            pst.setInt(1, nIdEmpresa);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){

                oblist1.add(new Embalamento(rs.getInt("ID_PRODUTO_FINAL"), rs.getInt("QTD_CAIXAS"),
                        rs.getString("TIPO_VINHO"), rs.getDate("DATA_EMB")));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        colNum.setCellValueFactory(new PropertyValueFactory<>("id_produto_final"));
        colQtdCaixas.setCellValueFactory(new PropertyValueFactory<>("qtd_caixas"));
        colTipoVinho.setCellValueFactory(new PropertyValueFactory<>("tipo_vinho"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_emb"));

        table.setItems(oblist1);


    }

    public void btnAddEmbalamentoClic(ActionEvent actionEvent) throws SQLException, IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/embalamento/addEmbalamentoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Embalamentos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addEmbalamentoController add = loader.getController();
        add.iniciar();
    }

    public void btnEditEmbalamentoClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/embalamento/selectEmbalamentoToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Embalamentos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        selectEmbalamentoToEditController add = loader.getController();
        add.iniciar();
    }


}
