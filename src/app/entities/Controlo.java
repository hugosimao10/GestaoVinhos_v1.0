package app.entities;

import java.sql.Date;

public class Controlo {

    int id_controlo;
    int qtd_acucar;
    int temperatura;
    int qualidade_ar;
    Date data_hora;
    int id_plant_vindima;
    int id_funcionario;
    int id_avaliacao;
    int resultado;


    public Controlo(int id_controlo, int qtd_acucar, int temperatura, int qualidade_ar, Date data_hora, int id_plant_vindima, int id_funcionario, int id_avaliacao) {
        this.id_controlo = id_controlo;
        this.qtd_acucar = qtd_acucar;
        this.temperatura = temperatura;
        this.qualidade_ar = qualidade_ar;
        this.data_hora = data_hora;
        this.id_plant_vindima = id_plant_vindima;
        this.id_funcionario = id_funcionario;
        this.id_avaliacao = id_avaliacao;
    }

    public Controlo(int id_controlo, int qtd_acucar, int temperatura, int qualidade_ar, Date data_hora, int id_funcionario, int resultado) {
        this.id_controlo = id_controlo;
        this.qtd_acucar = qtd_acucar;
        this.temperatura = temperatura;
        this.qualidade_ar = qualidade_ar;
        this.data_hora = data_hora;
        this.id_funcionario = id_funcionario;
        this.resultado = resultado;
    }

    public int getId_controlo() {
        return id_controlo;
    }

    public void setId_controlo(int id_controlo) {
        this.id_controlo = id_controlo;
    }

    public int getQtd_acucar() {
        return qtd_acucar;
    }

    public void setQtd_acucar(int qtd_acucar) {
        this.qtd_acucar = qtd_acucar;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getQualidade_ar() {
        return qualidade_ar;
    }

    public void setQualidade_ar(int qualidade_ar) {
        this.qualidade_ar = qualidade_ar;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public int getId_plant_vindima() {
        return id_plant_vindima;
    }

    public void setId_plant_vindima(int id_plant_vindima) {
        this.id_plant_vindima = id_plant_vindima;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getId_avaliacao() {
        return id_avaliacao;
    }

    public void setId_avaliacao(int id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
}
