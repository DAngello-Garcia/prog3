package co.edu.uniquindio.proyectofinal.model;

public class Administrador extends Persona{
    private static final long serialVersionUID = 1L;
    public Administrador(String nombre) {
        this.setNombre(nombre);
        this.setUsuario(new Usuario("admin@marketplace", "admin", Rol.ADMIN, -2));
    }
    public Administrador(){}

    public Administrador(String nombre, String apellido, String cedula, String direccion, Usuario usuario) {
        super(nombre, apellido, cedula, direccion, usuario);
    }
}
