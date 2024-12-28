package dao;


import db.DataBaseConnection;
import model.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    private Connection connection;

    public PersonaDAO() throws SQLException {
        this.connection = DataBaseConnection.getConnection();
    }

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
