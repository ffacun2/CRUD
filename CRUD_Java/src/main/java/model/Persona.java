package model;

import java.util.Date;

public class Persona {

    private String nombre;
    private String apellido;
    private int dni;
    private Date fecha_nacimiento;

    public Persona(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }


    public int getDni() {
        return dni;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }
}
