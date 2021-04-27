package app.entities;

public class Avaliacao {

        int id_avaliacao;
        int qtd_produzida;
        String qualidade_vinho;
        int id_produto_final;

    public Avaliacao(int id_avaliacao, int qtd_produzida, String qualidade_vinho, int id_produto_final) {
        this.id_avaliacao = id_avaliacao;
        this.qtd_produzida = qtd_produzida;
        this.qualidade_vinho = qualidade_vinho;
        this.id_produto_final = id_produto_final;
    }

    public int getId_avaliacao() {
        return id_avaliacao;
    }

    public void setId_avaliacao(int id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
    }

    public int getQtd_produzida() {
        return qtd_produzida;
    }

    public void setQtd_produzida(int qtd_produzida) {
        this.qtd_produzida = qtd_produzida;
    }

    public String getQualidade_vinho() {
        return qualidade_vinho;
    }

    public void setQualidade_vinho(String qualidade_vinho) {
        this.qualidade_vinho = qualidade_vinho;
    }

    public int getId_produto_final() {
        return id_produto_final;
    }

    public void setId_produto_final(int id_produto_final) {
        this.id_produto_final = id_produto_final;
    }
}
