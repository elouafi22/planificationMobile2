package com.example.planificationmobile2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class baseDeDonne {
    // class de base de donne oracle utilisé JBDC
    private final static String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private String user;
    private String password;

    private Statement Session; // for file session conection sql oracle

    // constructeur
    public baseDeDonne(String user , String password){
        this.user = user;
        this.password = password;
    }

    // methode de connexion
    public void connexion() throws ClassNotFoundException, SQLException {
        // code de connexion

            //Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(url, this.user, this.password);
            Session = conn.createStatement(); // on stock la session dans la variable Session


    }

    // methode for execute sql statement
    public void execute(String sql) throws SQLException {
        ResultSet rs = Session.executeQuery(sql);

        StringBuffer stringBuffer = new StringBuffer();
        while (rs.next()) {
            stringBuffer.append(rs.getString(1) + "\n");
        }

        System.out.println(stringBuffer.toString());


    }

    // methode de deconnexion
    public void deconnexion() {
        // code de deconnexion


    }
}
