package app.userLogado;

public class userID {

    private static int id;
    private static String username;
    private static String nomeEmpresa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        userID.username = username;
    }

    public static String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public static void setNomeEmpresa(String nomeEmpresa) {
        userID.nomeEmpresa = nomeEmpresa;
    }
}
