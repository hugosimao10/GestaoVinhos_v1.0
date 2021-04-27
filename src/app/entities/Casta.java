package app.entities;

public class Casta {
    int id_casta;
    String tipo_casta;


    public Casta(int id_casta, String tipo_casta) {
        this.id_casta = id_casta;
        this.tipo_casta = tipo_casta;
    }

    public int getId_casta() {
        return id_casta;
    }

    public void setId_casta(int id_casta) {
        this.id_casta = id_casta;
    }

    public String getTipo_casta() {
        return tipo_casta;
    }

    public void setTipo_casta(String tipo_casta) {
        this.tipo_casta = tipo_casta;
    }
}
