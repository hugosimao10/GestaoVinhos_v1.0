package app.entities;

public class TipoFuncionario {

    public int id;
    public String tipo_func;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return tipo_func;
    }

    public void setDescricao(String descricao) {
        this.tipo_func = descricao;
    }
}
