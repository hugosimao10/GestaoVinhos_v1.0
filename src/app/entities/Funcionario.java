package app.entities;

public class Funcionario {

    int id_funcionario;
    String nome;
    String tipo_funcionario;
    String email;
    String username;
    int estado;

    public Funcionario(int id_funcionario, String nome, String tipo_funcionario, String email, String username, int estado) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.tipo_funcionario = tipo_funcionario;
        this.email = email;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
