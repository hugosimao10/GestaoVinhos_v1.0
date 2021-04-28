package app.controller.vindima;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;

public class editVindimaController {
    public Pane editVindimasPane;
    public TextField usernameFunc;
    public TextField numPlant;
    public Button btnConfirmEditVindima;
    public Button btnCancelEditVindima;
    public DatePicker dtIniVindima;
    public DatePicker dtFimVindima;
    public TextField qtdVindimada;
    public Text guardaIdEditVindima;

    public void iniciar(int idEdit, LocalDate dataInic, LocalDate dataFim, int num_func, int nPla, int qtdV) throws SQLException {
        System.out.println("Está na area de editar Vindimas!");

        usernameFunc.setText(String.valueOf(num_func));
        numPlant.setText(String.valueOf(nPla));
        qtdVindimada.setText(String.valueOf(qtdV));
        dtIniVindima.setValue(dataInic);
        dtFimVindima.setValue(dataFim);

        guardaIdEditVindima.setText(String.valueOf(idEdit));
        guardaIdEditVindima.setVisible(false);

    }

    public void btnEditVindimaClic(ActionEvent actionEvent) throws SQLException {

        String a = usernameFunc.getText();
        int func = Integer.parseInt(a);
        String b = numPlant.getText();
        int numPla = Integer.parseInt(b);
        String c = qtdVindimada.getText();
        int qtdVi = Integer.parseInt(c);
        LocalDate dataI = dtIniVindima.getValue();
        LocalDate dataF = dtFimVindima.getValue();

        if(a.isEmpty() || b.isEmpty() || c.isEmpty()){
            System.out.println("Não pode haver campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        }
        else{

            Connection c2 = Util.criarConexao();
            String aa = guardaIdEditVindima.getText();
            int q = Integer.parseInt(aa);

            PreparedStatement pst2 = c2.prepareStatement("SELECT * FROM VINDIMA WHERE DATA_INICIO_VINDIMA = ?");
            pst2.setDate(1, Date.valueOf(dataI));
            ResultSet s1 = pst2.executeQuery();

            if(s1.next()){

                PreparedStatement pst = c2.prepareStatement("UPDATE PLANTACAO_VINDIMA SET QTD_VINDIMADA = ?, DATA_FIM_VINDIMA= ?," +
                        "ID_VINDIMA = ?, ID_PLANTACAO = ?, ID_FUNCIONARIO = ?" +
                        " WHERE ID_PLANT_VINDIMA = ?");

                pst.setInt(1, qtdVi);
                pst.setDate(2, Date.valueOf(dataF));
                pst.setInt(3, s1.getInt("ID_VINDIMA"));
                pst.setInt(4, numPla);
                pst.setInt(5, func);
                pst.setInt(6, q);

                pst.executeQuery();

                System.out.println("Vindima alterada com sucesso!");
                msg.alertaInfo("Vindima alterada com sucesso!", "Info!", "Sucesso!");



            }
            else{


                PreparedStatement p9 = c2.prepareStatement("INSERT INTO VINDIMA(DATA_INICIO_VINDIMA) VALUES (?)");
                p9.setDate(1, Date.valueOf(dataI));
                p9.executeQuery();

                PreparedStatement p5 = c2.prepareStatement("SELECT * FROM VINDIMA WHERE DATA_INICIO_VINDIMA = ?");
                p5.setDate(1, Date.valueOf(dataI));

                ResultSet s = p5.executeQuery();

                if(s.next()){

                    int a1 = s.getInt("ID_VINDIMA");

                    PreparedStatement pst = c2.prepareStatement("UPDATE PLANTACAO_VINDIMA SET QTD_VINDIMADA = ?, DATA_FIM_VINDIMA= ?," +
                            "ID_VINDIMA = ?, ID_PLANTACAO = ?, ID_FUNCIONARIO = ?" +
                            " WHERE ID_PLANT_VINDIMA = ?");

                    pst.setInt(1, qtdVi);
                    pst.setDate(2, Date.valueOf(dataF));
                    pst.setInt(3, a1);
                    pst.setInt(4, numPla);
                    pst.setInt(5, func);
                    pst.setInt(6, q);

                    pst.executeQuery();

                    System.out.println("Vindima alterada com sucesso, com data inicio!");
                    msg.alertaInfo("Vindima alterada com sucesso, com nova data de inicio!", "Info!", "Sucesso!");
                }
                else{
                    System.out.println("Erro a criar o novo ID da nova data de inicio de vindima. editVindimaCotnroller!");
                }

            }




        }


    }

    public void btnEditVindimaCancelarClic(ActionEvent actionEvent) {

        usernameFunc.setText("");
        numPlant.setText("");
        dtFimVindima.getEditor().clear();
        dtIniVindima.getEditor().clear();
        qtdVindimada.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
