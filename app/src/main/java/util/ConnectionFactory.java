
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Natanael
 */
public class ConnectionFactory {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/todoApp?useTimezone=true&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASS = "2018100857";
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PASS);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão com o banco de dados", e);
        }
    }
    
    public static void closeConnection(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (Exception e) {
             throw new RuntimeException("Erro ao fechar conexão", e);
        }
    }
}
