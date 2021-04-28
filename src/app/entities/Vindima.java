package app.entities;

import java.sql.Date;

public class Vindima {

    int id_plant_vindima;
    int qtd_vindimada;
    Date data_fim_vindima;
    Date data_inicio_vindima;
    int id_vindima;
    int id_plantacao;
    int id_funcionario;

    public Vindima(int id_plant_vindima, int qtd_vindimada, Date data_fim_vindima, int id_vindima, int id_plantacao, int id_funcionario) {
        this.id_plant_vindima = id_plant_vindima;
        this.qtd_vindimada = qtd_vindimada;
        this.data_fim_vindima = data_fim_vindima;
        this.id_vindima = id_vindima;
        this.id_plantacao = id_plantacao;
        this.id_funcionario = id_funcionario;
    }

    public Vindima(int id_plant_vindima, int qtd_vindimada, Date data_fim_vindima, Date data_inicio_vindima, int id_plantacao, int id_funcionario) {
        this.id_plant_vindima = id_plant_vindima;
        this.qtd_vindimada = qtd_vindimada;
        this.data_fim_vindima = data_fim_vindima;
        this.data_inicio_vindima = data_inicio_vindima;
        this.id_plantacao = id_plantacao;
        this.id_funcionario = id_funcionario;
    }

    public Date getData_inicio_vindima() {
        return data_inicio_vindima;
    }

    public void setData_inicio_vindima(Date data_inicio_vindima) {
        this.data_inicio_vindima = data_inicio_vindima;
    }

    public int getId_plant_vindima() {
        return id_plant_vindima;
    }

    public void setId_plant_vindima(int id_plant_vindima) {
        this.id_plant_vindima = id_plant_vindima;
    }

    public int getQtd_vindimada() {
        return qtd_vindimada;
    }

    public void setQtd_vindimada(int qtd_vindimada) {
        this.qtd_vindimada = qtd_vindimada;
    }

    public Date getData_fim_vindima() {
        return data_fim_vindima;
    }

    public void setData_fim_vindima(Date data_fim_vindima) {
        this.data_fim_vindima = data_fim_vindima;
    }

    public int getId_vindima() {
        return id_vindima;
    }

    public void setId_vindima(int id_vindima) {
        this.id_vindima = id_vindima;
    }

    public int getId_plantacao() {
        return id_plantacao;
    }

    public void setId_plantacao(int id_plantacao) {
        this.id_plantacao = id_plantacao;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }
}
