package co.edu.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private Usuario usuario;

    public Persona(){

    }
    public Persona(String nombre, String apellido, String cedula, String direccion, Usuario usuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
