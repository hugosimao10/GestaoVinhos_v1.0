package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

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

    public void iniciar(int idUserEdit, String cargo,String nome, String email,String tlm,int nPorta,String rua,int cod_postal,int empresa,String pw,String user,int estado) {
        System.out.println("Está na area de editar funcionários!");

        String nporta1 = String.valueOf(nPorta);
        String cp1 = String.valueOf(cod_postal);

        // dropdownEditFunc.setItems(list);
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
    public void btnEditFuncEditClic(ActionEvent actionEvent)throws IOException {



    }

    // BOTAO DE CANCELAR DO PANE DE EDITAR FUNCIONARIO
    @FXML
    public void btnEditFuncCancelarClic(ActionEvent actionEvent) throws IOException{

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
