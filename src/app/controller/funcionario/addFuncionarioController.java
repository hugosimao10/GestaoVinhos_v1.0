package app.controller.funcionario;

import app.error.msg;
import app.entities.userID;
import app.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.sql.*;

public class addFuncionarioController {
    public ComboBox dropdownTipoFunc;
    public TextField usernameFunc;
    public PasswordField pwdFunc;
    public TextField nomeFunc;
    public TextField emailFunc;
    public TextField tlmFunc;
    public TextField ruaFunc;
    public TextField portaFunc;
    public TextField cpFunc;
    public Button btnConfirmAddFunc;
    public Button btnCancelAddFunc;
    public Pane funcionariosPane;

    @FXML
    private ObservableList<String> tiposFunc;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de adicionar funcionários!");

        Connection c = Util.criarConexao();

        PreparedStatement p = c.prepareStatement("SELECT * FROM TIPO_FUNCIONARIO");


        ResultSet r = p.executeQuery();

        tiposFunc = FXCollections.observableArrayList();

        while(r.next()){

            int idTF = r.getInt("ID");
            String cargo = r.getString("CARGO");
            tiposFunc.add(cargo);
            dropdownTipoFunc.setValue(cargo);

        }

        dropdownTipoFunc.setItems(tiposFunc);


    }

    // BOTAO PARA ADICONAR FUNCIONARIO
    @FXML
    public void btnAddFuncionarioAddClic(ActionEvent actionEvent) throws IOException, SQLException {

        String tipo_func = dropdownTipoFunc.getValue().toString();
        String user = usernameFunc.getText();
        String email = emailFunc.getText();
        String tlm = tlmFunc.getText();
        int estado = 1;
        int idEmpresaLogada = userID.getId();
        String nPorta = portaFunc.getText();
        String rua = ruaFunc.getText();
        String pass = pwdFunc.getText();
        String nome = nomeFunc.getText();
        String codpostal = cpFunc.getText();

        if(user.isEmpty() || email.isEmpty() || tlm.isEmpty() || nPorta.isEmpty() || rua.isEmpty() || pass.isEmpty() || nome.isEmpty() || codpostal.isEmpty()){

            System.out.println("Não podem ficar campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");
        }
        else {

            Connection c1 = Util.criarConexao();

            int nportaInt = Integer.parseInt(nPorta);

            PreparedStatement p4 = c1.prepareStatement("SELECT ID FROM TIPO_FUNCIONARIO WHERE CARGO = ?");
            p4.setString(1, tipo_func);

            ResultSet s4 = p4.executeQuery();

            if (s4.next()) {

                int nCargo = s4.getInt("ID");

                PreparedStatement p5 = c1.prepareStatement("SELECT USERNAME FROM FUNCIONARIO WHERE USERNAME = ?");
                p5.setString(1, user);

                ResultSet s5 = p5.executeQuery();

                if (s5.next()) {
                    System.out.println("O username já existe!");
                    msg.alertaErro("O username já existe!", "Erro!", "Username em uso!");

                } else {

                    PreparedStatement pst10 = c1.prepareStatement("SELECT EMAIL FROM FUNCIONARIO WHERE EMAIL LIKE ?");
                    pst10.setString(1, email);

                    ResultSet rs10 = pst10.executeQuery();

                    if (rs10.next()) {
                        System.out.println("Esse email já se encontra registado!");
                        msg.alertaErro("O email já existe!", "Erro!", "Email em uso!");
                    } else {


                        PreparedStatement pst11 = c1.prepareStatement("SELECT TLM FROM FUNCIONARIO WHERE TLM LIKE ?");
                        pst11.setString(1, tlm);

                        ResultSet rs11 = pst11.executeQuery();

                        if (rs11.next()) {
                            System.out.println("Esse número telefónico já se encontra registado!");
                            msg.alertaErro("O Número telefónico já existe!", "Erro!", "Número telefónico em uso!");

                        } else {

                            PreparedStatement pst12 = c1.prepareStatement("SELECT * FROM COD_POSTAL WHERE COD_POSTAL LIKE ?");
                            pst12.setString(1, codpostal);

                            ResultSet rs12 = pst12.executeQuery();

                            if (rs12.next()) {

                                int codPostExiste = rs12.getInt("ID_CODPOSTAL");

                                PreparedStatement pst = c1.prepareStatement("INSERT INTO FUNCIONARIO(NOME, EMAIL, TLM, NPORTA," +
                                        "RUA, COD_POSTAL, ID_EMPRESA, PW, USERNAME, ESTADO, TIPO_FUNCIONARIO) VALUES (?,?,?,?,?,?,?,?,?,?,?)");


                                pst.setString(1, nome);
                                pst.setString(2, email);
                                pst.setString(3, tlm);
                                pst.setInt(4, nportaInt);
                                pst.setString(5, rua);
                                pst.setInt(6, codPostExiste);
                                pst.setInt(7, idEmpresaLogada);
                                pst.setString(8, pass);
                                pst.setString(9, user);
                                pst.setInt(10, estado);
                                pst.setInt(11, nCargo);

                                pst.executeQuery();

                                System.out.println("Funcionário adicionado com sucesso!");
                                msg.alertaInfo("Funcionário adicionado com sucesso!", "Info!", "Sucesso!");


                            } else {

                                PreparedStatement pst15 = c1.prepareStatement("INSERT INTO COD_POSTAL(COD_POSTAL) VALUES (?)");
                                pst15.setString(1, codpostal);
                                pst15.executeQuery();

                                PreparedStatement pst16 = c1.prepareStatement("SELECT ID_CODPOSTAL FROM COD_POSTAL WHERE COD_POSTAL LIKE ?");
                                pst16.setString(1, codpostal);
                                ResultSet rs16 = pst16.executeQuery();

                                if(rs16.next()) {
                                    int codPostExiste1 = rs16.getInt("ID_CODPOSTAL");

                                    PreparedStatement pst20 = c1.prepareStatement("INSERT INTO FUNCIONARIO(NOME, EMAIL, TLM, NPORTA," +
                                            "RUA, COD_POSTAL, ID_EMPRESA, PW, USERNAME, ESTADO, TIPO_FUNCIONARIO) VALUES (?,?,?,?,?,?,?,?,?,?,?)");


                                    pst20.setString(1, nome);
                                    pst20.setString(2, email);
                                    pst20.setString(3, tlm);
                                    pst20.setInt(4, nportaInt);
                                    pst20.setString(5, rua);
                                    pst20.setInt(6, codPostExiste1);
                                    pst20.setInt(7, idEmpresaLogada);
                                    pst20.setString(8, pass);
                                    pst20.setString(9, user);
                                    pst20.setInt(10, estado);
                                    pst20.setInt(11, nCargo);

                                    pst20.executeQuery();

                                    System.out.println("Funcionário adicionado com sucesso! (Novo código postal adicionado!)");
                                    msg.alertaInfo("Funcionário adicionado com sucesso! (Novo código postal adicionado!)", "Info!", "Sucesso!");
                                }
                                else{
                                    System.out.println("Não adicionou o novo código postal com sucesso!");
                                }

                            }

                        }
                    }
                }
            } else {
                System.out.println("ID do cargo não encontrado!");
            }
        }

    }

    // BOTAO PARA CANCELAR
    @FXML
    public void btnAddFuncCancelarClic(ActionEvent actionEvent)throws IOException {

        dropdownTipoFunc.cancelEdit();
        usernameFunc.setText("");
        pwdFunc.setText("");
        nomeFunc.setText("");
        emailFunc.setText("");
        tlmFunc.setText("");
        ruaFunc.setText("");
        portaFunc.setText("");
        cpFunc.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }
}
