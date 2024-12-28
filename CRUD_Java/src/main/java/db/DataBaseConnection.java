package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de implementacion de la conexion a la base de datos.
 * Utiliza patron Singueton para una unica instancia de la misma.
 */
public class DataBaseConnection {

    private static Connection connection;

    private DataBaseConnection(){

    }

    /**
     * Metodo para obtener la instancia de la base de datos.
     * @return Retorna una nueva instancia de la base de datos en caso de que no exista o la ya existente.
     * @throws SQLException Si la conexion a la base de datos falla, se lanza exepcion avisando.
     */
    public static Connection getConnection() throws SQLException{
        if(connection == null || connection.isClosed()){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/crud","root","");
            }
            catch(SQLException | ClassNotFoundException e){
                throw new SQLException("Fallo la conexion de la base de datos");
            }
        }
        return connection;
    }

    /**
     * Metodo que cierra la conexion a la base de datos en caso de que este abierta.
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException{
        if(connection!= null && !connection.isClosed()){
            connection.close();
        }
    }
}
