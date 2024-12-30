package dao;

import exceptions.DataBaseException;
import model.Persona;

import java.util.List;

public interface IPersonaDAO {

    boolean createUser(String name, String lastname, String dni)
    throws DataBaseException;

    List<Persona> read()
    throws DataBaseException;

    Persona search(Integer dni)
    throws DataBaseException;

    boolean updateUser(String nombre, String apellido, String dni)
    throws DataBaseException;

    boolean deleteUser(Integer dni)
    throws DataBaseException;

}
