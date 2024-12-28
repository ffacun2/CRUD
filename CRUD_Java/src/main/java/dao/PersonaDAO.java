package dao;


import db.DataBaseConnection;
import model.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que interactua directamente con la base de datos obteniendo los registros Personas
 */
public class PersonaDAO {

    private Connection connection;

    public PersonaDAO() throws SQLException {
        this.connection = DataBaseConnection.getConnection();
    }

    /**
     *  Metodo que crea un nuevo registro para la base de datos. Los parametros son validos.
     * @param name : Nombre de la persona.
     * @param lastname :Apellido de la persona.
     * @param dni : DNI de la persona.
     * @return Retorna true/false de acuerdo si se pudo crear correctamente o no.
     */
    public boolean createUser(String name, String lastname, String dni){
        String query = "INSERT INTO personas (nombre,apellido,dni) VALUES (?,?,?);";

        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, lastname);
            ps.setString(3, dni);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0)
                return true;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Metodo que lee todos los registros Persona de la base de datos.
     * @return retorna todos los registros de la base de datos en una Lista con elementos de tipo Persona.
     */
    public List<Persona> read(){
        String query = "SELECT * FROM personas;";
        List<Persona> personas = new ArrayList<Persona>();

        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
               personas.add(new Persona(rs.getString("nombre"),
                                        rs.getString("apellido"),
                                        rs.getInt("dni")
                                        ));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personas;
    }

    /**
     * Metodo para actualizar un regsitro Persona de la base de datos. Los datos estan validados.
     * @param name :Nombre de la persona.
     * @param lastname :Apellido de la persona.
     * @param DNI :DNI de la persona.
     * @return Retorna true/false de acuerdo a si se actualizo correctamente en la base de datos.
     */
    public boolean updateUser(String name, String lastname, String DNI){
        String query = "UPDATE personas SET nombre=?, apellido=? WHERE dni=?;";

        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, lastname);
            ps.setString(3, DNI);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0)
                return true;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Metodo para eliminar un registro Persona de la base de datos de acuerdo al DNI ingresado.
     * @param DNI :DNI de la persona a eliminar.
     * @return Retorna true/false de acuerdo si se pudo eliminar correctamente de la base de datos.
     */
    public boolean deleteUser(int DNI){
        String query = "DELETE FROM personas WHERE dni=?;";

        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, DNI);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0)
                return true;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Metodo que busca un registro con el DNI ingresado. Este retorna un Objeto de tipo Persona con el dni correspondiente
     * o NULL en caso de que no se encuentre.
     * @param dni :DNI de la persona a buscar.
     * @return Retorna el Objeto Persona si encontro registro con el dni correspondiente o null caso contrario.
     */
    public Persona search(int dni){
        String query = "SELECT * FROM personas WHERE dni=?;";
        Persona persona = null;

        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                persona = new Persona(rs.getString("nombre"),
                                        rs.getString("apellido"),
                                        rs.getInt("dni")
                                        );
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persona;
    }

    /**
     * Obtiene los nombres de las columnas de la tabla de la base de datos para la tabla de la ventana.
     * @return Retorna una lista con los nombres de las columnas.
     */
    public List<String> getColumnName(){
        String query = "SELECT 1 FROM personas LIMIT 1;";
        List<String> lista = new ArrayList<String>();

        try{

            DatabaseMetaData meta = connection.getMetaData();
            ResultSet rs = meta.getColumns(null,null,"personas",null);

            while(rs.next()){
                lista.add(rs.getString("COLUMN_NAME"));
            }

        }catch(SQLException e){

        }
        return lista;
    }
}
