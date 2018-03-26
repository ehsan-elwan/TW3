/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.tw3;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Ehsan
 */
public class DataSource {

    FileInputStream fis;
    MysqlDataSource mysqlDS;

    public MysqlDataSource getMySQLDataSource() {
        Properties props = new Properties();
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("db.properties");

        try {
            props.load(is);
            mysqlDS = new MysqlDataSource();
           // mysqlDS.setServerName(props.getProperty("MYSQL_DB_SRNAME"));
           // mysqlDS.setPortNumber(Integer.valueOf(props.getProperty("MYSQL_DB_SRPORT")));
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
            System.out.println();
           
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return mysqlDS;

    }
   

}
