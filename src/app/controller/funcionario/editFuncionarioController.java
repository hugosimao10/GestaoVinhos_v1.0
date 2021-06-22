package app.controller.funcionario;

import app.entities.userID;
import app.error.msg;
import app.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class editFuncionarioController {
    public ComboBox dropdownEditFunc;
    public TextField usernameEditFunc;
    public PasswordField pwdEditFunc;
    public TextField nomeEditFunc;
    public TextField emailEditFunc;
    public TextField tlmEditFunc;
    public TextField ruaEditFunc;
    public TextField portaEditFunc;
    public TextField cpEditFunc;
    public Button btnConfirmEditFunc;
    public Button btnCancelEditFunc;
    public Pane funcionariosEditPane;
    public Text editFuncLabel;
    public Text guardaIdEdit;

    @FXML
    private ObservableList<String> tiposFunc;

    public void iniciar(int idUserEdit, String cargo, String nome, String email, String tlm, int nPorta, String rua, int cod_postal, int empresa, String pw, String user, int estado) throws SQLException {
        System.out.println("Está na area de editar funcionários!");

        guardaIdEdit.setVisible(false);
        usernameEditFunc.setDisable(true);

        String nporta1 = String.valueOf(nPorta);
        String cp1 = String.valueOf(cod_postal);

        Connection c44 = Util.criarConexao();

        String idAEditar = String.valueOf(idUserEdit);
        guardaIdEdit.setText(idAEditar);


        int i = Integer.parseInt(cargo);

        PreparedStatement p33 = c44.prepareStatement("SELECT * FROM TIPO_FUNCIONARIO");

        ResultSet set1 = p33.executeQuery();

        tiposFunc = FXCollections.observableArrayList();

        while (set1.next()) {

            String cargo1 = set1.getString("CARGO");
            tiposFunc.add(cargo1);
            dropdownEditFunc.setValue(cargo1);

        }

        dropdownEditFunc.setItems(tiposFunc);
        usernameEditFunc.setText(user);
        pwdEditFunc.setText(pw);
        nomeEditFunc.setText(nome);
        emailEditFunc.setText(email);
        tlmEditFunc.setText(tlm);
        ruaEditFunc.setText(rua);
        portaEditFunc.setText(nporta1);
        cpEditFunc.setText(cp1);

    }

    // BOTAO DE EDITAR FUNCIONARIO
    @FXML
    public void btnEditFuncEditClic(ActionEvent actionEvent) throws IOException, SQLException {

        Connection c = Util.criarConexao();

        String tipo_func = dropdownEditFunc.getValue().toString();
        String user = usernameEditFunc.getText();
        String email = emailEditFunc.getText();
        String tlm = tlmEditFunc.getText();
        int estado = 1;
        int idEmpresaLogada = userID.getId();
        String nPorta = portaEditFunc.getText();
        String rua = ruaEditFunc.getText();
        String pass = pwdEditFunc.getText();
        String nome = nomeEditFunc.getText();
        String codpostal = cpEditFunc.getText();

        String r = guardaIdEdit.getText();
        int idEdit = Integer.parseInt(r);

        if (user.isEmpty() || email.isEmpty() || tlm.isEmpty() || nPorta.isEmpty() || rua.isEmpty() || pass.isEmpty() || nome.isEmpty() || codpostal.isEmpty()) {

            System.out.println("Não podem ficar campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");
        } else if (pass.length() < 6) {
            msg.alertaAviso("Password deve ter mais que 6 caracteres!", "Aviso!", "Password demasiado pequena!");
        } else if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            msg.alertaAviso("Deve introduzir um email válido!", "Aviso!", "Email inválido!");
        } else if (!Pattern.matches("[0-9]{4}-[0-9]{3}", codpostal)) {
            msg.alertaAviso("Deve introduzir um código postal válido!", "Aviso!", "Código postal inválido!");
        } else {
            Connection c1 = Util.criarConexao();

            int nportaInt = Integer.parseInt(nPorta);

            PreparedStatement p4 = c1.prepareStatement("SELECT ID FROM TIPO_FUNCIONARIO WHERE CARGO = ?");
            p4.setString(1, tipo_func);

            ResultSet s4 = p4.executeQuery();

            if (s4.next()) {

                int nCargo = s4.getInt("ID");

                PreparedStatement pst12 = c1.prepareStatement("SELECT * FROM COD_POSTAL WHERE COD_POSTAL LIKE ?");
                pst12.setString(1, codpostal);

                ResultSet rs12 = pst12.executeQuery();

                if (rs12.next()) {

                    int codPostExiste = rs12.getInt("ID_CODPOSTAL");

                    PreparedStatement pst = c1.prepareStatement("UPDATE FUNCIONARIO SET NOME = ?, EMAIL= ?, TLM= ?, NPORTA= ?," +
                            "RUA= ?, COD_POSTAL= ?, ID_EMPRESA= ?, PW= ?, USERNAME= ?, ESTADO= ?, TIPO_FUNCIONARIO= ? WHERE ID_FUNCIONARIO = ?");


                    addFuncionarioController.preparedStatement(user, email, tlm, estado, idEmpresaLogada, rua, pass, nome, nportaInt, nCargo, codPostExiste, pst);
                    pst.setInt(12, idEdit);

                    pst.executeQuery();

                    System.out.println("Funcionário alterado com sucesso!");
                    msg.alertaInfo("Funcionário alterado com sucesso!", "Info!", "Sucesso!");


                } else {

                    PreparedStatement pst15 = c1.prepareStatement("INSERT INTO COD_POSTAL(COD_POSTAL) VALUES (?)");
                    pst15.setString(1, codpostal);
                    pst15.executeQuery();

                    PreparedStatement pst16 = c1.prepareStatement("SELECT ID_CODPOSTAL FROM COD_POSTAL WHERE COD_POSTAL LIKE ?");
                    pst16.setString(1, codpostal);
                    ResultSet rs16 = pst16.executeQuery();

                    if (rs16.next()) {
                        int codPostExiste1 = rs16.getInt("ID_CODPOSTAL");

                        PreparedStatement pst20 = c1.prepareStatement("UPDATE FUNCIONARIO SET NOME = ?, EMAIL= ?, TLM= ?, NPORTA= ?," +
                                "RUA= ?, COD_POSTAL= ?, ID_EMPRESA= ?, PW= ?, USERNAME= ?, ESTADO= ?, TIPO_FUNCIONARIO= ? WHERE ID_FUNCIONARIO = ?");


                        addFuncionarioController.preparedStatement(user, email, tlm, estado, idEmpresaLogada, rua, pass, nome, nportaInt, nCargo, codPostExiste1, pst20);
                        pst20.setInt(12, idEdit);

                        pst20.executeQuery();

                        System.out.println("Funcionário adicionado com sucesso! (Novo código postal adicionado!)");
                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                        msg.alertaInfo("Funcionário adicionado com sucesso! (Novo código postal adicionado!)", "Info!", "Sucesso!");
                    } else {
                        System.out.println("Não adicionou o novo código postal com sucesso!");
                    }

                }


            } else {
                System.out.println("ID do cargo não encontrado!");
            }
        }

    }

    // BOTAO DE CANCELAR DO PANE DE EDITAR FUNCIONARIO
    @FXML
    public void btnEditFuncCancelarClic(ActionEvent actionEvent) throws IOException {

        dropdownEditFunc.cancelEdit();
        usernameEditFunc.setText("");
        pwdEditFunc.setText("");
        nomeEditFunc.setText("");
        emailEditFunc.setText("");
        tlmEditFunc.setText("");
        ruaEditFunc.setText("");
        portaEditFunc.setText("");
        cpEditFunc.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
