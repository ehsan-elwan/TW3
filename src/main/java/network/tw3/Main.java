/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.tw3;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Ehsan
 */
public class Main {

    public static void main(String[] args) {
        MysqlDataSource ds = new DataSource().getMySQLDataSource();

        try {
            try (Connection conn = ds.getConnection()) {
                Statement stmt = conn.createStatement();
                
                ResultSet rs = stmt.executeQuery("select id_etud, nom,prenom from ancien_etudiant");
                while (rs.next()) {
                    System.out.println("etudiant id=" + rs.getInt("id_etud") +
                            "etudiant nom=" + rs.getString("nom") +",etudiant prenom=" + rs.getString("prenom"));
                }
            }
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        }
        
    }

}
