/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class DBConnect {

    Connection connection = null;

    public DBConnect(String url, String user, String pass) {
        try {
            //call drive
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connect
            //call drive            //connect
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=OnlineQuiz1", "sa", "123456");
    }

    public ResultSet getResultSet(String sql) {
        ResultSet rs = null;
        Statement state;
        try {
            state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static void main(String[] args) {
        new DBConnect();
    }
    public static Properties getSiteMaps(String siteMapFile, ServletContext context)
            throws IOException {
        if (siteMapFile == null) {
            return null;
        }

        if (siteMapFile.trim().isEmpty()) {
            return null;
        }

        Properties result = new Properties();

        InputStream is = null;

        try {
            is = context.getResourceAsStream(siteMapFile);
            result.load(is);
            return result;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
