package app.controller.controlo;

import app.entities.Controlo;
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

public class controloController implements Initializable {
    @FXML
    public TableView table;
    public Pane controlosPane;
    @FXML
    public TableColumn colNum;
    @FXML
    public TableColumn colAcucar;
    @FXML
    public TableColumn colTemperatura;
    @FXML
    public TableColumn colData;
    @FXML
    public TableColumn colVindima;
    @FXML
    public TableColumn colFunc;
    public Button btnAddControlo;
    public Button btnEditControlo;
    @FXML
    public ObservableList<Controlo> oblist1 = FXCollections.observableArrayList();
    @FXML
    public TableColumn Colresult;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar Controlos!");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int nIdEmpresa = userID.getId();

        Connection conn = Util.criarConexao();

        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT c.*, f.*, pv.* FROM CONTROLO c, FUNCIONARIO f, PLANTACAO_VINDIMA pv " +
                    "WHERE f.ID_FUNCIONARIO = c.ID_FUNCIONARIO AND c.ID_PLANT_VINDIMA = pv.ID_PLANT_VINDIMA AND f.ID_EMPRESA = ?");

            pst.setInt(1, nIdEmpresa);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                oblist1.add(new Controlo(rs.getInt("id_controlo"), rs.getInt("qtd_acucar"),
                        rs.getInt("temperatura"), rs.getInt("qualidade_ar"),
                        rs.getDate("data_hora"), rs.getInt("id_funcionario"),
                        rs.getInt("resultado")));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        colNum.setCellValueFactory(new PropertyValueFactory<>("id_controlo"));
        colAcucar.setCellValueFactory(new PropertyValueFactory<>("qtd_acucar"));
        colTemperatura.setCellValueFactory(new PropertyValueFactory<>("temperatura"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_hora"));
        colVindima.setCellValueFactory(new PropertyValueFactory<>("qualidade_ar"));
        colFunc.setCellValueFactory(new PropertyValueFactory<>("id_funcionario"));
        Colresult.setCellValueFactory(new PropertyValueFactory<>("resultado"));

        table.setItems(oblist1);


    }

    public void btnAddControloClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/controlo/addControloPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Controlos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addControloController add = loader.getController();
        add.iniciar();

    }

    public void btnEditControloClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/controlo/selectControloToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Controlos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        selectControloToEditController add = loader.getController();
        add.iniciar();

    }


}
