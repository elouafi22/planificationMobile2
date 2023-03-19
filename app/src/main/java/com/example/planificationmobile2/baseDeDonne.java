package com.example.planificationmobile2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class baseDeDonne {
    // class de base de donne oracle utilisé JBDC
    private static baseDeDonne connection=null;
    private final static String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static Connection conn;

    private static Statement Session; // for file session conection sql oracle

    // constructeur
    public baseDeDonne(){
    }

    /// cette methode permet de recuperer l'objet courant dans n'import quelle activite
    public  static baseDeDonne getInstance(){
        if(connection==null)
            connection =new baseDeDonne();
        return  connection;
    }
    // methode de connexion
    public void connexion(String user, String password) throws ClassNotFoundException, SQLException {
        // code de connexion

            //Class.forName("oracle.jdbc.driver.OracleDriver");
             this.conn = DriverManager.getConnection(url,user,password);
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
        rs.close();


    }

    // methode de deconnexion
    public void deconnexion() throws SQLException {
        // code de deconnexion
        this.conn.close();

        Session=null;

    }
}
