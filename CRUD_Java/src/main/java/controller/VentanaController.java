package controller;

import dao.PersonaDAO;
import model.Persona;
import view.Ventana;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class VentanaController implements ActionListener, MouseListener {

    private Ventana ventana;
    private PersonaDAO personaDAO;

    public VentanaController(){
        this.ventana = new Ventana();
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setController(this,this);
        try {
            this.personaDAO = new PersonaDAO();
            this.ventana.setTableTitle(personaDAO.getColumnName());
            this.ventana.updateTable(personaDAO.read());
            this.ventana.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al conectar base de datos.");
            this.ventana.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msj = e.getActionCommand();

        if(msj.equals("CREATE")){
            personaDAO.createUser(
                            ventana.getNameTextField(),
                            ventana.getLastnameTextField(),
                            ventana.getDniTextField()
                            );
            this.ventana.updateTable(personaDAO.read());
            this.ventana.clearField();
        }else if(msj.equals("READ")){

        }else if(msj.equals("UPDATE")){
            Integer dni = this.ventana.getSeleccionado();
            if(dni != null){
                Persona persona = personaDAO.search(dni);
                if(persona != null)
                    personaDAO.updateUser(persona.getNombre(),
                                        persona.getApellido(),
                                        String.valueOf(persona.getDni())
                    );
            }
            this.ventana.updateTable(personaDAO.read());
            this.ventana.clearField();
        }else if(msj.equals("DELETE")){
            Integer dni = this.ventana.getSeleccionado();
            if(dni != null) {
                personaDAO.deleteUser(dni);
                this.ventana.updateTable(personaDAO.read());
                this.ventana.clearField();
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Integer dni = this.ventana.getSeleccionado();
        if(dni != null){
            Persona persona = personaDAO.search(dni);
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
}
