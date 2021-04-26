package app.controller;

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

public class quintaController implements Initializable {
    public Pane quintasPane;
    @FXML
    public TableView<Quinta> table;
    @FXML
    public TableColumn<Quinta, Integer> colNum;
    @FXML
    public TableColumn<Quinta, String> colArea;
    @FXML
    public TableColumn<Quinta, String> colLocalizacao;
    public Button btnAddQuinta;
    public Button btnDeleteQuinta;
    public Button btnEditQuinta;

    @FXML
    public ObservableList<Quinta> oblist1 = FXCollections.observableArrayList();


    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar quintas!");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int nIdEmpresa = userID.getId();

        Connection conn = Util.criarConexao();

        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT ID_QUINTA, AREA_QUINTA, LOCALIZACAO " +
                    "FROM QUINTA WHERE ID_EMPRESA LIKE ? AND ATIVA = 1");

            pst.setInt(1, nIdEmpresa);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){

                    oblist1.add(new Quinta(rs.getInt("id_quinta"), rs.getString("area_quinta"),
                            rs.getString("localizacao")));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        colNum.setCellValueFactory(new PropertyValueFactory<>("id_quinta"));
        colArea.setCellValueFactory(new PropertyValueFactory<>("area_quinta"));
        colLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));

        table.setItems(oblist1);

    }



    public void btnAddQuintaClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/addQuintasPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Quinta");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addQuintaController add = loader.getController();
        add.iniciar();

    }

    public void btnRemoveQuintaClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/removeQuintasPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Remover Quinta");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        removeQuintasController add = loader.getController();
        add.iniciar();

    }

    public void btnEditQuintaClic(ActionEvent actionEvent) {



    }
}
