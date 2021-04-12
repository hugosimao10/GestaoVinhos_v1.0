/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Funcionario {

    private int id_funcionario;
    private String tipo_funcionario;
    private String nome;
    private String email;
    private String tlm;
    private int nporta;
    private String rua;
    private int id_codpostal;

    public Funcionario() {



    }
    
    public Funcionario(String nome) {
        this.nome = nome;
    }


    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getTipo_funcionario() {
        return tipo_funcionario;
    }

    public void setTipo_funcionario(String tipo_funcionario) {
        this.tipo_funcionario = tipo_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlm() {
        return tlm;
    }

    public void setTlm(String tlm) {
        this.tlm = tlm;
    }

    public int getNporta() {
        return nporta;
    }

    public void setNporta(int nporta) {
        this.nporta = nporta;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getId_codpostal() {
        return id_codpostal;
    }

    public void setId_codpostal(int id_codpostal) {
        this.id_codpostal = id_codpostal;
    }


    // Criar funcionário

    public void create() throws SQLException {
        Connection conn = Util.criarConexao();

        PreparedStatement pst =
                conn.prepareStatement("INSERT INTO FUNCIONARIO COLUMNS (NOME, RUA) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS);

        pst.setString(1, this.nome);
        pst.setString(2, this.rua);

        pst.execute();

    }


    public void read(int id_funcionario) throws SQLException {
        Connection conn = Util.criarConexao();

        String sqlCommand = "SELECT TIPO_FUNCIONARIO, NOME, EMAIL, TLM, NPORTA, RUA, COD_POSTAL FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";

        PreparedStatement pst = conn.prepareStatement(sqlCommand);
        pst.setInt(1, id_funcionario);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            this.id_funcionario = id_funcionario;
            if (rs.getString("TIPO_FUNCIONARIO") != null) {
                this.tipo_funcionario = rs.getString("TIPO_FUNCIONARIO");
            }
            if (rs.getString("NOME") != null) {
                this.nome = rs.getString("NOME");
            }
            if (rs.getString("EMAIL") != null) {
                this.email = rs.getString("EMAIL");
            }
            if (rs.getString("TLM") != null) {
                this.tlm = rs.getString("TLM");
            }
            if (rs.getString("NPORTA") != null) {
                this.nporta = rs.getInt("NPORTA");
            }
            if (rs.getString("RUA") != null) {
                this.rua = rs.getString("RUA");
            }
            if (rs.getString("ID_CODPOSTAL") != null) {
                this.id_codpostal = rs.getInt("ID_CODPOSTAL");
            }

        }
        else throw new SQLException("idFuncionario não existe!!!");

    }

    public static List<Funcionario> readAll() throws SQLException {

        List<Funcionario> resultAux = new ArrayList<>();

        Connection conn = Util.criarConexao();
        String sqlCommand = "SELECT ID_FUNCIONARIO, TIPO_FUNCIONARIO, NOME, EMAIL, TLM, NPORTA, RUA, COD_POSTAL FROM FUNCIONARIO " +
                "ORDER BY NOME, ID_FUNCIONARIO";

        PreparedStatement pst = conn.prepareStatement(sqlCommand);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Funcionario funcionario = new Funcionario();

            if (rs.getString("ID_FUNCIONARIO") != null)
                funcionario.setId_funcionario(rs.getInt("ID_FUNCIONARIO"));

            if (rs.getString("TIPO_FUNCIONARIO") != null) {
                funcionario.setTipo_funcionario(rs.getString("TIPO_FUNCIONARIO"));
            }
            if (rs.getString("NOME") != null) {
                funcionario.setNome(rs.getString("NOME"));
            }
            if (rs.getString("EMAIL") != null) {
                funcionario.setEmail(rs.getString("EMAIL"));
            }
            if (rs.getString("TLM") != null) {
                funcionario.setTlm(rs.getString("TLM"));
            }
            if (rs.getString("NPORTA") != null) {
                funcionario.setNporta(rs.getInt("NPORTA"));
            }
            if (rs.getString("RUA") != null) {
                funcionario.setRua(rs.getString("RUA"));
            }
            if (rs.getString("COD_POSTAL") != null) {
                funcionario.setId_codpostal(rs.getInt("COD_POSTAL"));
            }


            resultAux.add(funcionario);
        }

        return resultAux;
    }
/*
    public static List<Funcionario> readAll(String nome) throws SQLException {

        List<Funcionario> resultAux = new ArrayList<>();

        Connection conn = Util.criarConexao();
        String sqlCommand = "SELECT ID_FUNCIONARIO, TIPO_FUNCIONARIO, NOME, EMAIL, TLM, NPORTA, RUA, COD_POSTAL FROM FUNCIONARIO " +
                "WHERE NOME LIKE ? " +
                "ORDER BY NOME, ID_FUNCIONARIO";

        PreparedStatement pst = conn.prepareStatement(sqlCommand);
        pst.setString(1, "%"+nome+"%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Funcionario funcionario = new Funcionario();

            if (rs.getString("ID_FUNCIONARIO") != null)
                funcionario.setId_funcionario(rs.getInt("ID_FUNCIONARIO"));

            if (rs.getString("TIPO_FUNCIONARIO") != null) {
                funcionario.setTipo_funcionario(rs.getString("TIPO_FUNCIONARIO"));
            }
            if (rs.getString("NOME") != null) {
                funcionario.setNome(rs.getString("NOME"));
            }
            if (rs.getString("EMAIL") != null) {
                funcionario.setEmail(rs.getString("EMAIL"));
            }
            if (rs.getString("TLM") != null) {

                funcionario.setTlm(rs.getString("TLM"));
            }
            if (rs.getString("NPORTA") != null) {
                funcionario.setNporta(rs.getInt("NPORTA"));
            }
            if (rs.getString("RUA") != null) {
                funcionario.setRua(rs.getString("RUA"));
            }
            if (rs.getString("COD_POSTAL") != null) {
                funcionario.setId_codpostal(rs.getInt("COD_POSTAL"));
            }

            resultAux.add(funcionario);
        }

        return resultAux;
    }

 */

}
