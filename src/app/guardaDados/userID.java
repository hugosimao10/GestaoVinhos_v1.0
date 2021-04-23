package app.guardaDados;

public class userID {

    private static int id;
    private static int idUser;
    private static String username;
    private static String nomeEmpresa;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        userID.id = id;
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

    public static int getIdUser() {
        return idUser;
    }

    public static void setIdUser(int idUser) {
        userID.idUser = idUser;
    }

    public static void setNomeEmpresa(String nomeEmpresa) {
        userID.nomeEmpresa = nomeEmpresa;
    }
}
