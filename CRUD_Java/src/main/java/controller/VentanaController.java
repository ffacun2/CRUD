package controller;

import dao.PersonaDAO;
import exceptions.DataBaseException;
import exceptions.ParametroInvalidoException;
import model.Persona;
import view.Ventana;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class VentanaController implements ActionListener, MouseListener {

    private Ventana ventana;
    private PersonaDAO personaDAO;

    public VentanaController(PersonaDAO personaDAO, Ventana ventana){
        this.ventana = ventana;
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setController(this,this);
        this.personaDAO = personaDAO;
        this.ventana.setTableTitle(personaDAO.getColumnName());
        this.ventana.updateTable(listarPersonas());
        this.ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msj = e.getActionCommand();

        if(msj.equals("CREATE")){
            crearPersona(ventana.getNameTextField(), ventana.getLastnameTextField(), ventana.getDniTextField());
        }else if(msj.equals("CLEAR")){
            this.ventana.clearField();
        }else if(msj.equals("UPDATE")){
            Integer dni = this.ventana.getSeleccionado();
            if(dni != null){
                Persona persona = buscarPersona(dni);
                if(persona != null)
                    actualizarPersona(ventana.getNameTextField(), ventana.getLastnameTextField(), ventana.getDniTextField());
            }
        }else if(msj.equals("DELETE")){
            Integer dni = this.ventana.getSeleccionado();
            if(dni != null) {
                eliminarPersona(dni);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Integer dni = this.ventana.getSeleccionado();
        if(dni != null){
            Persona persona = null;
            try {
                persona = personaDAO.search(dni);
            } catch (DataBaseException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            if(persona!=null){
                this.ventana.setNameTextField(persona.getNombre());
                this.ventana.setLastNameTextField(persona.getApellido());
                this.ventana.setDniTextField(persona.getDni());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void crearPersona(String nombre, String apellido, String dni){
        Persona persona;
        try {
            this.validarDatos(nombre,apellido,dni);
            persona = this.personaDAO.search(Integer.parseInt(dni));
            if(persona != null)
                throw new ParametroInvalidoException("El DNI ya está en uso.");
            this.personaDAO.createUser(nombre,apellido,dni);
            this.ventana.updateTable(personaDAO.read());
            this.ventana.clearField();
        }
        catch (DataBaseException | ParametroInvalidoException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public void actualizarPersona(String nombre, String apellido, String dni){
        Persona persona;
        try{
            this.validarDatos(nombre, apellido, dni);
            this.personaDAO.updateUser(nombre, apellido, dni);
            this.ventana.updateTable(personaDAO.read());
            this.ventana.clearField();
        }catch(DataBaseException | ParametroInvalidoException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public void eliminarPersona(int dni){
        try{
            personaDAO.deleteUser(dni);
            this.ventana.updateTable(listarPersonas());
            this.ventana.clearField();
        }catch(DataBaseException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

    public List<Persona> listarPersonas(){
        try{
            return personaDAO.read();
        } catch (DataBaseException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return null;
    }

    public Persona buscarPersona(Integer dni){
        try{
            return personaDAO.search(dni);
        } catch (DataBaseException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return null;
    }


    public void validarDatos(String nombre, String apellido, String dni)
    throws ParametroInvalidoException {
        if(nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty())
            throw new ParametroInvalidoException("Todos los campos son obligatorios.");
        if(!dni.matches("\\d+") || Integer.parseInt(dni) < 10000000 || Integer.parseInt(dni) > 99999999)
            throw new ParametroInvalidoException("El DNI no es válido.");
        if( !nombre.matches("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+(?: [A-ZÁÉÍÓÚÑa-záéíóúñ]+)*$") ||
                !apellido.matches("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+(?: [A-ZÁÉÍÓÚÑa-záéíóúñ]+)*$") ||
                nombre.length() < 3 || apellido.length() < 3)
            throw new ParametroInvalidoException("Los nombres y apellidos deben tener al menos 3 caracteres.");
    }

}
