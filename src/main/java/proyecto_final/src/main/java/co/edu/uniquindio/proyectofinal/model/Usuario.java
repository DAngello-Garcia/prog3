package co.edu.uniquindio.proyectofinal.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String user;
    private String password;
    private Rol rol;
    private int index;

    public Usuario(){

    }

    public Usuario(int index) {
        this.index = index;
        this.setUser("");
        this.setPassword("");
    }
    public Usuario(String user, String password, Rol rol, int index) {
        this.user = user;
        this.password = password;
        this.rol = rol;
        this.index = index;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
