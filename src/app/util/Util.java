/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author miguel
 */
public class Util {

    private static Connection conn = null;

    public static Connection criarConexao() {

        if (conn != null) {
            return conn;
        } else {

            try {
                Class.forName("oracle.jdbc.OracleDriver");
            } catch (ClassNotFoundException e) {
                System.out.println("Oops! Can't find class oracle.jdbc(.driver).OracleDriver");
                System.exit(-1);
            }

            try {
                conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", "user10", "user10");
                //conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println("ERRO " + e.getMessage());
                //javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO", javax.swing.JOptionPane.ERROR_MESSAGE);
                System.exit(-2);
            }

            return conn;
        }
    }

}
