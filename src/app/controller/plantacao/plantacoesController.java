package app.controller.plantacao;

import app.entities.Casta;
import app.entities.Plantacao;
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

public class plantacoesController implements Initializable {
    public Pane plantacoesPane;
    public TableView tablePlantacoes;
    public Button btnAddPlantacao;
    public Button btnEditPlantacao;
    public Button btnConcluirPlantacao;
    public TableView tableCastas;
    public Button btnAddCasta;
    @FXML
    public ObservableList<Casta> oblist2 = FXCollections.observableArrayList();
    @FXML
    public ObservableList<Plantacao> oblist3 = FXCollections.observableArrayList();
    @FXML
    public TableColumn colNum;
    @FXML
    public TableColumn colAreaCastaPlant;
    @FXML
    public TableColumn ColFunc;
    @FXML
    public TableColumn colNumQuinta;
    @FXML
    public TableColumn colNumCastaPlant;
    @FXML
    public TableColumn colEstado;

    //CASTA
    @FXML
    public TableColumn colNumCasta;
    @FXML
    public TableColumn colNomeCasta;

    public void iniciar() {
        System.out.println("Está na area de listar plantações!");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // CASTA -  TABELA

        Connection conn = Util.criarConexao();

        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM CASTA");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                oblist2.add(new Casta(rs.getInt("id_casta"), rs.getString("tipo_casta")));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        colNumCasta.setCellValueFactory(new PropertyValueFactory<>("id_casta"));
        colNomeCasta.setCellValueFactory(new PropertyValueFactory<>("tipo_casta"));

        tableCastas.setItems(oblist2);


        // PLANTACAO - TABELA

        int log = userID.getId();

        PreparedStatement pst1 = null;
        try {
            pst1 = conn.prepareStatement("SELECT p.id_plantacao, p.area_casta, p.id_funcionario, p.id_quinta," +
                    " p.id_casta, p.estado, f.id_funcionario, f.nome, f.id_empresa, c.id_casta, c.tipo_casta FROM PLANTACAO p, FUNCIONARIO f, CASTA c WHERE f.ID_FUNCIONARIO = p.ID_FUNCIONARIO AND f.ID_EMPRESA = ? AND p.ID_CASTA = c.ID_CASTA");
            pst1.setInt(1, log);
            ResultSet rs2 = pst1.executeQuery();

            while (rs2.next()) {

                oblist3.add(new Plantacao(rs2.getInt("id_plantacao"), rs2.getString("area_casta"),
                        rs2.getString("nome"), rs2.getInt("id_quinta"),
                        rs2.getString("tipo_casta"), rs2.getInt("estado")));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        colNum.setCellValueFactory(new PropertyValueFactory<>("id_plantacao"));
        colAreaCastaPlant.setCellValueFactory(new PropertyValueFactory<>("area_casta"));
        ColFunc.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNumQuinta.setCellValueFactory(new PropertyValueFactory<>("id_quinta"));
        colNumCastaPlant.setCellValueFactory(new PropertyValueFactory<>("tipo_casta"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tablePlantacoes.setItems(oblist3);

    }

    public void btnAddPlantClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/addPlantacaoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Plantacao");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image("/img/logo.png"));
        addPlantacaoController add = loader.getController();
        add.iniciar();
    }

    public void btnConcluirPlantClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/concluirPlantacaoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Concluir Plantação");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/img/logo.png"));
        stage.show();
        concluirPlantacaoController c = loader.getController();
        c.iniciar();
    }

    public void btnEditPlantClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/selectPlantacaoToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Plantação");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/img/logo.png"));
        stage.show();
        selectPlantToEditController c = loader.getController();
        c.iniciar();
    }

    public void btnAddCastaClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/addCastaPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Casta");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/img/logo.png"));
        stage.setResizable(false);
        stage.show();
        addCastaController add = loader.getController();
        add.iniciar();
    }


}
