package app.entities;

import java.sql.Date;

public class Embalamento {

    int id_produto_final;
    int qtd_caixas;
    String tipo_vinho;
    Date data_emb;

    public Embalamento(int id_produto_final, int qtd_caixas, String tipo_vinho, Date data_emb) {
        this.id_produto_final = id_produto_final;
        this.qtd_caixas = qtd_caixas;
        this.tipo_vinho = tipo_vinho;
        this.data_emb = data_emb;
    }

    public int getId_produto_final() {
        return id_produto_final;
    }

    public void setId_produto_final(int id_produto_final) {
        this.id_produto_final = id_produto_final;
    }

    public int getQtd_caixas() {
        return qtd_caixas;
    }

    public void setQtd_caixas(int qtd_caixas) {
        this.qtd_caixas = qtd_caixas;
    }

    public String getTipo_vinho() {
        return tipo_vinho;
    }

    public void setTipo_vinho(String tipo_vinho) {
        this.tipo_vinho = tipo_vinho;
    }

    public Date getData_emb() {
        return data_emb;
    }

    public void setData_emb(Date data_emb) {
        this.data_emb = data_emb;
    }
}
