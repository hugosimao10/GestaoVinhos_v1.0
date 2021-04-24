package app.guardaDados;

public class ModelTableFunc {

    int id_funcionario;
    String nome;
    String tipo_funcionario;
    String email;
    String tlm;
    int estado;

    public ModelTableFunc(int id_funcionario, String nome, String tipo_funcionario, String email, String tlm, int estado) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.tipo_funcionario = tipo_funcionario;
        this.email = email;
        this.tlm = tlm;
        this.estado = estado;
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

    public String getTipo_funcionario() {
        return tipo_funcionario;
    }

    public void setTipo_funcionario(String tipo_funcionario) {
        this.tipo_funcionario = tipo_funcionario;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
