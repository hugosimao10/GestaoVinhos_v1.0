package app.controller.avaliacao;

import app.entities.Avaliacao;
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

public class avaliacoesController implements Initializable {
    public Pane avaliacoesPane;
    @FXML
    public TableView table;
    @FXML
    public TableColumn colQtdProduzida;
    @FXML
    public TableColumn colQldVinho;
    public Button btnAddAvaliacao;
    public Button btnEditAvaliacao;
    @FXML
    public TableColumn colNumAv;
    @FXML
    public TableColumn colEmbalamento;

    @FXML
    public ObservableList<Avaliacao> oblist1 = FXCollections.observableArrayList();

    public void iniciar() {
        System.out.println("Está na area de listar avaliações!");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int nIdEmpresa = userID.getId();

        Connection conn = Util.criarConexao();

        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT a.*, c.*, f.*, e.* " +
                    "FROM AVALIACAO a, CONTROLO c, FUNCIONARIO f, PRODUTOFINAL e WHERE a.ID_AVALIACAO = c.ID_AVALIACAO AND " +
                    "c.ID_FUNCIONARIO = f.ID_FUNCIONARIO AND a.ID_PRODUTO_FINAL = e.ID_PRODUTO_FINAL AND f.ID_EMPRESA = ?");

            pst.setInt(1, nIdEmpresa);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                oblist1.add(new Avaliacao(rs.getInt("id_avaliacao"), rs.getInt("qtd_produzida"),
                        rs.getString("qualidade_vinho"), rs.getInt("id_produto_final")));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        colNumAv.setCellValueFactory(new PropertyValueFactory<>("id_avaliacao"));
        colQtdProduzida.setCellValueFactory(new PropertyValueFactory<>("qtd_produzida"));
        colQldVinho.setCellValueFactory(new PropertyValueFactory<>("qualidade_vinho"));
        colEmbalamento.setCellValueFactory(new PropertyValueFactory<>("id_produto_final"));

        table.setItems(oblist1);


    }

    public void btnAddAvaliacaoClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/avaliacao/addAvaliacaoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Avaliações");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/img/logo.png"));
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
        stage.getIcons().add(new Image("/img/logo.png"));
        stage.show();
        selectAvaliacaoToEditController add = loader.getController();
        add.iniciar();
    }


}
