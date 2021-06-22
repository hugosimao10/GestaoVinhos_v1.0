package app.entities;

public class Plantacao {

    int id_plantacao;
    String area_casta;
    String username;
    int id_quinta;
    String tipo_casta;
    int estado;

    public Plantacao(int id_plantacao, String area_casta, String username, int id_quinta, String tipo_casta, int estado) {
        this.id_plantacao = id_plantacao;
        this.area_casta = area_casta;
        this.username = username;
        this.id_quinta = id_quinta;
        this.tipo_casta = tipo_casta;
        this.estado = estado;
    }

    public Plantacao(int id_plantacao, String area_casta, String username, int id_quinta, String tipo_casta) {
        this.id_plantacao = id_plantacao;
        this.area_casta = area_casta;
        this.username = username;
        this.id_quinta = id_quinta;
        this.tipo_casta = tipo_casta;
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

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public int getId_quinta() {
        return id_quinta;
    }

    public void setId_quinta(int id_quinta) {
        this.id_quinta = id_quinta;
    }

    public String getTipo_casta() {
        return tipo_casta;
    }

    public void setTipo_casta(String tipo_casta) {
        this.tipo_casta = tipo_casta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
