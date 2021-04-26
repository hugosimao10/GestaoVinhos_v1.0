package app.entities;

public class Plantacao {

    int id_plantacao;
    String area_casta;
    int id_funcionario;
    int id_quinta;
    int id_casta;
    int estado;

    public Plantacao(int id_plantacao, String area_casta, int id_funcionario, int id_quinta, int id_casta, int estado) {
        this.id_plantacao = id_plantacao;
        this.area_casta = area_casta;
        this.id_funcionario = id_funcionario;
        this.id_quinta = id_quinta;
        this.id_casta = id_casta;
        this.estado = estado;
    }

    public int getId_plantacao() {
        return id_plantacao;
    }

    public void setId_plantacao(int id_plantacao) {
        this.id_plantacao = id_plantacao;
    }

    public String getArea_casta() {
        return area_casta;
    }

    public void setArea_casta(String area_casta) {
        this.area_casta = area_casta;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getId_quinta() {
        return id_quinta;
    }

    public void setId_quinta(int id_quinta) {
        this.id_quinta = id_quinta;
    }

    public int getId_casta() {
        return id_casta;
    }

    public void setId_casta(int id_casta) {
        this.id_casta = id_casta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
