/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities;

import app.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Funcionario {

    private int id_funcionario;
    private int tipo_funcionario;
    private String nome;
    private String email;
    private String tlm;
    private int nporta;
    private String rua;
    private int id_codpostal;
    private int id_empresa;
    private int estado;
    private String pw;
    private String username;

    public Funcionario() {



    }

    public Funcionario(int id_funcionario, int tipo_funcionario, String nome, String email, String tlm, int estado) {
        this.id_funcionario = id_funcionario;
        this.tipo_funcionario = tipo_funcionario;
        this.nome = nome;
        this.email = email;
        this.tlm = tlm;
        this.estado = estado;
    }

    public int getTipo_funcionario() {
        return tipo_funcionario;
    }

    public void setTipo_funcionario(int tipo_funcionario) {
        this.tipo_funcionario = tipo_funcionario;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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




}
