package app.entities;

public class Quinta {

    int id_quinta;
    String area_quinta;
    String localizacao;
    int id_empresa;

    public Quinta(int id_quinta, String area_quinta, String localizacao) {
        this.id_quinta = id_quinta;
        this.area_quinta = area_quinta;
        this.localizacao = localizacao;

    }

    public int getId_quinta() {
        return id_quinta;
    }

    public void setId_quinta(int id_quinta) {
        this.id_quinta = id_quinta;
    }

    public String getArea_quinta() {
        return area_quinta;
    }

    public void setArea_quinta(String area_quinta) {
        this.area_quinta = area_quinta;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }
}
