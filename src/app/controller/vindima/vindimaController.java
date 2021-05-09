package app.controller.vindima;

import app.entities.Vindima;
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

public class vindimaController implements Initializable {
    public Pane vindimasPane;
    @FXML
    public TableView table;
    @FXML
    public TableColumn colNum;
    @FXML
    public TableColumn colDtIni;
    @FXML
    public TableColumn colDtFim;
    @FXML
    public TableColumn colFunc;
    @FXML
    public TableColumn colPlant;
    @FXML
    public TableColumn colQtd;
    public Button btnAddVindima;
    public Button btnDeleteFuncionario;
    public Button butEditVindima;
    @FXML
    public ObservableList<Vindima> oblist4 = FXCollections.observableArrayList();

    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar Vindimas!");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection conn = Util.criarConexao();

        int log = userID.getId();

        PreparedStatement pst1 = null;
        try {
            pst1 = conn.prepareStatement("SELECT pv.id_plant_vindima, pv.qtd_vindimada, pv.data_fim_vindima, pv.id_vindima," +
                    " pv.id_plantacao, pv.id_funcionario, f.id_empresa, f.id_funcionario, v.id_vindima, v.data_inicio_vindima FROM PLANTACAO_VINDIMA pv, FUNCIONARIO f, VINDIMA v WHERE f.ID_FUNCIONARIO = pv.ID_FUNCIONARIO AND f.ID_EMPRESA = ? AND v.id_vindima = pv.id_vindima");
            pst1.setInt(1, log);
            ResultSet rs2 = pst1.executeQuery();

            while (rs2.next()) {

                oblist4.add(new Vindima(rs2.getInt("id_plant_vindima"), rs2.getInt("qtd_vindimada"),
                        rs2.getDate("data_fim_vindima"), rs2.getDate("DATA_INICIO_VINDIMA"),
                        rs2.getInt("id_plantacao"), rs2.getInt("id_funcionario")));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        colNum.setCellValueFactory(new PropertyValueFactory<>("id_plant_vindima"));
        colQtd.setCellValueFactory(new PropertyValueFactory<>("qtd_vindimada"));
        colDtIni.setCellValueFactory(new PropertyValueFactory<>("data_inicio_vindima"));
        colDtFim.setCellValueFactory(new PropertyValueFactory<>("data_fim_vindima"));
        colFunc.setCellValueFactory(new PropertyValueFactory<>("id_funcionario"));
        colPlant.setCellValueFactory(new PropertyValueFactory<>("id_plantacao"));

        table.setItems(oblist4);


    }


    public void btnAddVindimaClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/vindima/addVindimaPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Vindima");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addVindimaController add = loader.getController();
        add.iniciar();

    }

    public void btnConcluirVindimaClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/vindima/concluirVindimaPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Concluir Vindima");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        concluirVindimaController add = loader.getController();
        add.iniciar();

    }

    public void butEditVindimaClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/vindima/selectVindimaToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Vindima");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        selectVindimaToEditController add = loader.getController();
        add.iniciar();

    }


}
