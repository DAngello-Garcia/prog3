package co.edu.uniquindio.proyectofinal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String id;
    private String imagen;
    private CategoriaProducto categoria;
    private EstadoProducto estado;
    private double precio;
    private LocalDateTime fechaPublicacion;
    private ArrayList<Comentario> listaComentarios;
    private ArrayList<Like> listaLikes;
    private Vendedor vendedor;
    private int contadorLikes;
    private String idVendedor;

    {
        listaComentarios = new ArrayList<>();
        listaLikes = new ArrayList<>();
    }
    public Producto() {

    }

    public Producto(String nombre, EstadoProducto estado, double precio, Vendedor vendedor) {
        this.nombre = nombre;
        this.estado = estado;
        this.precio = precio;
        this.vendedor = vendedor;
        this.categoria = CategoriaProducto.MARKET;
        this.id = UUID.randomUUID().toString();
        this.fechaPublicacion = LocalDateTime.now();
        this.idVendedor = vendedor.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public ArrayList<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public ArrayList<Like> getListaLikes() {
        return listaLikes;
    }

    public void setListaLikes(ArrayList<Like> listaLikes) {
        this.listaLikes = listaLikes;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public int getContadorLikes() {
        return contadorLikes;
    }

    public void incrementarLikes() {
        this.contadorLikes += 1;
    }
    public void decrementarLikes() {
        this.contadorLikes -= 1;
    }

    public void setContadorLikes(int contadorLikes) {
        this.contadorLikes = contadorLikes;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
}